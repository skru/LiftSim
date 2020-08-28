// Fig. 26.9: Buffer.java
// Buffer interface specifies methods called by Producer and Consumer.
public interface Buffer
{
   // place int value into Buffer
   public void set( ActionableEvent value ) throws InterruptedException; 

   // obtain int value from Buffer
   public ActionableEvent get() throws InterruptedException; 
} // end interface Buffer
