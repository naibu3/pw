import java.time.LocalDate;

/** Represents an abstract Registration.
 * @version 1.0
*/
public class ClassLateRegistration extends AbstractRegistration{

    public ClassLateRegistration(){
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }

    
}
