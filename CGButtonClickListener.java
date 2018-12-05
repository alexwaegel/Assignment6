import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CGButtonClickListener implements ActionListener {

	JButton cgButton;
	JTextArea textArea;
	JLabel cglabel;	
	Campus campus;
	JButton acButton;
	JButton mcButton;
	JButton dcButton;
	
	CGButtonClickListener(JTextArea textArea, JButton cgButton, JLabel cglabel, JButton acButton, JButton mcButton, JButton dcButton, Campus campus) {
		this.cgButton = cgButton;
		this.textArea=textArea;
		this.cglabel=cglabel;
		this.acButton=acButton;
		this.mcButton=mcButton;
		this.dcButton=dcButton;
		this.campus=campus;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		cgButton.setText("    Working    ");
		textArea.setText("Reading Data to Generate Campus, this may take a minute...\n");
		SwingUtilities.invokeLater(new Runnable()
		{
		    @Override
		    public void run()
		    {
				DataReader dr = new DataReader("for_Alex_interpolated_FY18.csv");
				textArea.append("Reading Meter Inputs...\n");
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
				    public void run() {
						try {
							DataAggregator da = new DataAggregator(dr.makeMeterReadingList(), textArea);
							textArea.append("Aggregating Data and Generating Building List...\n"); 
							
							SwingUtilities.invokeLater(new Runnable()
							{
								@Override
							    public void run() {
									campus = new Campus(da.generateBuildingMap());
									textArea.append("Campus Generated!");
									cglabel.setText("Campus Generated");
									acButton.setText("Show Annual Consumption");
									mcButton.setText("Show Monthly Consumption");
									dcButton.setText("Show Daily Consumption");
									cgButton.setText("Read Input File");
								}
							});
						} 
						catch (FileNotFoundException e) {
							textArea.append("The specified input file was not found in this folder...\n");
							e.printStackTrace();
						}
					}
				});
		    }
		});
	}
}
