package de.bomberman.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**Beinhaltet die Sounds und Aufruf möglichkeiten jener
 * @author Gruppe 44 
 *
 */
public class SoundBib {

	private static File powerUp;
	private static File explosion;
	private static File countdown;
	private static File start;
	private static File gameOver;
	private static File timeOver;
	private static File hurryUp;
	
	
	
	/** initialisiert die Sounds
	 * @throws MalformedURLException
	 */
	public static void initSounds() throws MalformedURLException { 
		powerUp = new File("sounds/PowerUp.wav");
		//countdown = new File("sounds/PowerUp.wav");
		start = new File("sounds/StartNeu.wav");
		gameOver = new File("sounds/GameOver.wav");
		timeOver = new File("sounds/TimeOver.wav");
		explosion = new File("sounds/ExplosionNeu.wav");
		hurryUp = new File("sounds/HurryUp.wav");
	}


	
	// folgende Funktionen spielen die Sounds ab
	public static void playPowerUp() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(powerUp.toURL());
		sound.play();
	}


	public static void playExplosion() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(explosion.toURL());
		sound.play();
	}


	public static void playCountdown() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(countdown.toURL());
		sound.play();
	}


	public static void playStart() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(start.toURL());
		sound.play();
	}


	public static void playGameOver() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(gameOver.toURL());
		sound.play();
	}

	public static void playHurryUp() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(hurryUp.toURL());
		sound.play();
	}
	
	public static void playTimeOver() throws MalformedURLException {
		AudioClip sound = Applet.newAudioClip(timeOver.toURL());
		sound.play();
	}

}

