import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;

public class DCButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	JTextArea textArea;
	ChartPanel chartPanel;
	JTextField current;
	
	DCButtonClickListener(boolean next, JTextArea textArea, ChartPanel chartPanel, CGButtonClickListener cgbcl, JTextField current) {
		this.cgbcl=cgbcl;
		this.textArea=textArea;
		this.chartPanel=chartPanel;
		this.current=current;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
		textArea.append("Printing Daily Consumption...");
		Thread thread2 = new Thread() {
		    @Override
		    public void run() 
		    {
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
		};
		thread2.start();
	}
}
