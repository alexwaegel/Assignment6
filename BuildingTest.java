import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JTextArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a JUnit Test for the Building class
 * 
 * @author alexw_000
 *
 */
public class BuildingTest {
	
	/**
	 * The DataAggregator produces a TreeMap<String, Building> as input for this test. The buildings returned will be 
	 * tested using the Building getElecKbtuPerSqft(), getSteamKbtuPerSqft(), and getChwKbtuPerSqft() methods
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
	 * Checks to see that the electric per sqft returned is accurate
	 */
	@Test
	public void testGetElecKbtuPerSqft() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(100);
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2001"), (n*24*30*12)/100);
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2002"), (n*24*30*12)/100);
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2003"), (n*24*30*12)/100);
			n=n+1;
		}
	}	
	
	/**
	 * Checks to see that the steam per sqft returned is accurate
	 * 
	 */
	@Test
	public void testGetStmKbtuPerSqft() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(100);
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2001"), (n*24*30*12)/100);
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2002"), (n*24*30*12)/100);
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2003"), (n*24*30*12)/100);
			n=n+1;
		}
	}	
	
	/**
	 * Checks to see that the chw per sqft returned is accurate
	 * 
	 */
	@Test
	public void testGetChwKbtuPerSqft() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(100);
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2001"), (n*24*30*12)/100);
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2002"), (n*24*30*12)/100);
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2003"), (n*24*30*12)/100);
			n=n+1;
		}
	}	
	
	/**
	 * Checks to see that the elec per sqft returned is 0 if building area set to improper number
	 * 
	 */
	@Test
	public void testGetElecKbtuPerSqftWithBadArea() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(-1);
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			b.setBuildingArea(0);
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getElecKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the steam per sqft returned is 0 if building area set to improper number
	 * 
	 */
	@Test
	public void testGetStmKbtuPerSqftWithBadArea() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(-1);
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			b.setBuildingArea(0);
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getSteamKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			n=n+1;
		}
	}
	
	/**
	 * Checks to see that the chw per sqft returned is 0 if building area set to improper number
	 * 
	 */
	@Test
	public void testGetChwKbtuPerSqftWithBadArea() {
		double n = 1;
		for (Entry<String, Building> building : map.entrySet()) {
			Building b = building.getValue();
			b.setBuildingArea(-1);
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			b.setBuildingArea(0);
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2001"), (0.0));
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2002"), (0.0));
			assertEquals(b.getChwKbtuPerSqft(b.getBuildingName()+" 2003"), (0.0));
			n=n+1;
		}
	}
}