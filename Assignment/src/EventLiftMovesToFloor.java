
public class EventLiftMovesToFloor  extends ActionableEvent implements ActionableEventInterface  {
	Lift lift;
	int floor;
	
	public EventLiftMovesToFloor(int f, Lift l) {
		lift = l;
		floor = f;
	} 
	
	public void run() {
		try {
			if (floor == 0) {
				lift.moveDown();
			} else {
				lift.moveUp();
			}
			Thread.sleep( 5000 );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
