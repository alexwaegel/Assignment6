import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * The PrevBuildingButtonClickListener is an ActionListener that changes the current building to the previous entry in the 
 * Campus map
 * 
 * @author alexw_000
 *
 */
public class PrevBuildingButtonClickListener implements ActionListener {

	/**
	 * cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * current is the JTextField with a String containing the key from an entry of the campus map
	 */
	CGButtonClickListener cgbcl;
	JTextField current;
	
	/**
	 * This is the Constructor for the PrevBuildingButtonClickListener
	 * 
	 * @param cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * @param current is the JTextField with a String containing the key from an entry of the campus map
	 */
	PrevBuildingButtonClickListener(CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.current=current;
	}

	/**
	 * This sets current to the previous key in the Campus TreeMap
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (current.getText().equals(cgbcl.campus.getPortfolio().firstEntry().getKey())) {current.setText(cgbcl.campus.getPortfolio().firstEntry().getKey());}
		else {current.setText(cgbcl.campus.getPortfolio().lowerEntry(current.getText()).getKey());}
		current.getAction();
	}
}
