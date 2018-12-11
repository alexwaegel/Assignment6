import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * The NextBuildingButtonClickListener is an ActionListener that changes the current building to the next entry in the 
 * Campus map
 * 
 * @author alexw_000
 *
 */
public class NextBuildingButtonClickListener implements ActionListener {

	/**
	 * cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * current is the JTextField with a String containing the key from an entry of the campus map
	 */
	CGButtonClickListener cgbcl;
	JTextField current;
	
	/**
	 * This is the Constructor for the NextBuildingButtonClickListener
	 * 
	 * @param cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * @param current is the JTextField with a String containing the key from an entry of the campus map
	 */
	NextBuildingButtonClickListener(CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.current=current;
	}

	/**
	 * This sets current to the next key in the Campus TreeMap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (current.getText().equals(cgbcl.campus.getPortfolio().lastEntry().getKey())) {current.setText(cgbcl.campus.getPortfolio().firstEntry().getKey());}
		else {current.setText(cgbcl.campus.getPortfolio().higherEntry(current.getText()).getKey());}
		current.getAction();
	}
}
