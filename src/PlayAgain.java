/**
 * Creates GUI which displays a winning/losing picture, text, and a play again button that opens Chooser GUI and disposes
 * the current window, SlotPuzzleGame4 window, and C
 * @author candacechen
 */
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Color;

public class PlayAgain extends JFrame {
	
	/*
	 * INSTANCE VARIABLES
	 */
	
	// SCREEN SIZE
	final static Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// CONTENT PANE
	private JPanel contentPane;
	
	// game window
	protected static SlotPuzzleGame4 gameWindow;
	
	// large image window
	protected static LargeImage largeImageWindow;
	
	// instructions window
	protected static Instructions instructionsWindow;
	
	// win lose type
	protected static String winLoseType;
	
	//WIN LOSE LABEL
	protected JLabel winLoseLabel;
	
	// PICTURE LABEL
	protected JLabel pictureLabel;
	
	// WINNING PICTURE
	URL winningURL = PlayAgain.class.getResource("/win.jpg");
	protected ImageIcon winningPicture = new ImageIcon(winningURL);
	
	// LOSING PICTURE
	URL losingURL = PlayAgain.class.getResource("/lose.jpg");
	protected ImageIcon losingPicture = new ImageIcon(losingURL);
	
	//Play Again Button
	protected JButton playAgainButton;

	/**
	 * Methods
	 */
	

	/**
	 * @return the gameWindow
	 */
	public static SlotPuzzleGame4 getGameWindow() {
		return gameWindow;
	}

	/**
	 * @param gameWindow the gameWindow to set
	 */
	public static void setGameWindow(SlotPuzzleGame4 gameWindow1) {
		gameWindow = gameWindow1;
	}
	

	/**
	 * @return the largeImageWindow
	 */
	public static LargeImage getLargeImageWindow() {
		return largeImageWindow;
	}

	/**
	 * @param largeImageWindow the largeImageWindow to set
	 */
	public static void setLargeImageWindow(LargeImage largeImageWindow1) {
		largeImageWindow = largeImageWindow1;
	}

	/**
	 * @return the instructionsWindow
	 */
	public static Instructions getInstructionsWindow() {
		return instructionsWindow;
	}

	/**
	 * @param instructionsWindow the instructionsWindow to set
	 */
	public static void setInstructionsWindow(Instructions instructionsWindow1) {
		instructionsWindow = instructionsWindow1;
	}

	/**
	 * @return the winLoseType
	 */
	public static String getWinLoseType() {
		return winLoseType;
	}

	/**
	 * @param winLoseType the winLoseType to set
	 */
	public static void setWinLoseType(String winLoseType) {
		PlayAgain.winLoseType = winLoseType;
	}

	/**
	 * Based on type of winning or losing, set winLoseLabel text to corresponding text
	 * @return winning/losing text
	 */
	public String getWinLoseText() {
		if(winLoseType.equals("win")) {
			return "Congratulations, you won!";
		}
		
		else if (winLoseType.equals("tipping")) {
			return "You reached the tipping point :(";
		}
		
		else {
			return "You ran out of time!";			
		}
	}
	
	/**
	 * Based on whether user won or lost, returns corresponding ImageIcon
	 * @return ImageIcon to use
	 */
	public ImageIcon getWinLosePicture() {
		if (winLoseType.equals("win")) {
			return winningPicture;
		}
		else {
			return losingPicture;
		}
	}
	
	/**
	 * Disposes current Window --> use when winning the game and want to play again
	 */
	public void setClose() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	/**
	 * Launch the application.
	 */
	public static void play() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayAgain frame = new PlayAgain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/* ---------------------------
	 * Play Again Button Listener
	 * ---------------------------
	 */
	
	protected class PlayAgainButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// stop sounds
			SoundEffects.WINNING.stop();
			SoundEffects.LOSING.stop();
			
			
			// close gameWindow
			gameWindow.dispose();
			
			// close largeImageWindow
			if(largeImageWindow != null) {
				largeImageWindow.dispose();
			}
			
			// close instructionsWindow
			if(instructionsWindow != null) {
				instructionsWindow.dispose();
			}
			
			// closes current Window
			setClose();
			
			// opens new game
			Chooser.play();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public PlayAgain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int) myScreenSize.getWidth()/2 - 300,(int) myScreenSize.getHeight()/2 - 325, 600, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// PLAY CORRECT SOUND
		if(winLoseType.equals("win")) {
			SoundEffects.WINNING.play();
		}
		else {
			SoundEffects.LOSING.play();
		}
		
		// PICTURE LABEL
		pictureLabel = new JLabel();
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLabel.setBounds((int) this.getWidth()/2 - 250, 10, 500, 500);
		pictureLabel.setIcon(SlotPuzzleGame4.scaleImage(getWinLosePicture(), pictureLabel.getWidth(), pictureLabel.getHeight()));
		contentPane.add(pictureLabel);
		
		// WIN LOSE LASBEL
		winLoseLabel = new JLabel(getWinLoseText());
		winLoseLabel .setFont(new Font("Kohinoor Gujarati", Font.BOLD, 18));
		winLoseLabel .setHorizontalAlignment(SwingConstants.CENTER);
		winLoseLabel .setBounds(this.getWidth()/2 - 305/2, pictureLabel.getY() + 520, 305, 36);
		contentPane.add(winLoseLabel) ;
		
		// PLAY AGAIN BUTTON
		ActionListener playAgainButtonListener = new PlayAgainButtonListener();
		playAgainButton = new JButton("Play Again");
		playAgainButton .setForeground(Color.RED);
		playAgainButton .setFont(new Font("Kohinoor Gujarati", Font.BOLD, 13));
		playAgainButton .setBounds(this.getWidth()/2 - 105/2, winLoseLabel.getY() + 46, 105, 36);
		playAgainButton.addActionListener(playAgainButtonListener);
		contentPane.add(playAgainButton);
		
		
		

		
	}
}
