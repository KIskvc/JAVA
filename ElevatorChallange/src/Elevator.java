
public class Elevator {
	
	private static int lastId = 0;
	private int elevatorId;
	private int currentFloor;
	private boolean available;
	
	Elevator(){
		setElevatorId();
		setCurrentFloor(0);
		setAvailable(true);
	}
	
	Elevator(boolean available){
		setElevatorId();
		setCurrentFloor(0);
		setAvailable(available);
	}
	
	public int getElevatorId() {
		return elevatorId;
	}
	
	public void setElevatorId() {
		Elevator.lastId++;
		this.elevatorId = Elevator.lastId;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean isFree) {
		this.available = isFree;
	}

	@Override
	public String toString() {
		return "Elevator [elevatorId=" + elevatorId + ", currentFloor=" + currentFloor + ", available=" + available
				+ "]";
	}
	
	
}
