import java.util.TreeMap;

public class Campus {
	
	private TreeMap<String, Building> portfolio;

	Campus (TreeMap<String, Building> portfolio) {
		this.portfolio=portfolio;
	}

	public TreeMap<String, Building> getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(TreeMap<String, Building> portfolio) {
		this.portfolio = portfolio;
	}

}
