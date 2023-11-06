import java.time.LocalDate;

/** Represents an EarlyRegistration.
 * @version 1.0
*/
public class PartialRegistration extends AbstractRegistration{

    /** Description.
	*/
    public PartialRegistration(int id, int id_camp, LocalDate registrationDate, float price, EnumRegistrationType type, EnumRegistrtationTime time, boolean specialAttention) {
        super(id, id_camp, registrationDate, price, type, time, specialAttention);
        setRegistrationDate(LocalDate.now());
        //setRegistrationTime(EnumRegistrtationTime.Early);
    }

    /** Description.
	*/
    public PartialRegistration(){
        super();
        setRegistrationDate(LocalDate.now());
        //setRegistrationTime(EnumRegistrtationTime.Early);
    }
}
