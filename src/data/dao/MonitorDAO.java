package data.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import data.dto.MonitorDTO;
import data.shared.DBConnection;

public class MonitorDAO {
    public MonitorDAO() {}

    /**
	 * Creates a new monitor
     * @author Ignacio Caballero
	 * @param monitor
	 * @return true on success
	 */
    public Boolean createMonitor(MonitorDTO monitor) {
        try {
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

            dbConnection.createMonitor(monitor.getId(), monitor.getName(), monitor.getLastName(), monitor.getSpecialNeedsEducator());
            dbConnection.closeConnection();

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Delete a monitor from the database
     * @param dni
     * @author Ignacio Caballero
     * @return true on success, otherwise false
     */
    public Boolean deleteMonitor(int dni) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteMonitor(dni)) {
            return true;
        }

        return false;
    }

    public Boolean updateMonitor(int dni, String name, String lastName, Boolean specialEducator) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateMonitor(dni, name, lastName, specialEducator)) {
            return true;
        }
        
        return false;
    }

    /**
     * Get all the monitors in the database
     * @author Ignacio Caballero
     * @return ArrayList<MonitorDTO> with all the monitors in the database.
     */
    public ArrayList<MonitorDTO> getAllMonitors() {
        ArrayList<MonitorDTO> result = new ArrayList<MonitorDTO>();
        try {
            DBConnection dbConnection = new DBConnection();
            dbConnection.getConnection();

            ArrayList<Hashtable<String, String>> monitors = dbConnection.getAllMonitors();
            if (monitors == null) {
                return null;
            }

            for (Hashtable<String, String> monitor: monitors) {
                MonitorDTO currentMonitor = new MonitorDTO(Integer.parseInt(monitor.get("id")), monitor.get("name"), monitor.get("lastname"), Boolean.parseBoolean(monitor.get("specialeducator")));
                result.add(currentMonitor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

        return result;
    }
}