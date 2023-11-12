package business;

import java.time.LocalDate;
import java.util.ArrayList;

import data.shared.Level;
import data.dao.ActivityDAO;
import data.dao.CampDAO;
import data.dao.MonitorDAO;
import data.dto.CampDTO;
import data.dto.MonitorDTO;

public class CampManager {
    
    public void createCamp(int idCamp, LocalDate begginningDate, LocalDate endingDate, Level level, int maxAssistants){
        CampDTO newCamp= new CampDTO(idCamp, begginningDate, endingDate, level, maxAssistants);
        CampDAO Dao= new CampDAO();
        Dao.createCamp(newCamp);
    }

    public Boolean deleteCamp(int id){
        CampDAO deleteDao=new CampDAO();
        boolean r=deleteDao.deleteCamp(id);
        return r;
    }

    public void updateCamp(int id,LocalDate beginningDate,LocalDate endingDate, Level level, int maxAssistants) {
        CampDAO CampInfo = new CampDAO();
        CampInfo.updateCamp(id, beginningDate, endingDate, level, maxAssistants);        
    }

    public String getAllCamps(){
        String p="";

        CampDAO CampInfo= new CampDAO();

        ArrayList<CampDTO> camps= CampInfo.getAllCamps();
        for(CampDTO camp: camps){
            p += camp.toString();
            //si no fufa quita el if
            /*
             * 
             */
             if(camp.getresponsiblespecialMonitor()==1)
                 p+="Need special monitor";
            
            p+="\n";
        }

        return p;
    }

    

    public int addSpecialMonitor(int idM, int idC){
        CampDAO dao= new CampDAO();
        int r=dao.add_special_monitor(idM, idM);
        return r;
    }

    /**
     * Get all the monitors in the database as a string
     * @return String with all the monitors 
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

    public MonitorDTO getMonitor(int dni) {
        MonitorDAO monitorInfo = new MonitorDAO();
        return monitorInfo.getMonitor(dni);
    }
}
