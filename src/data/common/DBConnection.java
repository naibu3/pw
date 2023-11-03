package data.common;

import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Properties;
import java.util.ArrayList;

public class DBConnection {
    protected Connection connection = null;
    private Properties sqlProp;

    /**
	 * Constructor		se llama 1 vez
	 */
	public DBConnection(Properties path) {
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
	 * @param id	 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialAttention
	 * @return 1 on success
	 */
	public int createParticipant(int id, String name, String lastname, LocalDate birthdDate, boolean specialAttention){
		int status=0;
		try{
			
			PreparedStatement ps = connection.prepareStatement(sqlProp.getProperty("createParticipant"));
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthdDate));
			ps.setBoolean(5, specialAttention);
			
			
			status = ps.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
		return status;
	}

	/**
	 * Removes an participant from the data base
	 * @param id
	 * @param con
	 * @return 1 on success
	 */
	public int deleteParticipant(int id){
		int status=0;
		try {
			
			PreparedStatement ps=connection.prepareStatement(sqlProp.getProperty("deleteParticipant"));
			ps.setInt(1, id);
			status=ps.executeUpdate();
		} catch(Exception e) {System.out.println(e);}
		return status;
	}

	/**
	 * Updates Participant info
	 * @param id	 
	 * @param name
	 * @param lastname
	 * @param birthdate
	 * @param specialAttention
	 * @return 1 on success
	 */
	public int updateparticipant(int id, String name, String lastname, LocalDate birthdDate, boolean specialAttention){
		int status=0;
		try{
			
			PreparedStatement ps=connection.prepareStatement(sqlProp.getProperty("updateParticipant"));
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthdDate));
			ps.setBoolean(5, specialAttention);
			status=ps.executeUpdate();
		}catch(Exception e){System.out.println(e);}
		return status;
	}

	/**
	 * Gets the info of all the users registered in the data base
	 * @param con
	 * @return ArrayList<Hashtable<String,String>> with the info of the user
	 */
	public ArrayList<Hashtable<String,String>> getAllParticipants () {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> participantMap = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("getAllParticipants"));
			
			while (rs.next()) {
				int idparticipant = rs.getInt("id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");				
				Date birthdate = rs.getDate("birthdate");
				Boolean special_attention = rs.getBoolean("special_attention");

				participantMap = new Hashtable<String, String>();
				participantMap.put("id", Integer.toString(idparticipant));
				participantMap.put("name", name);
				participantMap.put("lastname", lastname);
				participantMap.put("birthdate", String.valueOf(birthdate));
				participantMap.put("special_attention", Boolean.toString(special_attention));

				
				result.add(participantMap);
			}
			
			if (stmt != null) stmt.close();
		} catch (Exception e) { System.out.println(e); }
		return result;
	}

	/**
	 * Checks if there is already an Participant in the data base with the given id 
	 * @param id
	 * @param con
	 * @return >=1 on success
	 */
	public int countParticipantid (int id) {
		Statement stmt = null;
		int result=0;
		try{
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlProp.getProperty("countParticipantid") + "'" + id + "'" );
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
				System.out.println("Database connection successfully closed!");
			}
		} catch (SQLException e) {
			System.err.println("Error while trying to close the connection.");
			e.printStackTrace();
		}
	}
}
