import java.time.LocalDate;

public class ClassEarlyRegistration extends AbstractRegistration{
    
    public ClassEarlyRegistration(){
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }
}
