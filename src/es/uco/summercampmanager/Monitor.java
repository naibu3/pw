package es.uco.summercampmanager;

public class Monitor extends Person {
	private String dateOfBirth_;
	private Boolean specialNeeds_;
	
	public Boolean getSpecialNeeds() { return specialNeeds_; }
	public String getDateOfBirth() { return dateOfBirth_; }
	public void setSpecialNeeds(Boolean specialNeeds) { specialNeeds_ = specialNeeds; }
	public void setDateOfBirth(String dateOfBirth) { dateOfBirth_ = dateOfBirth; }
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\nNombre: " + getNombre() + "\nApellidos: " + getApellidos() + "\nSpecial needs: " + specialNeeds_ + "\nDate of Birth: " + dateOfBirth_;
	}
	
	public Monitor() {}
	public Monitor(int id, String nombre, String apellidos, String dateOfBirth, Boolean specialNeeds) {
		super(id, nombre, apellidos);
		setSpecialNeeds(specialNeeds);
		setDateOfBirth(dateOfBirth);
	}
}
