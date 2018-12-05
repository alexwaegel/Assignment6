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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

public class CampusGUI {

	public static void main(String[] args) {
		
		// Initialize starting conditions
		Campus campus = new Campus(new TreeMap<>());
		boolean next = false;
		
		// Create Frame
		JFrame frame = new JFrame();
		frame.setSize(1200,600);
		frame.setTitle("The Campus Analyzer 5000!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// Panels for GUI
		// Main panel splits window into two columns
		JPanel main = new JPanel(new GridLayout(0,2));
		
		// Left Side holds left buttons and TextArea
		JPanel leftSide = new JPanel(new BorderLayout());
		JPanel leftButtons = new JPanel(new GridLayout(0,3));
		JTextArea textArea = new JTextArea(25, 120);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		
		// Right Side holds right buttons and ChartArea
		JPanel rightSide = new JPanel(new BorderLayout());
		JPanel rightButtons = new JPanel(new GridLayout(0,3));
		ChartPanel chartPanel = new ChartPanel( null );
		
		
		// Buttons
		// Campus Generation Button
		JLabel cgLabel1 = new JLabel("Input File Not Read");
		JButton cgButton = new JButton("Read Input File");
		
		// Annual Consumption Button
		JButton acButton = new JButton("Annual results not ready");
		
		// Monthly Consumption Button
		JButton mcButton = new JButton("Monthly results not ready");
		
		// Daily Consumption Button
		JButton dcButton = new JButton("Daily results not ready");
		
		// Next Building Button
		JButton nextBuilding = new JButton("Next Building");
		
		// Next Building Button
		JButton goToBuilding = new JButton("Go To Building");
		JTextField goTo = new JTextField("<Type Building Name>");
		
		
		// Button Listeners		
		// Campus Generation Button Listener
		CGButtonClickListener cgbcl = new CGButtonClickListener(textArea, cgButton, cgLabel1, acButton, mcButton, dcButton, campus);
		cgButton.addActionListener(cgbcl);
		
		// Annual Consumption Button Listener
		ACButtonClickListener acbcl = new ACButtonClickListener(textArea, cgbcl);
		acButton.addActionListener(acbcl);
		
		// Monthly Consumption Button Listener
		MCButtonClickListener mcbcl = new MCButtonClickListener(next, textArea, chartPanel, cgbcl);
		mcButton.addActionListener(mcbcl);
		
		// Daily Consumption Button Listener
		DCButtonClickListener dcbcl = new DCButtonClickListener(next, textArea, chartPanel, cgbcl);
		dcButton.addActionListener(dcbcl);
		
		// Next Building Button Listener
		NextBuildingButtonClickListener nbbcl = new NextBuildingButtonClickListener(mcbcl, dcbcl);
		nextBuilding.addActionListener(nbbcl);
		
		// Add buttons to left and right side buttons panels
		leftButtons.add(new JLabel());
		leftButtons.add(cgButton); 
		leftButtons.add(cgLabel1);
		
		rightButtons.add(new JLabel());
		rightButtons.add(acButton); 
		rightButtons.add(new JLabel());
		
		rightButtons.add(new JLabel());
		rightButtons.add(mcButton); 
		rightButtons.add(new JLabel());
		
		rightButtons.add(new JLabel());
		rightButtons.add(dcButton); 
		rightButtons.add(new JLabel());
		
		leftButtons.add(new JLabel());
		leftButtons.add(nextBuilding);
		leftButtons.add(new JLabel());
		
		leftButtons.add(new JLabel());
		leftButtons.add(goToBuilding);
		leftButtons.add(goTo);
		
		leftSide.add(leftButtons, BorderLayout.PAGE_START);
		leftSide.add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		rightSide.add(rightButtons, BorderLayout.PAGE_START);
		rightSide.add(chartPanel, BorderLayout.CENTER);
		
		main.add(leftSide);
		main.add(rightSide);
		
		frame.add(main);
		frame.setVisible(true);	
	}
}
