import java.util.ArrayList;

public class Lift implements Subject{
	private ArrayList<Observer> observers;
	int currentFloor = 0;
	Floor[] floors;
	
	
	public Lift(Floor[] f) {
		observers = new ArrayList<Observer>();
		floors = f;
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers(String message) {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer)observers.get(i);
			observer.update(message);
		}
	}
	
	public void moveUp() {
		currentFloor = 1;
		this.notifyObservers(String.format("lift is moving to %s \n", floors[currentFloor].floorName() ));
		
	}
	
	public void moveDown() {
		currentFloor = 0;
		this.notifyObservers(String.format("lift is moving to %s \n", floors[currentFloor].floorName() ));
		
	}
}
