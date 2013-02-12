package ps.system.api;


import ps.system.frames.Person;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class ChartMaker {
	
	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	private BindingInterface sharedData;
	private XYChart.Series<Number, Number> DataSeries;
	
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart<Number, Number> chart;

	
	//TEST
	Timeline timeline;
	Person[] person;
	
	public ChartMaker() {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		chart = new LineChart<Number, Number>(xAxis,yAxis);

		testChart();
		
		scene = new Scene(chart, 800,600);
	}

	public ChartMaker(BindingInterface sharedData) {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		this.sharedData = sharedData;
		
		timeline = (Timeline) sharedData.getSharedDataAtIndex(1);
		person = (Person[]) sharedData.getSharedDataAtIndex(0);
		
		
		chart = initChart("X", null, "Y", null);
		
		scene = new Scene(chart, 800, 600);
	}

	protected LineChart<Number, Number> initChart(String xLabel, String xUnit, String yLabel, String yUnit) {
		final NumberAxis yAxis = new NumberAxis(0, 1000, 100);
		final LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
		
		//Chart Var Setup
		chart.setTitle(xLabel + " vs " + yLabel);
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setLegendVisible(true);
		
		//Axes Setup
		xAxis.setLabel(xLabel);
		xAxis.setForceZeroInRange(false);
		yAxis.setLabel(yLabel);
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, yUnit, null));

		// Starting Data
		DataSeries = new XYChart.Series<Number, Number>();
		DataSeries.setName("Series: 1");
        DataSeries.getData().add(new XYChart.Data<Number, Number>(0, 5));

		plotData();
	
		//Finalize		
		chart.getData().add(DataSeries);
		return chart;
	}
	
	private void plotData() {
		
		timeline.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				double CurTime = timeline.currentTimeProperty().getValue().toSeconds();
				double CurTransX = person[0].runnerNode().getTranslateX();

				DataSeries.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX));
				
			}

		});// LISTENER END

	}
	
/*	private void writeData() {
		final XYChart.Series<Number,Number> series = new XYChart.Series<Number, Number>();
		series.setName("TEST RUNNERS");
		
		if (sharedData.getSharedDataAtIndex(1) instanceof Timeline) {
			System.out.println("IN1");
				System.out.println("IN2");
				timeline = (Timeline) sharedData.getSharedDataAtIndex(1);
				person = (Person[]) sharedData.getSharedDataAtIndex(0);
				
				timeline.currentTimeProperty().addListener(
						new InvalidationListener() {

							@Override
						public void invalidated(Observable arg0) {
							System.out.println("Time: " + (timeline.currentTimeProperty().getValue().toSeconds()));
							series.getData().add(
									new XYChart.Data(timeline
											.currentTimeProperty().getValue()
											.toSeconds(), person[0]
											.runnerNode().getTranslateX()));
							chart.getData().retainAll();
							chart.getData().add(series);
						}

					});
		}
	}*/

	private void testChart() {
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
