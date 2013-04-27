package ps.system.api;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ps.system.frames.JFXPanes;
import ps.system.main.PhysicsWindow;
import ps.system.main.SystemConstants;

import com.sun.javafx.css.converters.StringConverter;


public class InfoPane implements SystemConstants {
	
	
	private SimulatorInstanceSwing swingInstance;
	private Scene scene;

	public Scene getScene() {
		return scene;
	}
	
	
	private static Button startButton = new Button(SystemLanguage.getLanguageBundle().getString("InfoPane_start"));
	
	public Button getStartButton() {
		return startButton;
	}

	private static Button resetButton = new Button(SystemLanguage.getLanguageBundle().getString("InfoPane_reset"));

	public Button getResetButton() {
		return resetButton;
	}


	private static Button backButton = new Button(SystemLanguage.getLanguageBundle().getString("InfoPane_back"));

	public Button getBackButton() {
		return backButton;
	}

	
	public InfoPane() {
		BorderPane root = new BorderPane();
		scene = new Scene(root);
		
		Object[] test = DATAREAD.keySet().toArray();
		for (int i = 0; i < test.length; i++) {
			System.out.println(i + " DATAREAD: " + DATAREAD.get(test[i]));
		}
		
		root.setCenter(variableEditor());
		root.setBottom(Menu());
	}
	
	public InfoPane(SimulatorInstanceSwing swingInstance) {
		BorderPane root = new BorderPane();
		scene = new Scene(root);
		
		this.swingInstance = swingInstance;
		initalizeSwingButtonHandlers();

		root.setCenter(variableEditor());
		root.setBottom(Menu());
}
	
	private void initalizeSwingButtonHandlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.start();
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				JFXPanes.getGraphComponent().clearData();
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				swingInstance.stop();
				
				PhysicsWindow.JFXPanes.simulationID.setSimulationID(" ");
				PhysicsWindow.changeWindow("Menu");
			}
			
		});
	}
	
	private void initalizeJFXButtonHandlers() {
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});

		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				JFXPanes.getGraphComponent().clearData();
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {			
				PhysicsWindow.JFXPanes.simulationID.setSimulationID(" ");
				PhysicsWindow.changeWindow("Menu");
			}
			
		});
	}

	private HBox Menu() {
		HBox pane = new HBox();
		pane.setPadding(new Insets(10,10,10,10));
		pane.setSpacing(500/1.4);
		pane.setAlignment(Pos.CENTER);
		
		pane.getChildren().addAll(startButton, resetButton, backButton);
		
		return pane;
	}
	
	private ScrollPane variableEditor() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToWidth(true);
		scrollPane.autosize();

		scrollPane.setContent(variableEditor_node());
		
		return scrollPane;
	}
	
	
	private GridPane variableEditor_node() {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 15, 25, 15));
		
		final Object[] variableNames = DATAREAD.keySet().toArray();
		
		int gridCol = 1;
		int gridRow = 0;
		
		for (int i = 0; i < variableNames.length; i++) {
			final String currentKey = (String) variableNames[i];
			
			// Variable Name
			Label varLabel = new Label(currentKey + ":");
			varLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			gridPane.add(varLabel, gridCol, gridRow);
			gridPane.setAlignment(Pos.CENTER_LEFT);
			
			// Textfield for Editing
			final TextField varField = new TextField();
			varField.setAlignment(Pos.CENTER);
			varField.setText(DATAREAD.get(currentKey).getValue().toString());
			
			varField.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if ((varField.getText() != null && !varField.getText().isEmpty())) {

						try {

							double probedValue = Double.parseDouble(varField.getText());
							DATAREAD.get(currentKey).setValue(probedValue);
							
						} catch (Exception ex) {
							varField.setText("Input must be a number");
						}

					}
				}
			});

			gridPane.add(varField, gridCol, gridRow + 1);

			//Slider
			double minSliderValue = 0;
			double maxSliderValue = (double)DATAREAD.get(currentKey).getValue() * 4;
			double curSliderValue = (double)DATAREAD.get(currentKey).getValue();
			
			Slider varSlider = new Slider((int)minSliderValue, (int)maxSliderValue, (int)curSliderValue);
			varSlider.setShowTickLabels(true);
			varSlider.setShowTickMarks(true);
			varSlider.setMajorTickUnit((double)DATAREAD.get(currentKey).getValue() * 2);
			varSlider.setMinorTickCount(5);
			varSlider.setBlockIncrement(10);
			
			varSlider.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					DATAREAD.get(currentKey).setValue(new_val.intValue());
					varField.setText(DATAREAD.get(currentKey).getValue().toString());
					
					//This check is not necessary for javaFX instances since it redraws automatically when a bean
					//bound to the position of an object is updated
					if (JFXPanes.genericSimulation instanceof SimulatorInstanceSwing) {
						JFXPanes.SwingSimulation.repaint();
					}
				}
			});
			
			gridPane.add(varSlider, gridCol + 1, gridRow + 1);

			//Set offset for next variable modifier
			//gridRow += 4; <--Stacked
			gridCol += 4; //<--Lined up
		}
		
		return gridPane;
	}

}
