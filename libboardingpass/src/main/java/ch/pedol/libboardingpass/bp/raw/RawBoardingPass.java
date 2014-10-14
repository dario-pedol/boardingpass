package ch.pedol.libboardingpass.bp.raw;

import lombok.Data;
/**
 * All the possible values that can be stored in a boarding pass. 
 * 
 * Essentially there are 5 sections:
 * 	<ol>
 * 		<li>Required fields, some of which can be repeated.</li>
 * 		<li>Unique optional block.</li>
 * 		<li>Repeated (when multiple legs are encoded) optional block</li>
 * 		<li>Airline specific data (optional and repeatable)</li>
 * 		<li>Security data (optional and unique)</li>
 * 	</ol>
 * 
 * @author Dario Pedol (dario@pedol.ch)
 *
 */
@Data
public class RawBoardingPass {
	// First block: required fields
	/**
	 * S = single
	 * M = multiple
	 * I have only ever seen 'M'
	 */
	private String  formatCode; // 1
	/**
	 * Number of legs that are encoded in this boarding pass.
	 */
	private String numLegs; // 1
	/**
	 * Passengers name as in 'SMITH/JOHNMR'.
	 */
	private String name; // 20
	/**
	 * Indicates whether this is a boarding pass issued against an Electronic Ticket (ETIX).
	 * E     = yes
	 * blank = no
	 */
	private String electronicTicket; // 1
	
	// following fields are repeatable.
	/**
	 * The booking reference.
	 * Interestingly, this is often left blank.
	 */
	private String bookingRef; // 7
	/**
	 * The IATA code for the departing aiport.
	 * Example: MUC
	 */
	private String fromAirport; // 3
	/**
	 * The IATA code for the arriving airport.
	 * Example: LAX
	 */
	private String toAirport; // 3
	/**
	 * The IATA airline code.
	 * Example: LH
	 */
	private String airline; // 3
	/**
	 * Flight number.
	 * 
	 * Format: NNNN[a]
	 * 
	 * Example: 1212
	 */
	private String flightNr; // 5
	private String julDate; // 3
	private String cabinType; // 1
	private String seat; // 4
	private String sequenceNr; // 4
	private String status; // 1
	private int totalLength; // 2
	private String versionSeperator; // 1, always >
	private String version; // 1
	
	// Unique optional block.
	/**
	 * This describes the length of the data up until length2 in a hex value. 
	 * If smaller than the sum of all the field sizes then the fields will be left blank.
	 */
	private int length1; // 2
	private String passengerDescription;
	private String checkinSource;
	private String sourceOfBoardingPass;
	private String dateOfIssueJul;
	private String documentType;
	private String issuingAirline;
	private String baggageTag;
	
	// Unique repeatable block.
	/**
	 * This describes the length of the data up until forAirlineUse in a hex value.
	 * If smaller than the sum of all the field sizes then the fields will be left blank.
	 */
	private int length2; // 2
	private String airlineNumeric; // 3
	private String documentForm; // 10
	private String selecteeIndicator; // 1
	private String internationalDocumentValidation; // 1
	private String marketingCarrier; // 3
	private String frequentFlyerAirline; // 3
	private String frequentFlyerNumber; // 16
	private String idAdIndicator; // 1
	private String freeBaggageAllowance; // 3
	// Airline data (repeatable)
	private String forAirlineUse; // VAR
	
	// Decurity data.
	/**
	 * Indicates the start of the security info. 
	 * Useless in my opinion as we already know where it starts from totalLength.
	 */
	private String securityBeginning; // , always ^
	private String securityType; // 1
	private String lengthOfSecurity; // 2
	private String securityData; // VAR
}
