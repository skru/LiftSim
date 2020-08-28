
import java.util.Random;

public class Consumer implements Runnable
{ 
   private final Buffer sharedLocation; // reference to shared object

   public Consumer( Buffer shared )
   {
      sharedLocation = shared;
   }

   public void run()                                           
   {
	   while (true ) {
		   try 
	       {
//	          Thread.sleep( generator.nextInt( 3000 ) );
	          sharedLocation.get().run();

	       }
	       catch ( InterruptedException exception ) 
	       {
	          exception.printStackTrace();
	       }
	   }
   }
}
