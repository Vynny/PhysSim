package ps.system.api;

import ps.system.frames.Person;
import ps.system.main.PhysicsWindow;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;

public class ChartMaker {
	
	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	//private DataStore sharedData;
	private XYChart.Series<Number, Number> DataSeries;
	private XYChart.Series<Number, Number> DataSeries2;
	private XYChart.Series<Number, Number> DataSeries3;
	private XYChart.Series<Number, Number> DataSeries4;
	private XYChart.Series<Number, Number> DataSeries5;
	
	private Object[] keys = PhysicsWindow.sharedData.getDataWrite_dependant().keySet().toArray();
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart<Number, Number> chart;

	
	//TEST
	Timeline timeline;
	Node genericNode;

	public ChartMaker() {
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		
		timeline = (Timeline) PhysicsWindow.sharedData.getDataWrite_independant().get("Time");
		genericNode = (Node)  PhysicsWindow.sharedData.getDataWrite_dependant().get("m1");
		
		//DEBUG: Print all dependant key values
		for (int i = 0; i < keys.length; i++) {
			System.out.println("Key #" + i + " is: " + keys[i]);
		}
		
		chart = initChart("X", null, "Y", null);
		
		scene = new Scene(chart, 800, 600);
	}

	
	protected LineChart<Number, Number> initChart(String xLabel, String xUnit, String yLabel, String yUnit) {
		final NumberAxis yAxis = new NumberAxis(0, 1000, 100);
	    chart = new LineChart<>(xAxis, yAxis);
		
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
        //DataSeries.getData().add(new XYChart.Data<Number, Number>(0, 5));
        
        DataSeries2 = new XYChart.Series<Number, Number>();
		DataSeries2.setName("Series: 2");
        //DataSeries2.getData().add(new XYChart.Data<Number, Number>(0, 5));

		DataSeries3 = new XYChart.Series<Number, Number>();
		DataSeries3.setName("Series: 3");

		DataSeries4 = new XYChart.Series<Number, Number>();
		DataSeries4.setName("Series: 4");
		
		DataSeries5 = new XYChart.Series<Number, Number>();
		DataSeries5.setName("Series: 5");


		plotData();
	
		//Finalize	
		chart.getData().addAll(DataSeries, DataSeries2, DataSeries3, DataSeries4, DataSeries5);
		return chart;
	}
	
	private void plotData() {
		
		timeline.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				Number CurTime = timeline.currentTimeProperty().getValue().toSeconds();
				Number CurTransX = genericNode.getTranslateX();
				Number CurTransX2 = PhysicsWindow.sharedData.getDataWrite_dependant().get("m2").getTranslateX();
				Number CurTransX3 = PhysicsWindow.sharedData.getDataWrite_dependant().get("m3").getTranslateX();
				Number CurTransX4 = PhysicsWindow.sharedData.getDataWrite_dependant().get("m4").getTranslateX();
				Number CurTransX5 = PhysicsWindow.sharedData.getDataWrite_dependant().get("m5").getTranslateX();
				
				
				DataSeries.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX));
				DataSeries2.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX2));
				DataSeries3.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX3));
				DataSeries4.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX4));
				DataSeries5.getData().add(new XYChart.Data<Number, Number>(CurTime, CurTransX5));
			}

		});// LISTENER END
	}
}
