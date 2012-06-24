package de.bomberman.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class SoundBib {

	private static File powerUp;
	private static File explosion;
	private static File countdown;
	private static File start;
	private static File gameOver;
	
	
	public static void initSounds() throws MalformedURLException { 
		powerUp = new File("sounds/PowerUp.wav");
		countdown = new File("sounds/PowerUp.wav");
		start = new File("sounds/ExplosionCountdown3.wav");
		gameOver = new File("sounds/PowerUp.wav");
		explosion = new File("sounds/ExplosionCountdown2.wav");
	}


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


}

