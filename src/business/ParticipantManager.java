package business;

import java.util.ArrayList;
import java.time.LocalDate;

import data.dao.ParticipantDAO;
import data.dto.ParticipantDTO;

/**
 * A user manager that communicates with DAO and uses DTO to implement functionalities related to users.
 * @author Ángela González
 * */

public class ParticipantManager {
    public ParticipantManager(){

        
    }

    public void createParticipant(int id, String name, String lastname, LocalDate birthdDate, boolean specialAttention){
        ParticipantDTO newPart=new ParticipantDTO(id,name,lastname, birthdDate, specialAttention);
        ParticipantDAO Dao= new ParticipantDAO();
        Dao.createParticipant(newPart);
    }
  
    public void delete(int id){
        ParticipantDAO deleteDao=new ParticipantDAO();
        deleteDao.deleteParticipant(id);
    }

    public String getAllParticipants(){
        String p="";

        ParticipantDAO partInfo= new ParticipantDAO();

        ArrayList<ParticipantDTO> participants= partInfo.getAllParticipants();
        for(ParticipantDTO part: participants){
            p += part.toString()+"\n";
        }

        return p;
    }    
  
    public void updateParticipant(int dni, String toChange, int field){
        ParticipantDAO update=new ParticipantDAO();
        update.updateParticipant(dni, toChange, field);
    }

    public int countParticipant(){
        ParticipantDAO partInfo= new ParticipantDAO();

        int participants= partInfo.countParticipant();
        return participants;
    }

}
