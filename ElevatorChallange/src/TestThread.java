
public class TestThread {

	public static void main(String[] args) {
		elevatorThreadSuccessTest();
		elevatorSystemFailTest();
	}
	
	public static void elevatorThreadSuccessTest() {
		//Testkontext: 2 Lifte verarbeiten 4 Anfragen.
		ElevatorSystem elevatorSystem = new ElevatorSystem(2);
		elevatorSystem.elevators[0] = new Elevator();
		elevatorSystem.elevators[1] = new Elevator();		
		try {
			elevatorSystem.addRequest(0,30, Direction.UP);
			elevatorSystem.addRequest(0,20, Direction.UP);
			elevatorSystem.addRequest(31,35, Direction.UP);
			elevatorSystem.addRequest(20,0, Direction.DOWN);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(elevatorSystem);								//Ausgabe aller Lifte und Anfragen.
		
		ElevatorThread t1 = new ElevatorThread(elevatorSystem, 0);
		ElevatorThread t2 = new ElevatorThread(elevatorSystem, 1000);
		ElevatorThread t3 = new ElevatorThread(elevatorSystem, 1500);
		ElevatorThread t4 = new ElevatorThread(elevatorSystem, 2000);
		t1.start();														//Thread t1 verarbeitet Request 1. Lift 1 nicht mehr verfügbar und Request 1 aus Queue entfernt.
		t2.start();														//Thread t2 verarbeitet Request 2. Lift 2 nicht mehr verfügbar und Request 2 aus Queue entfernt.
		t3.start();														//Thread t3 wartet bis der nächste Lift frei wird (in diesem Fall Lift 1).
																		//Lift 1 ist nicht mehr verfügbar. Request 3 wird verarbeitet und aus Queue entfernt.
		t4.start();														//Thread t4 wartet bis der nächste Lift frei wird (in diesem Fall Lift 2).
																		//Lift 2 nicht mehr verfügbar. Request 4 wird verarbeitet und aus Queue entfernt.
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(elevatorSystem);								//Ausgabe aller Lifte und Anfragen. Keine Anfragen mehr vorhanden da alle verarbeitet wurden.
	}
	
	public static void elevatorSystemFailTest() {
		// Testkontext: 2 Lifte verarbeiten 4 Anfragen.
		ElevatorSystem elevatorSystem = new ElevatorSystem(2);
		elevatorSystem.elevators[0] = new Elevator();
		elevatorSystem.elevators[1] = new Elevator();

		ElevatorThread t1 = new ElevatorThread(elevatorSystem, 0);
		System.out.println(elevatorSystem); 							// Ausgabe aller Lifte und Anfragen.
		t1.start();													 	// Keine Anfragen zum verarbeiten.
	}

}
