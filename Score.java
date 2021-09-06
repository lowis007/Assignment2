//package skeletonCodeAssgnmt2;
/** Score class controls the games scoreboard
 *
*/
import java.util.concurrent.atomic.AtomicInteger;

public class Score {
	private static AtomicInteger missedWords;
	private static AtomicInteger caughtWords;
	private static AtomicInteger gameScore;
	
	Score() {
		missedWords=new AtomicInteger(0);
		caughtWords=new AtomicInteger(0); //constructor
		gameScore=new AtomicInteger(0);
	}
		
	// all getters and setters must be synchronized
/** gets missed words
 *  @return number of missed words
*/
	public synchronized int getMissed() {
		return missedWords.get();
	}
/** gets caught words
 *  @return number of caught words
*/
	public synchronized int getCaught() {
		return caughtWords.get();
	}
/** gets total amount of words
 *  @return total number of words
*/	
	public synchronized int getTotal() {
		return (missedWords.get()+caughtWords.get());
	}
/** gets game score
 *  @return score of game
*/
	public synchronized int getScore() {
		return gameScore.get();
	}
/** increases amount of missed words
*/	
	public synchronized void missedWord() {
		missedWords.getAndIncrement();
	}
/** increases amount of caught words aswell as increasing the score
 *  @param length of word
*/
	public synchronized static void caughtWord(int length) {
		caughtWords.getAndIncrement();
		gameScore.getAndAdd(length);
	}
/** resets the scoreboard
*/
	public void resetScore() {
		caughtWords.set(0);
		missedWords.set(0);
		gameScore.set(0);
	}
}
