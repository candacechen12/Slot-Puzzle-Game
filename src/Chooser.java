import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Chooser extends JFrame {
	
	private static final long serialVersionUID = 5245643883924487169L;

	// screen size
	final static Dimension myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/*
	 * instance variables
	 */
	
	// Default parts
	private JPanel contentPane;
	
	// Screen Size
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	// IMAGE NAMES ARRAY
	private final String[] imageNames = {"Kiki's Delivery Service By Jane Doe.jpg", "Castle In The Sky By Miyazaki.jpg", "Food-waste By Gourmet Food.jpg",
										 "Plant-Rich-Diets By Dinosaur.jpg", "Tropical-Forest-Restoration By Save The Trees.jpg", "Utility-scale-solar By Solar Energy.jpg",
										 "Wind-Turbines-Onshore By Renewable Energy.jpg"};
	
	// Image names without extension
	private String[] imageNamesNoExt;
	
	// Images Array
	private Map<String, ImageIcon> myImageMap;
	
	// Image Selection Instructions label
	private JLabel imageSelectionInstructionLabel;
	
	// Image Selection Box
	private JComboBox<String> imageSelectionBox ;
	
	// Selected Image Label
	private JLabel selectedImageLabel;
	
	// Image
	static protected ImageIcon selectedImage;
	static protected String version;
	
	// Play Button
	private JButton playButton;
	private JLabel imageNameLabel;
	
	
	/**
	 * Main Method
	 * Launch the application
	 */
	public static void main(String[] args) {
		play();
	}
	
	/* --------------------
	 * Getters and Setters
	 * --------------------
	 */
	

	/**
	 * @return the myImageMap
	 */
	public Map<String, ImageIcon> getMyImageMap() {
		return myImageMap;
	}

	/**
	 * @param myImageMap the myImageMap to set
	 */
	public void setMyImageMap(Map<String, ImageIcon> myImageMap) {
		this.myImageMap = myImageMap;
	}

	
	/**
	 * @return the selectedImageLabel
	 */
	public JLabel getSelectedImageLabel() {
		return selectedImageLabel;
	}

	/**
	 * @param selectedImageLabel the selectedImageLabel to set
	 */
	public void setSelectedImageLabel(JLabel selectedImageLabel) {
		this.selectedImageLabel = selectedImageLabel;
	}

	/**
	 * @return the selectedImage
	 */
	public static ImageIcon getSelectedImage() {
		return selectedImage;
	}

	/**
	 * @param selectedImage the selectedImage to set
	 */
	public static void setSelectedImage(ImageIcon selectedImage) {
		Chooser.selectedImage = selectedImage;
	}

	/**
	 * @return the version
	 */
	public static String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public static void setVersion(String version) {
		Chooser.version = version;
	}
	

	/* ---------------
	 * Other Methods
	 * ---------------
	 */
	
	/**
	 * @param orig
	 * @return multi-line label
	 */
	public static String convertToMultiline(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>");
	}
	

	
	/**
	 * Takes image names and creates ImageIcons to put in array
	 * @return  array of ImageIcons
	 */
	public Map<String, ImageIcon> putImages() {
		Map<String, ImageIcon> theImageMap = new HashMap<String, ImageIcon>();
		for(int i = 0; i<imageNames.length; i++){
			URL url = Chooser.class.getResource("/"+ imageNames[i]);
			theImageMap.put(imageNames[i].substring(0,imageNames[i].length() - 4), new ImageIcon(url));
		}
		
		return theImageMap;	
	}
	
	
	/**
	 * Takes ImageNames and creates array of them w/o extensions
	 * @return Array of strings of imageNames w/o extension
	 */
	public String[] makeImageNamesNoExt() {
		String[] myNamesNoExt = new String[imageNames.length];
		
		for(int i = 0; i<imageNames.length; i++) {
			myNamesNoExt[i] = imageNames[i].substring(0, imageNames[i].length() - 4);
		}
		return myNamesNoExt;
	}

	/**
	 * Play Method
	 * Instantiates Chooser and runs
	 */
	public static void play() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chooser frame = new Chooser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	/**
	 * Disposes current Window --> use after play button has been clicked and new window with game is initialized
	 */
	public void setClose() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.dispose();
	}
	
	/*
	 *  ACTION LISTENERS
	 */
	
	/**
	 * Image Selection Box Listener
	 * Select an image and set imageLabel to selected Icon and set imageSelected to the ImageIcon
	 */
	
	protected class ImageSelectionBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// action is when the comboBox change
			if(e.getActionCommand().equals("comboBoxChanged")) {
			String selectedString = (String) imageSelectionBox.getSelectedItem();
			ImageIcon iconSelected = myImageMap.get(selectedString);
			
			// display name in imageNameLabel
			int indexOfBy = selectedString.indexOf(" By ");
			String textToDisplay = convertToMultiline(selectedString.substring(0, indexOfBy) + "\n" + selectedString.substring(indexOfBy + 1));
			imageNameLabel.setText(textToDisplay);
			
			// reshape selectedImageLabel & change location based on shape of selected Image 
			// DEFAULT LOCATION): (40,66) for square images
	
			if(iconSelected.getIconWidth() == iconSelected.getIconHeight()) {
				selectedImageLabel.setBounds(30, 50, 280, 280);
				version = "Square";
			}
				
			// if portrait image
			if(iconSelected.getIconWidth() < iconSelected.getIconHeight()) {
				selectedImageLabel.setBounds(50, 30, 225, 300);
				version = "Portrait";
			}
				
			// if landscape image
			if(iconSelected.getIconWidth() > iconSelected.getIconHeight()) {
				selectedImageLabel.setBounds(10, 90, 325, 195);
				version = "Landscape";
			}
		
			selectedImageLabel.setIcon(SlotPuzzleGame4.scaleImage(iconSelected, (int) selectedImageLabel.getWidth(), (int) selectedImageLabel.getHeight()));
			setSelectedImage(getMyImageMap().get(selectedString));
			
			
			}
		}
			
		
	}
	/**
	 * Play Button Listener
	 * Click to get selected file and version and initialize Slot Puzzle Game
	 */
	protected class PlayButtonListener implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// ensure user selects an image
			if(imageSelectionBox.getSelectedItem() == null || Chooser.getVersion() == null) {return;}
			
			SlotPuzzleGame4.play();
			LargeImage.play();
			
			// closes current window
			setClose(); 
			
		}
		
	}

	
	/** -------------------
	 * 
	 * Constructor
	 * Creates the frame and adds all components
	 * 
	 * --------------------
	 */
	public Chooser() {
		
		// sets default traits
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int)myScreenSize.getWidth()/2 - 325, (int)myScreenSize.getHeight()/2 - 300, 650, 450);
		
		// Content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// Images
		this.myImageMap = putImages(); // calls method
		
		this.imageNamesNoExt = makeImageNamesNoExt();
		
		
		//imageSelectionInstructionLabel 
		imageSelectionInstructionLabel = new JLabel("Select an image to use");
		imageSelectionInstructionLabel.setFont(new Font("Kohinoor Telugu", Font.BOLD, 23));
		imageSelectionInstructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageSelectionInstructionLabel.setBounds(359, 85, 255, 36);
		contentPane.add(imageSelectionInstructionLabel);
		
		//Image Selection Box
		ActionListener imageSelectionListener = new ImageSelectionBoxListener();
		imageSelectionBox = new JComboBox<String>();
		imageSelectionBox.setModel(new DefaultComboBoxModel(this.imageNamesNoExt));
		imageSelectionBox.setBounds(390, 155, 200, 43);
		
		// make options array items
		imageSelectionBox.addActionListener(imageSelectionListener);
		
		contentPane.add(imageSelectionBox);
		
		// Selected Image Label
		Border blackLine = BorderFactory.createLineBorder(Color.black); // create border
		selectedImageLabel = new JLabel("");
		selectedImageLabel.setBounds(30, 50, 280, 280);
		selectedImageLabel.setBorder(blackLine);
		contentPane.add(selectedImageLabel);
		
		
		// Play Button
		ActionListener playButtonListener = new PlayButtonListener(); // instantiate listener
		playButton = new JButton("Play");
		playButton.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		playButton.setForeground(Color.BLUE);
		playButton.setBounds(this.getWidth()/2 - 100 , this.getHeight() - 100, 200, 50);
		playButton.addActionListener(playButtonListener); // assign listener
		contentPane.add(playButton);
		
		
		// Image Name Label
		imageNameLabel = new JLabel();
		imageNameLabel.setVerticalAlignment(SwingConstants.CENTER);
		imageNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		imageNameLabel.setBounds(359, 229, 255, 56);
		imageNameLabel.setBorder(blackLine);
		contentPane.add(imageNameLabel);
		
		
	}
}
