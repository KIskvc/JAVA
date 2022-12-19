
public class ElevatorThread extends Thread {
	
	private static int lastId = 0;
	private int threadId;
	private ElevatorSystem elevatorSystem;
	private int time;
	
	public ElevatorThread(ElevatorSystem es, int time) {
		setThreadId();
		this.elevatorSystem = es;
		setTime(time);
	}
	
	public int getThreadId() {
		return threadId;
	} 
	
	public void setThreadId() {
		ElevatorThread.lastId++;
		this.threadId = ElevatorThread.lastId;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public void run() {
		try {
			ElevatorThread.sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		if (elevatorSystem.checkRequests()) {
			Request r = elevatorSystem.requests.poll();
			Elevator e;
			synchronized (elevatorSystem) {
				e = elevatorSystem.checkAvailableElevators();
				System.out.println("Aufzug " + e.getElevatorId() + "(aktueller Stock: " + e.getCurrentFloor()
						+ ") steht in Kürze für Sie bereit.");
				try {
					ElevatorThread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				e.setAvailable(false);
			}
			System.out.println("Aufzug " + e.getElevatorId() + " ist aktiv.");
			try {
				ElevatorThread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			e.setCurrentFloor(r.getDestinationFloor());
			e.setAvailable(true);
			System.out.println("Aufzug " + e.getElevatorId() + " hat den " + e.getCurrentFloor()
					+ " Stock erreicht und ist wieder frei.");
		} else {
			System.out.println("Keine Anfragen zum bearbeiten");
		}
	}
	
}
