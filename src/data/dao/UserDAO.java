package data.dao;

import java.time.LocalDate;

import data.dto.UserDTO;
import data.shared.DBConnection;

public class UserDAO {    

    /**
	 * Constructor
	 * @param dbServidor
	 * @param dbUsuario
	 * @param dbPass
	 * @param dbName
	 * @param pathSQL, sql.properties path
	 */
	
	public UserDAO() {
	}

     /**
	 * Creates a new User
	 * @param User
	 * @return true on success
	 */
	public boolean createUser(UserDTO User) {
		int status = -1;
		try {			
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnection();

			status = dbConnection.createUser(User.getNombre(), User.getEmail(), User.getFechana(), User.getFechainsc(), User.getPassword(),User.getRol());
			
			dbConnection.closeConnection();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status == 1);
	}

    public Boolean deleteUser(String email) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();

        if (dbConnection.deleteUser(email)) {
            return true;
        }

        return false;
    }
	public Boolean updateUser(String nombre, String email, LocalDate fechana, LocalDate date, String pass, boolean rol) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        if (dbConnection.updateUser(nombre,email,fechana,date,pass,rol)) {
            return true;
        }

	
        
        return false;
    }

	
	

	

}
