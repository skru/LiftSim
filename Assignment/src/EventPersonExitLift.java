public class EventPersonExitLift extends ActionableEvent implements ActionableEventInterface  {
	Person person;
	public EventPersonExitLift(Person p) {
		person = p;
	} 	
	public void run() {
		try {
			person.exiting();
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
