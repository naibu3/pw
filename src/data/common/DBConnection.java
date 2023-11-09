package data.common;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Properties;
import java.util.ArrayList;

public class DBConnection {
    protected Connection connection = null;
    private Properties sqlProp;
	private Properties sqlQueries;

    /**
	 * Constructor, this loads all the necessary properties
	 */
	public DBConnection() {
		sqlProp = new Properties();
		try {
			sqlProp = new Properties();
			sqlProp.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		sqlQueries = new Properties();
		try {
			sqlQueries.load(new FileInputStream("sql.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Retrieves a new connection to the data base se llama antes de hacer los cambios
	 * @return Connection to the data base
	 */
	public Connection getConnection(){
		connection = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(sqlProp.getProperty("db_url"),sqlProp.getProperty("db_user"),sqlProp.getProperty("db_pass"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
			return connection;
		}


	/**
	 * Creates a new monitor in the data base
	 * @param dni	 
	 * @param name
	 * @param lastname
	 * @param specialneeds
	 * @return 1 on success
	 */
	public int createMonitor(int dni, String name, String lastname, boolean specialEducator) {
		int status = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_MONITORS"));
			ps.setInt(1, dni);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setBoolean(4, specialEducator);

			status = ps.executeUpdate();
		} catch(Exception e) { e.printStackTrace(); }

		return status;
	}

	/**
	 * Removes a monitor from the data base
	 * @param dni
	 * @return 1 on success
	 */
	public Boolean deleteMonitor(int dni){
		try {
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("DELETE_MONITOR"));
			ps.setInt(1, dni);
			ps.executeUpdate();

			return true;
		} catch(Exception e) { 
			e.printStackTrace(); 
			return false;
		}
	}

	/**
	 * Updates monitor info
	 * @param dni	 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialneeds
	 * @return 1 on success
	 */
	public Boolean updateMonitor(int dni, String name, String lastname, boolean specialEducator){
		try{
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_MONITOR"));
			ps.setInt(4, dni);
			ps.setString(1, name);
			ps.setString(2, lastname);
			ps.setBoolean(3, specialEducator);

			ps.executeUpdate();

			return true;
		} catch(Exception e) { 
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets the monitor info from the data base given its dni
	 * @param dni
	 * @return Hashtable<String,String> with the info of the monitor
	 */
	public Hashtable<String,String> monitorByDni(int dni) {
		Statement stmt = null;
		Hashtable<String,String> result = null;
		
		try {
			stmt = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(sqlProp.getProperty("monitorByDNI"));
			ps.setInt(1, dni);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				
				result = new Hashtable<String,String>();
				result.put("dni", Integer.toString(dni));
				result.put("name", rs.getString("name"));
				result.put("lastname", rs.getString("lastname"));
				result.put("specialeducator", Boolean.toString(rs.getBoolean("specialeducator")));
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { e.printStackTrace(); }
		return result;
	}


	/**
	 * Get all the monitors in the database
	 * @return ArrayList<HashTable<String, String>> with the info of all the monitors
	 */
	public ArrayList<Hashtable<String, String>> getAllMonitors() {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> monitors = new ArrayList<>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("getAllMonitors"));

			while(rs.next()) {
				Hashtable<String, String> monitor = new Hashtable<String, String>();
				monitor.put("id", Integer.toString(rs.getInt("dni")));
				monitor.put("name", rs.getString("name"));
				monitor.put("lastname", rs.getString("lastname"));
				monitor.put("specialeducator", Boolean.toString(rs.getBoolean("specialeducator")));

				monitors.add(monitor);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		return monitors;
	}


	public ArrayList<Hashtable<String, String>> getAvailableCamps() {
		ArrayList<Hashtable<String, String>> availableCamps = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("AVAILABLE_CAMPS"));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Hashtable<String, String> camp = new Hashtable<String, String>();
				camp.put("id", Integer.toString(rs.getInt("id")));
				camp.put("start", rs.getString("start"));
				camp.put("end", rs.getString("end"));
				camp.put("educational_level", rs.getString("educationallevel"));
				camp.put("max_participants", Integer.toString(rs.getInt("maxparticipants")));
				availableCamps.add(camp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return availableCamps;
	}

/**************************************************************************************************************************************************************
*		PARTICIPANTS
/************************************************************************************************************************************************************ */
	/**
	 * Creates a new participant in the data base
	 * @param id	// ==dni 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialAttention	//needs
	 * @return 1 on success
	 */
	public int createParticipant(int id, String name, String lastname, LocalDate birthdDate, boolean specialAttention){
		int status=0;
		try{
			System.out.println(sqlQueries.getProperty("FILL_PARTICIPANTS"));
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_PARTICIPANTS"));
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthdDate));
			ps.setBoolean(5, specialAttention);
			
			status = ps.executeUpdate();

		} catch (Exception e) { e.printStackTrace(); }

		return status;
	}

	/**
	 * Removes an participant from the data base
	 * @param id
	 * @return 1 on success
	 */
	public int deleteParticipant(int id){
		int status=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("DELETE_PARTICIPANT_BY_ID"));
			ps.setInt(1, id);
			status=ps.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	/**
	 * Gets the info of all the users registered in the data base
	 * @return ArrayList<Hashtable<String,String>> with the info of the user
	 */
	public ArrayList<Hashtable<String,String>> getAllParticipants () {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> participantMap = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("getAllParticipants"));
			
			while (rs.next()) {
				int idparticipant = rs.getInt("dni");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");				
				Date birthdate = rs.getDate("birthdate");
				Boolean special_attention = rs.getBoolean("specialneeds");

				participantMap = new Hashtable<String, String>();
				participantMap.put("id", Integer.toString(idparticipant));
				participantMap.put("name", name);
				participantMap.put("lastname", lastname);
				participantMap.put("birthdate", String.valueOf(birthdate));
				participantMap.put("special_attention", Boolean.toString(special_attention));

				
				result.add(participantMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { e.printStackTrace(); }
		return result;
	}

	
	/**
	 * Updates Participant Name
	 * @param id	 
	 * @param name
	 * @return 1 on success
	 */
	public int updateParticipantName(int id, String name){
		int status=0;
		try{
			
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_PARTICIPANT_NAME"));
			ps.setString(1, name);
			ps.setInt(2, id);
			status=ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	/**
	 * Updates Participant Lastname
	 * @param id	 
	 * @param lastname
	 * @return 1 on success
	 */
	public int updateParticipantLastname(int id, String lastname){
		int status=0;
		try{
			
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_PARTICIPANT_LASTNAME"));
			ps.setString(1, lastname);
			ps.setInt(2, id);
			status=ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	/**
	 * Updates Participant Birthdate
	 * @param id	 
	 * @param birthdate
	 * @return 1 on success
	 */
	public int updateParticipantBirthdate(int id, String Birthdate){
		int status=0;
		try{
			
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_PARTICIPANT_BIRTHDATE"));
			ps.setDate(1, Date.valueOf(Birthdate));
			ps.setInt(2, id);
			status=ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	/**
	 * Updates Participant SpecialNeeds
	 * @param id	 
	 * @param SpecialNeeds
	 * @return 1 on success
	 */
	public int updateParticipantSpecialNeeds(int id, String SpecialNeeds){
		int status=0;
		try{
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_PARTICIPANT_SPECIAL_NEEDS"));
			ps.setBoolean(1, (SpecialNeeds.equals("yes")));
			ps.setInt(2, id);
			status=ps.executeUpdate();
		}catch (Exception e) { e.printStackTrace(); }
		return status;
	}
	
	/**
	 * Checks if there is already an Participant in the data base with the given id 
	 * @param id
	 * @return >=1 on success
	 */
	public int countParticipant() {
		Statement stmt = null;
		int result=0;

		try{
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("COUNT_PARTICIPANTS"));
			if ( rs.next() ) 
			    result = rs.getInt(1);
			
		}catch(Exception e){System.out.println(e);}
		return result;
	}

/************************************************************************************************************************************************************ */
	
/**************************************************************************************************************************************************************
*		REGISTRATION
/************************************************************************************************************************************************************ */
	/**
	 * Creates a new registration in the data base
	 * @param idParticipant ->dni
	 * @param idCamp
	 * @param registrationDate
	 * @param price
	 * @return 1 on success
	 */
	public int createRegistration(int idPartcipant, int idCamp, LocalDate registrationDate,float price, String type){
		int status=0;
		type="full";
		try{			
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_REGISTRATION"));
			ps.setInt(1, idPartcipant);
			ps.setInt(2, idCamp);
			ps.setDate(3, Date.valueOf(registrationDate));
			ps.setFloat(4, price);
			ps.setString(5, type);
			
			
			status = ps.executeUpdate();

		} catch (Exception e) { e.printStackTrace(); }

		return status;
	}

/************************************************************************************************************************************************************ */
	
	/**
	 *  Closes connection		Se llama tras hacer los cambios
	 */
	public void closeConnection() {
		try {
			if(this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
				System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}
