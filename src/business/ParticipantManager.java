package business;

import java.util.ArrayList;

import data.dao.ParticipantDao;
import data.dto.ParticipantDTO;

/**
 * A user manager that communicates with DAO and uses DTO to implement functionalities related to users.
 * @author Ángela González
 * */

public class ParticipantManager {
    public ParticipantManager(){
        
    }

    public String getAllParticipants(){
        String p="";

        ParticipantDao partInfo= new ParticipantDao();

        ArrayList<ParticipantDTO> participants= partInfo.getAllParticipants();
        for(ParticipantDTO part: participants){
            p += part.toString()+"\n";
        }

        return p;
    }
}
