import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This is a JUnit test for the DataReader class. test.csv is provided and it contains info for 120 meter readings. Elec is held
 * constant and chilled water goes from 1 to 120 with the index while steam is held at 0.
 * 
 * @author alexw_000
 *
 */
public class DataReaderTest {
	
	/**
	 * energyFileName is a String containing the name of the file with properly formatted test data
	 * meterReadingList holds the ArrayList which is produced
	 */
	String energyFileName = ("test.csv");
	ArrayList<MeterReading> meterReadingList;
	
	/**
	 * Uses the DataReader class to read the energyFile an ArrayList<MeterReading> 
	 */
	@BeforeEach
	public void setUp() {
		DataReader dr = new DataReader(energyFileName);
		try {
			meterReadingList = dr.makeMeterReadingList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNumberOfMeterReadings() {		
		assertEquals(meterReadingList.size(), 120);
	}
	
	@Test
	public void testElecValue() {
		double n = 1;
		for (MeterReading mr : meterReadingList) {
			assertEquals(mr.getElecKbtu(),n);
			n=n+1;
		}
	}
	
	@Test
	public void testSteamValue() {		
		double n = 1;
		for (MeterReading mr : meterReadingList) {
			assertEquals(mr.getSteamKbtu(),n);
			n=n+1;
		}
	}
	
	@Test
	public void testChwValue() {		
		double n = 1;
		for (MeterReading mr : meterReadingList) {
			assertEquals(mr.getChwKbtu(),n);
			n=n+1;
		}
	}

}
