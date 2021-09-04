package skeletonCodeAssgnmt2;
public class Word implements Runnable
{
   WordRecord w;
   Word(WordRecord w) {this.w=w;}   
   public void run()
   {
      //System.out.println(w);
   }
}