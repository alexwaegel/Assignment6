import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map.Entry;
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
		String currentBuilding = null;
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
		JLabel cgLabel1 = new JLabel("Input File Not Read", JLabel.CENTER);
		JButton cgButton = new JButton("Read Input File");
		
		// Annual Consumption Button
		JButton acButton = new JButton("Annual results not ready");
		
		// Monthly Consumption Button
		JButton mcButton = new JButton("Monthly results not ready");
		
		// Daily Consumption Button
		JButton dcButton = new JButton("Daily results not ready");
		
		// Navigation Buttons
		JButton firstBuilding = new JButton("First Building");
		JButton nextBuilding = new JButton("Next Building");
		JButton previousBuilding = new JButton("Prev. Building");
		JButton goToBuilding = new JButton("Go To Building");
		JTextField goTo = new JTextField("<Type Building Name>");
		JLabel currentLabel = new JLabel("Current Building: ", JLabel.RIGHT);
		JTextField current = new JTextField("No Building");
		
		
		// Button Listeners		
		// Campus Generation Button Listener
		CGButtonClickListener cgbcl = new CGButtonClickListener(textArea, cgButton, cgLabel1, acButton, mcButton, dcButton, campus, current, chartPanel);
		cgButton.addActionListener(cgbcl);
		
		
		// Annual Consumption Button Listener
		ACButtonClickListener acbcl = new ACButtonClickListener(textArea, cgbcl);
		acButton.addActionListener(acbcl);
		
		// Monthly Consumption Button Listener
		MCButtonClickListener mcbcl = new MCButtonClickListener(next, textArea, chartPanel, cgbcl, current);
		mcButton.addActionListener(mcbcl);
		nextBuilding.addActionListener(mcbcl);
		previousBuilding.addActionListener(mcbcl);
		firstBuilding.addActionListener(mcbcl);
		goToBuilding.addActionListener(mcbcl);
		
		// Daily Consumption Button Listener
		DCButtonClickListener dcbcl = new DCButtonClickListener(next, textArea, chartPanel, cgbcl, current);
		dcButton.addActionListener(dcbcl);
		
		// Next Building Button Listener
		NextBuildingButtonClickListener nbbcl = new NextBuildingButtonClickListener(cgbcl, current);
		nextBuilding.addActionListener(nbbcl);
		
		// Prev Building Button Listener
		PrevBuildingButtonClickListener pbbcl = new PrevBuildingButtonClickListener(cgbcl, current);
		previousBuilding.addActionListener(pbbcl);
		
		// GoTo Building Button Listener
		GoToBuildingButtonClickListener gtbcl = new GoToBuildingButtonClickListener(cgbcl, goTo.getText(), current);
		goToBuilding.addActionListener(gtbcl);
		
		// GoTo Building Button Listener
		FirstBuildingButtonClickListener fbcl = new FirstBuildingButtonClickListener(cgbcl, current);
		firstBuilding.addActionListener(fbcl);
		
		// Current Building Change Listener
		CurrentBuildingActionListener cbcl = new CurrentBuildingActionListener(cgbcl);
		current.addActionListener(cbcl);
		
		// Add buttons to left and right side buttons panels
		leftButtons.add(cgLabel1);
		leftButtons.add(currentLabel);
		leftButtons.add(current);
		
		rightButtons.add(new JLabel());
		rightButtons.add(acButton); 
		rightButtons.add(new JLabel());
		
		rightButtons.add(new JLabel());
		rightButtons.add(mcButton); 
		rightButtons.add(new JLabel());
		
		rightButtons.add(new JLabel());
		rightButtons.add(dcButton); 
		rightButtons.add(new JLabel());
	
		
		leftButtons.add(cgButton);
		leftButtons.add(goToBuilding);
		leftButtons.add(goTo);
			
		leftButtons.add(firstBuilding);
		leftButtons.add(previousBuilding);
		leftButtons.add(nextBuilding);
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
