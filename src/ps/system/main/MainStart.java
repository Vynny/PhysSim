package ps.system.main;

public class MainStart {

	public static void main(String[] args) {
		//Swing Thread(EDT - Event Dispatch Thread)
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				PhysicsWindow.InitializeGUI();
			}
		});
	}

}
