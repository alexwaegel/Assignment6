import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
	
	private String energyFileName;
	
	public DataReader(String energyFileName) {
		this.energyFileName=energyFileName;
	}
	
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
	
