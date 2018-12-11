import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import javax.swing.JTextArea;

import org.jfree.chart.ChartPanel;

/**
 * The ACButtonClickListener class is a single method action listener for the GUI. This listener prints the Annual Consumption
 * information from every building in the campus in the main TextArea. 
 * 
 * @author alexw_000
 *
 */
public class ACButtonClickListener implements ActionListener {
	
	/**
	 * CGButtonClickListener cgbcl is the action listener for the Generate Campus button. It also holds much all of the information
	 * needed for the campus portfolio.
	 * The JTextArea textArea is the main display space for printed information within the GUI. It is loaded here so that it can be
	 * altered by the method.
	 */
	CGButtonClickListener cgbcl;
	JTextArea textArea;
	ChartPanel chartPanel;
	
	/**
	 * The constructor for the ACButtonCLickListener class
	 * 
	 * @param textAreais the main display space for printed information within the GUI. It is loaded here so that it can be altered 
	 * by the method.
	 * @param cgbcl the the action lister that is used to generate the Campus
	 */
	ACButtonClickListener(JTextArea textArea, CGButtonClickListener cgbcl, ChartPanel chartPanel) {
		this.cgbcl=cgbcl;
		this.textArea=textArea;
		this.chartPanel=chartPanel;
	}

	/**
	 * The action performed by this listener and its sole method is to look at every Building instance in the Campus and then to
	 * print each years annual consumption for that building, formatted so that it prints as a table
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
		textArea.append("\nPrinting Annual Consumption");
		textArea.append(String.format("\n%45s %15s %15s %15s %15s", "Date", "Elec", "Steam", "CHW", "Total"));
		for (Entry<String, Building> building : cgbcl.campus.getPortfolio().entrySet()) {
			for (Entry<String,MeterReading> element : building.getValue().getAnnualConsumption().entrySet()) {
				DecimalFormat formatConsumption = new DecimalFormat("###,###,###,###,###,###");
				String formatElec = formatConsumption.format(element.getValue().getElecKbtu());
				String formatStm = formatConsumption.format(element.getValue().getSteamKbtu());
				String formatChw = formatConsumption.format(element.getValue().getChwKbtu());
				String formatTot = formatConsumption.format(element.getValue().getElecKbtu()+element.getValue().getChwKbtu()+element.getValue().getSteamKbtu());
				String format = String.format("%45s %15s %15s %15s %15s", element.getKey(), formatElec, formatStm, formatChw, formatTot);
				textArea.append("\n"+format);
			}
		}
		textArea.append("\nAnnual Consumption Printed!");
		chartPanel.setChart(new AnnualChartGenerator().makeAnnualChart(cgbcl.campus.getPortfolio()));
	}
}
