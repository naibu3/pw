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

    public String getAllMonitors(){
        String p="";

        MonitorDAO monitorInfo = new MonitorDAO();

        ArrayList<MonitorDTO> monitors = monitorInfo.getAllMonitors();
        for(MonitorDTO monitor: monitors){
            p += monitor.toString()+"\n";
        }

        return p;
    }
}
