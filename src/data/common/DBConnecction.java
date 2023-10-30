import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Properties;

public class DBConnecction {
    protected Connection connection = null;
    private Properties sqlProp;

    /**
	 * Constructor		se llama 1 vez
	 */
	public DBConnecction(Properties path) {
		sqlProp = new Properties();
		sqlProp=path;
	}


	/**
	 * Retrieves a new connection to the data base		se llama antes de hacer los cambios
	 * @return Connection to the data base
	 */
	public Connection getConnection(){
		connection = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://oraclepr.uco.es:3306/database","i12gofoa","periquito");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return connection;
		}

	/**
	 * Creates a new participant in the data base
	 * @param dni	 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialneeds
	 * @return 1 on success
	 */
	public int createParticipant(int dni, String name, String lastname, LocalDate birthdDate, boolean specialneeds){
		int status=0;
		try{
			
			PreparedStatement ps = connection.prepareStatement(sqlProp.getProperty("createParticipant"));
			ps.setInt(1, dni);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthdDate));
			ps.setBoolean(5, specialneeds);
			
			
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}

	/**
	 * Removes an participant from the data base
	 * @param dni
	 * @param con
	 * @return 1 on success
	 */
	public int deleteParticipant(int dni){
		int status=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement(sqlProp.getProperty("deleteParticipant"));
			ps.setInt(1, dni);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}

	/**
	 * Updates Participant info
	 * @param dni	 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialneeds
	 * @return 1 on success
	 */
	public int updateparticipant(int dni, String name, String lastname, LocalDate birthdDate, boolean specialneeds){
		int status=0;
		try{
			
			PreparedStatement ps=connection.prepareStatement(sqlProp.getProperty("updateParticipant"));
			ps.setInt(1, dni);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthdDate));
			ps.setBoolean(5, specialneeds);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	/**
	 * Gets the Participant info from the data base given its dni
	 * @param dni
	 * @param con
	 * @return Hashtable<String,String> with the info of the Participant
	 */
	public Hashtable<String,String> participantByDni (int dni) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("participantBydni") + "'" + dni + "'");
			
			while (rs.next()) {				
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
				Date birthDate =rs.getDate("birthdate");
				Boolean specialneeds = rs.getBoolean("specialneeds");
				
				result = new Hashtable<String,String>();
				result.put("dni", Integer.toString(dni));
				result.put("name", name);
				result.put("lastname", lastname);
				result.put("birthdate", String.valueOf(birthDate));
				result.put("specialneeds", String.valueOf(specialneeds));

			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}

	/**
	 * Checks if there is already an Participant in the data base with the given Dni 
	 * @param dni
	 * @param con
	 * @return >=1 on success
	 */
	public int countParticipantDni (int dni) {
		Statement stmt = null;
		int result=0;
		try{
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("countParticipantDni") + "'" + dni + "'" );
			if ( rs.next() ) 
			    result = rs.getInt(1);
			
		}catch(Exception e){System.out.println(e);}
		return result;
	}

	/**
	 *  Closes connection		Se llama tras hacer los cambios
	 */
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
