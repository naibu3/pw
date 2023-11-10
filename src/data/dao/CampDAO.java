package data.dao;

import java.util.Properties;
import java.util.logging.Level;
import java.util.Hashtable;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import data.dto.CampDTO;
import data.shared.DBConnection;

public class CampDAO {    
    private Properties pathSQL;

    /**
	 * Constructor
	 * @param dbServidor
	 * @param dbUsuario
	 * @param dbPass
	 * @param dbName
	 * @param pathSQL, sql.properties path
	 */
	
	public CampDAO() {
	}

     /**
	 * Creates a new Camp
	 * @param Camp
	 * @return true on success
	 */
	public boolean createCamp(CampDTO Camp) {
		int status = -1;
		try {
			pathSQL = new Properties();
			pathSQL.load(new FileInputStream("config.properties"));
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.createCamp(Camp.getIdCamp(), Camp.getbeginningDate(), Camp.getendingDate(), Camp.geteducativeLevel(), Camp.getmaxAssistants());
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}

	/**
	 * Retrieves all the users in the data base, no matter which type of user they are
	 * @return ArrayList<CampDTO> with all the registered users in the data base
	 */
	public ArrayList<CampDTO> getAllCamps(){
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		ArrayList<CampDTO> Camps = new ArrayList<>();
		
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			result = dbConnection.getAllCamps();
			
			dbConnection.closeConnection();

			if (result != null) {
				result.forEach((item) -> {
					CampDTO Camp = new CampDTO(Integer.parseInt(item.get("idCamp")),LocalDate.parse(item.get("begginingDate")),LocalDate.parse(item.get("endingDate"))/* ,Level.parse("level_"))*/;

					Camps.add(Camp);
				});
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return Camps;	
	}

	public Boolean deleteCamp(int idCamp) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteMonitor(idCamp)) {
            return true;
        }

        return false;
    }

	public Boolean updateCamp(int idCamp, LocalDate begginningDate, LocalDate endingDate, Level level,int maxAssistants) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateMonitor(idCamp, begginningDate, endingDate, level,maxAssistants)) {
            return true;
        }
        
        return false;
    }

}
