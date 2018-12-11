import java.awt.Font;
import java.awt.Paint;
import java.text.NumberFormat;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class AnnualChartGenerator {
	
	public JFreeChart makeAnnualChart(TreeMap<String, Building> campus) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( ); 
		
		for (Entry<String, Building> building : campus.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getAnnualConsumption().entrySet()) {
				dataset.addValue(mr.getValue().getElecKbtu(),"Elec",(mr.getKey()+" "+mr.getValue().getYear()));
				dataset.addValue(mr.getValue().getSteamKbtu(),"Steam",(mr.getKey()+" "+mr.getValue().getYear()));
				dataset.addValue(mr.getValue().getChwKbtu(),"CHW",(mr.getKey()+" "+mr.getValue().getYear()));
			}
		}
		JFreeChart barChart = ChartFactory.createStackedBarChart("Campus", "", "kBtu", dataset, PlotOrientation.VERTICAL, true, true, false); 
		CategoryAxis axis = barChart.getCategoryPlot().getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
		
		StackedBarRenderer r = (StackedBarRenderer) barChart.getCategoryPlot().getRenderer();
	    r.setBase(1);
		
		Font font = barChart.getCategoryPlot().getRangeAxis().getTickLabelFont();
		Paint paint = barChart.getCategoryPlot().getRangeAxis().getTickLabelPaint();

		LogAxis logaxis = new LogAxis();
		logaxis.setBase(10);
		logaxis.setSmallestValue(1);
		logaxis.setTickLabelFont(font);
		logaxis.setTickLabelPaint(paint);
		logaxis.setNumberFormatOverride(NumberFormat.getInstance());
		logaxis.setSmallestValue(100000);

		// Adding new Axis to plot
		barChart.getCategoryPlot().setRangeAxis(logaxis);
		
		for (Entry<String, Building> building : campus.entrySet()) {
			for (Entry<String, MeterReading> mr : building.getValue().getAnnualConsumption().entrySet()) {
				axis.setTickLabelFont((mr.getKey()+" "+mr.getValue().getYear()), new Font("Serif", Font.PLAIN, 7));
			}
		}
		return barChart;
	}

}
