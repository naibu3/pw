package data.dao;

import java.util.Hashtable;
import java.time.LocalDate;
import java.util.ArrayList;

import data.dto.CampDTO;
import data.shared.DBConnection;
import data.shared.Level;

public class CampDAO {    

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
		ArrayList<CampDTO> result = new ArrayList<CampDTO>();
		
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			
			
			ArrayList<Hashtable<String, String>> camps = dbConnection.getAllCamps();
            if (camps == null) {
                return null;
            }

            for (Hashtable<String, String> camp: camps) {
                CampDTO currentcamp = new CampDTO(Integer.valueOf(camp.get("id")),
																LocalDate.parse(camp.get("start")),
																LocalDate.parse(camp.get("end")),
                                                                Level.valueOf(camp.get("timetable")),
																Integer.valueOf(camp.get("maxAssistants")));
                result.add(currentcamp);
            }
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;	
	}

	public Boolean deleteCamp(int idCamp) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteMonitor(idCamp)) {
            return true;
        }

        return false;
    }
	public boolean updateCamp(int id, String toChange, int field){
		int status=-1;
		try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();
			
			switch (field) {
				case 1:	//change name
					status=dbConnection.updateCampBeginningDate(id, toChange);
					break;
				case 2:
					status=dbConnection.updateCampEndingDate(id, toChange);
					break;
				case 3: 
					status=dbConnection.updateCampLevel(id, toChange);
					break;
				case 4: 
					status=dbConnection.updateCampMaxAssistants(id, toChange);
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

}
