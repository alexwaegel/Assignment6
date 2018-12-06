import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;

public class CGButtonClickListener implements ActionListener {

	JButton cgButton;
	JTextArea textArea;
	JLabel cglabel;	
	Campus campus;
	JTextField current;
	JButton acButton;
	JButton mcButton;
	JButton dcButton;
	ChartPanel chartPanel;
	
	CGButtonClickListener(JTextArea textArea, JButton cgButton, JLabel cglabel, JButton acButton, JButton mcButton, JButton dcButton, Campus campus, JTextField current, ChartPanel chartPanel) {
		this.cgButton = cgButton;
		this.textArea=textArea;
		this.cglabel=cglabel;
		this.acButton=acButton;
		this.mcButton=mcButton;
		this.dcButton=dcButton;
		this.campus=campus;
		this.current=current;
		this.chartPanel=chartPanel;
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
									current.setText(campus.getPortfolio().firstEntry().getKey());
									textArea.append("Campus Generated!");
									cglabel.setText("Campus Generated");
									acButton.setText("Show Annual Consumption");
									mcButton.setText("Show Monthly Consumption");
									dcButton.setText("Show Daily Consumption");
									cgButton.setText("Read Input File");
									textArea.append("\n\n"+current.getText()+":\n");
						    		textArea.append(String.format("%10s %15s %15s %15s %15s", "Date", "Elec", "Steam", "CHW", "Total"));
									for (Entry<String,MeterReading> element : campus.getPortfolio().get(current.getText()).getMonthlyConsumption().entrySet()) {
										DecimalFormat formatConsumption = new DecimalFormat("###,###,###,###,###,###");
										String formatElec = formatConsumption.format(element.getValue().getElecKbtu());
										String formatStm = formatConsumption.format(element.getValue().getSteamKbtu());
										String formatChw = formatConsumption.format(element.getValue().getChwKbtu());
										String formatTot = formatConsumption.format(element.getValue().getElecKbtu()+element.getValue().getChwKbtu()+element.getValue().getSteamKbtu());
										String format = String.format("%10s %15s %15s %15s %15s", element.getKey().substring(element.getKey().length()-7), formatElec, formatStm, formatChw, formatTot);
										textArea.append("\n"+format);
									}
									chartPanel.setChart(new BarChartGenerator().makeMonthChart("Monthly Consumption: "+current.getText(), campus.getPortfolio().get(current.getText()).getMonthlyConsumption()));
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
