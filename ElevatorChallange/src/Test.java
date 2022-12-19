
public class Test {
	
	public static void main(String[] args) {
		checkAvailableElevatorsSuccessTest();
		checkAvailableElevatorsFailTest();
		addRequestSuccessTest();
		addRequestFailTest();
		addRequestFailTest2();
		checkRequestSuccessTest();
		checkRequestFailTest();
		processRequestSuccessTest();
		processRequestFailTest();
		elevatorSystemTest();
	}
	
	
	public static void checkAvailableElevatorsSuccessTest() {
		ElevatorSystem et = new ElevatorSystem(3);
		et.elevators[0] = new Elevator(false);
		et.elevators[1] = new Elevator(true);
		et.elevators[2] = new Elevator(true);
		System.out.println(et.checkAvailableElevators());
		//Success if Elevator of Index 1 (Id = 2) is printed.		
	}
	
	public static void checkAvailableElevatorsFailTest() {
		//In case recursion is implemented.
		ElevatorSystem et = new ElevatorSystem(3);
		et.elevators[0] = new Elevator(false);
		et.elevators[1] = new Elevator(false);
		et.elevators[2] = new Elevator(false);
		System.out.println(et.checkAvailableElevators());
		//Success if StackOverflowException is thrown.		
	}
	
	public static void addRequestSuccessTest() {
		ElevatorSystem et = new ElevatorSystem(3);
		try {
			et.addRequest(0, 10, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et.requests.size());
		//Success if 1 is printed.
	}
	
	public static void addRequestFailTest() {
		ElevatorSystem et = new ElevatorSystem(3);
		try {
			et.addRequest(-1, 10, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et.requests.size());
		//Success if Exception was thrown and null is printed.
	}
	
	public static void addRequestFailTest2() {
		ElevatorSystem et = new ElevatorSystem(3);
		try {
			et.addRequest(0, 0, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et.requests.size());
		//Success if Error-Message and 0 are printed.
	}
	
	public static void checkRequestSuccessTest() {
		ElevatorSystem et = new ElevatorSystem(3);
		try {
			et.addRequest(0, 10, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et.checkRequests());
		//Success if true is printed.
	}
	
	public static void checkRequestFailTest() {
		ElevatorSystem et = new ElevatorSystem(3);
		try {
			et.addRequest(-1, 10, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et.checkRequests());
		//Success if false is printed.
	}
	
	public static void processRequestSuccessTest() {
		ElevatorSystem et = new ElevatorSystem(1);
		et.elevators[0] = new Elevator(true);
		try {
			et.addRequest(0,30, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et + "\n");
		et.processRequest();
		System.out.println(et);
		//Success if Elevator 1 available=false and Request-Queue is empty.
	}
	
	public static void processRequestFailTest() {
		ElevatorSystem et = new ElevatorSystem(1);
		et.elevators[0] = new Elevator(false);
		et.processRequest();					//Keine Anfragen vorhanden.
		try {
			et.addRequest(0,30, Direction.UP);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(et);
		et.processRequest();					//Kein Lift verfügbar.
		//Success if both failing conditions are true.
	}
	
	public static void elevatorSystemTest() {
		
		ElevatorSystem et = new ElevatorSystem(3);
		et.elevators[0] = new Elevator(true);
		et.elevators[1] = new Elevator(true);
		et.elevators[2] = new Elevator(true);
		try {
			et.addRequest(0,30, Direction.UP);
			et.addRequest(35,0, Direction.DOWN);
			et.addRequest(0,20, Direction.UP);
			et.addRequest(20,0, Direction.DOWN);
		} catch (RequestException e) {
			System.out.println(e.getMessage());
		}		
		System.out.println(et + "\n"); 			//Alle Anfragen und Lifte werden ausgegeben.
		et.processRequest();					//Lift 1 wird auf available=false gesetzt und die Request 1 in der Queue wird verarbeitet.
		System.out.println(et + "\n");			//Request 1 nicht mehr vorhanden da schon varbeitet.
		et.processRequest();					//Lift 2 wird auf available=false gesetzt und die Request 2 wird verarbeitet.
		et.elevators[0].setAvailable(true);		//Lift 1 wird wieder freigegeben.
		System.out.println(et + "\n");			//Lift 1 available=true, Lift 2 available=false, Request 2 nicht mehr vorhanden da schon verarbeitet.
		et.processRequest();					//List 1 available=false, Request 3 wird verarbeitet.
		et.processRequest();					//List 3 available=false, Request 4 wird verarbeitet.
		System.out.println(et + "\n");			//Alle Lifte available=false und Anfrage-Queue leer da alle verarbeitet wurden.
		et.elevators[0].setAvailable(true);
		et.elevators[1].setAvailable(true);
		et.elevators[2].setAvailable(true);
		System.out.println(et + "\n");			//Alle Lifte available=true durch vorherige Freigabe, Request-Queue leer.
		
		//Der Wert aller Lifte bei "current floor" entspricht dem "destination floor" der zuletzt verarbeiteten Request.
		
	}
	
	
	
	
	
	

}
