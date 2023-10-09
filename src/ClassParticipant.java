

public class ClassParticipant extends Person {
    private String birthday_;
    private boolean special_attention_;

    public ClassParticipant(){}
    public ClassParticipant(int identifier, String birthday, boolean special_attention){
        setId(identifier);
        this.birthday_=birthday;
        this.special_attention_=special_attention;
    }

    public String get_birthday(){
        return birthday_;
    }
    public boolean get_special_attention(){
        return special_attention_;
    }
    public void set_birthday(String birthday){
        birthday_=birthday;
    }
    public void set_special_attention(boolean special_attention){
        special_attention_=special_attention;
    }
    public String toString(){
        return "id:"+getId()+" birth:"+get_birthday()+" special attention:"+get_special_attention();
    }

        
        /*//ASÍ SE USA
        public static void main(String[] args){
            ClassParticipant yo = new ClassParticipant(1, "1990-01-01", true);
            System.out.println(yo.toString());
        }
        */
}
