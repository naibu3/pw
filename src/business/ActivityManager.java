package business;

import java.util.ArrayList;

import data.dao.ActivityDAO;
import data.dao.MonitorDAO;
import data.dto.ActivityDTO;
import data.dto.MonitorDTO;
import data.shared.DBConnection;
import data.shared.Level;

public class ActivityManager {
    
    public ActivityManager() {}

    /**
     * Get all the activities in the database as a string
     * @return a string containing all activities
     */
    public String getAllActivities(){
        String p="";

        ActivityDAO activityInfo = new ActivityDAO();

        ArrayList<ActivityDTO> activities = activityInfo.getAllActivities();
        for(ActivityDTO activity: activities){
            p += activity.toString()+"\n";
        }

        return p;
    }

    /**
	 * Creates a new activity in the database
	 * @param name
	 * @param level
	 * @param timetable
	 * @param max
	 * @param monitors_n
	 * @return true on success
	 */
    public Boolean createActivity(String name, Level level, String timetable, int max, int monitors_n) {
        ActivityDAO activityInfo = new ActivityDAO();
        //ActivityDTO createdActivity = new ActivityDTO();

        if (activityInfo.createActivity(name, level, timetable, max, monitors_n)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Delete an activity from the database
     * @param id
     * @return true on success, otherwise false
     */
    public Boolean deleteActivity(String name) {
        ActivityDAO activityInfo = new ActivityDAO();
        if (activityInfo.deleteActivity(name)) {
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
        ActivityDAO activityInfo = new ActivityDAO();
        if (activityInfo.updateActivity(name, level, timetable, max, monitors_n)) {
            return true;
        }

        return false;
    }

    /**
	 * Adds a monitor into activity
	 * @param activityName
	 * @param monitorId
	 * @return true on success
	 */
    public Boolean addMonitorToActivity(String activityName, int monitorId) {
        ActivityDAO activityInfo = new ActivityDAO();
        if (activityInfo.addMonitorToActivity(activityName, monitorId)) {
            return true;
        }

        return false;
    }
}
