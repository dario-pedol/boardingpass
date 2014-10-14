package ch.pedol.libboardingpass.iata;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public final class IataAirline {
	private final static Map<String, String> AIRLINES = new HashMap<>(20000);
	static{
		Reader in;
		try {
			URL csvUrl = IataAirline.class.getResource("/airlines.csv");
			String csvPath = csvUrl.getPath();
			in = new FileReader(csvPath);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
			for (CSVRecord record : records) {
				if (AIRLINES.containsKey(record.get(3))){
					System.err.println("Duplicate airline: " + record.get(3) + " - " + record.get(1));
					continue;
				}
			    AIRLINES.put(record.get(3),record.get(1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
	 * Use this to find the English name of an Airline by code.
	 * 
	 * @param code	The 2-letter IATA Airline Code.
	 * @return	The English name of that airline, empty String if not found.
	 */
	public static String getByCode(String code){
		return AIRLINES.containsKey(code) && !"".equals(code) ? AIRLINES.get(code).trim() : "";
	}
}
