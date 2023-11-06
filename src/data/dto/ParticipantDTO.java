package data.dto;

import java.time.LocalDate;

/**
 * A DTO for the user concept
 * @author Ángela González
 * */

public class ParticipantDTO {
    protected int id_;
	protected String name_;
    protected String lastname_;
    protected LocalDate birthdate_;
    protected boolean special_attention_;
	
	public ParticipantDTO() {
		
	}
	
	public ParticipantDTO(int identifier, String name,String lastname,LocalDate birthdate, boolean special_attention){
		this.id_=identifier;
        this.name_=name;
        this.lastname_=lastname;
        this.birthdate_=birthdate;
        this.special_attention_=special_attention;

	}

	public int getId(){		return this.id_;}

	public String getName() {
		return this.name_;
	}

	public void setName(String name) {
		this.name_ = name;
	}

	public String getLastname() {
		return this.lastname_;
	}

	public void setlastname(String lastname) {
		this.lastname_ = lastname;
	}

	public LocalDate getbirthdate() {
		return this.birthdate_;
	}

	public void setbirthdate(LocalDate birthdate) {
		this.birthdate_ = birthdate;
	}

	public boolean getSpecialAttention(){		return special_attention_;}

	public void setSpecialAttention(boolean special_attention){
		this.special_attention_=special_attention;
	}
	
	public String toString() {
        String ParticipantInfo = " Id: " + this.id_ +" name: " + this.name_ + " lastname:" + this.lastname_ + " birthdate: " + this.birthdate_ + " special attention: " + this.special_attention_ ;
		return ParticipantInfo;
	}
}
