import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map.Entry;

public class DataAggregatorTester {

	public static void main(String[] args) {
		DataReader dr = new DataReader("for_Alex_interpolated_FY18.csv");
		Scanner scan = new Scanner(System.in);
		
		try {
			DataAggregator da = new DataAggregator(dr.makeMeterReadingList());
			Campus campus = new Campus(da.generateBuildingMap());
			
			for (Entry<String, Building> building : campus.getPortfolio().entrySet()) {
				
				System.out.println(building.getKey());
				
				for (Entry<String,MeterReading> element : building.getValue().getDailyConsumption().entrySet()) {
					System.out.println(element.getKey()+" "+element.getValue().getElecKbtu());
				}
			
				for (Entry<String,MeterReading> element : building.getValue().getMonthlyConsumption().entrySet()) {
					System.out.println(element.getKey()+" "+element.getValue().getElecKbtu());
				}
			
				for (Entry<String,MeterReading> element : building.getValue().getAnnualConsumption().entrySet()) {
					System.out.println(element.getKey()+" "+element.getValue().getElecKbtu());
				}
				
				scan.nextLine();
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("The specified input file was not found in this folder...");
			e.printStackTrace();
		}
		scan.close();
	}
}
