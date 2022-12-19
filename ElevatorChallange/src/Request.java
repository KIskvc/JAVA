
public class Request {
	
	private static int lastId = 0;
	private int requestId;
	private int currentFloor;
	private int destinationFloor;
	private Direction direction;
	
	public Request() {
		
	}	
	
	public Request(int currentFloor, int destinationFloor, Direction direction) throws RequestException {
		setId();
		setCurrentFloor(currentFloor);
		setDestinationFloor(destinationFloor);
		setDirection(direction);
	}

	public int getId() {
		return requestId;
	}
	
	public void setId() {
		Request.lastId++;
		this.requestId = Request.lastId;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) throws RequestException {
		if(currentFloor >= 0 && currentFloor <= 55) {
			this.currentFloor = currentFloor;			
		} else {
			throw new RequestException("Error: The floor must be in the range between 0 and 55.");
		}
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) throws RequestException {
		if(destinationFloor >= 0 && destinationFloor <= 55) {
			this.destinationFloor = destinationFloor;			
		} else {
			throw new RequestException("Error: The floor must be in the range between 0 and 55.");
		}
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Request [requestId=" + requestId + ", currentFloor=" + currentFloor + ", destinationFloor="
				+ destinationFloor + ", direction=" + direction + "]";
	}
	
	
	
	

}
