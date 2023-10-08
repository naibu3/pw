package es.uco.summercampmanager;

public class Monitor extends Person {
	private Boolean specialNeedsEducator_;
	
	public Boolean getSpecialNeedsEducator() { return specialNeedsEducator_; }
	public void setSpecialNeedsEducator(Boolean specialNeeds) { specialNeedsEducator_ = specialNeeds; }
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\nNombre: " + getNombre() + "\nApellidos: " + getApellidos() + "\nSpecial needs: " + specialNeedsEducator_ + "\n";
	}
	
	public Monitor() {}
	public Monitor(int id, String nombre, String apellidos, Boolean specialNeeds) {
		super(id, nombre, apellidos);
		setSpecialNeedsEducator(specialNeeds);
	}
}
