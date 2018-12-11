import java.util.TreeMap;

/**
 * The Campus class is meant to create objects that will contain the information to analyze the energy consumption of the
 * campus as a whole in addition to its individual buildings. All of the building information is stored in a TreeMap called
 * portfolio.
 * 
 * @author alexw_000
 *
 */
public class Campus {
	
	/**
	 * portfolio is a TreeMap containing the Building information. It is keyed using the building names and the values are 
	 * Building objects
	 */
	private TreeMap<String, Building> portfolio;

	/**
	 * This is the constructor for the Campus objects
	 * 
	 * @param portfolio is a TreeMap keyed by building name and with Building instances as the values
	 */
	Campus (TreeMap<String, Building> portfolio) {
		this.portfolio=portfolio;
	}

	/**
	 * This method returns the portfolio of all the buildings on campus as a TreeMap
	 * 
	 * @return a TreeMap<String, Building> keyed by building name and with Building instances as the values
	 */
	public TreeMap<String, Building> getPortfolio() {
		return portfolio;
	}

	/**
	 * This method sets the portfolio of all the buildings on campus as a TreeMap
	 * 
	 * @param portfolio a TreeMap<String, Building> keyed by building name and with Building instances as the values
	 */
	public void setPortfolio(TreeMap<String, Building> portfolio) {
		this.portfolio = portfolio;
	}

}
