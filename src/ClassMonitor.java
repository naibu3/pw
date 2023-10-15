/** Represents a Monitor.
 * @version 1.0
*/
public class ClassMonitor extends ClassPerson {
	private Boolean specialNeedsEducator_;
	
	/** Gets the special needs educator.
     * @return special needs educator
    */
	public Boolean getSpecialNeedsEducator() { return specialNeedsEducator_; }
	
	/** Sets the special needs educator.
     * @param specialNeeds special needs educator
    */
	public void setSpecialNeedsEducator(Boolean specialNeeds) { specialNeedsEducator_ = specialNeeds; }
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\nNombre: " + getNombre() + "\nApellidos: " + getApellidos() + "\nSpecial needs: " + specialNeedsEducator_ + "\n";
	}
	
	public ClassMonitor() {}
	public ClassMonitor(int id, String nombre, String apellidos, Boolean specialNeeds) {
		super(id, nombre, apellidos);
		setSpecialNeedsEducator(specialNeeds);
	}
}
