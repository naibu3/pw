import java.time.LocalDate;

/** Represents an EarlyRegistration.
 * @version 1.0
*/
public class ClassEarlyRegistration extends AbstractRegistration{
    
    public ClassEarlyRegistration(){
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }
}
