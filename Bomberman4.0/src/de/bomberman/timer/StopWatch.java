package de.bomberman.timer;

/** Stopt die Zeit (für den EispielerModus) und berechnet die
 * Zeitbonus Punkte
 * @author Nuni
 *
 */
public class StopWatch {

	private static final boolean DEBUG = true;
	private long maxTime;
	private long startTime;
	private long stopTime;
	
	public StopWatch(int maxTime) {
		this.maxTime = maxTime;
		this.startTime = System.currentTimeMillis();
	}
	
	public long stop() {
		if( DEBUG )
			System.out.println("Stoping Stop-Watch");
		this.stopTime = System.currentTimeMillis();
		return calculatePoint();
	}

	private long calculatePoint() {
		long time = (int)( stopTime - startTime );
		if ( DEBUG )
			System.out.println("time:"+time);
		return (maxTime - time)*10;
	}
}
