
public class SynchronizedBuffer implements Buffer
{
   private ActionableEvent buffer; // shared by producer and consumer threads
   private boolean occupied = false;// whether the buffer is occupied
   
   // place value into buffer
   public synchronized void set( ActionableEvent value ) throws InterruptedException
   {
      // while there are no empty locations, place thread in waiting state
      while ( occupied ) 
      {
         wait();
      } // end while
        
      buffer = value; // set new buffer value
        
      // indicate producer cannot store another value
      // until consumer retrieves current buffer value
      occupied = true;
      
      notifyAll(); // tell waiting thread(s) to enter runnable state
   } // end method set; releases lock on SynchronizedBuffer 
    
   // return value from buffer
   public synchronized ActionableEvent get() throws InterruptedException
   {
      // while no data to read, place thread in waiting state
      while ( !occupied )
      {
         wait();
      } // end while

      // indicate that producer can store another value 
      // because consumer just retrieved buffer value
      occupied = false;
      
      notifyAll(); // tell waiting thread(s) to enter runnable state

      return buffer;
   } // end method get; releases lock on SynchronizedBuffer 
    
   // display current operation and buffer state
   
} // end class SynchronizedBuffer
