public class EventLiftDoorOpen extends ActionableEvent implements ActionableEventInterface  {
	Floor floor;
	public EventLiftDoorOpen(Floor f) {
		floor = f;
	} 	
	public void run() {
		try {
			floor.openDoors();
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}
