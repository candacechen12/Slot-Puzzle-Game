import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


/**
 * Slot Puzzle Game Class that runs GUI which displays 3 slots that user can spin and stop and use to complete puzzle with varying difficulties
 * @author Candace Chen
 *
 */
public class SlotPuzzleGame4 extends JFrame {

	

	/**
	 * ---------------------
	 * Instance Variables
	 * ---------------------
	 */
	
	/*
	 * Standard Puzzle Setup
	 */
	
	// Final variable
	static int LABEL_SIZE = 90;
	final static Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Content Pane
	protected JPanel contentPane;
	
	// Timer
	int INITIAL_TIME; // 2 minutes
	int tippingPointIndex = 0;
	protected Timer timer;
	protected JLabel timerLabel;
	
	// Game Version
	protected static boolean isSquare = false;
	protected static boolean isLandscape = false;
	protected static boolean isPortrait = false;
	
	// Boolean to keep playing main music
	protected Boolean mainMusicPlaying = true;


	//Base Image
	protected JLabel baseImageLabel;
	protected ImageIcon baseImage;
	protected JButton enlargeImageButton;
	
	//Pieces ImageIcons & correct puzzle order
	protected ArrayList<ImageIcon> myImageIcons;
	
	/*
	 * Tipping Points
	 */
	
	//Tipping point names
	private final String[] tippingPointNames = {"cherry.png", "apple.png", "orange.png", "diamond.png", "seven.png", "bomb.png"};
	
	// Tipping point images
	protected static ImageIcon[] tippingPoints;
	
	// Tipping point Labels
	protected JLabel tippingPoint1Label;
	protected JLabel tippingPoint1CountLabel;
	
	protected JLabel tippingPoint2Label;
	protected JLabel tippingPoint2CountLabel;
	
	protected JLabel tippingPoint3Label;
	protected JLabel tippingPoint3CountLabel;
	
	protected JLabel tippingPoint4Label;
	protected JLabel tippingPoint4CountLabel;
	
	protected JLabel tippingPoint5Label;
	protected JLabel tippingPoint5CountLabel;
	
	// Tipping point counts
	protected int tippingPoint1Count;
	protected int tippingPoint2Count;
	protected int tippingPoint3Count;
	protected int tippingPoint4Count;
	protected int tippingPoint5Count;
	
	// Map for tipping point and images 
	Map<ImageIcon, JLabel> imageToLabel;
	
	
	/*
	 * Slot Machine
	 */
	
	//Slot Machine Pane
	protected JPanel slotMachinePanel;
	
	//Title for Slot Machine
	protected JLabel slotMachineTitleLabel;
	
	//Buttons for Slots
	protected JButton slotMachineButton1;
	protected JButton slotMachineButton2;
	protected JButton slotMachineButton3;
	
	//Spin & StopButtons
	protected JButton spinButton;
	protected JButton stopButton;
	
	// Boolean indicating if Slot machine was spun already
	protected Boolean spun = false;
	
	// Boolean indicating Slot Machine was stopped -- prevent user from selecting image from spinning slot machine
	protected Boolean stopped = false;
	
	//Which button has been used --> holds button that was selected
	protected JButton usedButton = new JButton();
		
	// Boolean indicating if SlotMachineButton has been selected already
	protected boolean selected;
	
	// Boolean indicating if SlotMachineButton has been used already this turn
	protected boolean slotMachineButton1Used;
	protected boolean slotMachineButton2Used;
	protected boolean slotMachineButton3Used;
		
	// Label indicating whether piece has been used already this turn
	protected JLabel pieceUsedLabel;
	
	// Selected image to be used in puzzle area
	protected Icon imageToUse;
		
	
	// Runnable Object & Thread for running Slot Machine 
	SlotMachineRunnable r1 = new SlotMachineRunnable();
	Thread t1 = new Thread(r1);
	
	
	/*
	 * Puzzle Area
	 */
	
	//Puzzle Piece Buttons
	protected JButton puzzlePiece1;
	protected JButton puzzlePiece2;
	protected JButton puzzlePiece3;
	protected JButton puzzlePiece4;
	protected JButton puzzlePiece5;
	protected JButton puzzlePiece6;
	protected JButton puzzlePiece7;
	protected JButton puzzlePiece8;
	protected JButton puzzlePiece9;
	protected JButton puzzlePiece10;
	protected JButton puzzlePiece11;
	protected JButton puzzlePiece12;
	protected JButton puzzlePiece13;
	protected JButton puzzlePiece14;
	protected JButton puzzlePiece15;
	
	
	
	/*
	 *Game Over
	 */
	
	// game over boolean
	protected boolean gameOver = false;
	

	/**-----------------------
	 * Getters & Setters
	 * -----------------------
	 */

	
	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	/* ----------
	 * Images
	 * ----------
	 */

	/**
	 * @return the baseImage
	 */
	public ImageIcon getBaseImage() {
		return this.baseImage;
	}

	/**
	 * @param baseImage the baseImage to set
	 */
	public void setBaseImage(ImageIcon baseImage) {
		this.baseImage = baseImage;
	}
	

	/**
	 * @return the myImageIcons
	 */
	public ArrayList<ImageIcon> getMyImageIcons() {
		return myImageIcons;
	}

	/**
	 * @param myImageIcons the myImageIcons to set
	 */
	public void setMyImageIcons(ArrayList<ImageIcon> myImageIcons) {
		this.myImageIcons = myImageIcons;
	}
	
	/* ----------------
	 * Tipping Points
	 * ----------------
	 */
	
	
	/**
	 * @return the tippingPoints
	 */
	public ImageIcon[] getTippingPoints() {
		return tippingPoints;
	}

	/**
	 * @param tippingPoints the tippingPoints to set
	 */
	public void setTippingPoints(ImageIcon[] tippingPoints) {
		this.tippingPoints = tippingPoints;
	}
	
	
	/**
	 * @return the tippingPoint1Label
	 */
	public JLabel getTippingPoint1Label() {
		return tippingPoint1Label;
	}

	/**
	 * @param tippingPoint1Label the tippingPoint1Label to set
	 */
	public void setTippingPoint1Label(JLabel tippingPoint1Label) {
		this.tippingPoint1Label = tippingPoint1Label;
	}

	/**
	 * @return the tippingPoint1CountLabel
	 */
	public JLabel getTippingPoint1CountLabel() {
		return tippingPoint1CountLabel;
	}

	/**
	 * @param tippingPoint1CountLabel the tippingPoint1CountLabel to set
	 */
	public void setTippingPoint1CountLabel(JLabel tippingPoint1CountLabel) {
		this.tippingPoint1CountLabel = tippingPoint1CountLabel;
	}
	
	
	/* ---------------------
	 * Slot Machine
	 * ---------------------
	 */

	/**
	 * @return the titleLabel
	 */
	public JLabel getSlotMachineTitleLabel() {
		return this.slotMachineTitleLabel;
	}


	/**
	 * @param titleLabel the titleLabel to set
	 */
	public void setSlotMachineTitleLabel(JLabel titleLabel) {
		this.slotMachineTitleLabel= titleLabel;
	}


	/* ---------------------
	 * Spin & Stop Buttons
	 * ---------------------
	 */
	
	/**
	 * @return the spinButton
	 */
	public JButton getSpinButton() {
		return spinButton;
	}


