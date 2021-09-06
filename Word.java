package skeletonCodeAssgnmt2;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
public class Word implements Runnable
{
   WordRecord wr;
   Timer t=new Timer(17,new ActionListener()
   {
      public void actionPerformed(ActionEvent e) 
      {
         wr.drop(wr.getSpeed()/250);
         
         if(wr.getY()>=WordApp.yLimit)
         {
            WordApp.score.missedWord();
            WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
            //WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
         }
         //WordApp.missed.setText("Missed:" + WordApp.score.getMissed()+ "    ");
      }   
   });
   Word(WordRecord wr) {this.wr=wr;}{t.setInitialDelay(0);} 
   
     
   
   public void run()
   {
      //System.out.println(w);
      t.start();
   }
   public void end()
   {
      t.stop();
   }
}