//package skeletonCodeAssgnmt2;

/** WordRecord class controls properties and manipulations of words 
 *
 */
public class WordRecord {
	private String text;
	private  int x;
	private int y;
	private int maxY;
	private boolean dropped;
	
	private int fallingSpeed;
	private static int maxWait=1500;
	private static int minWait=100;

	public static WordDictionary dict;
	

	
	WordRecord() {
		text="";
		x=0;
		y=0;	       
		maxY=300;
		dropped=false;
		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	WordRecord(String text) {
		this();
		this.text=text;
	}
	
	WordRecord(String text,int x, int maxY) {
		this(text);
		this.x=x;
		this.maxY=maxY;
	}
	
// all getters and setters must be synchronized
/** sets words y postition
 *  @param y y postition of word
 */
	public synchronized  void setY(int y) {
		if (y>maxY) {
			y=maxY;
			dropped=true;
		}
		this.y=y;
	}
/** sets words x postition
 *  @param x x postition of word
 */
	public synchronized  void setX(int x) {
		this.x=x;
	}
/** sets the words text
 *  @param text text of word
 */
	public synchronized  void setWord(String text) {
		this.text=text;
	}
/** gets words text
 *  @return text of word
 */
	public synchronized  String getWord() {
		return text;
	}
/** get words x postition
 *  @return x x postition of word
 */	
	public synchronized  int getX() {
		return x;
	}	
/** get words y postition
 *  @return y y postition of word
 */		
	public synchronized  int getY() {
		return y;
	}
/** gets falling speed of word
 *  @return falling speed of word
 */		
	public synchronized  int getSpeed() {
		return fallingSpeed;
	}
/** sets x,y postition of word
 *  @param x x position of word
 *  @param y y position of word
 */	
	public synchronized void setPos(int x, int y) {
		setY(y);
		setX(x);
	}
/** resets word y position to be on top of the gui
*/
	public synchronized void resetPos() {
		setY(0);
	}
/** creates a new word on top of the gui
*/
	public synchronized void resetWord() {
      if(WordApp.complete==true)
      {
         text="";
      }
      else
      {
   		resetPos();
   		text=dict.getNewWord();
   		dropped=false;
   		fallingSpeed=(int)(Math.random() * (maxWait-minWait)+minWait); 
      }
		//System.out.println(getWord() + " falling speed = " + getSpeed());

	}
/** checks if the amount of words fallen are above the limit
 *  @return true if words greater than limit
 *  @return false if not
 */
   public synchronized boolean limitCheck()
   {
      if(((WordApp.noWords+WordApp.score.getTotal())>= WordApp.totalWords+1)&&(WordApp.totalWords==WordApp.score.getTotal()))
      {
         WordApp.complete=true;
         return true;
         
      }
      else{return false;}
   }
/** checks if generated word matches typed word
 *  @param typedText text entered in textfield
 *  @return true if word matches; false if not
 */
	public synchronized boolean matchWord(String typedText) {
		//System.out.println("Matching against: "+text);
		if (typedText.equals(this.text)) {
         WordApp.score.caughtWord(typedText.length());
         if(limitCheck()==true)
         {
            WordApp.limit=true;
         }
			resetWord();
			return true;
		}
		else
			return false;
	}
	
/** decreases words y value 
 *  @param inc value of y value that the word should drop by
 */
	public synchronized  void drop(int inc) {
		setY(y+inc);
	}
/** signals if word has dropped
 *  @return true if word is dropped; false if not
 */	
	public synchronized  boolean dropped() {
		return dropped;
	}

}
