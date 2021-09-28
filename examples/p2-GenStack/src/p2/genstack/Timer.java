package p2.genstack;
import java.util.Date;

/**
 * A utility class to help us run benchmarks.
 *
 * @author Oscar.Nierstrasz@acm.org
 * @version $Id: Timer.java 17003 2008-03-13 10:20:41Z oscar $
 */
public class Timer {
	long startTime;
	
	/**
	 * You can either create a new instance whenever
	 * you want to time something, or you can reset()
	 * an existing instance.
	 */
	public Timer() { this.reset(); }
	
	public void reset() {
		startTime = this.timeNow();
	}
	
	/**
	 * How many milliseconds have elapsed since
	 * the last reset()?  NB: does not reset the timer!
	 */
	public long timeElapsed() {
		return this.timeNow() - startTime;
	}

	protected long timeNow() {
		return new Date().getTime();
	}
}
