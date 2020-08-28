public class EventPersonOnFloor extends ActionableEvent implements ActionableEventInterface {
	Person person;
	Floor floor;
	public EventPersonOnFloor(Person p, Floor f) {
		person = p;
		floor = f;

	} 	
	public void run() {
		try {
			person.waiting(floor);
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
