package business;

import java.time.LocalDate;
import java.util.ArrayList;

import data.shared.Level;

import data.dao.CampDAO;
import data.dao.ParticipantDAO;
import data.dto.CampDTO;
import data.dto.ParticipantDTO;

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
        }

        return p;
    }


}
