
/**
 * @author Tara G.
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class CountryExports {
	public static void main(String[] args) {
		CountryExports exports = new CountryExports();
		exports.tester();
	}
	
	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		//String info = countryInfo(parser, "Nauru");
		//parser = fr.getCSVParser();
		//listExportersTwoProducts(parser,"cotton", "flowers");
		//parser = fr.getCSVParser();
		//int number = numberOfExporters(parser,"cocoa");
		//parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
	}
	
	public String countryInfo(CSVParser parser, String country) {
		String ret = "NOT FOUND";
		for (CSVRecord line : parser) {
			if (line.get("Country").equals(country)) {
				ret = country + ": " + line.get("Exports") + ": " + line.get("Value (dollars)");
			}
		}
		return ret;
	}
	
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord line : parser) {
			String exports = line.get("Exports");
			if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
				System.out.println(line.get("Country"));
			}
		}
	}
	
	public int numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0;	
		for (CSVRecord line : parser) {
			String exports = line.get("Exports");
			if (exports.contains(exportItem))
				count++;
		}
		return count;
	}
	
	public void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord line : parser) {
			String exportValue = line.get("Value (dollars)");
			if (exportValue.length() > amount.length()) {
				System.out.println(line.get("Country") + " " + line.get("Value (dollars)"));
			}
		}
	}
}
