import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class NextBuildingButtonClickListener implements ActionListener {

	MCButtonClickListener mcbcl;
	DCButtonClickListener dcbcl;
	
	NextBuildingButtonClickListener(MCButtonClickListener mcbcl, DCButtonClickListener dcbcl) {
		this.mcbcl=mcbcl;
		this.dcbcl=dcbcl;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() 
		{
		    @Override
		    public void run() 
		    {mcbcl.next = true;
		    dcbcl.next = true;}
		});
	}
}
