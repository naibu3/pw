package data.dto;
import java.time.LocalDate;

/** Represents a Person.
 * @version 1.0
*/
public abstract class AbstractRegistration {
    
    private int idPartcipant_;
    private int idCamp_;
    private boolean specialNeeds;
    private LocalDate RegistrationDate_;
    private float price_;
    private EnumRegistrationType type_;
    private EnumRegistrtationTime time_;

    /** Builder
     * @param id
     * @param id_camp
     * @param registrationDate
     * @param price
     * @param type
     * @param time
     * @param specialAttention
    */
    public AbstractRegistration(int id, int id_camp, LocalDate registrationDate, float price, EnumRegistrationType type, EnumRegistrtationTime time, boolean specialAttention) {
        setIdParticipant_(id);
        setIdCamp_(id_camp);
        setRegistrationDate(registrationDate);
        setPrice(price);
        setType(type);
        setRegistrationTime(time);
        setSpecialNeeds(specialAttention);
    }

    /** Builder
    */
    public AbstractRegistration() {}

    /** Gets special needs status
     * @return specialNeeds
    */
    public boolean getSpecialNeeds() {
        return specialNeeds;
    }

    /** Sets special needs status
     * @param special special needs status
    */
    public void setSpecialNeeds(boolean special) {
        specialNeeds = special;
    }

    /** Gets participant's id
     * @return idParticipant_
    */
    public int getIdParticipant() {
        return idPartcipant_;
    }

    /** Gets camp's id
     * @return idCamp_
    */
    public int getIdCamp() {
        return idCamp_;
    }

    /** Gets registration date
     * @return RegistrationDate_
    */
    public LocalDate getRegistrationDate() {
        return RegistrationDate_;
    }

    /** Gets price
     * @return price_
    */
    public float getPrice() {
        return price_;
    }

    /** Gets type
     * @return type_
    */
    public EnumRegistrationType getType() {
        return type_;
    }

    /** Gets registration time
     * @return time_
    */
    public EnumRegistrtationTime getRegistrationTime(){
        return time_;        
    }

    /** Sets participant's id
     * @param id
    */
    public void setIdParticipant_(int id) {
        idPartcipant_=id;
    }

    /** Sets camp's id
     * @param id
    */
    public void setIdCamp_(int id) {
        idCamp_=id;
    }

    /** Sets registration date
     * @param date
    */
    public void setRegistrationDate(LocalDate date) {
        RegistrationDate_ = date;
    }

    /** Sets price
     * @param price
    */
    public void setPrice(float price) {
        price_=price;
    }

    /** Sets type
     * @param type
    */
    public void setType(EnumRegistrationType type) {
        type_=type;
    }

    /** Sets registration time
     * @param time
    */
    public void setRegistrationTime(EnumRegistrtationTime time) {
        time_ = time;
    }

    /** Dumps all class info into an string
     * @return string
    */
    public String toString(){
        String string = "IdParticipant:"+getIdParticipant()+
                        "\nIdCamp:"+getIdCamp()+
                        "\nRegistrationDate:"+getRegistrationDate()+
                        "\nPrice:"+getPrice()+
                        "\nType:"+getType()+
                        "\nTime:"+getRegistrationTime() + 
                        "\nSpecialNeeds"+getSpecialNeeds()+"\n";
        return string;
    }

    /** Cancels registration
    */
    public void cancel(){

    }
}   
