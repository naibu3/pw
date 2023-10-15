import java.time.LocalDate;

public class ClassLateRegistration extends AbstractRegistration{

    public ClassLateRegistration(){
        setRegistrationDate(LocalDate.now());
        setRegistrationTime(EnumRegistrtationTime.Early);
    }

    
}
