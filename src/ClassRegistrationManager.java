import java.util.ArrayList;
import java.util.List;

public class ClassRegistrationManager {
    private List<AbstractRegistration> registrations = new ArrayList<AbstractRegistration>();

    private boolean isUserRegistered(int id, int camp) {
        for (AbstractRegistration registration : registrations) {
            if (registration.getIdParticipant() == id && registration.getIdCamp() == camp) {
                return true;
            }
        }
        return false;
    }

    public boolean createEarlyRegistration(int id, int id_camp, boolean special, EnumRegistrationType type, ClassCampsManager campsManager, NewClassParticipantManager participantManager) {
        if (! isUserRegistered(id, id_camp) && participantManager.participantExists(id)) {
            ClassEarlyRegistration registration = new ClassEarlyRegistration();
            registration.setIdParticipant_(id);
            registration.setIdCamp_(id_camp);
            registration.setType(type);
            registration.setSpecialNeeds(special);
            if (type == EnumRegistrationType.Full) registration.setPrice(300); 
            else registration.setPrice(100);
            registrations.add(registration);
            return true;
        }
        
        return false;
    }

    public boolean createLateRegistration(int id, int id_camp, boolean special, EnumRegistrationType type, NewClassParticipantManager participantManager) {
        if (! isUserRegistered(id, id_camp) && participantManager.participantExists(id)) {
            ClassLateRegistration registration = new ClassLateRegistration();
            registration.setIdParticipant_(id);
            registration.setIdCamp_(id_camp);
            registration.setType(type);
            registration.setSpecialNeeds(special);
            if (type == EnumRegistrationType.Full) registration.setPrice(300); 
            else registration.setPrice(100);
            registrations.add(registration);
            return true;
        }
        
        return true;
    }
}
