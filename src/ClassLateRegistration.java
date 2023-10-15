import java.time.LocalDate;

/** Represents an abstract Registration.
 * @version 1.0
*/
public class ClassLateRegistration extends AbstractRegistration{
    public ClassLateRegistration(int id, int id_camp, LocalDate registrationDate, float price, EnumRegistrationType type, EnumRegistrtationTime time, boolean specialAttention) {
        super(id, id_camp, registrationDate, price, type, time, specialAttention);
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }

    public ClassLateRegistration(){
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }

    
}
