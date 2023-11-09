package data.dto;

import java.time.LocalDate;

/**
 * A DTO for the registration concept
 * @author Ángela González
 * */

public class RegistrationDTO {
    protected int id;
    protected int idPartcipant_;
    protected int idCamp_;
    protected LocalDate RegistrationDate_;
    protected float price_;
    //protected EnumRegistrationType type_;
    //protected EnumRegistrtationTime time_;

    public RegistrationDTO(){}
    public RegistrationDTO(int id_part, int id_camp, LocalDate registrationDate, float price){ //faltan EnumRegistrationType type, EnumRegistrtationTime time
        //setId(id);
        setIdParticipant_(id_part);
        setIdCamp_(id_camp);
        setRegistrationDate(registrationDate);
        setPrice(price);
        //setType(type);
        //setRegistrationTime(time);
        
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
     public EnumRegistrationType getType() {
         return type_;
        }        
    */
    
    /** Gets registration time
     * @return time_
     public EnumRegistrtationTime getRegistrationTime(){
         return time_;        
        }
    */

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
     public void setType(EnumRegistrationType type) {
         type_=type;
        }
    */

    /** Sets registration time
     * @param time
     public void setRegistrationTime(EnumRegistrtationTime time) {
         time_ = time;
        }
    */

    /** Dumps all class info into an string
     * @return string
    */
    public String toString(){
        String string = "IdParticipant:"+getIdParticipant()+
                        "\nIdCamp:"+getIdCamp()+
                        "\nRegistrationDate:"+getRegistrationDate()+
                        "\nPrice:"+getPrice()+"\n";
                        //"\nType:"+getType()+
                        //"\nTime:"+getRegistrationTime() + 
        return string;
    }

}
