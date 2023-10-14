import java.util.ArrayList;
import java.util.List;

public class ClassParticipantManager {
    private List<ClassParticipant> participants;

    public ClassParticipantManager() {
        participants = new ArrayList<>();
    }

    public void enrollParticipant(ClassParticipant participant) {
        if (!participants.contains(participant)) {
            participants.add(participant);
        }
    }

    public void modifyParticipant(int id, String newBirthday, boolean newSpecialAttention) {
            ClassParticipant participantFounded = null;
            for (ClassParticipant participant : participants) {
                if (participant.getId() == id) {
                    participantFounded = participant;
                    break;
                }
            }
    
            if (participantFounded != null) {
                participantFounded.set_birthday(newBirthday);
                participantFounded.set_special_attention(newSpecialAttention);
            }
        }
    

    public List<ClassParticipant> listParticipants() {
        return participants;
    }
}
