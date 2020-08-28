public class Floor {
	int level;
	LiftDoor doors;
	
	public Floor() {}
	
	public Floor(int f, Observer xd) {
		level = f;
		doors = new LiftDoor(this);
		doors.registerObserver(xd);
	}
//	
	public String floorName() {
		if (level == 0) {
			return "Ground Floor";
		} else {
			return "First Floor";
		}
	}
	
	public void openDoors() {
		doors.open();
	}
	
	public void closeDoors() {
		doors.close();
	}
}
