public class EventFloorButton extends ActionableEvent implements ActionableEventInterface  {
	Floor floor;
	Person person;
	
	public EventFloorButton(Floor f, Person p) {
		floor = f;
		person = p;
	} 
	
	public void run() {
		try {
			person.pressFloorButton(floor);
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}

