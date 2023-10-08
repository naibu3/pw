/*Class Participant: A class representing a person attending the summer camp. The class
must contain the following attributes:
•Person identifier, using integer type.
•First name and surname, using string type.
•Date of birth (to manage age groups).
•A boolean value to specify if special attention is require or not.
As for methods, the class should provide the following ones:
•Empty constructor (without parameters).
•Parameterised constructor, whose parameters are all the attributes of the class.
•get/set methods for all attributes.
•toString method to print the information of the participant. */
public class ClassParticipant {
    private int identifier_;
    private String birthday_;
    private boolean special_attention_;

    public ClassParticipant(){}
    public ClassParticipant(int identifier, String birthday, boolean special_attention){
        this.identifier_=identifier;
        this.birthday_=birthday;
        this.special_attention_=special_attention;
    }

    public int get_id(){
        return identifier_;
    }
    public String get_birthday(){
        return birthday_;
    }
    public boolean get_special_attention(){
        return special_attention_;
    }
    public void set_id(int identifier){
        identifier_=identifier;
    }
    public void set_birthday(String birthday){
        birthday_=birthday;
    }
    public void set_special_attention(boolean special_attention){
        special_attention_=special_attention;
    }
    public String toString(){
        return "id:"+get_id()+" birth:"+get_birthday()+" special attention:"+get_special_attention();
    }

        /*
        ASÍ SE USA
        public static void main(String[] args){
            ClassParticipant yo = new ClassParticipant(1, "1990-01-01", true);
            System.out.println(yo.toString());
        }
        */
}
