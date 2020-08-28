import java.util.*;

public class Person implements Subject{
	
	private ArrayList<Observer> observers;
	public String name;
	
	public Person(int count){
		observers = new ArrayList<Observer>();
		name = "user" + count;
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
	
	
	public void waiting(Floor floor) {
		this.notifyObservers(String.format("%s is waiting on the %s \n", name, floor.floorName()));
	}
	
	public void entering() {
		this.notifyObservers(String.format("%s is entering lift \n", name ));
	}
	
	public void exiting() {
		this.notifyObservers(String.format("%s is exiting lift \n", name ));
	}
	
	public void pressFloorButton(Floor floor) {
		this.notifyObservers(String.format("%s pressed the button on the %s \n", name, floor.floorName() ));
	}
	
	public void pressLiftButton() {
		this.notifyObservers(String.format("%s pressed the lift button \n", name));
	}
}
