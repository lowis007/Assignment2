//package skeletonCodeAssgnmt2;
/**WordPanel class that controls the animation and look of words
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class WordPanel extends JPanel implements ActionListener,Runnable {
		public static volatile boolean done;
		private WordRecord[] words;
		private int noWords;
		private int maxY;
      private Timer t;
      private Word[] word;
      
/**displays the words on the JPanel
 * @param g Graphics object
 */
		
		public void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();
		    g.clearRect(0,0,width,height);
		    g.setColor(Color.red);
		    g.fillRect(0,maxY-10,width,height);

		    g.setColor(Color.black);
		    g.setFont(new Font("Helvetica", Font.PLAIN, 26));
		   //draw the words
		   //animation must be added 
		    for (int i=0;i<noWords;i++){	    	
		    	//g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
		    	g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);  //y-offset for skeleton so that you can see the words	
		    }
		   
		  }
		
		WordPanel(WordRecord[] words, int maxY) {
			this.words=words; //will this work?
			noWords = words.length;
			done=false;
			this.maxY=maxY;
         t=new Timer(17,this);
         t.setInitialDelay(0);
		}
      /** stops all the threads
      */
      public void resetGame()
      {
         for (int i=0;i<noWords;i++) 
         {
            word[i].end();
         }
      }
/**Action listener that updates the panels display with the timer
 * @param ex ActionEvent object
 */
		public void actionPerformed(ActionEvent ex)
      {
         
         repaint();
         if(WordApp.complete) //displays message if game completes and ends threads
         {
            resetGame();
            JOptionPane.showMessageDialog(null, "Game is Complete", "InfoBox: " + "GameOver", JOptionPane.INFORMATION_MESSAGE);
         }
      }
      /**creates the threads and starts them
      *
      */
		public void run() {
			//add in code to animate this
         //Runnable Word=new Word();
         t.start();
         //word=new Word[noWords];
         for (int i=0;i<noWords;i++) 
         {
				Thread word=new Thread(new Word(words[i]));
            word.start();
            
            
			}
       
		}

	}


