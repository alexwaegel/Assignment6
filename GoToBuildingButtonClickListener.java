import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * This is the ActionListener for the GoToBuildingButton. It sets the current building to a value the user enters into a text field
 * @author alexw_000
 *
 */
public class GoToBuildingButtonClickListener implements ActionListener {

	/**
	 * cgbcl is the CampusGenerationButtonClickListener that creates the campus map
	 * current is the JTextField with a String containing the key from an entry of the campus map
	 * goTo is the textField with the new name of a building that is a key from the campus map
	 */
	CGButtonClickListener cgbcl;
	JComboBox<String> goTo;
	JTextField current;
	
	/**
	 * This is the Constructor for the GoToBuildingCLickListener
	 * 
	 * @param cgbclis the CampusGenerationButtonClickListener that creates the campus map
	 * @param goTo is the textField with the new name of a building that is a key from the campus map
	 * @param current is the JTextField with a String containing the key from an entry of the campus map
	 */
	GoToBuildingButtonClickListener(CGButtonClickListener cgbcl, JComboBox<String> goTo, JTextField current) {
		this.cgbcl=cgbcl;
		this.goTo=goTo;
		this.current=current;
	}

	/**
	 * Sets current to the value in the goTo combobox
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		current.setText(goTo.getSelectedItem().toString());
		if (current.getText().equals(null)) {current.setText("Building Not Found");}
		current.getAction();
	}
}
