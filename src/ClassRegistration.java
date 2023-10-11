import java.time.LocalDate;

public class ClassRegistration {
    
    private int idPartcipant_;
    private int idCamp_;

    private LocalDate RegistrationDate_;
    private float price_;
    private EnumRegistrationType type_;


    public int geyIdParticipant_() {
        return idPartcipant_;
    }
    public int geyIdCamp_() {
        return idCamp_;
    }
    public LocalDate getRegistrationDate() {
        return RegistrationDate_;
    }
    public float getPrice() {
        return price_;
    }
    public EnumRegistrationType geyType_() {
        return type_;
    }
}   
