import java.util.ArrayList;

import business.ParticipantManager;
import data.dao.MonitorDAO;
import data.dto.MonitorDTO;

public class MainProgram {
    public static void main(String[] args) {
		System.out.println("Main program: Print participant");
		ParticipantManager participantManager = new ParticipantManager();
		String participantsInfo = participantManager.getAllParticipants();
		System.out.println(participantsInfo);

		MonitorDAO monitors = new MonitorDAO();
		ArrayList<MonitorDTO> monitorList = monitors.getAllMonitors();

		for (MonitorDTO monitor: monitorList) {
			System.out.println(monitor.toString());
		}
	}
}
