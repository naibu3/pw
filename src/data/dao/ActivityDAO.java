package data.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import data.common.DBConnection;
import data.dto.ActivityDTO;
import data.dto.MonitorDTO;

public class ActivityDAO {
    
    public ActivityDAO(){}

    /**
	 * Creates a new activity
	 * @param activity
	 * @return true on success
	 */
    public Boolean createActivity(ActivityDTO activity) {
        try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

            dbConnection.createActivity(/*TODO*/);
            dbConnection.closeConnection();

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Delete an activity from the database
     * @param id
     * @return true on success, otherwise false
     */
    public Boolean deleteActivity(int id) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteActivity(id)) {  //TODO
            return true;
        }

        return false;
    }

    //TODO
    public Boolean updateActivity(/*TODO*/) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateActivity(/*TODO*/)) {
            return true;
        }
        
        return false;
    }

    /**
     * Get all the activities in the database
     * @return ArrayList<ActivityDTO> with all the activities in the database.
     */
    public ArrayList<ActivityDTO> getAllActivities() {
        ArrayList<ActivityDTO> result = new ArrayList<ActivityDTO>();
        try {
            DBConnection dbConnection = new DBConnection();
            dbConnection.getConnection();

            ArrayList<Hashtable<String, String>> activities = dbConnection.getAllActivities();
            if (activities == null) {
                return null;
            }

            for (Hashtable<String, String> activity: activities) {
                ActivityDTO currentActivity = new ActivityDTO(/*TODO*/);
                result.add(currentActivity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return result;
    }
}
