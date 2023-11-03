
/** Description.
 * @version 1.0
*/
public class Monitor extends Person {

	private Boolean specialNeedsEducator_;
	
	/** Description.
	*/
	public Boolean getSpecialNeedsEducator() { return specialNeedsEducator_; }

	/** Description.
	*/
	public void setSpecialNeedsEducator(Boolean specialNeeds) { specialNeedsEducator_ = specialNeeds; }
	
	/** Description.
	*/
	@Override
	public String toString() {
		return "ID: " + getId() + "\nName: " + getNombre() + "\nLastname: " + getApellidos() + "\nSpecial educator: " + specialNeedsEducator_ + "\n";
	}
	
	/** Description.
	*/
	public Monitor() {}

	/** Description.
	*/
	public Monitor(int id, String nombre, String apellidos, Boolean specialNeeds) {
		super(id, nombre, apellidos);
		setSpecialNeedsEducator(specialNeeds);
	}
}
