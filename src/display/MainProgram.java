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
			System.out.println("-- Main menu: -------------------");
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
			System.out.println("-- Activity Manager: ------------");
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
			System.out.println("-- Participant Manager: ---------");
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
			System.out.println("-- Registration Manager: --------");
			System.out.println("1. Create registration");
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					createRegistration();
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
			System.out.println("-- Camps Manager: ---------------");
			System.out.println("1. Create new camp");
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
				case 1:
					createCamp();
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
	public static int countParticipant(ParticipantManager participantManager){
		int count=participantManager.countParticipant();
		return count;
	}*/


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
		System.out.println("Insert the name of the activity");
		String actiivityName = stdinScanner.nextLine();
		activityManager.deleteActivity(actiivityName);
	}

	// Menu option to list all the activities (past and future)
	public static void listActivities() {
		System.out.println(activityManager.getAllActivities());
	}

	/*
	 * Registration functions
	 */
	// Menu option to create a new registration
	public static void createRegistration(){
		System.out.println("Insert dni: ");
		int dni=Integer.valueOf(stdinScanner.nextLine());
		System.out.println("Insert idCamp: ");
		int idC=Integer.valueOf(stdinScanner.nextLine());
		LocalDate date=LocalDate.now();
		float price=0;
		System.out.println("Insert Full or Partial");
		Type type=Type.valueOf(stdinScanner.nextLine());
		
		registrationManager.createRegistration(dni, idC, date, price, type);
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

}
