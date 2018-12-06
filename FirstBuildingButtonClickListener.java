import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class FirstBuildingButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	JTextField current;
	
	FirstBuildingButtonClickListener(CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.current=current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		current.setText(cgbcl.campus.getPortfolio().firstEntry().getKey());
		current.getAction();
	}
}
