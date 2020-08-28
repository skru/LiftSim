import java.util.ArrayList;

public class LiftDoor extends Floor implements Subject{
	private ArrayList<Observer> observers;
	Floor floor;
	boolean status = false;
	
	public LiftDoor(Floor f) {
		observers = new ArrayList<Observer>();
		floor = f;
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
	
  	public void open() {
  		this.notifyObservers(String.format("%s doors opening \n", floor.floorName()));
		status = true;
	}
	
	public void close() {
		this.notifyObservers(String.format("%s doors closing \n", floor.floorName()));
		status = false;
	}
}
