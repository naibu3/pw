import business.MonitorManager;

public class MainProgram {
    public static void main(String[] args) {
		MonitorManager monitorManager = new MonitorManager();
		monitorManager.createMonitor(12,"Pitillo", "fumatas", true);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.updateMonitor(12, "notanpitillo", "antifumatas", false);
		System.out.println(monitorManager.getAllMonitors());
		monitorManager.deleteMonitor(12);
		System.out.println(monitorManager.getAllMonitors());		
	}


	public static boolean validarFormatoFecha(String fecha) {
        try {
            // Intentar parsear el String a LocalDate
            LocalDate.parse(fecha);
            return true; // El formato es válido
        } catch (DateTimeParseException e) {
            return false; // El formato no es válido
        }
    }


/****************************************************************************************************************************************** */
//	PARTICIPANT FUNCTIONS
/****************************************************************************************************************************************** */

	public static void createParticipant(ParticipantManager participantManager, Scanner leer){
		
		int dni;
		String name, lastname, aux;
		Boolean specialNeeds;
		LocalDate birthDate;

		System.out.println("Insert dni: ");
		dni=Integer.parseInt(leer.nextLine());
		System.out.println("Insert name: ");
		name=leer.nextLine();
		System.out.println("Insert lastname: ");
		lastname=leer.nextLine();
		System.out.println("Insert birthdate with this format [YYYY-MM-DD]: ");
		birthDate=LocalDate.parse(leer.nextLine());
		System.out.println("Insert yes if the kid need special attention: ");	
		aux=leer.nextLine();
		
		if(aux.equals("yes")){
			specialNeeds = true;
		}
		else{
			specialNeeds=false;
		}
		System.out.println(specialNeeds.toString());
		participantManager.createParticipant(dni, name, lastname, birthDate, specialNeeds);
	}

	public static void deleteParticipant(ParticipantManager participantManager, Scanner leer){
		
		int dni;
		System.out.println("Insert the dni: ");
		dni=leer.nextInt();
		participantManager.delete(dni);				
		
		

	}

	public static void updateParticipant(ParticipantManager participantManager, Scanner leer){
		System.out.println("Insert dni:");
		int dni=Integer.parseInt(leer.nextLine());
		System.out.println("Insert the number of the value that you want to modificate:\n	1<-Name 2<-Lastname 3<-Birthdate 4<-SpecialAttention\n");
		int aux=Integer.parseInt(leer.nextLine());
		String toChange;
		switch (aux) {
			case 1:
				System.out.println("Insert name:");
				toChange=leer.nextLine();
				break;
			case 2:
				System.out.println("Insert lastname:");
				toChange=leer.nextLine();
				break;
			case 3:
				System.out.println("Insert birthdate:");
				toChange=leer.nextLine();
				if(!validarFormatoFecha(toChange)){
					System.out.println("This is not a date. Follow this format please: YYYY-MM-DD\n");
				}
				break;
			case 4:
				System.out.println("Insert yes if the kid need special attention:");
				toChange=leer.nextLine();
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

	public static int countParticipant(ParticipantManager participantManager){
		int count=participantManager.countParticipant();
		return count;
	}
/****************************************************************************************************************************************** */
}
