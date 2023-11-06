package data.dao;

import java.util.Properties;
import java.util.Hashtable;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import data.dto.ParticipantDTO;
import data.common.DBConnection;

public class ParticipantDAO {
    
    
    private Properties pathSQL;

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
			pathSQL = new Properties();
			pathSQL.load(new FileInputStream("config.properties"));
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

}
