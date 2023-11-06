package business;

import java.util.ArrayList;

import data.dao.MonitorDAO;
import data.dto.MonitorDTO;

/**
 * A user manager that communicates with DAO and uses DTO to implement functionalities related to users.
 * @author Ignacio Caballero
 * */

public class MonitorManager {
    public MonitorManager() {}

    /**
     * Get all the monitors in the database as a string
     * @return
     */
    public String getAllMonitors(){
        String p="";

        MonitorDAO monitorInfo = new MonitorDAO();

        ArrayList<MonitorDTO> monitors = monitorInfo.getAllMonitors();
        for(MonitorDTO monitor: monitors){
            p += monitor.toString()+"\n";
        }

        return p;
    }

    /**
     * Creates a new Monitor in the database.
     * @param dni
     * @param name
     * @param lastName
     * @param specialEducator
     * @return true on success, otherwise false.
     */
    public Boolean createMonitor(int dni, String name, String lastName, Boolean specialEducator) {
        MonitorDAO monitorInfo = new MonitorDAO();
        MonitorDTO createdMonitor = new MonitorDTO(dni, name, lastName, specialEducator);

        if (monitorInfo.createMonitor(createdMonitor)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Delete a monitor from the database by its DNI
     * @param dni
     * @return true on success, otherwise false
     */
    public Boolean deleteMonitor(int dni) {
        MonitorDAO monitorInfo = new MonitorDAO();
        if (monitorInfo.deleteMonitor(dni)) {
            return true;
        }

        return false;
    }

    /**
     * Update a monitor data based on its DNI and the data passed. The monitor DNI may not be updated by an updateMonitor instruction
     * @param dni
     * @param name
     * @param lastName
     * @param specialEducator
     * @return true on success, otherwise false
     */
    public Boolean updateMonitor(int dni, String name, String lastName, Boolean specialEducator) {
        MonitorDAO monitorInfo = new MonitorDAO();
        if (monitorInfo.updateMonitor(dni, name, lastName, specialEducator)) {
            return true;
        }

        return false;
    }
}
