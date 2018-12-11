import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

/**
 * This is the ActionListener for the ShowDailyConsumption button on the CampusGUI. It changes the display from monthly to daily
 * data in both the JTextField and ChartPanel for the building currently being displayed. 
 * 
 * @author alexw_000
 *
 */
public class DCButtonClickListener implements ActionListener {

	/**
	 * cgbcl is the campusGenerationButtonClickListener used to created the Campus map
	 * textArea is the main text result area of the main display
	 * chartPanel is the main chart result area of the main display
	 * current holds a String with the name of the building currently being viewed in the TreeMap of Buildings
	 */
	CGButtonClickListener cgbcl;
	JTextArea textArea;
	ChartPanel chartPanel;
	JTextField current;
	
	/**
	 * This is the Constructor for the DCButtonClickListener
	 * 
	 * @param textArea is the main text result area of the main display
	 * @param chartPanel is the main chart result area of the main display
	 * @param cgbcl is the campusGenerationButtonClickListener used to created the Campus map
	 * @param current holds a String with the name of the building currently being viewed in the TreeMap of Buildings
	 */
	DCButtonClickListener(JTextArea textArea, ChartPanel chartPanel, CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.textArea=textArea;
		this.chartPanel=chartPanel;
		this.current=current;
	}

	/**
	 * When the Show Daily Consumption Button is Clicked every entry in the dailyConsumption TreeMap for the current building is 
	 * displayed in tabular format in the JTextArea and a stacked bar chart of those results is displayed in the ChartPanel
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
		textArea.append("Printing Daily Consumption...");
		textArea.append("\n\n"+current.getText()+":\n");
		textArea.append(String.format("%10s %15s %15s %15s %15s", "Date", "Elec", "Steam", "CHW", "Total"));
		for (Entry<String,MeterReading> element : cgbcl.campus.getPortfolio().get(current.getText()).getDailyConsumption().entrySet()) {
			DecimalFormat formatConsumption = new DecimalFormat("###,###,###,###,###,###");
			String formatElec = formatConsumption.format(element.getValue().getElecKbtu());
			String formatStm = formatConsumption.format(element.getValue().getSteamKbtu());
			String formatChw = formatConsumption.format(element.getValue().getChwKbtu());
			String formatTot = formatConsumption.format(element.getValue().getElecKbtu()+element.getValue().getChwKbtu()+element.getValue().getSteamKbtu());
			String format = String.format("%10s %15s %15s %15s %15s", element.getKey().substring(element.getKey().length()-10), formatElec, formatStm, formatChw, formatTot);
			textArea.append("\n"+format);
		}
		chartPanel.setChart(new BarChartGenerator().makeMonthChart("Daily Consumption: "+current.getText(), cgbcl.campus.getPortfolio().get(current.getText()).getDailyConsumption()));
		textArea.append("\nDaily Consumption Printed!");
	}
}
