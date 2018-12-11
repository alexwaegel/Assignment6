import java.awt.Font;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The BarChartGenerator class is used to create the annual, monthly, and daily stacked bar charts. These show the individual
 * contributions to total consumption from electricity, steam, and chilled water.
 * @author alexw_000
 *
 */
public class BarChartGenerator {
	
	/**
	 * The makeMonthChart method is slightly deceptively named as it ended up being suitable to make all the bar charts. The
	 * external library JFreeChart was utilized to create the chart. This is accomplished by reading a TreeMap of the
	 * daily, monthly, or annual consumption (elec, stm, and CHW) into a dataset which can then be used by JFreeChart
	 * 
	 * @param buildingName is a String supplied as a parameter for use as a label in the chart
	 * @param consumption is the TreeMap which contains the appropriate meter readings (Daily/monthly/or annual) for that building.
	 * @return the JFreeCHart stacked bar chart object that will be displayed in the GUI
	 */
	public JFreeChart makeMonthChart(String buildingName, TreeMap<String, MeterReading> consumption) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		
		for (Entry<String, MeterReading> mr : consumption.entrySet()) {
			if (mr.getValue().getDay().equals("--")) {
				dataset.addValue(mr.getValue().getElecKbtu(),"Elec",(mr.getValue().getYear()+" "+mr.getValue().getMonth()));
				dataset.addValue(mr.getValue().getSteamKbtu(),"Steam",(mr.getValue().getYear()+" "+mr.getValue().getMonth()));
				dataset.addValue(mr.getValue().getChwKbtu(),"CHW",(mr.getValue().getYear()+" "+mr.getValue().getMonth()));
			}
			else {
				dataset.addValue(mr.getValue().getElecKbtu(),"Elec",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
				dataset.addValue(mr.getValue().getSteamKbtu(),"Steam",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
				dataset.addValue(mr.getValue().getChwKbtu(),"CHW",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
			}
		}
		JFreeChart barChart = ChartFactory.createStackedBarChart(buildingName, "", "kBtu", dataset, PlotOrientation.VERTICAL, true, true, false); 
		CategoryAxis axis = barChart.getCategoryPlot().getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		int n=1;
		int showEvery = consumption.entrySet().size()/12;
		for (Entry<String, MeterReading> mr : consumption.entrySet()) {
			if (!mr.getValue().getDay().equals("--")) {
				if (n!=1 && n%showEvery!=0) {
					axis.setTickLabelFont((mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()), new Font("Serif", Font.PLAIN, 0));
				}
				else {
					axis.setTickLabelFont((mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()), new Font("Serif", Font.PLAIN, 10));
				}
			}
			n=n+1;
		}
		return barChart;
	}
}
