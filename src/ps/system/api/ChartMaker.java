package ps.system.api;

import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ps.system.main.SystemConstants;

public class ChartMaker implements SystemConstants {
	
	private Scene scene;

	public Scene getScene() {
		return scene;
	}

	//Main Series List (Populated automatically: depends on values written into 
	//the data_shared_write_dependant object array in the simulator plugin. 
	private ObservableList<XYChart.Series<Number, Number>> Data;

	private static Object[] keys = DATAWRITE_JFX_dependant.keySet().toArray();
    private static CheckBox[] cbs = new CheckBox[keys.length];
	
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	private LineChart<Number, Number> chart;

	
	//TEST
	private Timeline timeline;

	public ChartMaker() {
		
		BorderPane root = new BorderPane();
		
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		
		timeline = (Timeline) DATAWRITE_JFX_independant.get("Time");
		
		//DEBUG: Print all dependant key values
		for (int i = 0; i < keys.length; i++) {
			System.out.println("Key #" + i + " is: " + keys[i]);
			cbs[i] = new CheckBox((String)keys[i]);
		}

		root.setCenter(initChart("Time", null, "Position", null));
		root.setBottom(BottomMenu());
		
		scene = new Scene(root);
	}

	
	protected LineChart<Number, Number> initChart(String xLabel, String xUnit, String yLabel, String yUnit) {
		final NumberAxis yAxis = new NumberAxis(0, 1000, 100);
	    chart = new LineChart<Number, Number>(xAxis, yAxis);
		
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

		//Data
		Data =  FXCollections.observableArrayList();
		for (int i = 0; i < keys.length; i++) {
			Data.add(new Series<Number, Number>());
			Data.get(i).setName("Series: " + keys[i]);
		}

		plotData();

		chart.getData().addAll(Data);
		return chart;
	}
	
	private void plotData() {
		
		timeline.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				Number CurTime = timeline.currentTimeProperty().getValue().toSeconds();

				for (int i = 0; i < keys.length; i++) {
					if (cbs[i].selectedProperty().getValue().equals(true)) {
					Data.get(i).getData().add(new XYChart.Data<Number, Number>(CurTime, DATAWRITE_JFX_dependant.get(keys[i]).getTranslateX()));
					}
				}
			}

		});// LISTENER END
	}
	
	private static HBox BottomMenu() {
		HBox pane = new HBox();
		pane.setPadding(new Insets(10,10,10,10));
		pane.setSpacing(5);
		pane.setAlignment(Pos.CENTER);

		for (int i = 0; i < cbs.length; i++) {
			pane.getChildren().addAll(cbs[i]);
		}
		
		return pane;
	}
}
