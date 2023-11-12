package data.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Hashtable;
import java.time.LocalDate;

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
                                                                Level.valueOf(camp.get("educationallevel")),
																Integer.valueOf(camp.get("maxparticipants")));
				String str=camp.get("activities");

				List<String> nuevaLista = new ArrayList<>(Arrays.asList(str.split(",")));
                currentcamp.setactivity(nuevaLista);
				//si no va quita el if
				/*
				* 
				*/
				if(Boolean.valueOf(camp.get("special_monitor")))
				currentcamp.setresponsiblespecialMonitor(1);
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
	public Boolean updateCamp(int id,LocalDate beginningDate,LocalDate endingDate,Level level,int max) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateCamp(id,beginningDate,endingDate,level,max)) {
            return true;
        }
        
        return false;
    }

	public int add_special_monitor(int idM, int idC){
		int status = -1;
		try {			
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.add_special_monitor(idM, idC);
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status);
	}

}
