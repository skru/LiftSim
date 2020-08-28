public class EventPersonEnterLift extends ActionableEvent implements ActionableEventInterface  {
	Person person;
	public EventPersonEnterLift(Person p) {
		person = p;
	} 	
	public void run() {
		try {
			person.entering();
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
