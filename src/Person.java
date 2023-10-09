

public abstract class Person {
	private int id_;
	private String nombre_, apellidos_;
	
	public int getId() { return id_; }
	public String getNombre() { return nombre_; }
	public String getApellidos() { return apellidos_; }
	public void setId(int id) { id_ = id; }
	public void setNombre(String nombre) { nombre_ = nombre; }
	public void setApellidos(String apellidos) { apellidos_ = apellidos; }
	public Person() {};
	public Person(int id, String nombre, String apellidos) {
		setId(id);
		setNombre(nombre);
		setApellidos(apellidos);
	}
	public String toString() {
		return "ID: " + id_ + "\nNombre: " + nombre_ + "\nApellidos: " + apellidos_;
	}
}
