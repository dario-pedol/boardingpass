package ch.pedol.libboardingpass.iata;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public final class IataAirport {
	private final static Map<String, String> AIRPORTS = new HashMap<>(3650);
	static{
		Reader in;
		try {
			URL csvUrl = IataAirport.class.getResource("/airport-codes.csv");
			String csvPath = csvUrl.getPath();
			in = new FileReader(csvPath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
			    AIRPORTS.put(record.get(0),record.get(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * Use this to find the English name of an Airport by code.
	 * 
	 * @param code	The 3-letter IATA Airport Code.
	 * @return	The English name of that airport, empty String if not found.
	 */
	public static String getByCode(String code){
		return AIRPORTS.containsKey(code) ? AIRPORTS.get(code).trim() : "";
	}
}
