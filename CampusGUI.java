import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CampusGUI {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(900,600);
		frame.setTitle("The Campus Analyzer 5000!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Campus campus = new Campus(new TreeMap<>());
		
		// Panel for GUI
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(0,3));
				
		// Campus Generation Button
		JLabel cgLabel1 = new JLabel("Input File Not Read");
		JButton cgButton = new JButton("Read Input File");
		
		// Annual Consumption Button
		JLabel acLabel1 = new JLabel("Annual results not ready");
		JButton acButton = new JButton("Print Annual Results");
		
		// Monthly Consumption Button
		JLabel mcLabel1 = new JLabel("Monthly results not ready");
		JButton mcButton = new JButton("Print Monthly Results");
		
		// Daily Consumption Button
		JLabel dcLabel1 = new JLabel("Daily results not ready");
		JButton dcButton = new JButton("Print Daily Results");
		
		JButton nextBuilding = new JButton("Next Building");
		boolean next = false;
		boolean endMonth = false;
		boolean endDay = false;
		
		JTextArea textArea = new JTextArea(25, 120);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		
		// Campus Generation Button Listener
		CGButtonClickListener cgbcl = new CGButtonClickListener(textArea, cgButton, cgLabel1, acLabel1, mcLabel1, dcLabel1, campus);
		cgButton.addActionListener(cgbcl);
		
		// Annual Consumption Button Listener
		ACButtonClickListener acbcl = new ACButtonClickListener(textArea, cgbcl);
		acButton.addActionListener(acbcl);
		
		// Monthly Consumption Button Listener
		MCButtonClickListener mcbcl = new MCButtonClickListener(next, textArea, cgbcl);
		mcButton.addActionListener(mcbcl);
		
		// Daily Consumption Button Listener
		DCButtonClickListener dcbcl = new DCButtonClickListener(next, textArea, cgbcl);
		dcButton.addActionListener(dcbcl);
		
		// Next Building Button Listener
		NextBuildingButtonClickListener nbbcl = new NextBuildingButtonClickListener(mcbcl, dcbcl);
		nextBuilding.addActionListener(nbbcl);
	
		buttons.add(new JLabel());
		buttons.add(cgButton); 
		buttons.add(cgLabel1);
		
		buttons.add(new JLabel());
		buttons.add(acButton); 
		buttons.add(acLabel1);
		
		buttons.add(new JLabel());
		buttons.add(mcButton); 
		buttons.add(mcLabel1);
		
		buttons.add(new JLabel());
		buttons.add(dcButton); 
		buttons.add(dcLabel1);
		
		buttons.add(new JLabel());
		buttons.add(nextBuilding);
		
		panel.add(buttons, BorderLayout.PAGE_START);
		panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		
		frame.add(panel);
		frame.setVisible(true);	
	}
}
