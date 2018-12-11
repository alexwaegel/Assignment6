import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentBuildingActionListener implements ActionListener {

	/**
	 * The CGButtonCLickListener is an actionListener class associated with the 'current' text field, which lists the current
	 * building. Any time the text field is changed this action listener displays the monthly consumption in the TextArea
	 * and creates a monthly consumption stacked bar chart, which is displayed in the ChartArea
	 */
	CGButtonClickListener cgbcl;
	
	/**
	 * This is the constructor for the CurrentBuildingActionListener
	 * 
	 * @param cgbcl is the actionListener for the Generate Campus button.
	 */
	CurrentBuildingActionListener(CGButtonClickListener cgbcl) {
		this.cgbcl=cgbcl;
	}
	
	/**
	 * Any changes cause the mcButtonClick (monthly consumption) to register a click and print/display information for the new
	 * value in current. This arrangement allows this bit of code to be used automatically any time the current building
	 * changes rather than needing to include in the action performed code of any actionListener that changes the current building.
	 * It also ensures that it is always run after the field changes and not before.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		cgbcl.mcButton.doClick();
	}
}
