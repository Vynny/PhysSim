package ps.system.core;

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
				if (!swingInstance.isRUNNING()) {
					swingInstance.start();
					startButton.setText(SystemLanguage.getLanguageBundle().getString("InfoPane_pause"));
					resetButton.setDisable(false);
				} else {
					swingInstance.pause();
					startButton.setText(SystemLanguage.getLanguageBundle().getString("InfoPane_start"));
				}
			}
		});

		resetButton.setDisable(true);
		resetButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				startButton.setText(SystemLanguage.getLanguageBundle().getString("InfoPane_start"));
				swingInstance.stop();
				JFXPanes.getGraphComponent().clearData();
				PhysicsWindow.JFXPanes.simulationID.setSimulationID("RESET");
				resetButton.setDisable(true);
			}

		});
		
		
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				startButton.setText(SystemLanguage.getLanguageBundle().getString("InfoPane_start"));
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
			Label varLabel = new Label();
			if (currentKey.split("_").length > 1) {
				varLabel.setText(currentKey.split("_")[0] + ":");
			} else {
				varLabel.setText(currentKey + ":");
			}
			varLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			gridPane.add(varLabel, gridCol, gridRow);
			gridPane.setAlignment(Pos.CENTER_LEFT);
			
			// Textfield for Editing
			final TextField varField = new TextField();
			final String valueText;
			
			if ((Double)DATAREAD.get(currentKey).getValue() > 0) {
				valueText = DATAREAD.get(currentKey).getValue().toString();
			} else {
				valueText = Double.toString(Math.abs((Double)DATAREAD.get(currentKey).getValue()));
			}
			
			varField.setText(valueText);
			varField.setAlignment(Pos.CENTER);
			varField.setEditable(false);
			
			/*varField.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if ((varField.getText() != null && !varField.getText().isEmpty())) {

						try {

							double probedValue = Double.parseDouble(varField.getText());
							if (currentKey.split("_").length > 1) {
								if (Double.parseDouble(currentKey.split("_")[1].split(":")[1]) < 0) {
									DATAREAD.get(currentKey).setValue(-1 * probedValue);
									System.out.println("PROBED VALUE: " + (-1 * probedValue));
								} else {
									DATAREAD.get(currentKey).setValue(probedValue);
									System.out.println("PROBED VALUE: " + probedValue);
								}
							}
							
							if (JFXPanes.genericSimulation instanceof SimulatorInstanceSwing) {
								JFXPanes.SwingSimulation.repaint();
							}
							
						} catch (Exception ex) {
							varField.setText("Input must be a number");
						}

					}
				}
			});*/

			gridPane.add(varField, gridCol, gridRow + 1);

			//Slider
			double minSliderValue;
			double maxSliderValue;
			double curSliderValue;
			
			if (currentKey.split("_").length > 1) {
				minSliderValue = Double.parseDouble(currentKey.split("_")[1].split(":")[0]);
				maxSliderValue = Double.parseDouble(currentKey.split("_")[1].split(":")[1]);
				if ((Double)DATAREAD.get(currentKey).getValue() > 0) {
					curSliderValue = (Double)DATAREAD.get(currentKey).getValue();
				} else {
					curSliderValue = Math.abs((Double)DATAREAD.get(currentKey).getValue());
				}
			} else {
				minSliderValue = 0;
				maxSliderValue = (Double)DATAREAD.get(currentKey).getValue() * 4;
				curSliderValue = (Double)DATAREAD.get(currentKey).getValue();
			}
			
			Slider varSlider = new Slider(Math.abs((int)minSliderValue), Math.abs((int)maxSliderValue), Math.abs((int)curSliderValue));
			varSlider.setShowTickLabels(true);
			varSlider.setShowTickMarks(true);
			varSlider.setMajorTickUnit(Math.abs(maxSliderValue/2));
			varSlider.setMinorTickCount(10);
			varSlider.setBlockIncrement(5);		
			
			varSlider.valueProperty().addListener(new ChangeListener<Number>() {
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					String textLabel;
					if (currentKey.split("_").length > 1) {
						if (Double.parseDouble(currentKey.split("_")[1].split(":")[1]) < 0) {
							DATAREAD.get(currentKey).setValue(-1 * Math.floor(new_val.doubleValue() * 100) / 100);
							textLabel = Double.toString(Math.abs((Double)DATAREAD.get(currentKey).getValue()));
						} else {
							DATAREAD.get(currentKey).setValue(Math.floor(new_val.doubleValue() * 100) / 100);
							textLabel = DATAREAD.get(currentKey).getValue().toString();
						}
					} else {
						DATAREAD.get(currentKey).setValue(Math.floor(new_val.doubleValue() * 100) / 100);
						textLabel = DATAREAD.get(currentKey).getValue().toString();
					}
					
					varField.setText(textLabel);
					
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
