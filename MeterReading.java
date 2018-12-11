/**
 * THe MeterReading class is meant to hold energy consumption information for electricity, steam, and chilled water for a campus of
 * buildings. It is designed to work with hourly aggregations of data. It has no method other than getter and setters and
 * it is merely a container for the information
 * 
 * @author alexw_000
 *
 */
public class MeterReading {
	
	/**
	 * buildingName is a String containing the name of the building the reading is from
	 * year is a String with the full year (2018)
	 * month is a string with the 2 digit value of the month (01-12)
	 * day is a string with the 2 digit value of the day (01-31)
	 * hour is a string with the 2 digit value of the hour (00-23)
	 * fiscalYear is a String with a 4 digit year based on the month and year (07/2017-06/2018 is fiscalYear 2018)
	 * elecKbtu is the electric consumption for one hour in kBtu
	 * stmKbtu is the steam consumption for one hour in kBtu
	 * chwKbtu is the chilled water consumption for one hour in kBtu
	 */
	private String buildingName;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String fiscalYear;
	private double elecKbtu;
	private double steamKbtu;
	private double chwKbtu;
	
	/**
	 * This is the Constructor for the MeterReading class
	 * @param name is a String containing the name of the building the reading is from
	 * @param year is a String with the full year (2018)
	 * @param month is a string with the 2 digit value of the month (01-12)
	 * @param day is a string with the 2 digit value of the day (01-31)
	 * @param hour is a string with the 2 digit value of the hour (00-23)
	 * @param fiscalYear is a String with a 4 digit year based on the month and year (07/2017-06/2018 is fiscalYear 2018)
	 * @param elec is the electric consumption for one hour in kBtu
	 * @param stm is the steam consumption for one hour in kBtu
	 * @param chw is the chilled water consumption for one hour in kBtu
	 */
	MeterReading (String name, String year, String month, String day, String hour, String fiscalYear, double elec, double stm, double chw) {
		buildingName=name;
		this.year=year;
		this.month=month;
		this.day=day;
		this.hour=hour;
		this.fiscalYear=fiscalYear;
		elecKbtu=elec;
		steamKbtu=stm;
		chwKbtu=chw;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getHour() {
		return hour;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public double getElecKbtu() {
		return elecKbtu;
	}

	public double getSteamKbtu() {
		return steamKbtu;
	}

	public double getChwKbtu() {
		return chwKbtu;
	}

}
