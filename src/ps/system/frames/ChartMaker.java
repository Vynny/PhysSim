package ps.system.frames;

import ps.logic.beans.BindingInterface;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartMaker {
	
	private Scene scene;
	
	private BindingInterface sharedData;

	final NumberAxis xAxis;
	final NumberAxis yAxis;
	final LineChart<Number, Number> chart;
	
	public ChartMaker() {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		chart = new LineChart<Number, Number>(xAxis,yAxis);

		initChart();
		
		scene = new Scene(chart, 800,600);
	}

	public ChartMaker(BindingInterface sharedData) {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		this.sharedData = sharedData;
		chart = new LineChart<Number, Number>(xAxis,yAxis);
	}

	public Scene getScene() {
		return scene;
	}

	private void initChart() {
		XYChart.Series series = new XYChart.Series();
		series.setName("TEST");
		
		//SAMPLE DATA
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));
        
        chart.getData().add(series);
	}
	
}
