package skeletonCodeAssgnmt2;

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
		public void actionPerformed(ActionEvent ex)
      {
         repaint();
      }
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
         /*for (int i=0;i<noWords;i++)
         {
            word[i].start();
         }*/

        /* while(true)
         {
            repaint();
         }*/
         
         /*while(true)
         {
            //repaint();
            for (int i=0;i<noWords;i++)
            {
               words[i].drop(1);
               try
               {
                   Thread.sleep(10);
               }
               catch(InterruptedException ex)
               {
                   Thread.currentThread().interrupt();
               }
               //if m
               repaint();
  
            } 
            //repaint();  
         }*///repaint();
         //while(words)
         //this.repaint();
         /*for (int i=0;i<noWords;i++)
         {
            //new Thread(words[i].getWord())
            while(words[i].getY()!=480)
            {
               try
               {
                  Thread.sleep(100);
                  //g.drawString(words[i].getWord() ,words[i].getX(),words[i].getY()) ;

               }
               catch(Exception e)
               {
                  
               }
               this.repaint();
            }
         }
         for (int i=0;i<noWords;i++)
         {
            new Thread(words[i].getWord())
            {
               public void run()
               {
                  try
                  {
                     Thread.sleep(100);
                     //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY()+20);
                     words[i].setY(words[i].getY()+20);
                  }
                  //repaint();
                  catch(InterruptedException e)
                  {
                     //JOptionPane.showMessageDialog(this,e);
                  }   
               }
               //Thread.start();
            };
            
         }
         //repaint();
       */  
		}

	}


