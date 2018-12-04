import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import javax.swing.JTextArea;

public class MCButtonClickListener implements ActionListener {

	CGButtonClickListener cgbcl;
	JTextArea textArea;
	boolean next;
	
	MCButtonClickListener(boolean next, JTextArea textArea, CGButtonClickListener cgbcl) {
		this.cgbcl=cgbcl;
		this.textArea=textArea;
		this.next=next;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
		textArea.append("\nPrinting Monthly Consumption...");
		Thread thread = new Thread() {
			@Override
		    public void run()
		    {
		    	for (Entry<String, Building> building : cgbcl.campus.getPortfolio().entrySet()) {
		    		textArea.append("\n\n"+building.getKey()+":\n");
		    		textArea.append(String.format("%10s %15s %15s %15s %15s", "Date", "Elec", "Steam", "CHW", "Total"));
					for (Entry<String,MeterReading> element : building.getValue().getMonthlyConsumption().entrySet()) {
						DecimalFormat formatConsumption = new DecimalFormat("###,###,###,###,###,###");
						String formatElec = formatConsumption.format(element.getValue().getElecKbtu());
						String formatStm = formatConsumption.format(element.getValue().getSteamKbtu());
						String formatChw = formatConsumption.format(element.getValue().getChwKbtu());
						String formatTot = formatConsumption.format(element.getValue().getElecKbtu()+element.getValue().getChwKbtu()+element.getValue().getSteamKbtu());
						String format = String.format("%10s %15s %15s %15s %15s", element.getKey().substring(element.getKey().length()-7), formatElec, formatStm, formatChw, formatTot);
						textArea.append("\n"+format);
					}
					textArea.append("\nHit any key to proceed to the next building...");
					while (!next) {
						try {
							Thread.sleep(250);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					next=false;

					textArea.setText("Current number of buildings: "+cgbcl.campus.getPortfolio().size());
					textArea.append("\nPrinting Monthly Consumption...");
				}
				textArea.append("\nMonthly Consumption Printed!");
		    }
		};
		thread.start();
	}
}
