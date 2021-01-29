/**
 * Creates GUI which displays an enlarged version of the image the user chose in Chooser
 * @author candacechen
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LargeImage extends JFrame {

	/*
	 * Instance Variables
	 */
	
	private final int gameWindowX = PlayAgain.getGameWindow().getX();
	private final int gameWindowY = PlayAgain.getGameWindow().getY();
	private final int gameWindowWidth = PlayAgain.getGameWindow().getWidth();
	private final int gameWindowHeight = PlayAgain.getGameWindow().getHeight();
	
	
	private JPanel contentPane;

	private ImageIcon image = Chooser.getSelectedImage();
	
	private JLabel imageLabel;
	
	
	/**
	 * Launch the application.
	 */
	public static void play() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LargeImage frame = new LargeImage();
					frame.setVisible(true);
					
					// set frame as largeImageWindow in PlayAgain class -- closes window when user clicks playAgainButton
					PlayAgain.setLargeImageWindow(frame);
	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LargeImage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// instantiate imageLabel
		imageLabel = new JLabel();
		
		// square
		if(Chooser.getVersion().equals("Square")) {
			setBounds(gameWindowX + gameWindowWidth + 5, gameWindowY + 150, 500, 500);
			
			imageLabel.setBounds(this.getWidth()/2 - 225, this.getHeight()/2 -235, 450, 450);
		}
		
		// portrait
		else if(Chooser.getVersion().equals("Portrait")) {
			setBounds(gameWindowX + gameWindowWidth + 5, gameWindowY + 90, 450, 600);
			imageLabel.setBounds(this.getWidth()/2 - 212, this.getHeight()/2 - 290, 425, 560);
		}
		
		// landscape
		else {
			setBounds(gameWindowX + gameWindowWidth + 5, gameWindowY + 207, 600, 360);
			imageLabel.setBounds(this.getWidth()/2 - 275, this.getHeight()/2 - 175, 550, 330);
		}
			
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		imageLabel.setIcon(SlotPuzzleGame4.scaleImage(image, imageLabel.getWidth(), imageLabel.getHeight()));
		contentPane.add(imageLabel);
	}

}
