import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

/**
 * Class that holds sound effects and plays and stops them
 * @author candacechen (most of code taken from internet https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html)
 *
 */

// create sound Effect
	public enum SoundEffects {
		WINNING("winning.wav", false),
		LOSING("losing.wav", false),
		MAIN_MUSIC("elevator music.wav", true),
		ONE_COUNTER("one counter.wav", true),
		TWO_COUNTER("two counter.wav", true),
		COUNTER_NOTIF("counter notif.wav", false);
		
		 // Nested class for specifying volume
		 public static enum Volume {
		     MUTE, LOW, MEDIUM, HIGH
		   }
		
	 // instance variable
	  private boolean willLoop;
	 
	  public static Volume volume = Volume.LOW;
	   
	 // Each sound effect has its own clip, loaded with its own sound file.
	  public Clip clip;

	   
	// Constructor to construct each element of the enum with its own sound file.
	 SoundEffects(String soundFileName, boolean willLoop) {
		
		 // indicates whether the sound will loop
		this.willLoop = willLoop;
	     
		try {
	         
			// Use URL (instead of File) to read from disk and JAR.
	         URL url = this.getClass().getClassLoader().getResource(soundFileName);
	         // Set up an audio input stream piped from the sound file.
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
	         // Get a clip resource.
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	
	
	// Play or Re-play the sound effect from the beginning, by rewinding.
	   public void play() {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning())
	            clip.stop();   // Stop the player if it is still running
	         clip.setFramePosition(0); // rewind to the beginning
	         clip.start();     // Start playing
	         
	         // loops if willLoop was set ot true
	         if(willLoop) {
	        	 clip.loop(Clip.LOOP_CONTINUOUSLY);
	         }
	      }
	   }
	   
	   // Stops sound effect
	   public void stop() {
		   // checks if clip is running
		   if(clip.isRunning()){
			   clip.stop();
		   }
	   }
	}
	   
	
	
	
	
