import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JTextArea;

public class DataAggregator {
	
	private ArrayList<MeterReading> allMR;
	JTextArea textArea;
	
	DataAggregator (ArrayList<MeterReading> allMR, JTextArea textArea) {
		this.allMR = allMR;
		this.textArea=textArea;
	}
	
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
		
		for (MeterReading mr : allMR) {
			String mrBuilding = mr.getBuildingName();
			String mrYear = mr.getYear();
			String mrFiscalYear = mr.getFiscalYear();
			String mrMonth = mr.getMonth();
			String mrDay = mr.getDay();
			
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
				start=false;
			}
			
			else if (mrBuilding.equals(name)) {
				
				hourlyConsumption.put(name+" "+year+"/"+month+"/"+day+" "+mr.getHour(), mr);
				
				if (mrDay.equals(day)) {
					
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
				
				else {
					MeterReading daily = new MeterReading(name, year, month, day, "--", fiscalYear, elecDay, stmDay, chwDay);
					textArea.append("\nAdding: "+daily.getBuildingName()+" "+year+" "+month+" "+day);
					String key = name+" "+year+" "+month+" "+day;
					dailyConsumption.put(key, daily);
					elecDay = mr.getElecKbtu();
					stmDay = mr.getSteamKbtu();
					chwDay = mr.getChwKbtu();
					day=mrDay;
					
					if (mrMonth.equals(month)) {
						elecMonth = elecMonth + mr.getElecKbtu();
						stmMonth = stmMonth + mr.getSteamKbtu();
						chwMonth = chwMonth + mr.getChwKbtu();
						elecYear = elecYear + mr.getElecKbtu();
						stmYear = stmYear + mr.getSteamKbtu();
						chwYear = chwYear + mr.getChwKbtu();
					}
					
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
											
						if (mrFiscalYear.equals(fiscalYear)) {
							elecYear = elecYear + mr.getElecKbtu();
							stmYear = stmYear + mr.getSteamKbtu();
							chwYear = chwYear + mr.getChwKbtu();
						}
						
						else {
							MeterReading annual = new MeterReading(name, year, "--", "--", "--", fiscalYear, elecYear, stmYear, chwYear);
							textArea.append("\nAdding: "+annual.getBuildingName()+" "+fiscalYear+"\n");
							key = name+" "+fiscalYear;
							annualConsumption.put(key, annual);
							elecYear = mr.getElecKbtu();
							stmYear = mr.getSteamKbtu();
							chwYear = mr.getChwKbtu();
							fiscalYear=mrFiscalYear;
						}
					}
				}	
			}
			
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
			}
		}
		
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
