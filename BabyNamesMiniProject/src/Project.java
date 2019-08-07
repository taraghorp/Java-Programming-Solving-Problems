/**
 * @author Tara G.
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class Project  {

	public static void main(String[] args) {
		Project project = new Project();
		
		FileResource fr = new FileResource();
		project.totalBirths(fr);
		
		//long rank = project.getRank(1971, "Frank", "M");
		//System.out.println(rank);
		
		//String name = project.getName(1982, 450, "M");
		//System.out.println(name);
		
		//project.whatIsNameInYear("Susan", 1972, 2014, "F");
		//project.whatIsNameInYear("Owen", 1974, 2014, "M");
		
		//int year = project.yearOfHighestRank("Mich", "M");
		//System.out.println(year);
		
		//double avgRank = project.getAverageRank("Robert", "M");
		//System.out.println(avgRank);
		
		//int births = project.getTotalBirthsRankedHigher(1990, "Drew", "M");
		//System.out.println(births);
		
	}
	
	public long beforeFirstMaleRecord(CSVParser parser) {
		long maleStart = 0;
		for(CSVRecord record : parser) {
			String currGender = record.get(1);
			if (currGender.equals("M")) {
				maleStart = record.getRecordNumber();
				break;
			}
		}
		return maleStart - 1;
	}
	
	public void totalBirths(FileResource fr) {
		long totalBirths = 0;
		long totalGirls = 0;
		long totalBoys = 0;
		CSVParser parser = fr.getCSVParser(false);

		for(CSVRecord record : parser) {
			long numBorn = Long.parseLong(record.get(2));
			String gender = record.get(1);
			totalBirths += numBorn;
			if(gender.equals("M")) {
				totalBoys += numBorn;
			} else if (gender.equals("F")) {
				totalGirls += numBorn;
			} else {
				System.out.println("Unknown gender");
			}
		}

		System.out.println("Total: " + totalBirths);
		System.out.println("Boys: " + totalBoys);
		System.out.println("Girls: " + totalGirls);
	}
	
	public long getRank(int year, String name, String gender) {
		long rank = -1;
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		long beforeFirstMaleRecord = beforeFirstMaleRecord(parser);
		parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			String currName = record.get(0);
			String currGender = record.get(1);
			
			if(currGender.equals(gender) && currName.equals(name)) {
				rank = record.getRecordNumber();
				if (gender.equals("M")) {
					rank = rank - beforeFirstMaleRecord;
				}
				break;
			}
		}
		return rank;
	}
	
	public String getName(int year, long rank, String gender) {
		String name = "";
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		long beforeFirstMaleRecord = beforeFirstMaleRecord(parser);
		parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			long currRank = record.getRecordNumber();
			if (gender.equals("M")) {
				currRank = currRank - beforeFirstMaleRecord;
			}
			String currGender = record.get(1);
			String currName = record.get(0);

			if(currRank == rank && currGender.equals(gender)) {
				name = currName;
			}
		}
		if(name != "") {
			return name;
		} 
		else {
			return "NO NAME";
		}

		
	}
	
	public void whatIsNameInYear(String name, int year, int newYear, String gender) {
		
		FileResource fr = new FileResource();
		CSVParser parserOld = fr.getCSVParser(false);
		long beforeFirstMaleRecordOld = beforeFirstMaleRecord(parserOld);
		parserOld = fr.getCSVParser(false);
		
		String newName = "";
		long popularity = 0;

		for(CSVRecord record : parserOld) {
			String currName = record.get(0);
			String currGender = record.get(1);

			if(currName.equals(name) && currGender.equals(gender)) {
				popularity = record.getRecordNumber();
				if (gender.equals("M")) {
					popularity = popularity - beforeFirstMaleRecordOld;					
				}
				System.out.println("old rank:" + popularity);
			}
		}

		newName = getName(newYear, popularity, gender);
		
		System.out.println(name + " born in " + year + " would be " + newName + " if " + (gender.equals("M")? "he":"she") +" was born in " + newYear);
	}
	
	public int yearOfHighestRank(String name, String gender) {
		long highestRank = 0;
		int yearOfHighestRank = -1;
		String fileName = "";
		DirectoryResource dr = new DirectoryResource();
		
		// Iterate through all files
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			long beforeFirstMaleRecord = beforeFirstMaleRecord(parser);
			parser = fr.getCSVParser(false);
			// Iterate through all records in file
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);

				if(currName.equals(name) && currGender.equals(gender)) {
					long currRank = record.getRecordNumber();
					if (gender.equals("M")) {
						currRank = currRank - beforeFirstMaleRecord;
					}
					if(highestRank == 0) {
						highestRank = currRank;
						fileName = f.getName();
					} 
					else {
						if(highestRank > currRank) {
							highestRank = currRank;
							fileName = f.getName();
						}
					}
				}
			}
		}

		// Remove all non-numeric characters from the filename
		fileName = fileName.replaceAll("[^\\d]", "");
		
		// Convert String fileName to Integer
		yearOfHighestRank = Integer.parseInt(fileName);

		return yearOfHighestRank;
	}
	
	public double getAverageRank(String name, String gender) {
		// Initialize a DirectoryResource
		DirectoryResource dr = new DirectoryResource();
		// Define rankTotal, howMany
		double rankTotal = 0.0;
		int howMany = 0;
		// For every file the directory add name rank to agvRank
		for(File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVParser parser = fr.getCSVParser(false);
			long beforeFirstMaleRecord = beforeFirstMaleRecord(parser);
			parser = fr.getCSVParser(false);
			for(CSVRecord record : parser) {
				String currName = record.get(0);
				String currGender = record.get(1);
				if(currName.equals(name) && currGender.equals(gender)){
					long currRank = record.getRecordNumber();
					if (gender.equals("M")) {
						currRank = currRank - beforeFirstMaleRecord;
					}
					rankTotal += (double)currRank;
					howMany += 1;
				}
			}
		}
		// Define avgRank = rankTotal / howMany
		double avgRank = rankTotal / (double)howMany;
		return avgRank;
	}
	
	public int getTotalBirthsRankedHigher(int year, String name, String gender) {
		int numBorn = 0;
		long rank = getRank(year, name, gender);
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser(false);
		long beforeFirstMaleRecord = beforeFirstMaleRecord(parser);
		parser = fr.getCSVParser(false);
		for(CSVRecord record : parser) {
			int currBorn = Integer.parseInt(record.get(2));
			String currGender = record.get(1);
			long currRank = record.getRecordNumber();
			if (gender.equals("M")) {
				currRank = currRank - beforeFirstMaleRecord;
			}
			if(gender.equals(currGender) && rank > currRank) {
				numBorn += currBorn;
			}
		}
		return numBorn;
	}

}
