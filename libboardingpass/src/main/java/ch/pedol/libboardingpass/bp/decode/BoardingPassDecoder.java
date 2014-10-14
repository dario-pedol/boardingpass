package ch.pedol.libboardingpass.bp.decode;

import ch.pedol.libboardingpass.bp.raw.RawBoardingPass;

public class BoardingPassDecoder {
	
	private BoardingPassReader r;
	
	public BoardingPassDecoder(String encodedData){
		r = new BoardingPassReader(encodedData);
	}
	public RawBoardingPass decode(){
		RawBoardingPass data = new RawBoardingPass();
		
		// Unique and mandatory block
		data.setFormatCode(r.next(1));
		data.setNumLegs(r.next(1));
		data.setName(r.next(20));
		data.setElectronicTicket(r.next(1));
		
		// Repeatable and mandatory
		data.setBookingRef(r.next(7));
		data.setFromAirport(r.next(3));
		data.setToAirport(r.next(3));
		data.setAirline(r.next(3));
		data.setFlightNr(r.next(5));
		data.setJulDate(r.next(3));
		data.setCabinType(r.next());
		data.setSeat(r.next(4));
		data.setSequenceNr(r.next(5));
		data.setStatus(r.next());
		
		// Unique and optional block
		data.setTotalLength(r.nextHex());
		
		data.setVersionSeperator(r.next());
		data.setVersion(r.next());
		
		data.setLength1(r.nextHex());
		r.setBlockLength(data.getLength1());
		data.setPassengerDescription(r.next());
		data.setCheckinSource(r.next());
		data.setSourceOfBoardingPass(r.next());
		data.setDateOfIssueJul(r.next(4));
		data.setDocumentType(r.next());
		data.setIssuingAirline(r.next(3));
		data.setBaggageTag(r.next(13));
		
		// Repeatable and optional
		r.resetBlock();
		data.setLength2(r.nextHex());
		r.setBlockLength(data.getLength2());
		data.setAirlineNumeric(r.next(3));
		data.setDocumentForm(r.next(10));
		data.setSelecteeIndicator(r.next());
		data.setInternationalDocumentValidation(r.next());
		data.setMarketingCarrier(r.next(3));
		data.setFrequentFlyerAirline(r.next(3));
		data.setFrequentFlyerNumber(r.next(16));
		data.setIdAdIndicator(r.next());
		data.setFreeBaggageAllowance(r.next(3));
		
		r.resetBlock();
		int forAirlineUseLength = data.getTotalLength() - data.getLength1() - data.getLength2() - 6; // 6 for version indicator and number and 2 lengths
		data.setForAirlineUse(r.next(forAirlineUseLength));
		
		return data;
	}
	private void debugLengths(RawBoardingPass data, BoardingPassReader r, String forAirlineUseLength){
		System.out.println("Lenghts: "
				+ "\nTotal Length         : " + data.getTotalLength()
				+ "\nLength 1             : " + data.getLength1()
				+ "\nLength 2             : " + data.getLength2()
				+ "\nData Length          : " + r.getDataLength()
				+ "\nAirline Data Length  : " + forAirlineUseLength
				);
	}
}
