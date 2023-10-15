import java.time.LocalDate;

public class ClassParticipant extends ClassPerson {
    private LocalDate birthday_;
    private boolean special_attention_;

    public ClassParticipant(int identifier, String name,String lastname,LocalDate birthday, boolean special_attention){
        setId(identifier);
        this.birthday_=birthday;
        this.special_attention_=special_attention;
        setNombre(name);
        setApellidos(lastname);
    }
    public ClassParticipant(){}

    public LocalDate get_birthday(){
        return birthday_;
    }
    public boolean get_special_attention(){
        return special_attention_;
    }
    public void set_birthday(LocalDate birthday){
        birthday_=birthday;
    }
    public void set_special_attention(boolean special_attention){
        special_attention_=special_attention;
    }
    public String toString(){
        return "\nId:"+getId()+"\nName:"+getNombre()+"\nLastname:"+getApellidos()+" \nBirth:"+get_birthday()+"\nSpecial attention:"+get_special_attention();
    }

        
      /*  //ASÍ SE USA
        public static void main(String[] args){
            ClassParticipant yo = new ClassParticipant(1, LocalDate.now(), true);
            System.out.println(yo.toString());
        }
       */ 
}
