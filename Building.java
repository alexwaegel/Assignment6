import java.util.TreeMap;

public class Building {
	
	private String buildingName;
	private int buildingArea;
	private String type;
	private TreeMap<String, MeterReading> hourlyConsumption;
	private TreeMap<String, MeterReading> dailyConsumption;
	private TreeMap<String, MeterReading> monthlyConsumption;
	private TreeMap<String, MeterReading> annualConsumption;
	
	Building (String buildingName, int buildingArea, String type, TreeMap<String, MeterReading> hourly, TreeMap<String, MeterReading> daily, TreeMap<String, MeterReading> monthly, TreeMap<String, MeterReading> annual) {
		this.buildingName=buildingName;
		this.buildingArea=buildingArea;
		this.type=type;
		hourlyConsumption = hourly;
		dailyConsumption = daily;
		monthlyConsumption = monthly;
		annualConsumption = annual;
	}

	public int getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(int buildingArea) {
		this.buildingArea = buildingArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public TreeMap<String, MeterReading> getHourlyConsumption() {
		return hourlyConsumption;
	}

	public TreeMap<String, MeterReading> getDailyConsumption() {
		return dailyConsumption;
	}

	public TreeMap<String, MeterReading> getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public TreeMap<String, MeterReading> getAnnualConsumption() {
		return annualConsumption;
	}

}
