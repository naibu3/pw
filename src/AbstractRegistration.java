
import java.time.LocalDate;

public abstract class AbstractRegistration {
    
    private int idPartcipant_;
    private int idCamp_;
    private boolean specialNeeds;
    private LocalDate RegistrationDate_;
    private float price_;
    private EnumRegistrationType type_;
    private EnumRegistrtationTime time_;

    public boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(boolean special) {
        specialNeeds = special;
    }

    public int getIdParticipant() {
        return idPartcipant_;
    }
    public int getIdCamp() {
        return idCamp_;
    }
    public LocalDate getRegistrationDate() {
        return RegistrationDate_;
    }
    public float getPrice() {
        return price_;
    }
    public EnumRegistrationType getType() {
        return type_;
    }

    public EnumRegistrtationTime getRegistrationTime(){
        return time_;        
    }

    public void setIdParticipant_(int id) {
        idPartcipant_=id;
    }
    public void setIdCamp_(int id) {
        idCamp_=id;
    }
    public void setRegistrationDate(LocalDate date) {
        RegistrationDate_ = date;
    }
    public void setPrice(float price) {
        price_=price;
    }
    public void setType(EnumRegistrationType type) {
        type_=type;
    }
    public void setRegistrationTime(EnumRegistrtationTime time) {
        time_ = time;
    }

    public String toString(){
        String string = "IdParticipant:"+getIdParticipant()+
                        "\nIdCamp:"+getIdCamp()+
                        "\nRegistrationDate:"+getRegistrationDate()+
                        "\nPrice:"+getPrice()+
                        "\nType:"+getType()+"\n";
        return string;
    }

    public void cancel(){

    }
}   
