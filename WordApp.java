//package skeletonCodeAssgnmt2;
/** WordApp Class to play the game */
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;


import java.util.Scanner;
import java.util.concurrent.*;
//model is separate from the view.

public class WordApp {
//shared variables
	static int noWords=4;
	static int totalWords;

   	static int frameX=1000;
	static int frameY=600;
	static int yLimit=480;

	static WordDictionary dict = new WordDictionary(); //use default dictionary, to read from file eventually

	static WordRecord[] words;
	static volatile boolean done;  //must be volatile
   
	static 	Score score = new Score();

	static WordPanel w;
   /** Creates an array of Jlables 
    *  so that the Word class can receive
    *  the labels in order to update the text
    */
   static JLabel[] arrlabels; 
   static volatile boolean limit=false;//show that all words fell
   static volatile boolean complete=false;//show that game ended
   static volatile boolean end=false; //show that end button was pressed

   static boolean beginGame=true;
   /** Allow communication between WordApp and Word
    */
   static Word wrd;
   
	
	/**Creates the GUI and controls button events
    * @param frameX controls the x axis of GUI
    * @param frameY controls the y axis of GUI
    * @param yLimit sets the max height that words can fall 
    */
	
	public static void setupGUI(int frameX,int frameY,int yLimit) {
		// Frame init and dimensions
    	JFrame frame = new JFrame("WordGame"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameX, frameY);
      JPanel g = new JPanel();
      g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      g.setSize(frameX,frameY);
    	
		w = new WordPanel(words,yLimit);
		w.setSize(frameX,yLimit+100);
	   g.add(w); 
	    
      JPanel txt = new JPanel();
      txt.setLayout(new BoxLayout(txt, BoxLayout.LINE_AXIS)); 
      JLabel caught =new JLabel("Caught: " + score.getCaught() + "    ");
      JLabel missed =new JLabel("Missed:" + score.getMissed()+ "    ");
      JLabel scr =new JLabel("Score:" + score.getScore()+ "    ");  
      arrlabels=new JLabel[]{caught,missed,scr};  
      txt.add(caught);
	   txt.add(missed);
	   txt.add(scr);
    
	    //[snip]
  
	   final JTextField textEntry = new JTextField("",20);
	   textEntry.addActionListener(new ActionListener()
	   {
	      public void actionPerformed(ActionEvent evt) {
	         String text = textEntry.getText();
	          //[snip]
            if((!text.equals(""))&&(wrd.catchWord(text)==true)) //calls functions that increment the caught counter
            {;}
	         textEntry.setText("");
            if(complete==false)
            {
	            textEntry.requestFocus();
            }
	      }
	   });
	   
	   txt.add(textEntry);
	   txt.setMaximumSize( txt.getPreferredSize() );
	   g.add(txt);
	    
	   JPanel b = new JPanel();
      b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS)); 
	   JButton startB = new JButton("Start");;
		
			// add the listener to the jbutton to handle the "pressed" event
		startB.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
            
            if(beginGame==false)
            {
               w.resetGame();
            }
            beginGame=false;
			   score.resetScore();
				caught.setText("Caught: " + score.getCaught() + "    ");//resets the games variable states and labels
				missed.setText("Missed: " + score.getMissed() + "    ");
				scr.setText("Score: " + score.getScore()+ "    ");
				complete=false;
				end=false;
				limit=false;
		      textEntry.requestFocus();//return focus to the text entry field
            int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

      		for (int i=0;i<noWords;i++) {
      			words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
      		}
            w.run(); 
		   }
		});
		JButton endB = new JButton("End");;
			
				// add the listener to the jbutton to handle the "pressed" event
		endB.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            //tt.interrupt();
            end=true;
            beginGame=true;
            score.resetScore();
            caught.setText("Caught: " + score.getCaught() + "    ");//stops game and resets the score
				missed.setText("Missed: " + score.getMissed() + "    ");
				scr.setText("Score: " + score.getScore()+ "    ");
            
		   }
		});
		JButton quitB = new JButton("Quit");;
			
				// add the listener to the jbutton to handle the "pressed" event
		quitB.addActionListener(new ActionListener()
		{
		   public void actionPerformed(ActionEvent e)
		   {
		      //[snip]
            System.exit(0);//closes the game
		   }
		});
		
		b.add(startB);
		b.add(endB);
      b.add(quitB);
		
		g.add(b);
    	
      frame.setLocationRelativeTo(null);  // Center window on screen.
      frame.add(g); //add contents to window
      frame.setContentPane(g);     
       	//frame.pack();  // don't do this - packs it into small space
      frame.setVisible(true);
	}
/** takes the list of words from the textfile
 *  @param filename name of file
 *  @return an array of words from the textfile
 */
   public static String[] getDictFromFile(String filename) {
		String [] dictStr = null;
		try {
			Scanner dictReader = new Scanner(new FileInputStream(filename));
			int dictLength = dictReader.nextInt();
			//System.out.println("read '" + dictLength+"'");

			dictStr=new String[dictLength];
			for (int i=0;i<dictLength;i++) {
				dictStr[i]=new String(dictReader.next());
				//System.out.println(i+ " read '" + dictStr[i]+"'"); //for checking
			}
			dictReader.close();
		} catch (IOException e) {
	        System.err.println("Problem reading file " + filename + " default dictionary will be used");
	    }
		return dictStr;
	}
/** runs the game
 *  @param args[0]=total words;args[1]=number of falling words;args[2]=file of words
 */
	public static void main(String[] args) {
    	
		//deal with command line arguments
		totalWords=Integer.parseInt(args[0]);  //total words to fall
		noWords=Integer.parseInt(args[1]); // total words falling at any point
		assert(totalWords>=noWords); // this could be done more neatly
		String[] tmpDict=getDictFromFile(args[2]); //file of words
		if (tmpDict!=null)
			dict= new WordDictionary(tmpDict);
		
		WordRecord.dict=dict; //set the class dictionary for the words.
		
		words = new WordRecord[noWords];  //shared array of current words
		
		//[snip]
		
		setupGUI(frameX, frameY, yLimit);  
    	//Start WordPanel thread - for redrawing animation

		int x_inc=(int)frameX/noWords;
	  	//initialize shared array of current words

		for (int i=0;i<noWords;i++) {
			words[i]=new WordRecord(dict.getNewWord(),i*x_inc,yLimit);
		}
	}
}