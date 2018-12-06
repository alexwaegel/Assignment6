import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class NextBuildingButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	JTextField current;
	
	NextBuildingButtonClickListener(CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.current=current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (current.getText().equals(cgbcl.campus.getPortfolio().lastEntry().getKey())) {current.setText(cgbcl.campus.getPortfolio().firstEntry().getKey());}
		else {current.setText(cgbcl.campus.getPortfolio().higherEntry(current.getText()).getKey());}
		current.getAction();
	}
}
