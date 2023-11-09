package data.dao;

import java.util.Hashtable;
import java.time.LocalDate;
import java.util.ArrayList;

import data.dto.ParticipantDTO;
import data.common.DBConnection;

public class ParticipantDAO {
    
    

    /**
	 * Constructor
	 * @param dbServidor
	 * @param dbUsuario
	 * @param dbPass
	 * @param dbName
	 * @param pathSQL, sql.properties path
	 */
	
	public ParticipantDAO() {
	}

     /**
	 * Creates a new participant
	 * @param Participant
	 * @return true on success
	 */
	public boolean createParticipant(ParticipantDTO Participant) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.createParticipant(Participant.getId(), Participant.getName(), Participant.getLastname(), Participant.getbirthdate(), Participant.getSpecialAttention());
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}

/**
	 * Removes an user from the data base
	 * @param participant
	 * @return true on success
	 */
	public boolean deleteParticipant (int id) {
		int status = -1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			
			status = dbConnection.deleteParticipant(id);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		
		return (status == 1);
	}

	/**
	 * Retrieves all the users in the data base, no matter which type of user they are
	 * @return ArrayList<ParticipantDTO> with all the registered users in the data base
	 */
	public ArrayList<ParticipantDTO> getAllParticipants(){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<ParticipantDTO> Participants = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			result = dbConnection.getAllParticipants();
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					ParticipantDTO Participant = new ParticipantDTO(Integer.parseInt(item.get("id")), item.get("name"), item.get("lastname"),
							LocalDate.parse(item.get("birthdate")), Boolean.parseBoolean(item.get("special_attention")));

					Participants.add(Participant);
				});
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return Participants;	
	}


	/**
	 * Updates Participant info
	 * @param id	 
	 * @param name
	 * @return 1 on success
	 */
	public boolean updateParticipant(int id, String toChange, int field){
		int status=-1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			
			switch (field) {
				case 1:	//change name
					status=dbConnection.updateParticipantName(id, toChange);
					break;
				case 2:
					status=dbConnection.updateParticipantLastname(id, toChange);
					break;
				case 3: 
					status=dbConnection.updateParticipantBirthdate(id, toChange);
					break;
				case 4: 
					status=dbConnection.updateParticipantSpecialNeeds(id, toChange);
					break;
			
				default:
					break;
			}
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}		

		return (status==1);
	}


	public int countParticipant(){
		int result = 0;
		
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			result = dbConnection.countParticipant();
			
			dbConnection.closeConnection();


			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;	
	}
}
