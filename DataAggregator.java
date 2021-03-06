import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JTextArea;

/**
 * The DataAggregator class is designed to accept an array list of MeterReadings and to assign each MeterReading to Building 
 * objects named in the MeterReading. In addition to dividing up the hourly MeterReadings amongst the Buildings, it also aggregates
 * them into daily, monthly, and annual consumption, which are then also stored by the appropriate Building objects.
 * 
 * @author alexw_000
 *
 */
public class DataAggregator {
	
	/**
	 * allMR is an ArrayList of MeterReadings containing all the meter readings for the campus. The ArrayList is generated by
	 * the DataReader class.
	 * textArea is a JTextArea which is used to display text results generated by the DataAggregator
	 */
	private ArrayList<MeterReading> allMR;
	JTextArea textArea;
	
	/**
	 * This is the constructor for the DataAggregator class
	 * @param allMR is an ArrayList of MeterReadings containing all the meter readings for the campus. The ArrayList is generated by
	 * the DataReader class.
	 * @param textArea is a JTextArea which is used to display text results generated by the DataAggregator
	 */
	DataAggregator (ArrayList<MeterReading> allMR, JTextArea textArea) {
		this.allMR = allMR;
		this.textArea=textArea;
	}
	
	/**
	 * The generateBuildingMap() method is the primary method of the DataAggregator class. uses the ArrayList<MeterReading> to
	 * find the hourly, daily, monthly, and annual consumption of all the Buildings on the Campus. It returns a TreeMap
	 * <String, Building> that can be used to access this information once created.
	 * 
	 * @return It returns a TreeMap <String, Building> that can be used to access this information once created.
	 */
	public TreeMap<String, Building> generateBuildingMap() {
		TreeMap<String, Building> buildingMap = new TreeMap<>();
		String name = "";
		String fiscalYear = "";
		String year = "";
		String month = "";
		String day = "";
		double elecDay = 0;
		double stmDay = 0;
		double chwDay = 0;
		double elecMonth = 0;
		double stmMonth = 0;
		double chwMonth = 0;
		double elecYear = 0;
		double stmYear = 0;
		double chwYear = 0;
		TreeMap<String, MeterReading> hourlyConsumption = new TreeMap<>();
		TreeMap<String, MeterReading> dailyConsumption = new TreeMap<>();
		TreeMap<String, MeterReading> monthlyConsumption = new TreeMap<>();
		TreeMap<String, MeterReading> annualConsumption = new TreeMap<>();
		boolean start = true;
		
		// Loop through every MeterReading
		for (MeterReading mr : allMR) {
			
			// Grab identifying information for each MeterReading
			String mrBuilding = mr.getBuildingName();
			String mrYear = mr.getYear();
			String mrFiscalYear = mr.getFiscalYear();
			String mrMonth = mr.getMonth();
			String mrDay = mr.getDay();
			
			// If its the first MeterReading, set all the labels and initialize the daily, monthly, and annual consumption sums,
			// also adds the MeterReading to the hourlyConsumption TreeMap for the Building
			if (start==true) {
				name = mrBuilding;
				fiscalYear = mrFiscalYear;
				year = mrYear;
				month = mrMonth;
				day = mrDay;
				elecDay = mr.getElecKbtu();
				stmDay = mr.getSteamKbtu();
				chwDay = mr.getChwKbtu();
				elecMonth = mr.getElecKbtu();
				stmMonth = mr.getSteamKbtu();
				chwMonth = mr.getChwKbtu();
				elecYear = mr.getElecKbtu();
				stmYear = mr.getSteamKbtu();
				chwYear = mr.getChwKbtu();
				hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
				start=false;
			}
			
			// If it isn't the first meter reading but if the MeterReading belongs to the same Building as the previous MR
			else if (mrBuilding.equals(name)) {
				
				// If its the same Building and the same Day as the previous MeterReading then the Daily, Monthly, and Annual
				// sums are increased by the consumption values of this MeterReading
				if (mrDay.equals(day)) {
					hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
					elecDay = elecDay + mr.getElecKbtu();
					stmDay = stmDay + mr.getSteamKbtu();
					chwDay = chwDay + mr.getChwKbtu();
					elecMonth = elecMonth + mr.getElecKbtu();
					stmMonth = stmMonth + mr.getSteamKbtu();
					chwMonth = chwMonth + mr.getChwKbtu();
					elecYear = elecYear + mr.getElecKbtu();
					stmYear = stmYear + mr.getSteamKbtu();
					chwYear = chwYear + mr.getChwKbtu();
				}
				
				// If its the same Building but the day has changed then Create a MeterReading with the daily sums to this
				// point and put it on the DailyConsumption map for this Building. Then reset the sums and the Day label
				else {
					MeterReading daily = new MeterReading(name, year, month, day, "--", fiscalYear, elecDay, stmDay, chwDay);
					textArea.append("\nAdding: "+daily.getBuildingName()+" "+year+" "+month+" "+day);
					String key = name+" "+year+" "+month+" "+day;
					dailyConsumption.put(key, daily);
					elecDay = mr.getElecKbtu();
					stmDay = mr.getSteamKbtu();
					chwDay = mr.getChwKbtu();
					day=mrDay;
					
					// If the day changes, then check to see if the month has changed too, if so then add the current 
					// consumption to the sums for monthly and annual consumption. Also put the houryreading onthe Map
					if (mrMonth.equals(month)) {
						elecMonth = elecMonth + mr.getElecKbtu();
						stmMonth = stmMonth + mr.getSteamKbtu();
						chwMonth = chwMonth + mr.getChwKbtu();
						elecYear = elecYear + mr.getElecKbtu();
						stmYear = stmYear + mr.getSteamKbtu();
						chwYear = chwYear + mr.getChwKbtu();
						hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
					}
					
					// If the month HAS changed since the previous reading then then Create a MeterReading with 
					// the monthly sums to this point and put it on the MonthlyConsumption map for this Building. Then reset the  
					// sums and the Month label but continue to add to the annual sums.
					else {
						MeterReading monthly = new MeterReading(name, year, month, "--", "--", fiscalYear, elecMonth, stmMonth, chwMonth);
						textArea.append("\nAdding: "+monthly.getBuildingName()+" "+fiscalYear+" "+month+"\n");
						key = name+" "+year+" "+month;
						monthlyConsumption.put(key, monthly);
						elecMonth = mr.getElecKbtu();
						stmMonth = mr.getSteamKbtu();
						chwMonth = mr.getChwKbtu();
						month=mrMonth;
						year=mrYear;
						
						// Do a final check to see if its the same fiscal year after the month changes. If so add consumption
						// to the annual sum and add the hour MR to the hourly map
						if (mrFiscalYear.equals(fiscalYear)) {
							elecYear = elecYear + mr.getElecKbtu();
							stmYear = stmYear + mr.getSteamKbtu();
							chwYear = chwYear + mr.getChwKbtu();
							hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
						}
						
						// If the fiscal year changes then create an MR for the year, add it to the annual map and reset the
						// annual counts. Still add the hourly consumption however.
						else {
							MeterReading annual = new MeterReading(name, year, "--", "--", "--", fiscalYear, elecYear, stmYear, chwYear);
							textArea.append("\nAdding: "+annual.getBuildingName()+" "+fiscalYear+"\n");
							key = name+" "+fiscalYear;
							annualConsumption.put(key, annual);
							elecYear = mr.getElecKbtu();
							stmYear = mr.getSteamKbtu();
							chwYear = mr.getChwKbtu();
							fiscalYear=mrFiscalYear;
							hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
						}
					}
				}	
			}
			
			// If it isn't the same building any longer, then the sums to this point should be added to the daily, monthly,
			// and annual maps prior to creating a Building object for that building. Then all the maps and values should be
			// reset and the current meter reading used to initalize them as the start of a new building
			else {
				textArea.append("\nAdding: "+name+" "+year+" "+month+" "+day);
				textArea.append("\nAdding: "+name+" "+fiscalYear+" "+month+"\n");
				textArea.append("\nAdding: "+name+" "+fiscalYear+"\n");
				textArea.append("\nAdding building: "+name+"\n");
				dailyConsumption.put(name+" "+year+" "+month+" "+day, new MeterReading(name, year, month, day, "--", fiscalYear, elecDay, stmDay, chwDay));
				monthlyConsumption.put(name+" "+year+" "+month, new MeterReading(name, year, month, "--", "--", fiscalYear, elecMonth, stmMonth, chwMonth));
				annualConsumption.put(name+" "+fiscalYear, new MeterReading(name, fiscalYear, "--", "--", "--", fiscalYear, elecYear, stmYear, chwYear));				
				Building building = new Building (name, 0, "", hourlyConsumption, dailyConsumption, monthlyConsumption, annualConsumption);
				buildingMap.put(name, building);
				name = mrBuilding;
				fiscalYear = mrFiscalYear;
				year = mrYear;
				month = mrMonth;
				day = mrDay;
				hourlyConsumption = new TreeMap<>();
				dailyConsumption = new TreeMap<>();
				monthlyConsumption = new TreeMap<>();
				annualConsumption = new TreeMap<>();
				elecDay = mr.getElecKbtu();
				stmDay = mr.getSteamKbtu();
				chwDay = mr.getChwKbtu();
				elecMonth = mr.getElecKbtu();
				stmMonth = mr.getSteamKbtu();
				chwMonth = mr.getChwKbtu();
				elecYear = mr.getElecKbtu();
				stmYear = mr.getSteamKbtu();
				chwYear = mr.getChwKbtu();
				hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
			}
		}
		
		// This performs the same action as when the building changes, but after the last meter reading causes the main for loop
		// to end. As values are saved when a change in MR dates is detected, the final building will not have its last Day,
		// Month, or Year consumptions unless added here.
		MeterReading daily = new MeterReading(name, year, month, day, "--", fiscalYear, elecDay, stmDay, chwDay);
		textArea.append("\nAdding: "+daily.getBuildingName()+" "+year+" "+month+" "+day);
		String key = name+" "+year+" "+month+" "+day;
		dailyConsumption.put(key, daily);
		
		MeterReading monthly = new MeterReading(name, year, month, "--", "--", fiscalYear, elecMonth, stmMonth, chwMonth);
		textArea.append("\nAdding: "+monthly.getBuildingName()+" "+fiscalYear+" "+month+"\n");
		key = name+" "+year+" "+month;
		monthlyConsumption.put(key, monthly);
		
		MeterReading annual = new MeterReading(name, year, "--", "--", "--", fiscalYear, elecYear, stmYear, chwYear);
		textArea.append("\nAdding: "+annual.getBuildingName()+" "+fiscalYear+"\n");
		key = name+" "+fiscalYear;
		annualConsumption.put(key, annual);
		
		textArea.append("\nAdding final building: "+name+"\n");
		Building building = new Building (name, 0, "", hourlyConsumption, dailyConsumption, monthlyConsumption, annualConsumption);
		buildingMap.put(name, building);
		
		return buildingMap;
	}

	public ArrayList<MeterReading> getAllMR() {
		return allMR;
	}

}
