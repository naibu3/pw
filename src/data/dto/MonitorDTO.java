package data.dto;

/**
 * A DTO for the monitor concept
 * @author Ignacio Caballero
 * */

public class MonitorDTO {
    private int dni_;
    private String name_;
    private String lastName_;
    private Boolean specialEducator_;

    public MonitorDTO(int dni, String name, String lastName, Boolean specialEductator) {
        dni_ = dni;
        name_ = name;
        lastName_ = lastName;
        specialEducator_ = specialEductator;
    }

    public int getId() { return dni_; };
    public String getName() { return name_; };
    public String getLastName() { return lastName_; };
    public Boolean getSpecialNeedsEducator() { return specialEducator_; };

    public void setId(int dni) { dni_ = dni; };
    public void setName(String name) { name_ = name; };
    public void setLastNane(String lastName) { lastName_ = lastName; };
    public void setSpecialNeedsEducator(Boolean specialEducator) { specialEducator_ = specialEducator; };
}
