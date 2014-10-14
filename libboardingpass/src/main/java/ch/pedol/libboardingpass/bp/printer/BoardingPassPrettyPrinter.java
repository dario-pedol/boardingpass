package ch.pedol.libboardingpass.bp.printer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.chrono.JulianChronology;

import ch.pedol.libboardingpass.bp.raw.RawBoardingPass;
import ch.pedol.libboardingpass.iata.IataAirline;
import ch.pedol.libboardingpass.iata.IataAirport;

public class BoardingPassPrettyPrinter {
	/**
	 * 
	 */
	private final StringBuilder sb = new StringBuilder(1000);
	/**
	 * 
	 * @param bp
	 */
	public  void print(RawBoardingPass bp){
		makeHeader();
		makeEmptyLine();
		addLine("Format", bp.getFormatCode());
		addLine("Number of Legs", bp.getNumLegs());
		addLine("Passenger Name", bp.getName());
		addLine("Electronic Ticket", bp.getElectronicTicket());
		makeEmptyLine();
		addLine("Booking Reference", bp.getBookingRef());
		addLine("Departure Airport", bp.getFromAirport(), IataAirport.getByCode(bp.getFromAirport()));
		addLine("Destination Airport", bp.getToAirport(), IataAirport.getByCode(bp.getToAirport()));
		addLine("Operating Carrier", bp.getAirline(), IataAirline.getByCode(bp.getAirline()));
		addLine("Flight Number", bp.getFlightNr());
		Chronology chrono = JulianChronology.getInstance();
		DateTime dt = new DateTime(1000+Integer.valueOf(bp.getJulDate()), 10, 14, 10, 0, 0, 0, chrono);
		addLine("Date of Flight", bp.getJulDate(), julToGregorian("4"+bp.getJulDate()));
		addLine("Compartment Code", bp.getCabinType());
		addLine("Seat Number", bp.getSeat());
		addLine("Check-In Sequence Number", bp.getSequenceNr());
		addLine("Passenger Status", bp.getStatus());
		addLine("Field Size of Following Field", String.valueOf(bp.getTotalLength()));
		makeEmptyLine();
		addLine("Beginning of Version Number", String.valueOf(bp.getVersionSeperator()));
		addLine("Version Number", String.valueOf(bp.getVersion()));

		addLine("Field Size of following message", String.valueOf(bp.getLength1()));
		addLine("Passenger Description", bp.getPassengerDescription());
		addLine("Source of Check-In", bp.getCheckinSource());
		addLine("Source of Boarding Pass", bp.getSourceOfBoardingPass());
		
		addLine("Date of Issue of BP", bp.getDateOfIssueJul(), julToGregorian(bp.getDateOfIssueJul()));
		addLine("Document Type", bp.getDocumentType());
		addLine("Airline Designator of BP issuer", bp.getIssuingAirline(), IataAirline.getByCode(bp.getIssuingAirline()));
		addLine("Baggage Tag Licence Plate", bp.getBaggageTag());
		makeEmptyLine();
		addLine("Field Size of Following message", String.valueOf(bp.getLength2()));
		addLine("Airline Numeric Code", bp.getAirlineNumeric());
		addLine("Document Form", bp.getDocumentForm());
		addLine("Selectee Indicator", bp.getSelecteeIndicator());
		addLine("Internation Document Verification", bp.getInternationalDocumentValidation());
		addLine("Marketing carrier designation", bp.getMarketingCarrier(), IataAirline.getByCode(bp.getMarketingCarrier()));
		addLine("Frequent Flyer Airline", bp.getFrequentFlyerAirline(), IataAirline.getByCode(bp.getFrequentFlyerAirline()));
		addLine("Frequent Flyer Number", bp.getFrequentFlyerNumber());
		addLine("ID/AD Indicator", bp.getIdAdIndicator());
		addLine("Free Baggage Allowance", bp.getFreeBaggageAllowance());
		makeEmptyLine();
		addLine("Airline Specific", bp.getForAirlineUse());
		
		makeEmptyLine();
		makeSeparator();
		System.out.println(sb.toString());
	}
	private void addLine(String label, String value){
		addLine(label, value, "");
	}
	/**
	 * 
	 * @param sb
	 * @param label
	 * @param value
	 */
	private void addLine(String label, String value, String translatedValue){
		sb.append(String.format("*   %-40s :   %-18s | %-45s *\n", label, value, translatedValue));
		
	}
	/**
	 * 
	 */
	private void makeHeader(){
		makeSeparator();
		makeEmptyLine();
		sb.append("*                        =====     B O A R D I N G      P A S S    ======                                           *\n");
		makeEmptyLine();
		makeSeparator();
	}
	private void makeSeparator(){
		sb.append("*********************************************************************************************************************\n");
	}
	private void makeEmptyLine(){
		sb.append("*                                                                                                                   *\n");
	}
	private String julToGregorian(String jul){
        String input = "201" + jul;
        DateFormat fmt1 = new SimpleDateFormat("yyyyDDD");
        Date date;
		try {
			date = fmt1.parse(input);
			 DateFormat fmt2 = new SimpleDateFormat("MM/dd/yyyy");
		        String output = fmt2.format(date);
		        return output;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
       
	}
}
