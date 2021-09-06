//package skeletonCodeAssgnmt2;
/** Word class that controls the multithreading process
 * @author LWXMAT008
 */
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
public class Word implements Runnable
{
   public WordRecord wr;
   private Timer t=new Timer(17,new ActionListener()
   {
      /** action listners that triggers everytime the timer does
       */
      public void actionPerformed(ActionEvent e) 
      {
         
         if (wr.getWord().equals(""))//if game ends due to all words falling
         {
            t.stop();
         }
         else if(WordApp.end==true)//if the end button is clicked
         {
            t.stop();
            wr.setWord("");
         }
         else //standard game state
         {  
            wr.drop(wr.getSpeed()/250);  //drops the word 
            if(wr.getY()>=WordApp.yLimit) //checks if word touches red layer and counts the word as missed if true
            {
               WordApp.score.missedWord(); 
               if(wr.limitCheck()==true) //stops threads if all words completed else displays new word
               {
                  t.stop();
                  wr.setWord("");
               }
               else
               {
                  wr.resetWord();
               }
               //WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
            }
            WordApp.arrlabels[1].setText("Missed:" + WordApp.score.getMissed()+ "    ");
            /*if(wr.matchWord(WordApp.textEntry.getText())==true)
            {
               WordApp.score.caughtWord(text.length());
               WordApp.arrlabels[0].setText("Caught: " + WordApp.score.getCaught() + "    ");
               WordApp.arrlabels[2].setText("Score: " + WordApp.score.getScore()+ "    ");
            }*/
         }
         //WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
      }   
   });
   public Word(WordRecord wr) {this.wr=wr;}{t.setInitialDelay(0);} //constructs Word thread with WordRecord parameter
  /**determines if word matches the typed text in the textentry field, updates score and relevant labels
   * @param text typed text in textfield
   * @return true if word matches typed word;false if not
   */
   public synchronized boolean catchWord(String text)
   {
      for (int i=0;i<WordApp.totalWords;i++)
      {
         if(wr.matchWord(text)==true)
         {
            WordApp.score.caughtWord(text.length());
            WordApp.arrlabels[0].setText("Caught: " + WordApp.score.getCaught() + "    ");
            WordApp.arrlabels[2].setText("Score: " + WordApp.score.getScore()+ "    ");
            return true;
         }
      }
      return false;
   }
   
     
   //public 
   /**starts thread
    */
   public void run()
   {
      //System.out.println(w);
      t.start();
   }
   /**stops thread
   */
   public void end()
   {
      t.stop();
   }
}