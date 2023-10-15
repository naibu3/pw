import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class App {
    private static void printCamps(List<ClassCamp> listCamps) {
        for (ClassCamp camp : listCamps) {
            System.out.println(camp.toString());
        }
    }

    private static void printActivities(List<ClassActivity> listActivities) {
        for (ClassActivity activity : listActivities) {
            System.out.println(activity.toString());
        }
    }

    private static void printMonitors(List<ClassMonitor> listMonitors) {
        for (ClassMonitor monitor: listMonitors) {
            System.out.println(monitor.toString());
        }
    } 

    private static ClassParticipant createNewParticipant() {
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
        ClassParticipant participant = new ClassParticipant(id, name, lastName, birthDate, specialAttention);
        return participant;
    }

    private static ClassCamp createNewCamp() {
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
        return new ClassCamp(id, beginDate, endDate, level, participants);
    }
    
    private static ClassActivity cretaeNewActivity() {
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
        return new ClassActivity(name, level, timetable, max, monitors);
    }

    private static ClassMonitor createNewMonitor() {
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
        return new ClassMonitor(id, name, lastName, specialAttention);
    }

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassParticipantManager participantManager = new ClassParticipantManager();
        ClassCampsManager campsManager = new ClassCampsManager();
        ClassRegistrationManager registrationManager = new ClassRegistrationManager();
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