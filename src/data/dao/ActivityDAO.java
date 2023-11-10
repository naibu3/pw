package data.dao;

import java.util.ArrayList;
import java.util.Hashtable;

import data.shared.DBConnection;
import data.dto.ActivityDTO;

import data.shared.Level;

public class ActivityDAO {
    
    public ActivityDAO(){}

    /**
	 * Creates a new activity
	 * @param name
	 * @param level
	 * @param timetable
	 * @param max
	 * @param monitors_n
	 * @return true on success
	 */
    public Boolean createActivity(String name, Level level, String timetable, int max, int monitors_n) {
        try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

            dbConnection.createActivity(name, level, timetable, max, monitors_n);
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
    public Boolean deleteActivity(String name) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteActivity(name)) {
            return true;
        }

        return false;
    }

    /**
     * Update an activity from the database
     * @param name
	 * @param level
	 * @param timetable
	 * @param max
	 * @param monitors_n
     * @return true on success, otherwise false
     */
    public Boolean updateActivity(String name, Level level, String timetable, int max, int monitors_n) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateActivity(name, level, timetable, max, monitors_n)) {
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
                ActivityDTO currentActivity = new ActivityDTO(activity.get("name"),
                                                                Level.valueOf(activity.get("timetable")),
                                                                activity.get("educationallevel"),
                                                                Integer.parseInt(activity.get("maxparticipants")),
                                                                Integer.parseInt(activity.get("monitorsrequired")));
                result.add(currentActivity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return result;
    }
}
