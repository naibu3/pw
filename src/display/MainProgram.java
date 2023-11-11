import java.time.LocalDate;
import java.util.Scanner;
import business.ParticipantManager;

public class MainProgram {
	private static Scanner stdinScanner;
	private static ParticipantManager participantManager;

    public static void main(String[] args) {
		stdinScanner = new Scanner(System.in);
		participantManager = new ParticipantManager();

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
		System.out.println("-- Main menu: -------------------");
		System.out.println("1. Activity Manager");
		System.out.println("2. Participant Manager");
		System.out.println("3. Registration Manager");
		System.out.println("4. Camps Manager");
		System.out.println("0. Exit");

		Boolean flag = true;
		while (flag) {
			int input = stdinScanner.nextInt();
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
		System.out.println("-- Activity Manager: ------------");

	}

	// Participant manager menu
	public static void displayParticipantMenu() {
		System.out.println("-- Participant Manager: ---------");
		System.out.println("1. Create participant");
		System.out.println("2. Update participant data");
		System.out.println("3. Remove participant");
		System.out.println("4. Display all participants");
		System.out.println("0. Go back");

		Boolean flag = true;
		while(flag) {
			int input = stdinScanner.nextInt();
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
					break;

				default:
					System.out.println("Invalid option. Try again.");
					break;
			}
		}
	}
	
	// Registration manager menu
	public static void displayRegistrationMenu() {
		System.out.println("-- Registration Manager: --------");
	}

	// Camps manager menu
	public static void displayCampsMenu() {
		System.out.println("-- Camps Manager: ---------------");
	}

	public static boolean validarFormatoFecha(String fecha) {
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
		LocalDate birthDate;

		System.out.println("Insert DNI: ");
		dni=Integer.parseInt(stdinScanner.nextLine());

		System.out.println("Insert name: ");
		name=stdinScanner.nextLine();

		System.out.println("Insert lastname: ");
		lastname=stdinScanner.nextLine();

		System.out.println("Insert birthdate with this format [YYYY-MM-DD]: ");
		birthDate=LocalDate.parse(stdinScanner.nextLine());

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

		dni=stdinScanner.nextInt();
		participantManager.delete(dni);				
	}

	public static void updateParticipant(){
		System.out.println("Insert DNI:");
		int dni = stdinScanner.nextInt();

		System.out.println("Insert the number of the value that you want to modificate:\n	1<-Name 2<-Lastname 3<-Birthdate 4<-SpecialAttention\n");
		int aux = stdinScanner.nextInt();
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
}
