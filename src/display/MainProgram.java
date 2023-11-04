import business.ParticipantManager;

public class MainProgram {
    public static void main(String[] args) {
		System.out.println("Main program: Print participant");
		ParticipantManager participantManager = new ParticipantManager();
		String participantsInfo = participantManager.getAllParticipants();
		System.out.println(participantsInfo);
	}
}
