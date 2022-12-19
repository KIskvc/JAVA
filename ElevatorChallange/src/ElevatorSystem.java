import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ElevatorSystem {
	
	public Elevator[] elevators;
	public Queue<Request> requests;
	
	ElevatorSystem(){
		elevators = new Elevator[]{new Elevator(), new Elevator(), new Elevator(), new Elevator(), new Elevator(), new Elevator(), new Elevator()};
		requests = new LinkedList<Request>();
	}
	
	ElevatorSystem(int length){
		elevators = new Elevator[length];
		requests = new LinkedList<Request>();
	}
	
	public synchronized Elevator checkAvailableElevators() {
		boolean found = false;
		Elevator e = null;
		
		while(e == null) {						//Recursion with StackOverflowException possible but less performant and not implemented due to demonstration reasons.
			for(Elevator x : elevators) {
				if(x.getAvailable() == true) {
					e = x;
					found = true;
				}
				if(found)
					break;
			}					
		}
		return e;
	}
	
	public void addRequest(int currentFloor, int destinationFloor, Direction direction) throws RequestException {
		if(currentFloor == destinationFloor) {
			System.out.println("Current floor and destination floor need to be different.");			
		} else {
			requests.add(new Request(currentFloor, destinationFloor, direction));			
		}
	}
	
	public synchronized boolean checkRequests() {
		if(requests.size()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void processRequest() {
		if(checkRequests()) {
			Elevator e = checkAvailableElevators();
			if(e == null) {
				System.out.println("Kein Lift verfügbar.");
			} else {
				e.setAvailable(false);
				e.setCurrentFloor(this.requests.poll().getDestinationFloor());			
			}			
		} else {
			System.out.println("Keine Anfragen vorhanden.");
		}
	}	

	@Override
	public String toString() {
		return "ElevatorSystem\nElevators:\n" + Arrays.toString(elevators) + 
				"\nRequests:\n" + requests.toString();
	}
	
	
	
	

}
