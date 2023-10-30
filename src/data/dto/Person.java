package data.dto;
/** Represents a Person.
 * @version 1.0
*/
public abstract class Person {
	private int id_;
	private String nombre_, apellidos_;
	
	/** Gets the id.
     * @return id
    */
	public int getId() { return id_; }
	
	/** Gets the name.
     * @return name
    */
	public String getNombre() { return nombre_; }
	
	/** Gets the surnames.
     * @return surnames
    */
	public String getApellidos() { return apellidos_; }
	
	/** Sets the id.
     * @param id
    */
	public void setId(int id) { id_ = id; }
	
	/** Sets the name.
     * @param name
    */
	public void setNombre(String nombre) { nombre_ = nombre; }
	
	/** Sets the surname.
     * @param apellidos
    */
	public void setApellidos(String apellidos) { apellidos_ = apellidos; }
	
	/** Builder.
    */
	public Person() {};

	/** Builder.
     * @param id
	 * @param nombre
	 * @param apellidos
    */
	public Person(int id, String nombre, String apellidos) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
	}

	/** Dumps all class info into a string.
     * @return class info string
    */
	public String toString() {
		return "ID: " + id_ + "\nNombre: " + nombre_ + "\nApellidos: " + apellidos_;
	}
}
