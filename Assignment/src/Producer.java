public class Producer implements Runnable
{
	

   private final Buffer sharedLocation; // reference to shared object
   private int liftStarting;

   private Floor floors[];
   private Lift lift;
   
   
   private PersonFactory personFactory;
   private Person[] gfPeople;
   private Person[] ffPeople;
   private Person[] people[];
   
   

   // constructor
   public Producer( ConsoleView xd, Buffer shared, Lift li, Floor[] f, int l, int gfPersonCount, int ffPersonCount )
   {
	   sharedLocation = shared;
	   lift = li;
	   floors = f;
	   liftStarting = l;
	   personFactory = new PersonFactory();
	   gfPeople = personFactory.createPeople(gfPersonCount, xd);
	   ffPeople = personFactory.createPeople(ffPersonCount, xd);
	   //lift.registerObserver(xd);

   }


   public void run()                             
   {
	 Person[] people[] = {gfPeople, ffPeople};
	 ActionableEventFactory eventFactory = new ActionableEventFactory();
	 eventFactory.createEvents(people, liftStarting, lift, floors, sharedLocation);
   }
}