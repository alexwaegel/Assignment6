import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JTextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a JUnit Test for the DataAggregator class
 * 
 * @author alexw_000
 *
 */
public class DataAggregatorTest {
	
	/**
	 * The DataAggregator produces a TreeMap<String, Building> as output. For testing purposes one is generated before each test
	 * the DataAggregator is used with a set of procedurally generated MeterReadings and the output stored in map. This is then 
	 * used in the tests.
	 */
	TreeMap<String, Building> map;
	
	/**
	 * Before each test the map is populated with Building A and Building B, both of which are given hourly MeterReadings for 
	 * 3 years (12mo/yr, 30days/mo, 24hrs/day). The elec, stm and chw consumption for each reading was set to 1.0 for Building A
	 * and 2.0 for Building B. 
	 * 
	 */
	@BeforeEach
	public void setUp() {
		ArrayList<MeterReading> allMR = new ArrayList<MeterReading>();
		for (int year = 2001; year <= 2003; year++) {
			for (int month = 1; month <= 12; month++) {
				for (int day = 1; day <= 30; day++) {
					for (int hour = 0; hour <= 23; hour++) {
						double consumption = 1;
						allMR.add(new MeterReading("Building A", Integer.toString(year), Integer.toString(month), Integer.toString(day), Integer.toString(hour), Integer.toString(year), consumption, consumption, consumption));
					}
				}
			}
		}
		for (int year = 2001; year <= 2003; year++) {
			for (int month = 1; month <= 12; month++) {
				for (int day = 1; day <= 30; day++) {
					for (int hour = 0; hour <= 23; hour++) {
						double consumption = 2;
						allMR.add(new MeterReading("Building B", Integer.toString(year), Integer.toString(month), Integer.toString(day), Integer.toString(hour), Integer.toString(year), consumption, consumption, consumption));
					}
				}
			}
		}
		DataAggregator da = new DataAggregator(allMR, new JTextArea());
		map = da.generateBuildingMap();
	}
	
	/**
	 * Checks to see that the electric values in the annualConsumption TreeMap of building a and b are accurate
	 */
	@Test
	public void testAnnualElec() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getAnnualConsumption().entrySet()) {
				assertEquals(mr.getValue().getElecKbtu(), (n*24*30*12));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the steam values in the annualConsumption TreeMap of building a and B are accurate
	 */
	@Test
	public void testAnnualSteam() {	
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getAnnualConsumption().entrySet()) {
				assertEquals(mr.getValue().getSteamKbtu(), (n*24*30*12));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the chilled water values in the annualConsumption TreeMap of building a and B are accurate
	 */
	@Test
	public void testAnnualChw() {	
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getAnnualConsumption().entrySet()) {
				assertEquals(mr.getValue().getChwKbtu(), (n*24*30*12));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the electric values in the monthlyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testMonthlyElec() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getMonthlyConsumption().entrySet()) {
				assertEquals(mr.getValue().getElecKbtu(), (n*24*30));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the steam values in the monthlyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testMonthlySteam() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getMonthlyConsumption().entrySet()) {
				assertEquals(mr.getValue().getSteamKbtu(), (n*24*30));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the chilled water values in the monthlyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testMonthlyChw() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getMonthlyConsumption().entrySet()) {
				assertEquals(mr.getValue().getChwKbtu(), (n*24*30));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the electric values in the dailyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testDailyElec() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getDailyConsumption().entrySet()) {
				assertEquals(mr.getValue().getElecKbtu(), (n*24));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the steam values in the dailyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testDailySteam() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getDailyConsumption().entrySet()) {
				assertEquals(mr.getValue().getSteamKbtu(), (n*24));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the chilled water values in the dailyConsumption TreeMap of building A are accurate
	 */
	@Test
	public void testDailyChw() {
		int n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getDailyConsumption().entrySet()) {
				assertEquals(mr.getValue().getChwKbtu(), (n*24));
			}
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the number of meter reading recorded in the hourlyConsumption TreeMap
	 */
	@Test
	public void testNumberMeterReadings() {
		for (Entry<String, Building> building : map.entrySet()) {
			assertEquals(building.getValue().getHourlyConsumption().size(), 24*30*12*3);
		}
	}
	
	/**
	 * Checks to see that the number of meter reading recorded in the annualConsumption TreeMap
	 */
	@Test
	public void testNumberAnnualMeterReadings() {
		for (Entry<String, Building> building : map.entrySet()) {
			assertEquals(building.getValue().getAnnualConsumption().size(), 3);
		}
	}
	
	/**
	 * Checks to see that the number of meter reading recorded in the monthlyConsumption TreeMap
	 */
	@Test
	public void testNumberMonthlyMeterReadings() {
		for (Entry<String, Building> building : map.entrySet()) {
			assertEquals(building.getValue().getMonthlyConsumption().size(), 12*3);
		}
	}
	
	/**
	 * Checks to see that the number of meter reading recorded in the dailyConsumption TreeMap
	 */
	@Test
	public void testNumberDailyMeterReadings() {
		for (Entry<String, Building> building : map.entrySet()) {
			assertEquals(building.getValue().getDailyConsumption().size(), 30*12*3);
		}
	}

}
