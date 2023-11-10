package data.dao;

import data.shared.DBConnection;
import data.dto.RegistrationDTO;

import java.time.LocalDate;

import data.dto.ParticipantDTO;
import data.shared.Type;

public class RegistrationDAO {
    public RegistrationDAO(){}

    /**
	 * Creates a new registration
	 * @param participant
	 * @return true on success
	 */
	public boolean createRegistration(int idParticipant, int idCamp, LocalDate registrationDate, float price, Type type, String timetable) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.createRegistration(idParticipant, idCamp, registrationDate, price, type);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}
}
