import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrentBuildingActionListener implements ActionListener {

	CGButtonClickListener cgbcl;
	
	CurrentBuildingActionListener(CGButtonClickListener cgbcl) {
		this.cgbcl=cgbcl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		cgbcl.mcButton.doClick();
	}
}
