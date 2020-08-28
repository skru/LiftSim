public class EventLiftButton extends ActionableEvent implements ActionableEventInterface  {

	Person person;
	
	public EventLiftButton(Person p) {

		person = p;
	} 
	
	public void run() {
		try {
			person.pressLiftButton();
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}