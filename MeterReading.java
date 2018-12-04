import java.util.Calendar;
import java.util.Date;

public class MeterReading {
	
	private String buildingName;
	private String year;
	private String month;
	private String day;
	private String hour;
	private String fiscalYear;
	private double elecKbtu;
	private double steamKbtu;
	private double chwKbtu;
	
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
