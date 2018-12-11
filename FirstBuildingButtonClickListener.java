import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * This is the ActionListener for the FirstBuilding Button. It sets the current building to the first building in the Campus
 * TreeMap
 * 
 * @author alexw_000
 *
 */
public class FirstBuildingButtonClickListener implements ActionListener {

	/**
	 * cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * current is the JTextField with a String containing the key from an entry of the campus map
	 */
	CGButtonClickListener cgbcl;
	JTextField current;
	
	/**
	 * This is the Constructor for the FirstBuildingButtonClickListener
	 * 
	 * @param cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * @param current is the JTextField with a String containing the key from an entry of the campus map
	 */
	FirstBuildingButtonClickListener(CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.current=current;
	}

	/**
	 * sets the current building to the first building in the Campus TreeMap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		current.setText(cgbcl.campus.getPortfolio().firstEntry().getKey());
		current.getAction();
	}
}
