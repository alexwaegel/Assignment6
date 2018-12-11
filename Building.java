import java.util.TreeMap;


/**
 * The Building class is designed to model building energy consumption. It holds several pieces of information that describe the
 * building and 4 TreeMaps that store the hourly, daily, monthly, and annual consumption of electric, steam, and chilled water.
 * The TreeMap keys are composed of the building name and time frame identifiers and the values are MeterReadings, which store
 * the consumption for each time frame.
 * 
 * @author alexw_000
 *
 */
public class Building {
	
	/**
	 * buildingName is a String of the buildings name. These string values are derived from the name associated with each meter
	 * buildingArea is an double value representing the gross area of each building in square feet. 
	 * type is a String that identifies the main purpose of the building (ie lab, office, classroom) which will be used to set 
	 * appropriate benchmarks for consumption for each building.
	 * hourlyConsumption is a TreeMap that stores every MeterReading associated with this specific building. The keys are the 
	 * 'name fiscalYear month day hour' of the reading.
	 * dailyConsumption is a TreeMap that stores the daily consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear month day' of the reading. The values are MeterReadings that aggregate all the hourly readings into days.
	 * monthlyConsumption is a TreeMap that stores monthly consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear month' of the reading. The values are MeterReadings that aggregate all the hourly readings into months.
	 * annualConsumption is a TreeMap that stores annual consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear' of the reading. The values are MeterReadings that aggregate all the hourly readings into years.
	 */
	private String buildingName;
	private double buildingArea;
	private String type;
	private TreeMap<String, MeterReading> hourlyConsumption;
	private TreeMap<String, MeterReading> dailyConsumption;
	private TreeMap<String, MeterReading> monthlyConsumption;
	private TreeMap<String, MeterReading> annualConsumption;
	
	/**
	 * This is the constructor for Building objects
	 * 
	 * @param buildingName is a String of the buildings name. These string values are derived from the name associated with each meter
	 * buildingArea is an double value representing the gross area of each building in square feet. 
	 * @param type is a String that identifies the main purpose of the building (ie lab, office, classroom) which will be used to set 
	 * appropriate benchmarks for consumption for each building.
	 * @param hourlyConsumption is a TreeMap that stores every MeterReading associated with this specific building. The keys are the 
	 * 'name fiscalYear month day hour' of the reading.
	 * @param dailyConsumption is a TreeMap that stores the daily consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear month day' of the reading. The values are MeterReadings that aggregate all the hourly readings into days.
	 * @param monthlyConsumption is a TreeMap that stores monthly consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear month' of the reading. The values are MeterReadings that aggregate all the hourly readings into months.
	 * @param annualConsumption is a TreeMap that stores annual consumption associated with this specific building. The keys are the 
	 * 'name fiscalYear' of the reading. The values are MeterReadings that aggregate all the hourly readings into years.
	 */
	Building (String buildingName, int buildingArea, String type, TreeMap<String, MeterReading> hourly, TreeMap<String, MeterReading> daily, TreeMap<String, MeterReading> monthly, TreeMap<String, MeterReading> annual) {
		this.buildingName=buildingName;
		this.buildingArea=buildingArea;
		this.type=type;
		hourlyConsumption = hourly;
		dailyConsumption = daily;
		monthlyConsumption = monthly;
		annualConsumption = annual;
	}

	/**
	 * Returns the building area
	 * 
	 * @return a double representing the gross area of the building in square feet
	 */
	public double getBuildingArea() {
		return buildingArea;
	}

	/**
	 * Sets the building area
	 * 
	 * @param buildingArea a double representing the gross area of the building in square feet
	 */
	public void setBuildingArea(double buildingArea) {
		this.buildingArea = buildingArea;
	}

	/**
	 * Gets the building type
	 * 
	 * @return a String representing the primary use of the building 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the building type
	 * 
	 * @param type a String representing the primary use of the building
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Returns the name of the building
	 * 
	 * @return a String representing the building name
	 */
	public String getBuildingName() {
		return buildingName;
	}

	/**
	 * Returns the set of hourly MeterReadings for this building
	 * 
	 * @return a TreeMap of <String, MeterReading> entries containing consumption information for that building in an hourly format
	 */
	public TreeMap<String, MeterReading> getHourlyConsumption() {
		return hourlyConsumption;
	}

	/**
	 * Returns the set of daily MeterReadings for this building
	 * 
	 * @return a TreeMap of <String, MeterReading> entries containing consumption information for that building in a daily format
	 */
	public TreeMap<String, MeterReading> getDailyConsumption() {
		return dailyConsumption;
	}

	/**
	 * Returns the set of monthly MeterReadings for this building
	 * 
	 * @return a TreeMap of <String, MeterReading> entries containing consumption information for that building in a monthly format
	 */
	public TreeMap<String, MeterReading> getMonthlyConsumption() {
		return monthlyConsumption;
	}

	/**
	 * Returns the set of annual MeterReadings for this building
	 * 
	 * @return a TreeMap of <String, MeterReading> entries containing consumption information for that building in an annual format
	 */
	public TreeMap<String, MeterReading> getAnnualConsumption() {
		return annualConsumption;
	}

}
