import java.time.LocalDate;
import java.util.Scanner;

import business.ActivityManager;
import business.CampManager;
import business.ParticipantManager;
import business.RegistrationManager;
import data.shared.Level;
import data.shared.Type;

public class MainProgram {
	private static Scanner stdinScanner;
	private static ParticipantManager participantManager;
	private static ActivityManager activityManager;
	private static RegistrationManager registrationManager;
	private static CampManager campManager;

    public static void main(String[] args) {
		stdinScanner = new Scanner(System.in);
		participantManager = new ParticipantManager();
		activityManager = new ActivityManager();
		registrationManager = new RegistrationManager();
		campManager = new CampManager();

		// Intentemos usar el menu para todas las implementaciones pls, no toqueis esto gracias
		// Sincearamente Nacho
		displayMainMenu();
	}

	/*
	 * Menu display functions
	 */

	// Main menu
	public static void displayMainMenu() {
		Boolean flag = true;
		while (flag) {
			System.out.println("-- Main menu: -----------------------------");
			System.out.println("1. Activity Manager");
			System.out.println("2. Participant Manager");
			System.out.println("3. Registration Manager");
			System.out.println("4. Camps Manager");
			System.out.println("0. Exit");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					displayActivityMenu();
					break;
			
				case 2:
					displayParticipantMenu();
					break;

				case 3:
					displayRegistrationMenu();
					break;

				case 4:
					displayCampsMenu();
					break;

				case 0:
					flag = false;
					break;

				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}
	
	// Activity manager menu
	public static void displayActivityMenu() {		
		Boolean flag = true;
		while (flag) {
			System.out.println("-- Activity Manager: ----------------------");
			System.out.println("1. Create activity");
			System.out.println("2. Update activity information");
			System.out.println("3. Remove activity");
			System.out.println("4. Assign monitor to activity");
			System.out.println("5. List all the activities");
			System.out.println("0. Go back");
			
			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					createActivity();
					break;

				case 3:
					removeActivity();
					break;

				case 4:
					assignMonitorToActivity();
					break;

				case 5:
					listActivities();
					break;

				case 0:
					flag = false;
					System.out.println("Returning to previous menu...");
					break;
				
				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}

	// Participant manager menu
	public static void displayParticipantMenu() {
		Boolean flag = true;
		while(flag) {
			System.out.println("-- Participant Manager: -------------------");
			System.out.println("1. Create participant");
			System.out.println("2. Update participant data");
			System.out.println("3. Remove participant");
			System.out.println("4. Display all participants");
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					createParticipant();
					break;
			
				case 2:
					updateParticipant();
					break;
				
				case 3:
					deleteParticipant();
					break;

				case 4:
					getAllParticipants();
					break;
				
				case 0:
					flag = false;
					System.out.println("Returning to previous menu...");
					break;

				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}
	
	// Registration manager menu
	public static void displayRegistrationMenu() {
		Boolean flag = true;
		while (flag) {
			System.out.println("-- Registration Manager: ------------------");
			System.out.println("1. Create registration");
			System.out.println("2. Delete registration");
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					float aux=createRegistration();
					if(aux>0)
						System.out.println("Price: "+aux);
					else
						System.out.println("Invalid dni or idCamp. Try again.");
						
					break;

				case 2:
					deleteRegistration();
					break;
					
				case 0:
					flag = false;
					System.out.println("Returning to previous menu...");
					break;
			
				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}

	// Camps manager menu
	public static void displayCampsMenu() {
		Boolean flag = true;
		while (flag) {
			System.out.println("-- Camps Manager: -------------------------");
			System.out.println("1. Create new camp");
			System.out.println("2. Update camp details");
			System.out.println("3. Remove camp");
			System.out.println("4. Assign special needs monitor to a camp");
			System.out.println("5. List camps");
			System.out.println("6. Create new monitor");
			System.out.println("7. Update monitor details");
			System.out.println("8. Remove a monitor");
			System.out.println("9. List monitors");
			System.out.println("10. Assign activity to camp");
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					createCamp();
					break;
					
				case 2:
					updateCamp();
					break;

				case 3:
					removeCamp();
					break;
				
				case 4:
					assignSpecialNeedsMonitorToCamp();
					break;

				case 5:
					listAllCamps();
					break;

				case 6:
					createMonitor();
					break;
				
				case 7:
					updateMonitor();
					break;
				
				case 8:
					removeMonitor();
					break;

				case 9:
					listAllMonitors();
					break;

				case 10:
					addActivityToCamp();
					break;

				case 0:
					flag = false;
					System.out.println("Returning to previous menu...");
					break;
			
				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}

	// This function is here to aid in the parsing of dates
	private static boolean validarFormatoFecha(String fecha) {
        try {
            // Intentar parsear el String a LocalDate
            LocalDate.parse(fecha);
            return true; // El formato es válido
        } catch (Exception e) {
            return false; // El formato no es válido
        }
    }

	// This function is here to check the timetable was inserted correctly
	private static boolean validateTimetable(String timetable) {
		if (timetable.toLowerCase().equals("morning") || timetable.toLowerCase().equals("afternoon")) {
			return true;
		}

		return false;
	}


	/*
	 *	Participant functions
	 */
	// Menu option to create participant
	public static void createParticipant(){
		int dni;
		String name, lastname, aux;
		Boolean specialNeeds;
		LocalDate birthDate = LocalDate.now();

		System.out.println("Insert DNI: ");
		dni=Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert name: ");
		name=stdinScanner.nextLine();

		System.out.println("Insert lastname: ");
		lastname=stdinScanner.nextLine();

		System.out.println("Insert birthdate with this format [YYYY-MM-DD]: ");
		String date;
		do {
			date = stdinScanner.nextLine();
			if (validarFormatoFecha(date)) {
				birthDate=LocalDate.parse(date);
			} else {
				System.out.println("The date you inserted is not correctly formatted. Please try again.");
			}
		} while (!validarFormatoFecha(date));

		System.out.println("Insert yes if the kid need special attention: ");	
		aux=stdinScanner.nextLine();
		
		if(aux.equals("yes")){
			specialNeeds = true;
		}
		else{
			specialNeeds=false;
		}

		System.out.println(specialNeeds.toString());
		participantManager.createParticipant(dni, name, lastname, birthDate, specialNeeds);
	}

	// Menu option to delete participant
	public static void deleteParticipant(){
		int dni;
		System.out.println("Insert the DNI: ");

		dni=Integer.parseInt(stdinScanner.nextLine());
		participantManager.delete(dni);				
	}

	// Menu option to update participant information
	public static void updateParticipant(){
		System.out.println("Insert DNI:");
		int dni = Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert the number of the value that you want to modificate:\n	1<-Name 2<-Lastname 3<-Birthdate 4<-SpecialAttention\n");
		int aux = Integer.parseInt(stdinScanner.nextLine());
		String toChange;

		switch (aux) {
			case 1:
				System.out.println("Insert name:");
				toChange=stdinScanner.nextLine();
				break;

			case 2:
				System.out.println("Insert lastname:");
				toChange=stdinScanner.nextLine();
				break;

			case 3:
				System.out.println("Insert birthdate:");
				toChange=stdinScanner.nextLine();

				if(!validarFormatoFecha(toChange)){
					System.out.println("This is not a date. Follow this format please: YYYY-MM-DD\n");
				}
				break;

			case 4:
				System.out.println("Insert yes if the kid need special attention:");
				toChange=stdinScanner.nextLine();
				
				if(!toChange.equals("yes")){
					toChange="false";	
				}		
				break;
		
			default:				
				aux=0;
				toChange="";
				break;
		}
			
		participantManager.updateParticipant(dni,toChange, aux);
	}

	// Menu opction to get all the participants currently in the database
	public static void getAllParticipants() {
		System.out.println(participantManager.getAllParticipants());
	}


	/*
	 * Activity functions
	 */
	// Menu ootion to create a new activity
	public static void createActivity(){
		String name;
		Level activityLevel;
		String timetable;
		int maxParticipants;
		int monitorsRequired;

		System.out.println("Insert name: ");
		name=stdinScanner.nextLine();

		System.out.println("Insert level (1: children, 2: youth, 3: teenager): ");
		activityLevel = Level.values()[ Integer.parseInt(stdinScanner.nextLine()) - 1];

		System.out.println("Insert timetable (morning, afternoon)");
		do {
			timetable = stdinScanner.nextLine();
			timetable = timetable.substring(0, 1).toUpperCase() + timetable.substring(1);

			if (!validateTimetable(timetable)) {
				System.out.println("You have to insert either Morning or Afternoon");
			}
		} while (!validateTimetable(timetable));

		System.out.println("Insert the maximum number of participants in the activity: ");	
		maxParticipants = Integer.parseInt(stdinScanner.nextLine());

		System.out.println("How many monitors are required for this activity?");
		monitorsRequired = Integer.parseInt(stdinScanner.nextLine());

		activityManager.createActivity(name, activityLevel, timetable, maxParticipants, monitorsRequired);
	}

	// Menu option to remove activity based on it's identifier (the activity name)
	public static void removeActivity() {
		System.out.println("NOTE: Before removing an activity make sure it has no monitors");
		System.out.println("Insert the name of the activity");
		String actiivityName = stdinScanner.nextLine();
		activityManager.deleteActivity(actiivityName);
	}

	// Menu option to list all the activities (past and future)
	public static void listActivities() {
		System.out.println(activityManager.getAllActivities());
	}

	// Menu option to assign a monitor to an activity
	public static void assignMonitorToActivity() {
		System.out.println("Insert the monitor DNI");
		Integer dni = Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert the activity name");
		String name = stdinScanner.nextLine();

		activityManager.addMonitorToActivity(name, dni);
	}

	/*
	 * Registration functions
	 */
	// Menu option to create a new registration
	public static float createRegistration(){
		System.out.println("Insert dni: ");
		int dni=Integer.valueOf(stdinScanner.nextLine());
		System.out.println("Insert idCamp: ");
		int idC=Integer.valueOf(stdinScanner.nextLine());
		LocalDate date=LocalDate.now();
		float price=0;
		System.out.println("Insert Full or Partial");
		Type type=Type.valueOf(stdinScanner.nextLine());
		
		float aux=registrationManager.createRegistration(dni, idC, date, price, type);
		return aux;
	}
	public static void deleteRegistration(){
		System.out.println("Insert dni: ");
		int dni=Integer.valueOf(stdinScanner.nextLine());
		System.out.println("Insert idCamp: ");
		int idC=Integer.valueOf(stdinScanner.nextLine());
		registrationManager.removeRegistration(dni, idC);
	}

	/*
	 * Camps functions
	 */
	// Menu option to create a new camp
	public static void createCamp(){
		int id;
		LocalDate beginningDate = LocalDate.now();
		LocalDate endingDate=LocalDate.now();

		System.out.println("Insert Camp ID: ");
		id=Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert beginning date with this format [YYYY-MM-DD]: ");
		String date;
		do {
			date = stdinScanner.nextLine();
			if (validarFormatoFecha(date)) {
				beginningDate=LocalDate.parse(date);
			} else {
				System.out.println("The date you inserted is not correctly formatted. Please try again.");
			}
		} while (!validarFormatoFecha(date));

		System.out.println("Insert ending date with this format [YYYY-MM-DD]: ");
		String date2;
		do {
			date2 = stdinScanner.nextLine();
			if (validarFormatoFecha(date2)) {
				endingDate=LocalDate.parse(date);
			} else {
				System.out.println("The date you inserted is not correctly formatted. Please try again.");
			}
		} while (!validarFormatoFecha(date2));

		System.out.println("Insert the level of the camp: ");
		String level=stdinScanner.nextLine();

		System.out.println("Insert maximum number of assistants: ");
		int maxAssistants=Integer.valueOf(stdinScanner.nextLine());
		

		campManager.createCamp(id, beginningDate, endingDate, Level.valueOf(level), maxAssistants);
	}
	

	// Menu option to remove a camp
	public static void removeCamp() {
		System.out.println("NOTE: Before removing a camp, make sure to remove all the participants, activities or monitors associated to it");
		System.out.println("Insert the ID of the camp you want to delete: ");
		Integer campId = Integer.parseInt(stdinScanner.nextLine());
		boolean aux=campManager.deleteCamp(campId);
		if(!aux){
			System.out.println("There is a participant/activity/special monitor associated with your camp");
		}
	}

	// Mneu option to list all the current camps
	public static void listAllCamps() {
		System.out.println(campManager.getAllCamps());
	}

	// menu option to assign an special needs monitor to a camp
	public static void assignSpecialNeedsMonitorToCamp() {
		String monitorId = "";
		String campId = "";
		Boolean flag = true;

		while (flag) {
			System.out.println("Insert the Camp ID (press l to list all the available camps)");
			campId = stdinScanner.nextLine();
			if (campId.equals("l")) {
				listAllCamps();
			} else {
				flag = false;
			}
		}

		flag = true;

		while (flag) {
			System.out.println("Insert the Monitor ID (press l to list all the available monitors)");
			monitorId = stdinScanner.nextLine();
			if (campId.equals("l")) {
				listAllMonitors();
			} else {
				flag = false;
			}
		}

		try {
			System.out.println(monitorId);
			System.out.println(campId);
			campManager.addSpecialMonitor(Integer.parseInt(monitorId), Integer.parseInt(campId));
		} catch (NumberFormatException e) {
			System.out.println("Failed associating a special needs monitor to a camp... Perhaps you put in the name of the monitor/camp instead of the monitor/camp ID?");
		}
	}

	// Menu option to create a new monitor
	public static void createMonitor(){
		int dni;
		String name, lastname, aux;
		Boolean specialEducator;

		System.out.println("Insert DNI: ");
		dni=Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert name: ");
		name=stdinScanner.nextLine();

		System.out.println("Insert lastname: ");
		lastname=stdinScanner.nextLine();

		System.out.println("Insert yes if the monitor is an special educator: ");	
		aux=stdinScanner.nextLine();
		
		if(aux.equals("yes")){
			specialEducator = true;
		} else{
			specialEducator = false;
		}

		System.out.println(specialEducator.toString());
		campManager.createMonitor(dni, name, lastname, specialEducator);
	}

	// Menu option to remove an special educator
	public static void removeMonitor() {
		System.out.println("NOTE: Before removing any monitor make sure the monitor is not associated to a camp.");
		Integer dni;
		System.out.println("Insert the DNI of the monitor to be removed");
		dni = Integer.parseInt(stdinScanner.nextLine());
		campManager.deleteMonitor(dni);
	}

	// Menu option to update a monitor
	public static void updateMonitor() {
		Boolean flag = true;
		String dni = "";
		while (flag) {			
			System.out.println("Insert the DNI of the monitor you want to update (press l to get a list of monitors)");
			dni = stdinScanner.nextLine();
			if (dni.equals("l")) {
				listAllMonitors();
			} else {
				flag = false;
			}
		}

		System.out.println("Insert name: ");
		String name=stdinScanner.nextLine();

		System.out.println("Insert lastname: ");
		String lastname=stdinScanner.nextLine();

		System.out.println("Insert yes if the monitor is an special educator: ");	
		String aux=stdinScanner.nextLine();
		
		Boolean specialEducator;
		if(aux.equals("yes")){
			specialEducator = true;
		} else{
			specialEducator = false;
		}

		campManager.updateMonitor(Integer.parseInt(dni), name, lastname, specialEducator);
	}

	// Menu option to update the camp
	public static void updateCamp() {
		LocalDate beginningDate = LocalDate.now();
		LocalDate endingDate = LocalDate.now();
		Boolean flag = true;
		String id = "";
		while (flag) {			
			System.out.println("Insert the ID of the camp you want to update (press l to get a list of all the camps)");
			id = stdinScanner.nextLine();
			if (id.equals("l")) {
				listAllCamps();
			} else {
				flag = false;
			}
		}

		System.out.println("Insert beginning date with this format [YYYY-MM-DD]: ");
		String date;
		do {
			date = stdinScanner.nextLine();
			if (validarFormatoFecha(date)) {
				beginningDate=LocalDate.parse(date);
			} else {
				System.out.println("The date you inserted is not correctly formatted. Please try again.");
			}
		} while (!validarFormatoFecha(date));

		System.out.println("Insert ending date with this format [YYYY-MM-DD]: ");
		String date2;
		do {
			date2 = stdinScanner.nextLine();
			if (validarFormatoFecha(date2)) {
				endingDate=LocalDate.parse(date2);
			} else {
				System.out.println("The date you inserted is not correctly formatted. Please try again.");
			}
		} while (!validarFormatoFecha(date2));

		System.out.println("Insert the level of the camp: ");
		String level=stdinScanner.nextLine();

		System.out.println("Insert maximum number of assistants: ");
		int maxAssistants=Integer.valueOf(stdinScanner.nextLine());
		

		campManager.updateCamp(Integer.parseInt(id),beginningDate,endingDate, Level.valueOf(level),maxAssistants);
	}

	// Mneu option to add an activity to a camp
	public static void addActivityToCamp() {
		System.out.println("Insert the actvity name:");
		String name = stdinScanner.nextLine();

		System.out.println("Insert the id of the camp");
		Integer campId = Integer.parseInt(stdinScanner.nextLine());

		campManager.addActivityToCamp(campId, name);
	}

	// Menu option to list all monitors
	public static void listAllMonitors() {
		System.out.println(campManager.getAllMonitors());
	} 
}
