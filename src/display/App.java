import java.time.LocalDate;
import java.util.Scanner;

import business.CampsManager;
import business.ParticipantManager;
import business.RegistrationManager;
import data.dto.Activity;
import data.dto.Camp;
import data.dto.EnumLevel;
import data.dto.EnumRegistrationType;
import data.dto.Monitor;
import data.dto.Participant;

import java.util.List;

/** Description
 * @version 1.0
*/
public class App {

    /** Description.
	*/
    private static void printCamps(List<Camp> listCamps) {
        for (Camp camp : listCamps) {
            System.out.println(camp.toString());
        }
    }

    /** Description.
	*/
    private static void printActivities(List<Activity> listActivities) {
        for (Activity activity : listActivities) {
            System.out.println(activity.toString());
        }
    }

    /** Description.
	*/
    private static void printMonitors(List<Monitor> listMonitors) {
        for (Monitor monitor: listMonitors) {
            System.out.println(monitor.toString());
        }
    } 

    /** Description.
	*/
    private static Participant createNewParticipant() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        System.out.println("Day:");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Month:");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Special needs?");
        LocalDate birthDate = LocalDate.of(year, month, day);
        boolean specialAttention;
        if (scanner.nextLine() == "yes") {
            specialAttention = true;
        } else {
            specialAttention = false;
        }
        scanner.close();
        Participant participant = new Participant(id, name, lastName, birthDate, specialAttention);
        return participant;
    }

    /** Description.
	*/
    private static Camp createNewCamp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Beginning date");
        System.out.println("Day:");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Month:");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.println("Year:");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("end date");
        System.out.println("Day:");
        int endday = Integer.parseInt(scanner.nextLine());
        System.out.println("Month:");
        int endmonth = Integer.parseInt(scanner.nextLine());
        System.out.println("Year:");
        int endyear = Integer.parseInt(scanner.nextLine());
        System.out.println("Special needs?");
        LocalDate beginDate = LocalDate.of(year, month, day);
        LocalDate endDate = LocalDate.of(endyear, endmonth, endday);
        System.out.println("Level");
        EnumLevel level;
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1: 
                level = EnumLevel.Children;
                break;
            case 2: 
                level = EnumLevel.Youth;
                break;    
            case 3: 
                level = EnumLevel.Teenager;
                break;
            default:
                level = EnumLevel.Children;
                break;
        }
        System.out.println("Max participants");
        int participants = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return new Camp(id, beginDate, endDate, level, participants);
    }
    
    /** Description.
	*/
    private static Activity cretaeNewActivity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Level");
        EnumLevel level;
        switch (Integer.parseInt(scanner.nextLine())) {
            case 1: 
                level = EnumLevel.Children;
                break;
            case 2: 
                level = EnumLevel.Youth;
                break;    
            case 3: 
                level = EnumLevel.Teenager;
                break;
            default:
                level = EnumLevel.Children;
                break;
        }
        System.out.println("Timetable:");
        String timetable = scanner.nextLine();
        System.out.println("Max capacity:");
        int max = Integer.parseInt(scanner.nextLine());
        System.out.println("Number of monitors:");
        int monitors = Integer.parseInt(scanner.nextLine());
        scanner.close();
        return new Activity(name, level, timetable, max, monitors);
    }

    /** Description.
	*/
    private static Monitor createNewMonitor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Name:");
        String name = scanner.nextLine();
        System.out.println("Last name:");
        String lastName = scanner.nextLine();
        System.out.println("Day:");
        System.out.println("Special needs?");
        boolean specialAttention;
        if (scanner.nextLine() == "yes") {
            specialAttention = true;
        } else {
            specialAttention = false;
        }    
        scanner.close();
        return new Monitor(id, name, lastName, specialAttention);
    }

    /** Description.
	*/
    private static void menu() {
        System.out.println("1. Create Participant (ParticipantManager)");
        System.out.println("2. List Participants (ParticipantManager)");
        System.out.println("3. Create Camp (CampsManager)");
        System.out.println("4. Create Activity (CampsManager)");
        System.out.println("5. Create Monitor (CampsManager)");
        System.out.println("6. Create Early Registration (RegistrationManager)");
        System.out.println("7. Create Late Registration (RegistrationManager)");
        System.out.println("8. List camps.");
        System.out.println("9. List activities.");
        System.out.println("10. List monitors.");
        System.out.println("11. Associate monitor to camp.");
        System.out.println("12. Associate activity to camp.");
        System.out.println("15. Exit");
    }

    /** Description.
	*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParticipantManager participantManager = new ParticipantManager();
        CampsManager campsManager = new CampsManager();
        RegistrationManager registrationManager = new RegistrationManager();
        int id, id_camp;
        boolean specialNeeds;
        String actName;
        EnumRegistrationType type;
        while (true) {
            menu();
            switch(Integer.parseInt(scanner.nextLine())) {
                case 1:
                    participantManager.enrollParticipant(createNewParticipant());
                    break;
                case 2:
                    participantManager.writeParticipants();
                    break;
                case 3:
                    campsManager.createCamp(createNewCamp());
                    break;
                case 4:
                    campsManager.createActivity(cretaeNewActivity());
                    break;
                case 5:
                    campsManager.createMonitor(createNewMonitor());
                    break;
                case 6:
                    System.out.println("Participant ID");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Camp ID");
                    id_camp = Integer.parseInt(scanner.nextLine());
                    if (scanner.nextLine() == "yes") specialNeeds = true;
                    else specialNeeds = false;
                    System.out.println("Type of registration:");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1: 
                            type = EnumRegistrationType.Partial;
                            break;
                        case 2: 
                            type = EnumRegistrationType.Full;
                            break;
                        default:
                            type = EnumRegistrationType.Partial;
                            break;
                    }
                    registrationManager.createEarlyRegistration(id, id_camp, specialNeeds, type, campsManager, participantManager);
                    break;
                case 7:
                    System.out.println("Participant ID");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Camp ID");
                    id_camp = Integer.parseInt(scanner.nextLine());
                    if (scanner.nextLine() == "yes") specialNeeds = true;
                    else specialNeeds = false;
                    System.out.println("Type of registration:");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1: 
                            type = EnumRegistrationType.Partial;
                            break;
                        case 2: 
                            type = EnumRegistrationType.Full;
                            break;
                        default:
                            type = EnumRegistrationType.Partial;
                            break;
                    }
                    registrationManager.createLateRegistration(id, id_camp, specialNeeds, type, participantManager);
                    break;
                case 8:
                    printCamps(campsManager.listCamps());
                    break;
                case 9:
                    printActivities(campsManager.listActivities());
                    break;
                case 10:
                    printMonitors(campsManager.listMonitors());
                    break;
                case 11:
                    System.out.println("Monitor ID:");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Camp ID:");
                    id_camp = Integer.parseInt(scanner.nextLine());
                    campsManager.associateMonitor(id, id_camp);
                case 12:
                    System.out.println("Activity Name:");
                    actName = scanner.nextLine();
                    System.out.println("Camp ID:");
                    id_camp = Integer.parseInt(scanner.nextLine());
                    campsManager.associateActivity(actName, id_camp);
                case 15:
                    scanner.close();
                    break;
            }
        }
    }
}