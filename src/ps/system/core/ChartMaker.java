package ps.system.core;

import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
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

	//Chart Legend Objects
	private Object[] keys;
    private static CheckBox[] cbs;
    private static Button backButton;
    private static Button forwardButton;
    
	//Chart Objects
	private final NumberAxis xAxis;
	private static LineChart<Number, Number> chart;
	private String title;
	
	//Dataset Ientifiers
	public final static Object JFXDATASET = 0;
	public final static Object SWINGDATASET = 1;
	
	//Independant Trackers
	private Timeline JFXtimeline;
	private SimpleDoubleProperty SwingTime;

	public ChartMaker(Object dataSet, String title) {
		BorderPane root = new BorderPane();
		this.title = title;
		xAxis = new NumberAxis();	
		
		if (dataSet.equals((int) 0)) {
			System.out.println("JFX DATA SET");
			keys = DATAWRITE_JFX_dependant.keySet().toArray();
			cbs = new CheckBox[keys.length];
			
			JFXtimeline = (Timeline) DATAWRITE_JFX_independant.get("Time");
			System.out.println("TEST: " + JFXtimeline.toString());
			
			//DEBUG: Print all dependant key values
			for (int i = 0; i < keys.length; i++) {
				System.out.println("Key #" + i + " is: " + keys[i]);
				cbs[i] = new CheckBox((String)keys[i]);
				cbs[i].setSelected(true);
			}
			
			root.setCenter(initJFXChart("Time", null, "Position", null));
			
		} else if (dataSet.equals((int) 1)) {
			System.out.println("SWING DATA SET");
			keys = DATAWRITE_Swing_dependant.keySet().toArray();
			cbs = new CheckBox[keys.length];
			
			SwingTime = DATAWRITE_Swing_independant.get("Time");
			
			//DEBUG: Print all dependant key values
			for (int i = 0; i < keys.length; i++) {
				System.out.println("Key #" + i + " is: " + keys[i]);
				cbs[i] = new CheckBox((String)keys[i]);
				cbs[i].setSelected(true);
			}
	
			root.setCenter(initSwingChart("Time", null, "", null));
		}
		
		root.setBottom(BottomMenu());
		
		scene = new Scene(root);
	}

	
	/*
	 * JFX METHODS
	 */
	protected LineChart<Number, Number> initJFXChart(String xLabel, String xUnit, String yLabel, String yUnit) {
		final NumberAxis yAxis = new NumberAxis(0, 1000, 100);
		
	    chart = new LineChart<Number, Number>(xAxis, yAxis);
		
		//Chart Var Setup
	    chart.setTitle(title);
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setLegendVisible(true);
		
		//Axes Setup
		xAxis.setLabel(xLabel);
		xAxis.setForceZeroInRange(false);
		yAxis.setLabel(yLabel);
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, yUnit, null));

		dataInit();

		plotJFXData();

		return chart;
	}
	
	private void plotJFXData() {
		
		JFXtimeline.currentTimeProperty().addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				Number CurTime = JFXtimeline.currentTimeProperty().getValue().toSeconds();

				for (int i = 0; i < keys.length; i++) {
					if (cbs[i].selectedProperty().getValue().equals(true) && (DATAWRITE_JFX_dependant.get(keys[i]) != null)) {
						Data.get(i).getData().add(new XYChart.Data<Number, Number>(CurTime, DATAWRITE_JFX_dependant.get(keys[i]).getTranslateX()));
					}	
				}
			}
		});
	}
	
	
	/*
	 * SWING METHODS
	 */
	
	protected LineChart<Number, Number> initSwingChart(String xLabel, String xUnit, String yLabel, String yUnit) {
	    final NumberAxis yAxis = new NumberAxis(-100, 100, 25);
	    chart = new LineChart<Number, Number>(xAxis, yAxis);
	    
		//Chart Var Setup
	    //->BUG: Auto-ranging causes concurrency issues for both axes.
	    chart.setTitle(title);
		chart.setCreateSymbols(false);
		chart.setAnimated(false);
		chart.setLegendVisible(true);
		
		//Axes Setup
		
		//xAxis.setLabel(xLabel);
		xAxis.setForceZeroInRange(false);
		//->BUG: Auto-ranging causes concurrency issues for both axes.
		xAxis.setAutoRanging(false);
	    yAxis.setAutoRanging(false);
		//yAxis.setLabel(yLabel);
		yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis, yUnit, null));

		dataInit();

		plotSwingData();

		return chart;
	}
	
	private void plotSwingData() {
		
		xAxis.setUpperBound(SwingTime.getValue().doubleValue() + 5);
		xAxis.setLowerBound(SwingTime.getValue().doubleValue());
		xAxis.setTickUnit(1);
		
		SwingTime.addListener(new InvalidationListener() {

			@Override
			public void invalidated(Observable arg0) {
				NumberAxis xAxis = (NumberAxis) chart.getXAxis();
				if (SwingTime.getValue().doubleValue() > xAxis.getUpperBound()) {
					xAxis.setUpperBound(xAxis.getUpperBound() + 2);
					xAxis.setLowerBound(xAxis.getLowerBound() + 2);
				} 
				
					for (int i = 0; i < keys.length; i++) {
						if (cbs[i].selectedProperty().getValue().equals(true) && (DATAWRITE_Swing_dependant.get(keys[i]) != null)) {
							Number curValue = DATAWRITE_Swing_dependant.get(keys[i]).getValue();
							
							Data.get(i).getData().add(new XYChart.Data<Number, Number>(SwingTime.getValue().doubleValue(), curValue));
							
							NumberAxis yAxis = (NumberAxis) chart.getYAxis();
							if (curValue.doubleValue() >= yAxis.getUpperBound()) {
								yAxis.setUpperBound(yAxis.getUpperBound() + (curValue.doubleValue() - yAxis.getUpperBound()) + 10);
							} else if (curValue.doubleValue() <= yAxis.getLowerBound()) {
								yAxis.setLowerBound(yAxis.getLowerBound() - (yAxis.getLowerBound() - curValue.doubleValue()) - 10);
							} 
					}
				}
			}

		});// LISTENER END

	}
	
	private void dataInit() {
		// Data
		Data = FXCollections.observableArrayList();
		for (int i = 0; i < keys.length; i++) {
			Data.add(new Series<Number, Number>());
			Data.get(i).setName("Series: " + keys[i]);
		}
		
		chart.getData().addAll(Data);
	}
	
	public void clearData() {
		chart.getData().clear();
		dataInit();
	}

	/*
	 * CONTENT LEAFS
	 */
	private static HBox BottomMenu() {
		HBox pane = new HBox();
		pane.setPadding(new Insets(10,10,10,10));
		pane.setSpacing(5);
		pane.setAlignment(Pos.CENTER);
		
		backButton = new Button("<");
		forwardButton = new Button(">");
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
		    	
		    	xAxis.setUpperBound(xAxis.getUpperBound() - 1);
		        xAxis.setLowerBound(xAxis.getLowerBound() - 1);
		    }
		});
		
		
		forwardButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
		    	
		        xAxis.setUpperBound(xAxis.getUpperBound() + 1);
		        xAxis.setLowerBound(xAxis.getLowerBound() + 1);
		    }
		});
		
		
		pane.getChildren().add(backButton);
		for (int i = 0; i < cbs.length; i++) {
			pane.getChildren().addAll(cbs[i]);
		}
		pane.getChildren().add(forwardButton);
		
		return pane;
	}
}
