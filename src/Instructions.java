import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Instructions extends JFrame {

	private JPanel contentPane;
	
	private JLabel titleLabel;
	private JLabel instructionsLabel;
	
	
	/**
	 * @param orig
	 * @return multi-line label
	 */
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void play(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructions frame = new Instructions();
					frame.setVisible(true);
					
					// set frame as instructionsWindow in PlayAgain class -- closes window when user clicks playAgainButton
					PlayAgain.setInstructionsWindow(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Instructions() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// titlelabel
		titleLabel = new JLabel("Instructions");
		titleLabel.setForeground(Color.RED);
		titleLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(141, 0, 165, 50);
		contentPane.add(titleLabel);
		
		// instructionsLabel
		instructionsLabel = new JLabel();
		instructionsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionsLabel.setBounds(60, 50, 333, 200);
		
		if(Chooser.getVersion().equals("Square") || Chooser.getVersion().equals("Portrait")) {
			instructionsLabel.setText("<html> The goal is to recreate the picture chosen. "
				+ "Click the 'Spin' button to start the Slot Machine, and click the 'Stop' button to stop the Slot Machine. "
				+ "Click the piece you want to use from what you received from the Slot Machine and "
				+ "click one of the puzzle pieces to place it there. "
				+ "You can use each piece from each spin only once. "
				+ "Every 30 seconds, a tipping point is added to the Slot Machine. "
				+ "The counter for each tipping point is below the puzzle board. "
				+ "If you land on the same tipping point 3 TIMES or run out of time, it is game over!");
		}
		else {
			instructionsLabel.setText("<html> The goal is to recreate the picture chosen. "
					+ "Click the 'Spin' button to start the Slot Machine, and click the 'Stop' button to stop the Slot Machine. "
					+ "Click the piece you want to use from what you received from the Slot Machine and "
					+ "click one of the puzzle pieces to place it there. "
					+ "You can use each piece from each spin only once. "
					+ "Every 20 seconds, a tipping point is added to the Slot Machine. "
					+ "The counter for each tipping point is below the puzzle board."
					+ "If you land on the same tipping point 3 TIMES or run out of time, it is game over!");
		
	}
		contentPane.add(instructionsLabel);
}
}
