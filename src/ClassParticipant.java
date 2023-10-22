import java.time.LocalDate;

/** Represents a Participant.
 * @version 1.0
*/
public class ClassParticipant extends ClassPerson {
    private LocalDate birthday_;
    private boolean special_attention_;

    /** Description.
	*/
    public ClassParticipant(int identifier, String name,String lastname,LocalDate birthday, boolean special_attention){
        setId(identifier);
        this.birthday_=birthday;
        this.special_attention_=special_attention;
        setNombre(name);
        setApellidos(lastname);
    }
    public ClassParticipant(){}

    /** Gets the birthday date.
     * @return birthday date
    */
    public LocalDate get_birthday(){
        return birthday_;
    }

    /** Gets the special attention status.
     * @return special attention status
    */
    public boolean get_special_attention(){
        return special_attention_;
    }

    /** Sets the birthday date.
     * @param birthday birthday date
    */
    public void set_birthday(LocalDate birthday){
        birthday_=birthday;
    }

    /** Sets the special attention status.
     * @param special_attention special attention status
    */
    public void set_special_attention(boolean special_attention){
        special_attention_=special_attention;
    }

    /** Dumps all class info into a string.
     * @return class info string
    */
    public String toString(){
        return "Id:"+getId()+"\nName:"+getNombre()+"\nLastname:"+getApellidos()+" \nBirth:"+get_birthday()+"\nSpecial attention:"+get_special_attention()+"\n";
    }

        
      /*  //ASÍ SE USA
        public static void main(String[] args){
            ClassParticipant yo = new ClassParticipant(1, LocalDate.now(), true);
            System.out.println(yo.toString());
        }
       */ 
}
