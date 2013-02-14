package ps.system.main;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InfoPane extends JPanel {
	
	private static boolean[] actionFlags = new boolean[3];
	
	private JLabel instructionLabel = new JLabel("<html><u>Select An Action</u></html>");
    private JLabel transactionLabel = new JLabel("Test Value: " + PhysicsWindow.sharedData.getDataByKey("SIM_basetime"));
    private JLabel securityLabel = new JLabel("Security Question:");
    private JLabel changepassLabel = new JLabel("Change Password:");
    
    private JCheckBox transactionCheckBox = new JCheckBox();
    private JCheckBox securityCheckBox = new JCheckBox();
    private JCheckBox changepassCheckBox = new JCheckBox();
    
    private JButton continueButton = new JButton("Continue");
    private JButton backButton = new JButton("Back");
	
	public InfoPane() {
	
				//Instruction Panel 
				JPanel instructionsPanel = new JPanel();
				instructionsPanel.add(instructionLabel);
				
				// Content Panel
				JPanel contentPanel = new JPanel(new GridLayout(3, 2, 10, 10));
				transactionLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(transactionLabel);
				contentPanel.add(transactionCheckBox);
				securityLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(securityLabel);
				contentPanel.add(securityCheckBox);
				changepassLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(changepassLabel);
				contentPanel.add(changepassCheckBox);
				contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

				//Button Panel
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(continueButton);
				buttonPanel.add(backButton);
				
				//Master Panel Configuration
				setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				setBorder(new EmptyBorder(10,10,10,10));
				add(instructionsPanel);
				add(contentPanel);
				add(buttonPanel);
				

		
	}

}
