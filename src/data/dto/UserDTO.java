package data.dto;
import java.time.LocalDate;


public class UserDTO {
    private String nombre;
    private String email;
    private LocalDate fechana;
    private LocalDate date;
    private String password;
    private boolean rol;

public UserDTO(String nombre, String email, LocalDate fechana, LocalDate date, String pass, boolean rol) {
    this.nombre = nombre;
    this.email = email;
    this.fechana = fechana;
    this.date = date;
    this.password = pass;
    this.rol = rol;
}

    public String getNombre() {
        return this.nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getFechana() {
        return this.fechana;
    }

    public LocalDate getFechainsc() {
        return this.date;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getRol() {
        return this.rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFechana(LocalDate fechana) {
        this.fechana = fechana;
    }

    public void setFechainsc(LocalDate date) {
        this.date = date;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public String toString() {
        String UserInfo = "Soy el usuario " + this.nombre + ", mi email es: " + this.email + ", naci el: " + this.fechana + " ,me inscribi el dia:  " + this.date + " , mi contrasela es: " + this.password + " y mi rol es: " + this.rol;
        return UserInfo;
    }
    }
