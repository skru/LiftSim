
public class ActionableEventFactory {
	
	void addToBuffer(Buffer sharedLocation, ActionableEvent e){
		try
        {
           sharedLocation.set( e ); // set value in buffer
        } 
        catch ( InterruptedException exception ) 
        {
           exception.printStackTrace();
        } // end catch
	}
	
	void createEventQueue(Person[][] people, Floor[] floors, int startingFloor, int nextFloor, Lift lift, Buffer sharedLocation) {

		// people entering lift
		for (int x = 0; x < people[startingFloor].length; x++ ) {
			EventPersonEnterLift entrants = new EventPersonEnterLift(people[startingFloor][x]);
			addToBuffer(sharedLocation, entrants);
		}
		
		// person presses lift button
		EventLiftButton liftButton = new EventLiftButton(people[startingFloor][0]);
		addToBuffer(sharedLocation, liftButton);
		
		// doors close
		EventLiftDoorClose closeDoor = new EventLiftDoorClose(floors[startingFloor]);
		addToBuffer(sharedLocation, closeDoor);
		
		// lift moves
		EventLiftMovesToFloor moveTo = new EventLiftMovesToFloor(nextFloor, lift);
		addToBuffer(sharedLocation, moveTo);
		
		
		// doors open
		EventLiftDoorOpen openDoorsAgain = new EventLiftDoorOpen(floors[nextFloor]);
		addToBuffer(sharedLocation, openDoorsAgain);
		
		// people leave lift
		for (int x = 0; x < people[startingFloor].length; x++ ) {
			EventPersonExitLift f = new EventPersonExitLift(people[startingFloor][x]);
			addToBuffer(sharedLocation, f);
		}
		
		
	}
	

	
	
	public void createEvents(Person[][] people, int startingFloor, Lift lift, Floor[] floors, Buffer sharedLocation) {
		//System.out.printf("\n %s DFDFDD", people[0][0].observers.get(0).toString());
		int nextFloor;
		if (startingFloor == 0) {
			nextFloor = 1;
		
		} else {
			nextFloor = 0;
		}

		// add people to floors
		for (int x = 0; x < people.length; x++ ) {
			for (int y = 0; y < people[x].length; y++ ) {
				EventPersonOnFloor e = new EventPersonOnFloor(people[x][y], floors[x]);
				addToBuffer(sharedLocation, e);
				
			}
		}
		
		// people press button/s
		for (int x = 0; x < floors.length; x++) {
			if (people[x].length > 0) {
				EventFloorButton e = new EventFloorButton(floors[x], people[x][0]);
				addToBuffer(sharedLocation, e);
			}
		}
		
		// move lift to starting floor
		if (lift.currentFloor != startingFloor) {
			EventLiftMovesToFloor e = new EventLiftMovesToFloor(startingFloor, lift);
			addToBuffer(sharedLocation, e);
		}
		
		// if people at floor open doors
		if ( people[startingFloor].length > 0 ) {
			
			// open doors
			if (!floors[lift.currentFloor].doors.status) {
				EventLiftDoorOpen openDoors = new EventLiftDoorOpen(floors[startingFloor]);
				addToBuffer(sharedLocation, openDoors);
			}
			
			
			// person presses button
			
			createEventQueue(people, floors, startingFloor, nextFloor, lift, sharedLocation);
		}

		// if people at other floor
		if (people[nextFloor].length > 0) {
			createEventQueue(people, floors, nextFloor, startingFloor, lift, sharedLocation);
		}
		
		// doors close
		EventLiftDoorClose closeDoorAgain = new EventLiftDoorClose(floors[startingFloor]);
		addToBuffer(sharedLocation, closeDoorAgain);
			
		//return events;
	}
}
