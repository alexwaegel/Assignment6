import java.util.Map.Entry;
import java.util.TreeMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartGenerator {
	
	public JFreeChart makeMonthChart(String buildingName, TreeMap<String, MeterReading> consumption) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		for (Entry<String, MeterReading> mr : consumption.entrySet()) {
			dataset.addValue(mr.getValue().getElecKbtu(),"Elec",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
			dataset.addValue(mr.getValue().getSteamKbtu(),"Steam",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
			dataset.addValue(mr.getValue().getChwKbtu(),"CHW",(mr.getValue().getYear()+" "+mr.getValue().getMonth()+" "+mr.getValue().getDay()));
		}
		JFreeChart barChart = ChartFactory.createStackedBarChart(buildingName, "", "kBtu", dataset, PlotOrientation.VERTICAL, true, true, false); 
		return barChart;
	}
}
