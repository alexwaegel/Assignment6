import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the DataReader class. Its purpose is to read a .csv file containing specifically formatted energy meter readings from
 * multiple buildings and to produce an ArrayList of MeterReading objects that could then be used for further analysis and 
 * sorting. The data file to be read is produced by an interpolation software written previously in Python for the Center for
 * Environmental Building + Design, UPenn. The interpolator takes the raw meter data, cleans it of outliers and errors, and then
 * fills those with predicted consumption values. The interpolator is really cool and sadly NOT MINE, but I do have to use its
 * results. Previously they were loaded into a Filemaker database, but this diverts the data into MeterReading objects so that 
 * they can be analyzed them using java code instead. The .csv format is as follows:
 * 
 * "BuildingName","Date-Time","EleckBtu","SteamkBtu","ChilledWaterkBtu"
 * "BuildingName","Date-Time","EleckBtu","SteamkBtu","ChilledWaterkBtu"
 * "BuildingName","Date-Time","EleckBtu","SteamkBtu","ChilledWaterkBtu"....
 * 
 * The interpolator provides the results as a single file for all 118 metered buildings on UPenns campus. Each meter reading 
 * represents one hour of energy consumption and these hourly readings are sorted by building and then by date-time. The Data 
 * Reader will operate as intended if the file is not in this order but the resulting ArrayList would need to be sorted into 
 * this order prior to use in the DataAggregator.
 * 
 * The DataReader also assumes a FiscalYear that runs from July 1st 00:00 to June 30th 23:59 and assigns a FiscalYear value based
 * on the month and the year.
 * 
 * @author alexw_000
 *
 */
public class DataReader {
	
	/**
	 * energyFileName is a String containing the full filename of the .csv (ie "test.csv")
	 */
	private String energyFileName;
	
	/**
	 * This is the Constructor for the DataReader class. 
	 * 
	 * @param energyFileName is a String containing the full filename of the .csv (ie "test.csv")
	 */
	public DataReader(String energyFileName) {
		this.energyFileName=energyFileName;
	}
	
	/**
	 * This method parses the energy file into MeterReading objects and returns them as an ArrayList
	 * 
	 * @return an ArrayList of hourly MeterReadings for all buildings in the energyFile provided
	 * @throws FileNotFoundException if the energyFileName used in the constructor does not point to a valid .csv file in the
	 * expected location that matches that name
	 */
	public ArrayList<MeterReading> makeMeterReadingList() throws FileNotFoundException {
		ArrayList<MeterReading> meterReadingList = new ArrayList<>();
		File energyFile = new File(energyFileName);
		Scanner fileScan = new Scanner(energyFile);
		
		// Reads headers
		fileScan.nextLine();
		
		while (fileScan.hasNextLine()) {
			String s = fileScan.nextLine();
			
			s = s + "0";
			
			Scanner stringScan = new Scanner(s);
			stringScan.useDelimiter(",");	
			
			String date = stringScan.next();
			String year = date.substring(0, 4);
			String month = date.substring(5,7);
			String day = date.substring(8,10);
			String hour = date.substring(11,13);
			String fiscalYear = year;
			
			if (Integer.parseInt(month)>=7) {fiscalYear=Integer.toString((Integer.parseInt(year)+1));}
			
			String name = stringScan.next();
		
			String s1 = stringScan.next();
			double elec = 0;
			if (!s1.equals("")) {elec = Double.parseDouble(s1);}
						
			s1 = stringScan.next();
			double stm=0;
			if (!s1.equals("")) {stm = Double.parseDouble(s1);}
						
			s1 = stringScan.next();
			double chw=0;
			if (!s1.equals("")) {chw = Double.parseDouble(s1);}
						
			meterReadingList.add(new MeterReading(name, year, month, day, hour, fiscalYear, elec, stm, chw));
			stringScan.close();
		}
		
		fileScan.close();
		return meterReadingList;
	}
}
	
