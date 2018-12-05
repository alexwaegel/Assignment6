import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeChartTest {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setSize(900,600);
		frame.setTitle("The Campus Analyzer 5000!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		
		dataset.addValue(1,"Elec","July");
		dataset.addValue(1, "Steam", "July");
		dataset.addValue(1, "CHW", "July");
		
		dataset.addValue(1,"Elec","Aug");
		dataset.addValue(1, "Steam", "Aug");
		dataset.addValue(1, "CHW", "Aug");
		
		dataset.addValue(1,"Elec","Sep");
		dataset.addValue(1, "Steam", "Sep");
		dataset.addValue(1, "CHW", "Sep");
		
		dataset.addValue(1,"Elec","Oct");
		dataset.addValue(1, "Steam", "Oct");
		dataset.addValue(1, "CHW", "Oct");
		
		dataset.addValue(1,"Elec","Nov");
		dataset.addValue(1, "Steam", "Nov");
		dataset.addValue(1, "CHW", "Nov");
		
		dataset.addValue(1,"Elec","Dec");
		dataset.addValue(1, "Steam", "Dec");
		dataset.addValue(1, "CHW", "Dec");
		
		JFreeChart barChart = ChartFactory.createStackedBarChart("MonthlyConsumption for Building X", "Month", "kBtu", dataset, PlotOrientation.VERTICAL, true, true, false); 
		ChartPanel chartPanel = new ChartPanel( barChart );        
		chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
		
		frame.add(chartPanel);
		frame.setVisible(true);
		
	}

}
