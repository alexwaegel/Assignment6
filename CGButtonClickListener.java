import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;

/**
 * The CGButtonClickListener class is an action listener associated with the Generate Campus button of the GUI. It is the largest
 * and most complex of the listeners as it ensures that the .csv input file is read and updates a number of the JPanel components 
 * as it works. 
 * 
 * @author alexw_000
 *
 */
public class CGButtonClickListener implements ActionListener {

	/**
	 * cgButton is the JButton for Campus Generation
	 * textArea is the JTextArea in the GUI where text results are displayed
	 * cgLabel is a JLabel next to the CGButton that tells the user whether or not an input file has been read yet
	 * campus is a Campus object that will hold the TreeMap of Buildings
	 * current is a JTextField that displays the building currently being displayed by the GUI
	 * acButton is a JBotton that the user can use to view consumption in annual granularity
	 * mcButton is a JBotton that the user can use to view consumption in monthly granularity
	 * dcButton is a JBotton that the user can use to view consumption in daily granularity
	 * chartPanel is the ChartPanel in the GUI that is used to display a chart of the results 
	 */
	JButton cgButton;
	JTextArea textArea;
	JLabel cglabel;	
	Campus campus;
	JTextField current;
	JButton acButton;
	JButton mcButton;
	JButton dcButton;
	ChartPanel chartPanel;
	JComboBox<String> goTo;
	
	/**
	 * This is the constructor for CGButtonClickListener
	 * 
	 * @param textArea is the JTextArea in the GUI where text results are displayed
	 * @param cgButton is the JButton for Campus Generation
	 * @param cglabel is a JLabel next to the CGButton that tells the user whether or not an input file has been read yet
	 * @param acButton is a JBotton that the user can use to view consumption in annual granularity
	 * @param mcButton is a JBotton that the user can use to view consumption in monthly granularity
	 * @param dcButton is a JBotton that the user can use to view consumption in daily granularity
	 * @param campus is a Campus object that will hold the TreeMap of Buildings
	 * @param current is a JTextField that displays the building currently being displayed by the GUI
	 * @param chartPanel is the ChartPanel in the GUI that is used to display a chart of the results
	 */
	CGButtonClickListener(JTextArea textArea, JButton cgButton, JLabel cglabel, JButton acButton, JButton mcButton, JButton dcButton, Campus campus, JTextField current, ChartPanel chartPanel, JComboBox<String> goTo) {
		this.cgButton = cgButton;
		this.textArea=textArea;
		this.cglabel=cglabel;
		this.acButton=acButton;
		this.mcButton=mcButton;
		this.dcButton=dcButton;
		this.campus=campus;
		this.current=current;
		this.chartPanel=chartPanel;
		this.goTo=goTo;
	}
	
	/**
	 * This is a actionPerformed method of this button clicker class. It contains a series of nested runnables that perform
	 * a task and update the progress status on the GUI. It uses invokeLater() so that the GUI textArea updates while waiting 
	 * for the longer processes. 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Sets button and TextArea to show that data is being processed
		cgButton.setText("    Working    ");
		textArea.setText("Reading Data to Generate Campus, this may take a minute...\n");
		
		SwingUtilities.invokeLater(new Runnable()
		{
		    @Override
		    public void run()
		    {
				// Creates the DataReader
		    	DataReader dr = new DataReader("for_Alex_interpolated_FY18.csv");
				textArea.append("Reading Meter Inputs...\n");
				
				SwingUtilities.invokeLater(new Runnable()
				{
					@Override
				    public void run() {
						try {
							// Creates the Data Aggregator using the File Reader
							DataAggregator da = new DataAggregator(dr.makeMeterReadingList(), textArea);
							textArea.append("Aggregating Data and Generating Building List...\n"); 
							
							SwingUtilities.invokeLater(new Runnable()
							{
								@Override
							    public void run() {
									// Creates the Campus using the Data Aggregator and updates text and labels on components
									campus = new Campus(da.generateBuildingMap());
									current.setText(campus.getPortfolio().firstEntry().getKey());
									textArea.append("Campus Generated!");
									cglabel.setText("Campus Generated");
									acButton.setText("Show Annual Consumption");
									mcButton.setText("Show Monthly Consumption");
									dcButton.setText("Show Daily Consumption");
									cgButton.setText("Read Input File");
									
									// Sets text area to display monthly consumption for first Building
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
									// Creates monthly consumption chart for the first building
									chartPanel.setChart(new BarChartGenerator().makeMonthChart("Monthly Consumption: "+current.getText(), campus.getPortfolio().get(current.getText()).getMonthlyConsumption()));
									for (String key : campus.getPortfolio().keySet()) {
										goTo.addItem(key);
									}
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
