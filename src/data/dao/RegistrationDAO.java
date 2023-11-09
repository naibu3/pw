package data.dao;

import data.common.DBConnection;
import data.dto.RegistrationDTO;

public class RegistrationDAO {
    public RegistrationDAO(){}

    /**
	 * Creates a new registration
	 * @param registration
	 * @return true on success
	 */
	public boolean createRegistration(RegistrationDTO registration) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.createRegistration(registration.getIdParticipant(), registration.getIdCamp(), registration.getRegistrationDate(), registration.getPrice(), "full");
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}
}