	/**
	 * @param spinButton the spinButton to set
	 */
	public void setSpinButton(JButton spinButton) {
		this.spinButton = spinButton;
	}

	
	/**
	 * @return the stopButton
	 */
	public JButton getStopButton() {
		return stopButton;
	}

	
	/**
	 * @param stopButton the stopButton to set
	 */
	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}
	


	/* ---------------------
	 * Slot Machine Buttons
	 * ---------------------
	 */
	
	
	/**
	 * @return the slotMachineButton1
	 */
	public JButton getSlotMachineButton1() {
		return slotMachineButton1;
	}

	/**
	 * @param slotMachineButton1 the slotMachineButton1 to set
	 */
	public void setSlotMachineButton1(JButton slotMachineButton1) {
		this.slotMachineButton1 = slotMachineButton1;
	}

	/**
	 * @return the slotMachineButton2
	 */
	public JButton getSlotMachineButton2() {
		return slotMachineButton2;
	}

	/**
	 * @param slotMachineButton2 the slotMachineButton2 to set
	 */
	public void setSlotMachineButton2(JButton slotMachineButton2) {
		this.slotMachineButton2 = slotMachineButton2;
	}

	/**
	 * @return the slotMachineButton3
	 */
	public JButton getSlotMachineButton3() {
		return slotMachineButton3;
	}

	/**
	 * @param slotMachineButton3 the slotMachineButton3 to set
	 */
	public void setSlotMachineButton3(JButton slotMachineButton3) {
		this.slotMachineButton3 = slotMachineButton3;
	}
	
	/**
	 * @return the usedButton
	 */
	public JButton getUsedButton() {
		return usedButton;
	}

	/**
	 * @param usedButton the usedButton to set
	 */
	public void setUsedButton(JButton usedButton) {
		this.usedButton = usedButton;
	}
	
	
	/**
	 * @return the selected
	 */
	public boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
	/**
	 * @return the slotMachineButton1Used
	 */
	public boolean getSlotMachineButton1Used() {
		return slotMachineButton1Used;
	}

	/**
	 * @param slotMachineButton1Used the slotMachineButton1Used to set
	 */
	public void setSlotMachineButton1Used(boolean slotMachineButton1Used) {
		this.slotMachineButton1Used = slotMachineButton1Used;
	}

	/**
	 * @return the slotMachineButton2Used
	 */
	public boolean getSlotMachineButton2Used() {
		return slotMachineButton2Used;
	}

	/**
	 * @param slotMachineButton2Used the slotMachineButton2Used to set
	 */
	public void setSlotMachineButton2Used(boolean slotMachineButton2Used) {
		this.slotMachineButton2Used = slotMachineButton2Used;
	}

	/**
	 * @return the slotMachineButton3Used
	 */
	public boolean getSlotMachineButton3Used() {
		return slotMachineButton3Used;
	}

	/**
	 * @param slotMachineButton3Used the slotMachineButton3Used to set
	 */
	public void setSlotMachineButton3Used(boolean slotMachineButton3Used) {
		this.slotMachineButton3Used = slotMachineButton3Used;
	}

	/**
	 * @return the imageToUse
	 */
	public Icon getImageToUse() {
		return imageToUse;
	}

	/**
	 * @param imageToUse the imageToUse to set
	 */
	public void setImageToUse(Icon imageToUse) {
		this.imageToUse = imageToUse;
	}

	
	/* -----------------
	 * Puzzle Pieces
	 * -----------------
	 */
	
	/**
	 * @return the puzzlePiece1
	 */
	public JButton getPuzzlePiece1() {
		return this.puzzlePiece1;
	}

	/**
	 * @param puzzlePiece1 the puzzlePiece1 to set
	 */
	public void setPuzzlePiece1(JButton puzzlePiece1) {
		this.puzzlePiece1 = puzzlePiece1;
	}

	/**
	 * @return the puzzlePiece2
	 */
	public JButton getPuzzlePiece2() {
		return this.puzzlePiece2;
	}

	/**
	 * @param puzzlePiece2 the puzzlePiece2 to set
	 */
	public void setPuzzlePiece2(JButton puzzlePiece2) {
		this.puzzlePiece2 = puzzlePiece2;
	}

	/**
	 * @return the puzzlePiece3
	 */
	public JButton getPuzzlePiece3() {
		return this.puzzlePiece3;
	}

	/**
	 * @param puzzlePiece3 the puzzlePiece3 to set
	 */
	public void setPuzzlePiece3(JButton puzzlePiece3) {
		this.puzzlePiece3 = puzzlePiece3;
	}

	/**
	 * @return the puzzlePiece4
	 */
	public JButton getPuzzlePiece4() {
		return this.puzzlePiece4;
	}

	/**
	 * @param puzzlePiece4 the puzzlePiece4 to set
	 */
	public void setPuzzlePiece4(JButton puzzlePiece4) {
		this.puzzlePiece4 = puzzlePiece4;
	}

	/**
	 * @return the puzzlePiece5
	 */
	public JButton getPuzzlePiece5() {
		return this.puzzlePiece5;
	}

	/**
	 * @param puzzlePiece5 the puzzlePiece5 to set
	 */
	public void setPuzzlePiece5(JButton puzzlePiece5) {
		this.puzzlePiece5 = puzzlePiece5;
	}

	/**
	 * @return the puzzlePiece6
	 */
	public JButton getPuzzlePiece6() {
		return puzzlePiece6;
	}

	/**
	 * @param puzzlePiece6 the puzzlePiece6 to set
	 */
	public void setPuzzlePiece6(JButton puzzlePiece6) {
		this.puzzlePiece6 = puzzlePiece6;
	}
	
	
	
	/**
	 * @return the puzzlePiece7
	 */
	public JButton getPuzzlePiece7() {
		return this.puzzlePiece7;
	}

	/**
	 * @param puzzlePiece7 the puzzlePiece7 to set
	 */
	public void setPuzzlePiece7(JButton puzzlePiece7) {
		this.puzzlePiece7 = puzzlePiece7;
	}

	/**
	 * @return the puzzlePiece8
	 */
	public JButton getPuzzlePiece8() {
		return this.puzzlePiece8;
	}

	/**
	 * @param puzzlePiece8 the puzzlePiece8 to set
	 */
	public void setPuzzlePiece8(JButton puzzlePiece8) {
		this.puzzlePiece8 = puzzlePiece8;
	}

	/**
	 * @return the puzzlePiece9
	 */
	public JButton getPuzzlePiece9() {
		return this.puzzlePiece9;
	}

	/**
	 * @param puzzlePiece9 the puzzlePiece9 to set
	 */
	public void setPuzzlePiece9(JButton puzzlePiece9) {
		this.puzzlePiece9 = puzzlePiece9;
	}

	
	/**
	 * @return the puzzlePiece10
	 */
	public JButton getPuzzlePiece10() {
		return puzzlePiece10;
	}

	/**
	 * @param puzzlePiece10 the puzzlePiece10 to set
	 */
	public void setPuzzlePiece10(JButton puzzlePiece10) {
		this.puzzlePiece10 = puzzlePiece10;
	}

	/**
	 * @return the puzzlePiece11
	 */
	public JButton getPuzzlePiece11() {
		return puzzlePiece11;
	}

	/**
	 * @param puzzlePiece11 the puzzlePiece11 to set
	 */
	public void setPuzzlePiece11(JButton puzzlePiece11) {
		this.puzzlePiece11 = puzzlePiece11;
	}

	/**
	 * @return the puzzlePiece12
	 */
	public JButton getPuzzlePiece12() {
		return puzzlePiece12;
	}

	/**
	 * @param puzzlePiece12 the puzzlePiece12 to set
	 */
	public void setPuzzlePiece12(JButton puzzlePiece12) {
		this.puzzlePiece12 = puzzlePiece12;
	}
	
	
	/**
	 * @return the puzzlePiece13
	 */
	public JButton getPuzzlePiece13() {
		return puzzlePiece13;
	}

	/**
	 * @param puzzlePiece13 the puzzlePiece13 to set
	 */
	public void setPuzzlePiece13(JButton puzzlePiece13) {
		this.puzzlePiece13 = puzzlePiece13;
	}

	/**
	 * @return the puzzlePiece14
	 */
	public JButton getPuzzlePiece14() {
		return puzzlePiece14;
	}

	/**
	 * @param puzzlePiece14 the puzzlePiece14 to set
	 */
	public void setPuzzlePiece14(JButton puzzlePiece14) {
		this.puzzlePiece14 = puzzlePiece14;
	}

	/**
	 * @return the puzzlePiece15
	 */
	public JButton getPuzzlePiece15() {
		return puzzlePiece15;
	}

	/**
	 * @param puzzlePiece15 the puzzlePiece15 to set
	 */
	public void setPuzzlePiece15(JButton puzzlePiece15) {
		this.puzzlePiece15 = puzzlePiece15;
	}
	
	
	/**--------------
	 * OTHER METHODS
	 * --------------
	 */

	/**
	 * @param orig
	 * @return multi-line label
	 */
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	
	/*
	 * Timer Methods
	 */
	
	/**
	 * Gets remaining time in format of mins:secs
	 * @return remainingtime
	 */
	
	public String getRemainingTime() {
		
		// decrements by 1 second
		INITIAL_TIME -= 1000;
		
		// get minutes and seconds
		int minutes = (int)((INITIAL_TIME/60000) % 60);
		int seconds = (int)((INITIAL_TIME/1000) % 60);
		
		// if there is no more time, game over
		if(minutes == 0 && seconds == 0) {
			
			// game over
			gameOver("time");	
		}
		
		// add "0" before the seconds if it is in the single digits
		if(seconds < 10) {
			return minutes + ":0" + seconds;
		}
		
		return minutes + ":" + seconds;
		
	}

	
	/* --------------
	 * Board Setup
	 * --------------
	 */
	
	
	/**
	 * Checks Version / Size of the base image
	 * Sets corresponding boolean to true
	 */
	
	public static void checkVersion() {
		if(Chooser.getVersion().equals("Square")) {
			isSquare = true;
		}
		else if(Chooser.getVersion().equals("Landscape")) {
			isLandscape = true;
		}
		else {
			isPortrait = true;
		}
}
	
	
	/**
	 * Sets up board depending on version
	 * Square: 9 pieces - set pieces 10,11,12,13,14,& 15 invisible
	 * Portrait: 12 pieces with 4 rows & 3 columns - set pieces 13,14,& 15 invisible
	 * Landscape: 15 pieces with 3 rows and 5 columns - change location of pieces
	 */
	
	public void setUpBoard(){
		
		
		// square only has 9 piece - default locations
		if(isSquare) {
			puzzlePiece10.setVisible(false);
			puzzlePiece11.setVisible(false);
			puzzlePiece12.setVisible(false);
			puzzlePiece13.setVisible(false);
			puzzlePiece14.setVisible(false);
			puzzlePiece15.setVisible(false);
		}
		
		// portrait only has 12 pieces - default locations
		if(isPortrait) {
			puzzlePiece13.setVisible(false);
			puzzlePiece14.setVisible(false);
			puzzlePiece15.setVisible(false);
		}
		
	
		// landscape has ALL 15 pieces - rearrange locations
		if(isLandscape) {
			
			// first pieces coordinates
			int X_LOC = 400 - (5*LABEL_SIZE)/2; // middle of window - 3.5 puzzle pieces
			int Y_LOC = slotMachinePanel.getY() + slotMachinePanel.getHeight() + 20; // 20 below slotMachinePanel
			
			puzzlePiece1.setLocation(X_LOC, Y_LOC);
			puzzlePiece2.setLocation(X_LOC + LABEL_SIZE - 1, Y_LOC);
			puzzlePiece3.setLocation(X_LOC + 2*(LABEL_SIZE -1), Y_LOC);
			puzzlePiece4.setLocation(X_LOC + 3*(LABEL_SIZE -1), Y_LOC);
			puzzlePiece5.setLocation(X_LOC + 4*(LABEL_SIZE - 1), Y_LOC);
			puzzlePiece6.setLocation(X_LOC, Y_LOC + LABEL_SIZE - 1);
			puzzlePiece7.setLocation(X_LOC + LABEL_SIZE - 1, Y_LOC + LABEL_SIZE - 1);
			puzzlePiece8.setLocation(X_LOC + 2*(LABEL_SIZE -1), Y_LOC + LABEL_SIZE - 1);
			puzzlePiece9.setLocation(X_LOC + 3*(LABEL_SIZE -1), Y_LOC + LABEL_SIZE -1);
			puzzlePiece10.setLocation(X_LOC + 4*(LABEL_SIZE - 1), Y_LOC + LABEL_SIZE - 1);
			puzzlePiece11.setLocation(X_LOC, Y_LOC + 2*(LABEL_SIZE - 1));
			puzzlePiece12.setLocation(X_LOC + LABEL_SIZE - 1,  Y_LOC + 2*(LABEL_SIZE - 1));
			puzzlePiece13.setLocation(X_LOC + 2*(LABEL_SIZE -1),  Y_LOC + 2*(LABEL_SIZE - 1));
			puzzlePiece14.setLocation(X_LOC + 3*(LABEL_SIZE -1),  Y_LOC + 2*(LABEL_SIZE - 1));
			puzzlePiece15.setLocation(X_LOC + 4*(LABEL_SIZE - 1), Y_LOC + 2*(LABEL_SIZE - 1));
	
		}
			
	}
	
	
	/**
	 *  BaseImage setup Method
	 *  Sets up baseImageLabel so baseImage is correctly shaped based on version
	 */
	public void setUpBaseImage() {
		
		// square
		if(isSquare) {
			baseImageLabel.setBounds(450, 10, 250, 250);
		}
		
		// landscape
		if(isLandscape) {
			baseImageLabel.setBounds(420, 35, 333, 200);
		}
		
		// portrait
		if(isPortrait) {
			baseImageLabel.setBounds(450, 10, 187, 250);
		}
		
		// set icon to scaled ImageIcon
		baseImageLabel.setIcon(scaleImage(getBaseImage(), (int)baseImageLabel.getWidth(), (int)baseImageLabel.getHeight()));
	}
	
	
	/**
	 * Splits Images depending on version
	 * Easy: 3 pieces
	 * Medium: 6 pieces
	 * Hard: 9 pieces
	 */
	
	public ArrayList<ImageIcon> splitImage() {
		// assigns base image to new ImageIcon variable
		ImageIcon base = getBaseImage();
		
		// create BufferedImage object so can take subimages
		BufferedImage bi = new BufferedImage(
			    base.getIconWidth(),
			    base.getIconHeight(),
			    BufferedImage.TYPE_INT_ARGB);
		
		// paint graphics of base onto BufferedImage object
		Graphics g = bi.createGraphics();
		base.paintIcon(null, g, 0, 0);
		
		// height & width
		int height = base.getIconHeight();
		int width = base.getIconWidth(); 
		g.dispose(); // do not need anymore
		
		// ArrayList that holds the split images, the value returned
		ArrayList<ImageIcon> myImages = new ArrayList<ImageIcon>();
		
		// will store the height and width of each sub-image
		int newHeight;
		int newWidth;
		
		/*
		 *  Splits baseImage depending on version
		 */
	
		// Square 3 rows | 3 columns
		if(isSquare) {
			newHeight = height/3; 
			newWidth = width/3;
			//rows
			for(int j = 0; j<=2*(newHeight); j += newHeight) {
				//columns
				for(int i = 0; i<=2*newWidth; i+=newWidth) {
					BufferedImage splitImage =  bi.getSubimage(i,j, newWidth, newHeight);;
					Image img = (Image) splitImage;
					ImageIcon imgIcon = new ImageIcon(img);
					myImages.add(scaleImage(imgIcon, LABEL_SIZE, LABEL_SIZE));
				}
			}
		}
		
		
		// Portrait 4 rows | 3 columns
		if(isPortrait) {
			newHeight = height/4; 
			newWidth = width/3;
			//rows
			for(int j = 0; j<=3*(newHeight); j += newHeight) {
				//columns
				for(int i = 0; i<=2*newWidth; i+=newWidth) {
					BufferedImage splitImage =  bi.getSubimage(i,j, newWidth, newHeight);;
					Image img = (Image) splitImage;
					ImageIcon imgIcon = new ImageIcon(img);
					myImages.add(scaleImage(imgIcon, LABEL_SIZE, LABEL_SIZE));
				}
			}
			
		}
		
		// Landscape  3 rows | 5 columns
		if(isLandscape) {
			newHeight = height/3; 
			newWidth = width/5;
			
			//rows
			for(int j = 0; j<=2*newHeight; j+=newHeight) {
				//columns
				for(int i = 0; i<=4*newWidth; i+= newWidth) {
					BufferedImage splitImage = bi.getSubimage(i ,j, newWidth, newHeight);
					Image img = (Image) splitImage;
					ImageIcon imgIcon = new ImageIcon(img);
					myImages.add(scaleImage(imgIcon, LABEL_SIZE, LABEL_SIZE));
	
				}
			}
			
		}

		// ArrayList of split images from left -> right & top -> bottom
		return myImages;
			
	}
	
	
	/*
	 * Tipping points
	 */
	
	/**
	 * Creates array of tipping point ImageIcons and return it
	 * @return tipping points array
	 */
		public ImageIcon[] setUpTippingPoints() {
			// Array to hold ImageIcons
			ImageIcon[] images = new ImageIcon[tippingPointNames.length];
			ImageIcon[] tipping;
			
			// create set to put image icons into and prevent duplicates
			Set<ImageIcon> imageSet = new HashSet<ImageIcon>();
		
			// initialize ImageIcons & put in array
			for(int i = 0; i < tippingPointNames.length; i++) {
				URL url = SlotPuzzleGame4.class.getResource("/" + tippingPointNames[i]);
				images[i] = scaleImage(new ImageIcon(url) , LABEL_SIZE, LABEL_SIZE);
			}
		
			// square has 3 tipping points
			if(isSquare) {
				tipping = new ImageIcon[3];
				// add 3 random ImageIcons to set
				while(imageSet.size() < 3) {
					imageSet.add(images[(int)(Math.random()*images.length)]);
				}
			}
		
			// portrait has 4 tipping points
			else if(isPortrait) {
				tipping = new ImageIcon[4];
				// add 4 random ImageIcons to set
				while(imageSet.size() < 4) {
					imageSet.add(images[(int)(Math.random()*images.length)]);	
				}
			}
		
			// landscape has 5 tipping points
			else {
				tipping = new ImageIcon[5];
				// add 5 random ImageIcons to set
				while(imageSet.size() < 5) {
					imageSet.add(images[(int)(Math.random()*images.length)]);
				}
			}
		
			// set has no duplicates -- add ImageIcons in set to array that will be returned
			int counter = 0;
			for(ImageIcon i : imageSet) {
				tipping[counter] = i;
				counter++;
			}
			
			// return array of tipping point ImageIcons (NO DUPLICATES)
			return tipping;
		
	}
	
	/**
	 * Sets up tipping point labels depending on version
	 * Puts tipping points below puzzle board w/ count labels
	 */
	
	public void setUpTippingPointLabels() {
		
		// SQUARE IS AUTOMATICALLY SET UP ALREADY
		
		// portrait - shift all up to avoid overlap w/ instructionsButton
		// spacing is LABEL_SIZE
		if(isPortrait) {
			tippingPoint1Label.setLocation(puzzlePiece11.getX() - 3*LABEL_SIZE, puzzlePiece11.getY() + LABEL_SIZE + 20);
			tippingPoint1CountLabel.setLocation(tippingPoint1Label.getX() + LABEL_SIZE/2 - 3, tippingPoint1Label.getY() + LABEL_SIZE + 3);
			tippingPoint2Label.setLocation(puzzlePiece11.getX() - LABEL_SIZE, puzzlePiece11.getY() + LABEL_SIZE + 20);
			tippingPoint2CountLabel.setLocation(tippingPoint2Label.getX() + LABEL_SIZE/2 - 3, tippingPoint2Label.getY() + LABEL_SIZE + 3);
			tippingPoint3Label.setLocation(puzzlePiece11.getX() + LABEL_SIZE , puzzlePiece11.getY() + LABEL_SIZE +  20);
			tippingPoint3CountLabel.setLocation(tippingPoint3Label.getX() + LABEL_SIZE/2 - 3, tippingPoint3Label.getY() + LABEL_SIZE + 3);
			tippingPoint4Label.setLocation(puzzlePiece11.getX() + 3*LABEL_SIZE, puzzlePiece11.getY() + LABEL_SIZE + 20);
			tippingPoint4CountLabel.setLocation(tippingPoint4Label.getX() + LABEL_SIZE/2 - 3, tippingPoint4Label.getY() + LABEL_SIZE + 3);
		}
		
		// landscape
		if(isLandscape) {

			int spacing = 70; // spacing is smaller to shorten window width
			tippingPoint1Label.setLocation(puzzlePiece13.getX() - 2*LABEL_SIZE - 2*spacing, puzzlePiece11.getY() + LABEL_SIZE + 30);
			tippingPoint1CountLabel.setLocation(tippingPoint1Label.getX() + LABEL_SIZE/2 - 3, tippingPoint1Label.getY() + LABEL_SIZE + 3);
			tippingPoint2Label.setLocation(puzzlePiece13.getX() - LABEL_SIZE - spacing, puzzlePiece11.getY() + LABEL_SIZE + 30);
			tippingPoint2CountLabel.setLocation(tippingPoint2Label.getX() + LABEL_SIZE/2 - 3, tippingPoint2Label.getY() + LABEL_SIZE + 3);
			tippingPoint3Label.setLocation(puzzlePiece13.getX(), puzzlePiece13.getY() + LABEL_SIZE + 30);
			tippingPoint3CountLabel.setLocation(tippingPoint3Label.getX() + LABEL_SIZE/2 - 3, tippingPoint3Label.getY() + LABEL_SIZE + 3);
			tippingPoint4Label.setLocation(puzzlePiece13.getX() + LABEL_SIZE + spacing, puzzlePiece15.getY() + LABEL_SIZE + 30);
			tippingPoint4CountLabel.setLocation(tippingPoint4Label.getX() + LABEL_SIZE/2 - 3, tippingPoint4Label.getY() + LABEL_SIZE + 3);
			tippingPoint5Label.setLocation(puzzlePiece13.getX() + 2*LABEL_SIZE + 2*spacing, puzzlePiece15.getY() + LABEL_SIZE + 30);
			tippingPoint5CountLabel.setLocation(tippingPoint5Label.getX() + LABEL_SIZE/2 - 3, tippingPoint5Label.getY() + LABEL_SIZE + 3);
		}
			
	}
	
	
	/**
	 * Inserts tipping point ImageIcon
	 * For square & portrait: every 30 seconds, adds a tipping point image to the ArrayList slot machine draws from (can be same image)
	 * For landscape: every 20 seconds, adds a tipping point image to the ArrayList slot machine draws from (can be same image)
	 */
	public void insertTippingPoint() {
		
		// find out the seconds
		int seconds = (int)((INITIAL_TIME/1000) % 1000);
		
		// square & portrait
		// if it has been 30 seconds, add tipping points to ArrayList Slot Machine draws from 
		if(isSquare || isPortrait) {
			if(seconds % 30 == 0 && INITIAL_TIME != 120000) {
				myImageIcons.add(tippingPoints[(int)(Math.random()*tippingPoints.length)]);	
				}
		}
		// landscape - more pieces
		// if it has been 20 secs, add tipping points to ArrayList Slot Machine draws from
		else {
			if(seconds % 20 == 0 && INITIAL_TIME != 180000) {
				myImageIcons.add(tippingPoints[(int)(Math.random()*tippingPoints.length)]);	
			}
		}
		
		}
		
	
	/**
	 * Changes tipping point counter (tippingPointCountLabel) if tipping point was received from Slot Machine spin
	 * Plays sounds corresponding to number of tipping points accumulated
	 * To be used in checkForTippingPoints (method below)
	 * @param image  the ImageIcon appearing in SlotMachine
	 */
	public void counterChange(ImageIcon image) {
		
		// stops 'MAIN_MUSIC' sound effect
		if(SoundEffects.MAIN_MUSIC.clip.isRunning()) {
		SoundEffects.MAIN_MUSIC.stop();
		}
		
		// play 'counter' sound first 
		SoundEffects.COUNTER_NOTIF.play();
		 
		// change from 0 -> 1
		if(imageToLabel.get(image).getText().equals("0")) { 
			imageToLabel.get(image).setText("1"); 
			
			// ensures both ONE COUNTER & TWO COUNTER sound effects aren't playing to play 'ONE COUNTER' & game is NOT over
			// in case another tipping point has 2 already, does not play then
			if(!SoundEffects.TWO_COUNTER.clip.isRunning() && !SoundEffects.ONE_COUNTER.clip.isRunning() && !gameOver) {
				SoundEffects.ONE_COUNTER.play();
			}
		}
		
		// change from 1 -> 2
		 else if(imageToLabel.get(image).getText().equals("1")) { 
			 imageToLabel.get(image).setText("2");
			
			 // stops 'ONE COUNTER' sound effect
			 SoundEffects.ONE_COUNTER.stop();
			
			 // plays 'TWO COUNTER' sound effect if not playing already & game is NOT over
			 if(!SoundEffects.TWO_COUNTER.clip.isRunning() && !gameOver) {
				SoundEffects.TWO_COUNTER.play();
				}
		 }
		// change from 2 -> 3
		 else if(imageToLabel.get(image).getText().equals("2")) { 
			 imageToLabel.get(image).setText("3");
			 
			 }		
	}
	
	
	/**
	 * Check for tipping points
	 * Increments count by 1 for tipping point if it was received through slot machine
	 * ends game if 3 tipping points are accumulated
	 */
	 public void checkForTippingPoints() {
		 for(ImageIcon i : tippingPoints) {
			
			 // check if any of the slot machines display a tipping point - yes, then increment
			 
			 // slotMachineButton1
			 if(i.equals(slotMachineButton1.getIcon())) {
				 counterChange(i);
			 }
			 
			// slotMachineButton2
			 if(i.equals(slotMachineButton2.getIcon())) {
				counterChange(i);
			 }
			 
			// slotMachineButton3
			if(i.equals(slotMachineButton3.getIcon())) {
				counterChange(i);
			}
			
			// if the count is 3 - game over
			if(imageToLabel.get(i).getText().equals("3")) {
				gameOver("tipping");
				
			 }
		 }
	 
	 }

	
	/*
	 * Scaling Images Method
	 */
	
	/**
	 * Receives ImageIcon and scales it to indicated width & height, then returns the scaled ImageIcon
	 * @param image  the ImageIcon to scale
	 * @return  scaledImage  the scaled ImageIcon
	 */
	public static ImageIcon scaleImage(ImageIcon image, int width, int height) {
		Image scalingImage = image.getImage();
		Image newImage = scalingImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		ImageIcon finalImage = new ImageIcon(newImage);
		return finalImage;
	}

	
	/**
	 * Checks if Puzzle is correct / pieces in right order depending on version
	 * @return whether board has all correct pieces in correct order
	 */
	public boolean correctCheck() {
		
		if(!myImageIcons.get(0).equals((ImageIcon)getPuzzlePiece1().getIcon())){ return false; }
		else if (!myImageIcons.get(1).equals((ImageIcon)getPuzzlePiece2().getIcon())){ return false; }
		else if (!myImageIcons.get(2).equals((ImageIcon)getPuzzlePiece3().getIcon())){ return false; }
		else if (!myImageIcons.get(3).equals((ImageIcon)getPuzzlePiece4().getIcon())){ return false; }
		else if (!myImageIcons.get(4).equals((ImageIcon)getPuzzlePiece5().getIcon())){ return false; }
		else if (!myImageIcons.get(5).equals((ImageIcon)getPuzzlePiece6().getIcon())){ return false; }
		else if (!myImageIcons.get(6).equals((ImageIcon)getPuzzlePiece7().getIcon())){ return false; }
		else if (!myImageIcons.get(7).equals((ImageIcon)getPuzzlePiece8().getIcon())){ return false; }
		else if (!myImageIcons.get(8).equals((ImageIcon)getPuzzlePiece9().getIcon())){ return false; }
		
		// landscape & portrait have pieces 10, 11, & 12
		if(isLandscape || isPortrait) {
			if(!myImageIcons.get(9).equals((ImageIcon)getPuzzlePiece10().getIcon())){ return false; }
			else if (!myImageIcons.get(10).equals((ImageIcon)getPuzzlePiece11().getIcon())){ return false; }
			else if (!myImageIcons.get(11).equals((ImageIcon)getPuzzlePiece12().getIcon())){ return false; }
			
			// landscape have pieces 13, 14, & 15
			if(isLandscape) {
				if(!myImageIcons.get(12).equals((ImageIcon)getPuzzlePiece13().getIcon())){ return false;}
				else if (!myImageIcons.get(13).equals((ImageIcon)getPuzzlePiece14().getIcon())){ return false; }
				else if (!myImageIcons.get(14).equals((ImageIcon)getPuzzlePiece15().getIcon())){ return false; }
			}
		}
		
		return true;
	}
	
	
	
	/**
	 * Resets version when ending game
	 */
	public void reset() {
		isSquare = false;
		isPortrait = false;
		isLandscape = false;
	}
	
	
	/**
	 * Stops all the music
	 * Used when game is over
	 */
	public void gameOver(String winLoseType) {
		
		// set gameOver to true
		gameOver = true;
		
		PlayAgain.setWinLoseType(winLoseType);
		
		// stop ALL music
		SoundEffects.MAIN_MUSIC.stop();
		SoundEffects.ONE_COUNTER.stop();
		SoundEffects.TWO_COUNTER.stop();
		
		// stop timer
		timer.stop();
		
		// reset versions
		reset();
		
		// open PlayAgain window
		PlayAgain.play();
	}
	
	
	/**
	 * Play Method
	 * Initializes Puzzle Game
	 */

	public static void play() {
		
		// Checks version immediately
		checkVersion();
		
		//instantiate image chosen by user from Chooser
		ImageIcon fullImage = Chooser.getSelectedImage();
		
		// open window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlotPuzzleGame4 frame = new SlotPuzzleGame4(fullImage);
					frame.setVisible(true);
					
					// set frame as gameWindow in PlayAgain class -- closes window when user clicks playAgainButton
					PlayAgain.setGameWindow(frame);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}



	/**------------------
	 * Action Listeners
	 * ------------------
	 */
	
	
	/* ---------------------
	 * Spin Button Listener
	 * ---------------------
	 */
	protected class SpinButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// checks if game is over, if it is then prevent action
			if(gameOver) {return;} 
			
			// starts timer at first spin
			if(!spun) {
				timer.start();
			}
			
			// slot machine has not been stopped yet, so images CANNOT be used
			stopped = false;
			
			
			//After first spin, set all borders to null
			if(getSelected() == true) {
				getSlotMachineButton1().setBorder(null);
				getSlotMachineButton2().setBorder(null);
				getSlotMachineButton3().setBorder(null);
				setImageToUse(null);
			}
			
			// get rid of pieceUsedLabel
			pieceUsedLabel.setVisible(false);
			
			// slot machine has been spun -- timer already starts
			spun = true;
			
			// if thread is already running (press spin button 2 times without pressing stop), stops thread and regenerates it
			if(t1.isAlive()) {
				r1.setRunning(false);
				t1.interrupt();
				t1 = new Thread(r1);
			}
			
			// starts thread, spin slot machine
			else {
			r1.setRunning(true);
			t1.start();
			}

		}
	}
		
	/* ---------------------
	 * Stop Button Listener
	 * ---------------------
	 */
	protected class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// checks if game is over, if it is then prevent action
			if(gameOver) {return;}
			
			// if slot machine is not running, do not do anything
			if(r1.getRunning() == false) return;
			
			// stop slot machine 
			r1.setRunning(false);
			t1.interrupt();
			
			// set stop to true, so can use pieces
			stopped = true;
			
			// make capable of restarting thread
			t1 = new Thread(r1);
			
			// set all Slot Machine buttons to usable
			setSlotMachineButton1Used(false);
			setSlotMachineButton2Used(false);
			setSlotMachineButton3Used(false);
			
			// check for tipping points and end game if needed
			checkForTippingPoints();
		}
	}
	/* --------------------------
	 * Slot Machine Buttons Listener
	 * --------------------------	
	 */
	protected class SlotMachineButtonListener implements ActionListener{
	
		
		// create yellow border object
		Border yellow = BorderFactory.createLineBorder(Color.YELLOW);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// checks if game is over, if it is then prevent action
			if(gameOver) {return;}
	
			// checks if slot machine was spun (only for 1st turn to prevent default images from being used)
			// checks if slot machine has been stopped (if not, cannot use pieces)
			if(!spun || !stopped) {
				return;
			}
			
			// if button was already selected, make all borders null
			if(getSelected() == true) {
				getSlotMachineButton1().setBorder(null);
				getSlotMachineButton2().setBorder(null);
				getSlotMachineButton3().setBorder(null);
			}
			
			// since one is selected, set to true
			setSelected(true);
			
			/**
			 * if button is selected and it has not been used this spin, set border to yellow, sets pieceUserLabel to not visible, and sets the button's icon to imageToUse
			 * if button has been used this spin already, shows pieceUsedLabel
			 */
			
			//SlotMachineButton1
			if(e.getActionCommand().equals("slotMachineButton1")) {
				
				// if piece has been used
				if(getSlotMachineButton1Used() == true) {
					// tell user piece has been used
					pieceUsedLabel.setVisible(true);
					return;
				}
				
				// hide piece used label
				pieceUsedLabel.setVisible(false);
				
				// button used is button1 (use in puzzlePieceListener)
				setUsedButton(slotMachineButton1);
				
				// set border to yellow
				getSlotMachineButton1().setBorder(yellow);
				
				// get icon and store in variable to use in puzzlePieceListener
				setImageToUse(getSlotMachineButton1().getIcon());
			}
			//SlotMachineButton2
			else if (e.getActionCommand().equals("slotMachineButton2")) {
				// if piece has been used
				if(getSlotMachineButton2Used() == true) {
					// tell user piece has been used
					pieceUsedLabel.setVisible(true);
					return;
				}
				// hide piece used label
				pieceUsedLabel.setVisible(false);
				
				// button used is button2 (used in puzzlePieceListener)
				setUsedButton(getSlotMachineButton2());
				
				// set border to yellow
				getSlotMachineButton2().setBorder(yellow);
				
				// get icon and store in variable to use in puzzlePieceListener
				setImageToUse(getSlotMachineButton2().getIcon());
			}
			//SlotMachineButton3
			else {
				
				// if piece has been used
				if(getSlotMachineButton3Used() == true) {
					// tell user piece has been used
					pieceUsedLabel.setVisible(true);
					return;
				}
				
				// hide piece used label
				pieceUsedLabel.setVisible(false);
				
				// button used is button3 (used in puzzlePieceListener)
				setUsedButton(getSlotMachineButton3());
				
				// set border to yellow
				getSlotMachineButton3().setBorder(yellow);
				
				// get icon and store in variable to use in puzzlePieceListener
				setImageToUse(getSlotMachineButton3().getIcon());
			}
			
		}
	}
	
	
	/*------------------------
	 * Puzzle Piece Listener
	 * -----------------------
	 */
	protected class PuzzlePieceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// if nothing is selected, cannot do anything
			if(getSelected() == false) {
				return;
			}
			
			
			/**
			 * setting piece clicked to icon of selected image
			 */
			
			// puzzle piece 1
			if(e.getActionCommand().equals("puzzlePiece1")) {
				puzzlePiece1.setIcon(getImageToUse());
			}
			// puzzle piece 2
			if(e.getActionCommand().equals("puzzlePiece2")) {
				puzzlePiece2.setIcon(getImageToUse());
			}
			// puzzle piece 3
			if(e.getActionCommand().equals("puzzlePiece3")) {
				puzzlePiece3.setIcon(getImageToUse());
			}
			// puzzle piece 4
			if(e.getActionCommand().equals("puzzlePiece4")) {
				puzzlePiece4.setIcon(getImageToUse());
			}
			// puzzle piece 5
			if(e.getActionCommand().equals("puzzlePiece5")) {
				puzzlePiece5.setIcon(getImageToUse());
			}
			// puzzle piece 6
			if(e.getActionCommand().equals("puzzlePiece6")) {
				puzzlePiece6.setIcon(getImageToUse());
			}
			
			// puzzle piece 7
			if(e.getActionCommand().equals("puzzlePiece7")) {
				puzzlePiece7.setIcon(getImageToUse());
			}
			// puzzle piece 8
			if(e.getActionCommand().equals("puzzlePiece8")) {
				puzzlePiece8.setIcon(getImageToUse());
			}
			// puzzle piece 9
			if(e.getActionCommand().equals("puzzlePiece9")) {
				puzzlePiece9.setIcon(getImageToUse());
			}
			// puzzle piece 10
			if(e.getActionCommand().equals("puzzlePiece10")) {
				puzzlePiece10.setIcon(getImageToUse());
			}
			// puzzle piece 11
			if(e.getActionCommand().equals("puzzlePiece11")) {
				puzzlePiece11.setIcon(getImageToUse());
			}
			// puzzle piece 12
			if(e.getActionCommand().equals("puzzlePiece12")) {
				puzzlePiece12.setIcon(getImageToUse());
			}
			// puzzle piece 13
			if(e.getActionCommand().equals("puzzlePiece13")) {
				puzzlePiece13.setIcon(getImageToUse());
			}
			// puzzle piece 14
			if(e.getActionCommand().equals("puzzlePiece14")) {
				puzzlePiece14.setIcon(getImageToUse());
			}
			// puzzle piece 15
			if(e.getActionCommand().equals("puzzlePiece15")) {
				puzzlePiece15.setIcon(getImageToUse());
			}
			
			
			/**
			 * checking which available button was used, and setting the corresponding button to used
			 */
			
			// available button 1
			if(usedButton == getSlotMachineButton1()) {
				setSlotMachineButton1Used(true);
				getSlotMachineButton1().setBorder(null);
			}
			// available button 2
			if(usedButton == getSlotMachineButton2()) {
				setSlotMachineButton2Used(true);
				getSlotMachineButton2().setBorder(null);
			}
			// available button 3
			if(usedButton == getSlotMachineButton3()) {
				setSlotMachineButton3Used(true);
				getSlotMachineButton3().setBorder(null);
			}
			
			/*
			 *  if all pieces have correct images
			 */
			
			 if(correctCheck() == true) {
				 // game over
				 gameOver("win");
			 }
			
			// resetting selected, imageToUse, and usedButton
			setSelected(false);
			setImageToUse(null);
			setUsedButton(null);
			};
			
		}
	

					
	
	/**-----------------------------------------
	 * 
	 * Slot Machine constructor + creating GUI
	 * 
	 * -----------------------------------------
	 */

	/**
	 * Create the frame.
	 */
	public SlotPuzzleGame4(ImageIcon baseImage) {
		
		// Content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//set to no layout
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// create border
		Border blackline = BorderFactory.createLineBorder(Color.black);
		
		// change label size if isPortrait
		if(isPortrait) {
			LABEL_SIZE = 80;
		}
		else {
			LABEL_SIZE = 90;
		}
		// Base Image
		this.baseImage = baseImage;
		
		// Set Images
		this.myImageIcons = splitImage();
		
		// Set tipping points
		this.tippingPoints = setUpTippingPoints();
		
		// instantiate map
		imageToLabel = new HashMap<ImageIcon, JLabel>();

		//GUI Details
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(isSquare) {
			setBounds((int) (myScreenSize.getWidth()/2) - 650 , 100 , 775, 775);
		}
		else if(isLandscape) {
			setBounds((int) (myScreenSize.getWidth()/2) - 700, 50, 800, 775);
		}
		else {
			setBounds((int) (myScreenSize.getWidth()/2) - 537, 0, 700, 780);
		}
	
		
		/*--------------------------------------------------
		 * instructionsButton - click to show instructions
		 * -------------------------------------------------
		 */
		
		JButton instructionsButton = new JButton("Instructions");
		instructionsButton.setBounds(0, this.getHeight() - 60, 120, 40);
		instructionsButton.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 15));
		instructionsButton.setHorizontalAlignment(SwingConstants.CENTER);
		instructionsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Instructions.play();	
			}
		});
		contentPane.add(instructionsButton);
		
		/*------------------------------
		 * Base Image - the image to make
		 *------------------------------
		 */
		
		baseImageLabel = new JLabel();
		setUpBaseImage();
		contentPane.add(baseImageLabel);
		
		/*
		 * Enlarge Base Image Button
		 */
		
		enlargeImageButton = new JButton("+");
		enlargeImageButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		enlargeImageButton.setBounds(baseImageLabel.getX() + baseImageLabel.getWidth() + 5, baseImageLabel.getY() + baseImageLabel.getHeight() - 40, 40, 40);
		
		// action listener
		enlargeImageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LargeImage.play();	
			}
		});
		contentPane.add(enlargeImageButton);
		
		
		/* -------
		 * Timer
		 * -------
		 */
		
		if(isSquare || isPortrait) { INITIAL_TIME  = 120000;}
		else {INITIAL_TIME = 180000;}
		
		// timer
		timer = new Timer(1000, new ActionListener() {
			
			// action listener method
			@Override
			public void actionPerformed(ActionEvent e) {
				// set text to right time
				timerLabel.setText(getRemainingTime());
				
				// insert tipping points at correct times
				insertTippingPoint();
				
			}
		});
		
		// timer label
		timerLabel = new JLabel(); 
		
		// 2 min for square or portrait
		if(isSquare || isPortrait) {
			timerLabel.setText("2:00");
		}
		// 3 min for landscape
		else {
			timerLabel.setText("3:00");
		}
		
		timerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		// set in bottom-left corner
		timerLabel.setBounds(this.getWidth() - 86, this.getHeight() - 55, 80, 30);
		timerLabel.setBorder(blackline);
		contentPane.add(timerLabel);
		
		
	
		/*----------------------------
		 * Slot Machine Panel
		 *----------------------------
		 */
		
		slotMachinePanel = new JPanel();
		slotMachinePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.RED));
		slotMachinePanel.setBounds(20, 10, 350, 250);
		
		if(isLandscape) {
			slotMachinePanel.setBounds(20,10, 350, 250);
		}
		slotMachinePanel.setLayout(null);
		
		/**----------------------------
		 * Title for Slot Machine
		 * ----------------------------
		 */
		
		//Title
		slotMachineTitleLabel = new JLabel("Slot Machine");
		slotMachineTitleLabel.setForeground(Color.RED);
		slotMachineTitleLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 30));
		slotMachineTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		slotMachineTitleLabel.setBounds((int)slotMachinePanel.getWidth()/2 - 100, 6, 200, 60);
		slotMachinePanel.add(slotMachineTitleLabel);
		
		/**----------------------------
		 * Slots area
		 * Displays first, second, and third image given in SlotMachine parameter in each label respectively
		 * ----------------------------
		 */
		
		/*
		 * Slot Machine Slot 1
		 * Displays ImageIcons while will keep interchanging
		 */
		
		ActionListener SlotMachineButtonListener = new SlotMachineButtonListener();
		slotMachineButton1 = new JButton();
		slotMachineButton1.setBorder(blackline);
		slotMachineButton1.setBounds(slotMachinePanel.getWidth()/2 - 3*LABEL_SIZE/2 - 15 , slotMachineTitleLabel.getY() + slotMachineTitleLabel.getHeight() + 10, LABEL_SIZE, LABEL_SIZE);
		//setting icon
		slotMachineButton1.setIcon(this.myImageIcons.get(0));
		// adding action listener
		slotMachineButton1.addActionListener(SlotMachineButtonListener);
		slotMachineButton1.setActionCommand("slotMachineButton1");
		slotMachinePanel.add(slotMachineButton1);
		
		/*
		 * Slot Machine Slot 2
		 * Displays ImageIcons while will keep interchanging
		 */
		slotMachineButton2 = new JButton();
		slotMachineButton2.setBounds(slotMachinePanel.getWidth()/2 - LABEL_SIZE/2, slotMachineTitleLabel.getY() + slotMachineTitleLabel.getHeight() + 10, LABEL_SIZE, LABEL_SIZE);
		slotMachineButton2.setBorder(blackline);
		//setting icon
		slotMachineButton2.setIcon(this.myImageIcons.get(1));
		// adding action listener
		slotMachineButton2.addActionListener(SlotMachineButtonListener);
		slotMachineButton2.setActionCommand("slotMachineButton2");
		slotMachinePanel.add(slotMachineButton2);
		
		/*
		 * Slot Machine Slot 3
		 * Displays ImageIcons while will keep interchanging
		 */
		slotMachineButton3 = new JButton();
		slotMachineButton3.setBounds(slotMachinePanel.getWidth()/2 + LABEL_SIZE/2 + 15, slotMachineTitleLabel.getY() + slotMachineTitleLabel.getHeight() + 10, LABEL_SIZE, LABEL_SIZE);
		slotMachineButton3.setBorder(blackline);
		//setting icon
		slotMachineButton3.setIcon(this.myImageIcons.get(2));
		// adding action listener
		slotMachineButton3.addActionListener(SlotMachineButtonListener);
		slotMachineButton3.setActionCommand("slotMachineButton3");
		slotMachinePanel.add(slotMachineButton3);
		
		/**----------------------------
		 * Stop and Spin Buttons
		 * ----------------------------
		 */
		
		/*
		 * Spin Button
		 * Sets icons in label to random ImageIcon each 100 milliseconds
		 */
		ActionListener spinButtonListener = new SpinButtonListener(); // instantiate listener
		JButton spinButton = new JButton("Spin");
		spinButton.setFont(new Font("Kohinoor Devanagari", Font.PLAIN, 20));
		spinButton.setForeground(Color.BLUE);
		spinButton.setBounds(slotMachinePanel.getWidth()/2 - 110, slotMachineButton1.getY() + slotMachineButton1.getWidth() + 25, 100, 40);
		//adding ActionListener
		spinButton.addActionListener(spinButtonListener); // assign listener
		slotMachinePanel.add(spinButton);
		
		/*
		 * Stop Button
		 * Stop pinning/changing of ImageIcons
		 */
		ActionListener stopButtonListener = new StopButtonListener(); // instantiate listener
		JButton stopButton = new JButton("Stop");
		stopButton.setFont(new Font("Kohinoor Devanagari", Font.PLAIN, 20));
		stopButton.setForeground(Color.BLUE);
		stopButton.setBounds(slotMachinePanel.getWidth()/2 + 10, slotMachineButton1.getY() + slotMachineButton1.getWidth() + 25, 100, 40);
		stopButton.addActionListener(stopButtonListener); // assign listener
		slotMachinePanel.add(stopButton); 	
		
		contentPane.add(slotMachinePanel);
		
		
		pieceUsedLabel = new JLabel(convertToMultiline("Piece has already\nbeen used this turn"));
		pieceUsedLabel.setBorder(blackline);
		pieceUsedLabel.setFont(new Font("Lantinghei SC", Font.PLAIN, 15));
		pieceUsedLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pieceUsedLabel.setVerticalAlignment(SwingConstants.TOP);
		pieceUsedLabel.setBounds(slotMachinePanel.getX(), slotMachinePanel.getY() + slotMachinePanel.getHeight() + 10, 150, 50);
	
		pieceUsedLabel.setVisible(false);
		contentPane.add(pieceUsedLabel);
		
		
		/**---------------------------------------------
		 * Puzzle Piece Labels --> main puzzle board
		 * ---------------------------------------------
		 */
		
		/*
		 * Puzzle Piece 1
		 * Click to set Puzzle Piece to image from Available Button
		 */
		ActionListener puzzlePiece = new PuzzlePieceListener(); // instantiate listener
		puzzlePiece1 = new JButton();
		puzzlePiece1.setBounds(this.getWidth()/2 - 3*LABEL_SIZE/2, slotMachinePanel.getY() + slotMachinePanel.getHeight() + 20, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece1.setBorder(blackline);
		puzzlePiece1.addActionListener(puzzlePiece); // assign listener
		puzzlePiece1.setActionCommand("puzzlePiece1");
		contentPane.add(puzzlePiece1);
		   
		int pieceX = puzzlePiece1.getX();
		int pieceY = puzzlePiece1.getY();
		
		
		/*
		 * Puzzle Piece 2
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece2 = new JButton();
		puzzlePiece2.setBounds(pieceX + LABEL_SIZE - 1, pieceY, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece2.setBorder(blackline);
		puzzlePiece2.addActionListener(puzzlePiece); // assign listener
		puzzlePiece2.setActionCommand("puzzlePiece2");
		contentPane.add(puzzlePiece2);
		
		/*
		 * Puzzle Piece 3
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece3 = new JButton();
		puzzlePiece3.setBounds(pieceX + 2*(LABEL_SIZE - 1), pieceY, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece3.setBorder(blackline);
		puzzlePiece3.addActionListener(puzzlePiece); // assign listener
		puzzlePiece3.setActionCommand("puzzlePiece3");
		contentPane.add(puzzlePiece3);
		
		/*
		 * Puzzle Piece 4
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece4 = new JButton();
		puzzlePiece4.setBounds(pieceX, pieceY + LABEL_SIZE - 1, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece4.setBorder(blackline);
		puzzlePiece4.addActionListener(puzzlePiece); // assign listener
		puzzlePiece4.setActionCommand("puzzlePiece4");
		contentPane.add(puzzlePiece4);
		
		/*
		 * Puzzle Piece 5
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece5 = new JButton();
		puzzlePiece5.setBounds(pieceX + LABEL_SIZE - 1, pieceY + LABEL_SIZE - 1, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece5.setBorder(blackline);
		puzzlePiece5.addActionListener(puzzlePiece); // assign listener
		puzzlePiece5.setActionCommand("puzzlePiece5");
		contentPane.add(puzzlePiece5);
		
		/*
		 * Puzzle Piece 6
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece6 = new JButton();
		puzzlePiece6.setBounds(pieceX + 2*(LABEL_SIZE - 1), pieceY + LABEL_SIZE - 1, LABEL_SIZE, LABEL_SIZE);
		puzzlePiece6.setBorder(blackline);
		puzzlePiece6.addActionListener(puzzlePiece); // assign listener
		puzzlePiece6.setActionCommand("puzzlePiece6");
		contentPane.add(puzzlePiece6);
		
		/*
		 * Puzzle Piece 7
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece7 = new JButton();
		puzzlePiece7.setBounds(pieceX, pieceY + 2*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece7.setBorder(blackline);
		puzzlePiece7.addActionListener(puzzlePiece); // assign listener
		puzzlePiece7.setActionCommand("puzzlePiece7");
		contentPane.add(puzzlePiece7);
		
		/*
		 * Puzzle Piece 8
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece8 = new JButton();
		puzzlePiece8.setBounds(pieceX + LABEL_SIZE - 1, pieceY + 2*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece8.setBorder(blackline);
		puzzlePiece8.addActionListener(puzzlePiece); // assign listener
		puzzlePiece8.setActionCommand("puzzlePiece8");
		contentPane.add(puzzlePiece8);
		
		/*
		 * Puzzle Piece 9
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece9 = new JButton();
		puzzlePiece9.setBounds(pieceX + 2*(LABEL_SIZE - 1), pieceY + 2*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece9.setBorder(blackline);
		puzzlePiece9.addActionListener(puzzlePiece); // assign listener
		puzzlePiece9.setActionCommand("puzzlePiece9");
		contentPane.add(puzzlePiece9);
		
		/*
		 * Puzzle Piece 10
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece10 = new JButton();
		puzzlePiece10.setBounds(pieceX, pieceY + 3*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece10.setBorder(blackline);
		puzzlePiece10.addActionListener(puzzlePiece); // assign listener
		puzzlePiece10.setActionCommand("puzzlePiece10");
		contentPane.add(puzzlePiece10);
		
		/*
		 * Puzzle Piece 11
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece11 = new JButton();
		puzzlePiece11.setBounds(pieceX + LABEL_SIZE -1, pieceY + 3*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece11.setBorder(blackline);
		puzzlePiece11.addActionListener(puzzlePiece); // assign listener
		puzzlePiece11.setActionCommand("puzzlePiece11");
		contentPane.add(puzzlePiece11);
		
		/*
		 * Puzzle Piece 12
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece12 = new JButton();
		puzzlePiece12.setBounds(pieceX + 2*(LABEL_SIZE -1), pieceY + 3*(LABEL_SIZE - 1), LABEL_SIZE, LABEL_SIZE);
		puzzlePiece12.setBorder(blackline);
		puzzlePiece12.addActionListener(puzzlePiece); // assign listener
		puzzlePiece12.setActionCommand("puzzlePiece12");
		contentPane.add(puzzlePiece12);
		
		/*
		 * Puzzle Piece 13
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece13 = new JButton();
		puzzlePiece13.setSize(LABEL_SIZE, LABEL_SIZE);
		puzzlePiece13.setBorder(blackline);
		puzzlePiece13.addActionListener(puzzlePiece); // assign listener
		puzzlePiece13.setActionCommand("puzzlePiece13");
		contentPane.add(puzzlePiece13);
		
		/*
		 * Puzzle Piece 14
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece14 = new JButton();
		puzzlePiece14.setSize(LABEL_SIZE, LABEL_SIZE);
		puzzlePiece14.setBorder(blackline);
		puzzlePiece14.addActionListener(puzzlePiece); // assign listener
		puzzlePiece14.setActionCommand("puzzlePiece14");
		contentPane.add(puzzlePiece14);
		
		/*
		 * Puzzle Piece 15
		 * Click to set Puzzle Piece to image from Available Button
		 */
		puzzlePiece15 = new JButton();
		puzzlePiece15.setSize(LABEL_SIZE, LABEL_SIZE);
		puzzlePiece15.setBorder(blackline);
		puzzlePiece15.addActionListener(puzzlePiece); // assign listener
		puzzlePiece15.setActionCommand("puzzlePiece15");
		contentPane.add(puzzlePiece15);
		
	
		
		// Set up the board depending on the version
		setUpBoard();
		
		/*----------------
		 * Tipping Points
		 * ---------------
		 */
		
		/*
		 * Label 1
		 */
		tippingPoint1Label = new JLabel();
		tippingPoint1Label.setBounds(puzzlePiece8.getX() - 2*LABEL_SIZE, puzzlePiece8.getY() + LABEL_SIZE + 40 , LABEL_SIZE, LABEL_SIZE);
		tippingPoint1Label.setBorder(blackline);
		tippingPoint1Label.setIcon(this.tippingPoints[0]);
		contentPane.add(tippingPoint1Label);
		
		tippingPoint1CountLabel = new JLabel("0");
		tippingPoint1CountLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
		tippingPoint1CountLabel.setBounds(tippingPoint1Label.getX() + LABEL_SIZE/2 - 3, tippingPoint1Label.getY() + LABEL_SIZE + 3, 20, 20);
		imageToLabel.put(this.tippingPoints[0], tippingPoint1CountLabel);
		contentPane.add(tippingPoint1CountLabel);
		
		/*
		 * Label 2
		 */
		tippingPoint2Label = new JLabel();
		tippingPoint2Label.setBounds(puzzlePiece8.getX(), puzzlePiece8.getY() + LABEL_SIZE + 40, LABEL_SIZE, LABEL_SIZE);
		tippingPoint2Label.setBorder(blackline);
		tippingPoint2Label.setIcon(this.tippingPoints[1]);
		contentPane.add(tippingPoint2Label);
		
		tippingPoint2CountLabel = new JLabel("0");
		tippingPoint2CountLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
		tippingPoint2CountLabel.setBounds(tippingPoint2Label.getX() + LABEL_SIZE/2 - 3, tippingPoint2Label.getY() + LABEL_SIZE + 3, 20, 20);
		imageToLabel.put(this.tippingPoints[1], tippingPoint2CountLabel);
		contentPane.add(tippingPoint2CountLabel);
		
		/*
		 * Label 3
		 */
		tippingPoint3Label = new JLabel();
		tippingPoint3Label.setBounds(puzzlePiece8.getX() + 2*LABEL_SIZE, puzzlePiece8.getY() + LABEL_SIZE + 40, LABEL_SIZE, LABEL_SIZE);
		tippingPoint3Label.setBorder(blackline);
		tippingPoint3Label.setIcon(this.tippingPoints[2]);
		contentPane.add(tippingPoint3Label);
		
		tippingPoint3CountLabel = new JLabel("0");
		tippingPoint3CountLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
		tippingPoint3CountLabel.setBounds(tippingPoint3Label.getX() + LABEL_SIZE/2 - 3, tippingPoint3Label.getY() + LABEL_SIZE + 3, 20, 20);
		imageToLabel.put(this.tippingPoints[2], tippingPoint3CountLabel);
		contentPane.add(tippingPoint3CountLabel);
		
		// portait & landscape both have label 4
		if(isPortrait || isLandscape) {
		/*
		 * Label 4
		 */
		tippingPoint4Label = new JLabel();
		tippingPoint4Label.setSize(LABEL_SIZE, LABEL_SIZE);
		tippingPoint4Label.setBorder(blackline);
		tippingPoint4Label.setIcon(this.tippingPoints[3]);
		contentPane.add(tippingPoint4Label);
		
		tippingPoint4CountLabel = new JLabel("0");
		tippingPoint4CountLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
		tippingPoint4CountLabel.setBounds(tippingPoint4Label.getX() + LABEL_SIZE/2 - 3, tippingPoint4Label.getY() + LABEL_SIZE + 3, 20, 20);
		imageToLabel.put(this.tippingPoints[3], tippingPoint4CountLabel);
		contentPane.add(tippingPoint4CountLabel);
			
			// landscape has 5 tipping points
			if(isLandscape) {
			/*
			 * Label 5
			 */
			tippingPoint5Label = new JLabel();
			tippingPoint5Label.setSize(LABEL_SIZE, LABEL_SIZE);
			tippingPoint5Label.setBorder(blackline);
			tippingPoint5Label.setIcon(this.tippingPoints[4]);
			contentPane.add(tippingPoint5Label);
		
			tippingPoint5CountLabel = new JLabel("0");
			tippingPoint5CountLabel.setFont(new Font("Kohinoor Devanagari", Font.BOLD, 20));
			tippingPoint5CountLabel.setBounds(tippingPoint5Label.getX() + LABEL_SIZE/2 - 3, tippingPoint5Label.getY() + LABEL_SIZE + 3, 20, 20);
			imageToLabel.put(this.tippingPoints[4], tippingPoint5CountLabel);
			contentPane.add(tippingPoint5CountLabel);
			}
		}
		
		// Finish setting up tipping points depending on version
		setUpTippingPointLabels();
		
		// play 'main music' sound effect
		SoundEffects.MAIN_MUSIC.play();
		


	}
	

	
	/**--------------------------------------
	 * Slot Machine Runnable Class
	 * assigns random icons to each label
	 * --------------------------------------
	 */
	
 class SlotMachineRunnable implements Runnable{
	
	 // tells whether thread is running
	 private volatile boolean running;

	 
	 /* get running instance variable
	  * @return running  if thread is running
	  */
	 public boolean getRunning() {
		 return running;
	 }
	 
	 /* set the running instance variable
	  * @param running
	  */
	 public void setRunning(Boolean running) {
		 this.running = running;
	 }
	 
		
		@Override
		public void run() {
		
			// runs while running = true
				while(running) {
					
					try {
						Thread.sleep(75); // 75 millisecond pause 
						
						// generate random number between 0 and number of myImageIcons-1
						int random1 = (int)(Math.random() * myImageIcons.size());
						int random2 = (int)(Math.random() * myImageIcons.size());
						int random3 = (int)(Math.random() * myImageIcons.size());
						//set icon for each label to random image in ArrayList
						getSlotMachineButton1().setIcon(myImageIcons.get(random1));
						getSlotMachineButton2().setIcon(myImageIcons.get(random2));
						getSlotMachineButton3().setIcon(myImageIcons.get(random3));
					}
					catch(InterruptedException e) {
					}
					
				
	
				}

		}
	}
 
}
		
