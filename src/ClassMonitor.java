

public class ClassMonitor extends ClassPerson {
	private Boolean specialNeedsEducator_;
	
	public Boolean getSpecialNeedsEducator() { return specialNeedsEducator_; }
	public void setSpecialNeedsEducator(Boolean specialNeeds) { specialNeedsEducator_ = specialNeeds; }
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\nName: " + getNombre() + "\nLastName: " + getApellidos() + "\nSpecial needs: " + specialNeedsEducator_ + "\n";
	}
	
	public ClassMonitor() {}
	public ClassMonitor(int id, String nombre, String apellidos, Boolean specialNeeds) {
		super(id, nombre, apellidos);
		setSpecialNeedsEducator(specialNeeds);
	}
}
