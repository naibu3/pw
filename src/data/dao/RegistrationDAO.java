package data.dao;

import data.dto.RegistrationDTO;
import data.shared.DBConnection;

import java.time.LocalDate;

import data.shared.Type;

public class RegistrationDAO {
    public RegistrationDAO(){}

    /**
	 * Creates a new registration
	 * @param participant
	 * @return true on success
	 */
	public float createRegistration(RegistrationDTO myDTO) {
		float prize=0;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			
			prize = dbConnection.createRegistration(myDTO.getIdParticipant(), myDTO.getIdCamp(), myDTO.getRegistrationDate(), myDTO.getType());
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return prize;
	}

	/**
	 * Updates a registration in the database
	 * @param registrationId
	 * @param idParticipant ->dni
	 * @param idCamp
	 * @param registrationDate
	 * @param price
	 * @param type
	 * @return 1 on success
	 */
	public int updateRegistration(int idPartcipant, int idCamp, LocalDate registrationDate,float price, Type type){
		
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.updateRegistration(idPartcipant, idCamp, registrationDate, price, type);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}

		return (status);
		//		return (status == 1);
	}

	/**
	 * Delete a registration in the database
	 * @param id
	 * @return true on success
	 */
	public boolean deleteRegistration(int dni, int idCamp) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.deleteRegistration(dni, idCamp);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}
}
