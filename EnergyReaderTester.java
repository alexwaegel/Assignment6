import java.io.FileNotFoundException;

public class EnergyReaderTester {
	
	public static void main(String[] args) {
		DataReader dr = new DataReader("for_Alex_interpolated_FY18.csv");
		
		try {
			DataAggregator da = new DataAggregator(dr.makeMeterReadingList());
			for (MeterReading mr : da.getAllMR()) {
				System.out.println(mr.getBuildingName()+" "+mr.getYear()+" "+mr.getMonth()+" "+mr.getDay()+" "+mr.getHour());
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("The specified input file was not found in this folder...");
			e.printStackTrace();
		}
	}
}
