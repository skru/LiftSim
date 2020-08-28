
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

public class Main
{
   public static void main( String[] args )
   {
	   // View
	   LiftSimFrame liftSimFrame = new LiftSimFrame(); 
	   liftSimFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	   //liftSimFrame.setSize( 300, 150 ); // set frame size
	   liftSimFrame.setVisible( true ); // display frame
	      
	      
     
   } // end main
} // end class Main

