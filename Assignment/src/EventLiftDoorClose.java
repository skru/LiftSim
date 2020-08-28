public class EventLiftDoorClose extends ActionableEvent implements ActionableEventInterface  {
	Floor floor;
	public EventLiftDoorClose(Floor f) {
		floor = f;
	} 	
	public void run() {
		try {
			floor.closeDoors();
			Thread.sleep( 1000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
}