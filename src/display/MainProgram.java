import java.time.LocalDate;
import java.util.Scanner;

import business.ActivityManager;
import business.ParticipantManager;
import business.RegistrationManager;
import data.shared.Level;
import data.shared.Type;

public class MainProgram {
	private static Scanner stdinScanner;
	private static ParticipantManager participantManager;
	private static ActivityManager activityManager;
	private static RegistrationManager registrationManager;

    public static void main(String[] args) {
		stdinScanner = new Scanner(System.in);
		participantManager = new ParticipantManager();
		activityManager = new ActivityManager();
		registrationManager = new RegistrationManager();
		displayMainMenu();

		/*MonitorManager monitorManager = new MonitorManager();
		monitorManager.createMonitor(12,"Pitillo", "fumatas", true);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.updateMonitor(12, "notanpitillo", "antifumatas", false);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.deleteMonitor(12);
		System.out.println(monitorManager.getAllMonitors());*/		
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
			System.out.println("0. Go back");
			
			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
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
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
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
			System.out.println("0. Go back");

			int input = Integer.parseInt(stdinScanner.nextLine());
			switch (input) {
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


	/****************************************************************************************************************************************** */
	//	PARTICIPANT FUNCTIONS
	/****************************************************************************************************************************************** */
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

	public static void deleteParticipant(){
		int dni;
		System.out.println("Insert the DNI: ");

		dni=Integer.parseInt(stdinScanner.nextLine());
		participantManager.delete(dni);				
	}

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

	public static void getAllParticipants() {
		System.out.println(participantManager.getAllParticipants());
	}

	public static int countParticipant(ParticipantManager participantManager){
		int count=participantManager.countParticipant();
		return count;
	}
/****************************************************************************************************************************************** */

	/*
	 * Activity functions
	 */
	/*
	public static void createActivity(){
		String name;
		Level activityLevel;
		LocalDate timeTable = LocalDate.now();
		int maxParticipants;

		
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
	*/
	/******************************
	 * REGISTRATION
	 ******************************/
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
}
