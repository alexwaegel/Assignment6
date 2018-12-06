import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class GoToBuildingButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	String buildingName;
	JTextField current;
	
	GoToBuildingButtonClickListener(CGButtonClickListener cgbcl, String buildingName, JTextField current) {
		this.cgbcl=cgbcl;
		this.buildingName=buildingName;
		this.current=current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		current.setText(buildingName);
		if (current.getText().equals(null)) {current.setText("Building Not Found");}
		current.getAction();
	}
}
