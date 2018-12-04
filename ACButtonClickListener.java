import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ACButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	JTextArea textArea;
	
	ACButtonClickListener(JTextArea textArea, CGButtonClickListener cgbcl) {
		this.cgbcl=cgbcl;
		this.textArea=textArea;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
		textArea.append("\nPrinting Annual Consumption");
		
		SwingUtilities.invokeLater(new Runnable()
		{
		    @Override
		    public void run()
		    {
		    	textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
				textArea.append("\nPrinting Monthly Consumption...\n\n");
				textArea.append(String.format("%45s %15s %15s %15s %15s", "Date", "Elec", "Steam", "CHW", "Total"));
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
		    }
		});
	}
}
