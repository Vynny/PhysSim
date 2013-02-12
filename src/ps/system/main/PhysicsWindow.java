package ps.system.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import ps.system.frames.MenuWindow;

public class PhysicsWindow extends JFrame implements SystemConstants {
	
	public PhysicsWindow() {

		//Create JFX pane container object
		//Contains: JSplitPane of two javaFX scenes (R: Simulation, L: Graph)
		JFXPanes JFXPanes = new JFXPanes();
		JSplitPane topPanes = JFXPanes.getSeperatedPanes();

		// Create bottom info pane(contains buttons and configuration)
		InfoPane bottomPane = new InfoPane();

		// Create nested split container containing horiz layout jfxpanes setup
		// vertically with bottom pane
		JSplitPane windowPanes = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				topPanes, bottomPane);
		
		windowPanes.setResizeWeight(MainSplitPanes_RESIZEWEIGHT);
		windowPanes.setContinuousLayout(true);

		// Set Minimum Size
		topPanes.setMinimumSize(new Dimension(topPanes_MINW, topPanes_MINH));
		bottomPane.setMinimumSize(new Dimension(bottomPane_MINW,
				bottomPane_MINH));

		// Add split pane to frame
		getContentPane().add(windowPanes);

	}
	
	public static void InitializeGUI() {
		JFrame mainFrame = new PhysicsWindow();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.pack();
		mainFrame.setTitle(TITLE);
		mainFrame.setSize(mainWindow_DEFAULTW, mainWindow_DEFAULTH);
		mainFrame.setVisible(true);
	}
}