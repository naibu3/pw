package business;

import java.time.LocalDate;
import java.util.ArrayList;

import data.shared.Level;

import data.dao.CampDAO;
import data.dto.CampDTO;

public class CampManager {
    
    public void createCamp(int idCamp, LocalDate begginningDate, LocalDate endingDate, Level level, int maxAssistants){
        CampDTO newCamp= new CampDTO(idCamp, begginningDate, endingDate, level, maxAssistants);
        CampDAO Dao= new CampDAO();
        Dao.createCamp(newCamp);
    }

    public void deleteCamp(int id){
        CampDAO deleteDao=new CampDAO();
        deleteDao.deleteCamp(id);
    }

    public String getAllCamps(){
        String p="";

        CampDAO CampInfo= new CampDAO();

        ArrayList<CampDTO> camps= CampInfo.getAllCamps();
        for(CampDTO camp: camps){
            p += camp.toString()+"\n";
            //si no fufa quita el if
            if(camp.getresponsiblespecialMonitor()==1)
                p+="Need special monitor\n";
        }

        return p;
    }
    public void add_special_monitor(int idM, int idC){
        CampDAO dao= new CampDAO();
        dao.add_special_monitor(idM, idM);
    }


}
