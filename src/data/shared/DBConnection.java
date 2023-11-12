package data.shared;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;
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

	/*********************************
	 * MONITORS
	 *********************************/

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
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("GET_ALL_MONITORS"));

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

	/******************************
	 * REGISTRATIONS
	 ******************************/
	/**
	 * Creates a new registration in the data base
	 * @param id
	 * @param idP
	 * @param idC
	 * @param rDate
	 * @param price		//calculated
	 * @param type
	 * @param time		//calculated
	 * @return 1 on success
	 */
	public int createRegistration(int idP, int idC, LocalDate rDate, Type type){
		int status=0;
		try{

			if(!isRegistration(idP, idC)){				
				String time = calculateRegistrationTime(idC, rDate);
				float price = calculateRegistrationPrice(idC, type);
				
				PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_REGISTRATION"));
				ps.setInt(1, idP);
				ps.setInt(2, idC);
				ps.setDate(3, Date.valueOf(rDate));
				ps.setFloat(4, price);
				ps.setString(5, String.valueOf(type));
				ps.setString(6, time);
				
				
				status = ps.executeUpdate();
			}

		} catch (Exception e) { e.printStackTrace(); }

		return status;
	}
	/*
	 * Return 1 if the registration is done
	 */
		public Boolean isRegistration(int dni, int idC) {
			PreparedStatement ps = null;
			Boolean result = null;
		
			try {
				ps = connection.prepareStatement(sqlQueries.getProperty("IS_REGISTRATION"));
				
				ps.setInt(1, dni);
				ps.setInt(2, idC);
		
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = (rs.getInt("exists_count")>0);
				}
		
			} catch (Exception e) { e.printStackTrace(); }
		
			return result;
		}
	
	
	/*
	* GET TIME EARLY/LATE
	*/
		public String calculateRegistrationTime(int campId, LocalDate campStartDate) {
			PreparedStatement ps = null;
			String result = null;
		
			try {
				ps = connection.prepareStatement(sqlQueries.getProperty("CALCULATE_TIME"));
				
				ps.setDate(1, Date.valueOf(campStartDate));
				ps.setInt(2, campId);
		
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = rs.getString("registration_tim");
				}
		
			} catch (Exception e) { e.printStackTrace(); }
		
			return result;
		}
	
	/*
	 * GET PRICE 
	 */
		public float calculateRegistrationPrice(int campId, Type type) {
			PreparedStatement ps = null;
			float result = 0;
		
			try {
				ps = connection.prepareStatement(sqlQueries.getProperty("CALCULATE_PRICE"));
				
				ps.setInt(1, campId);
		
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = rs.getInt("activity_count");
				}
				if(type.equals(Type.Full))
					result=result*20+300;
				else
					result=result*20+100;
		
			} catch (Exception e) { e.printStackTrace(); }
		
			return result;
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

	public int addActivityToCamp(int id,String activityName) {
		int status = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("ADD_ACTIVITY_TO_CAMP"));

			ps.setInt(1, id);
			ps.setString(2, activityName);

			status = ps.executeUpdate();
		} catch(Exception e) { e.printStackTrace(); }

		return status;
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
	public int createParticipant(int id, String name, String lastname, LocalDate birthDate, boolean specialAttention){
		int status=0;
		try{
			System.out.println(sqlQueries.getProperty("FILL_PARTICIPANTS"));
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_PARTICIPANTS"));
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, lastname);
			ps.setDate(4, Date.valueOf(birthDate));
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
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("GET_ALL_PARTICIPANTS"));
			
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

	/**
	 * Check if the participants need special attention
	 * @param dni
	 */
	public boolean special_attention(int dni){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("GET_SPECIAL_NEEDS"));
			ps.setInt(1, dni);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getBoolean("specialneeds")){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }

		return r;
	}
	
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
	public int createRegistration(int idPartcipant, int idCamp, LocalDate registrationDate,float price, Type type){
		int status=0;
		try{			
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_REGISTRATION"));
			ps.setInt(1, idPartcipant);
			ps.setInt(2, idCamp);
			ps.setDate(3, Date.valueOf(registrationDate));
			ps.setFloat(4, price);
			ps.setString(5, type.toString());
			
			
			status = ps.executeUpdate();

		} catch (Exception e) { e.printStackTrace(); }

		return status;
	}

	/**
	 * Updates a registration in the data base
	 * @param registrationId
	 * @param idParticipant ->dni
	 * @param idCamp
	 * @param registrationDate
	 * @param price
	 * @param type
	 * @return 1 on success
	 */
	public int updateRegistration(int registrationId, int idPartcipant, int idCamp, LocalDate registrationDate,float price, Type type){
		int status=0;
		try{			
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("UPDATE_REGISTRATION"));
			ps.setInt(1, idPartcipant);
			ps.setInt(2, idCamp);
			ps.setDate(3, Date.valueOf(registrationDate));
			ps.setFloat(4, price);
			ps.setString(5, type.toString());
			ps.setInt(6, registrationId);
			
			status = ps.executeUpdate();

		} catch (Exception e) { e.printStackTrace(); }

		return status;
	}

	/**
	 * Removes a registration from the data base
	 * @param dni
	 * @param idC
	 * @return 1 on success
	 */
	public int deleteRegistration(int dni, int idC){
		int status=0;
		try {			
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("DELETE_REGISTRATION"));
			ps.setInt(1, dni);
			ps.setInt(2, idC);
			status=ps.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	/************************************************************************************************************************************************************ */
	
	/******************
	*	ACTIVITY
	*******************/

	/**
	 * Creates a new activity in the data base
	 * @param name
	 * @param level
	 * @param timetable
	 * @param max
	 * @param monitors_n
	 * @return 1 on success
	 */
	public int createActivity(String name, Level level, String timetable, int max, int monitors_n) {
		int status = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_ACTIVITIES"));

			ps.setString(1, name);
			ps.setString(2, level.toString());
			ps.setString(3, timetable);
			ps.setInt(4, max);
			ps.setInt(5, monitors_n);

			status = ps.executeUpdate();
		} catch(Exception e) { e.printStackTrace(); }

		return status;
	}

	/**
	 * Removes an activity from the data base
	 * @param name
	 * @return 1 on success
	 */
	public Boolean deleteActivity(String name){
		try {
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("DELETE_ACTIVITY"));

			ps.setString(1, name);
			ps.executeUpdate();

			return true;
		} catch(Exception e) { 
			e.printStackTrace(); 
			return false;
		}
	}

	/**
	 * Updates activity info
	 * @param name
	 * @param level
	 * @param timetable
	 * @param max
	 * @param monitors_n
	 * @return 1 on success
	 */
	public Boolean updateActivity(String name, Level level, String timetable, int max, int monitors_n){
		try{
			PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_ACTIVITY"));
			
			ps.setString(1, level.toString());
			ps.setString(2, timetable);
			ps.setInt(3, max);
			ps.setInt(4, monitors_n);
			ps.setString(5, name);

			ps.executeUpdate();

			return true;
		} catch(Exception e) { 
			e.printStackTrace();
			return false;
		}
	}	

	public ArrayList<Hashtable<String,String>> getAllActivities () {
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> participantMap = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQueries.getProperty("GET_ALL_ACTIVITIES"));
			
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
	 * Adds a monitor into activity
	 * @param activityName
	 * @param monitorId
	 * @return true on success
	 */
	public int addMonitorToActivity(String activityName, int monitorId) {
		int status = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("ADD_MONITOR_TO_ACTIVITY"));

			ps.setString(1, activityName);
			ps.setInt(2, monitorId);

			status = ps.executeUpdate();
		} catch(Exception e) { e.printStackTrace(); }

		return status;
	}

	/******************
	*	CAMP
	*******************/
													//Revisar autoincremento
	public int createCamp(int id, LocalDate beggin, LocalDate end, Level level, int maxAssistants) {
		int status = -1;
		try {
			
			PreparedStatement ps = connection.prepareStatement(sqlQueries.getProperty("FILL_CAMPS"));
			
			ps.setDate(1, Date.valueOf(beggin));
			ps.setDate(2, Date.valueOf(end));
			ps.setString(3, String.valueOf(level));
			ps.setInt(4, maxAssistants);


			status= ps.executeUpdate();
			
		} catch (Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return (status);
	}

	public List<String> getActivityNamesByCampId(int campId) {
        List<String> activityNames = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Utiliza la conexión a la base de datos (this.connection)
            ps = this.connection.prepareStatement(sqlQueries.getProperty("get.activity.names.by_camp_id"));
            ps.setInt(1, campId);

            rs = ps.executeQuery();

            while (rs.next()) {
                // Obtiene el nombre de la actividad y lo agrega a la lista
                String activityName = rs.getString("name");
                activityNames.add(activityName);
            }
		} catch(Exception e) { e.printStackTrace(); }		
		return activityNames;
	}

	/**
	 * Retrieves all the users in the data base, no matter which type of user they are
	 * @return ArrayList<Hashtable<String,String>>  with all the registered users in the data base
	 */
	public ArrayList<Hashtable<String,String>>  getAllCamps(){
		Statement stmt = null;
		ArrayList<Hashtable<String, String>> result = new ArrayList<>();
		Hashtable<String,String> CampMap = null;
		
		try {
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(sqlQueries.getProperty("GET_ALL_CAMPS"));		
//Integer.parseInt(item.get("idCamp")),LocalDate.parse(item.get("begginingDate")),LocalDate.parse(item.get("endingDate"))/* ,Level.parse("level_"))*/;
			while(rs.next()){
				int idCamp=rs.getInt("id");
				Date beggin=rs.getDate("start");
				Date end=rs.getDate("end");
				String level =rs.getString("educationallevel");
				int max=rs.getInt("maxparticipants");
				
				CampMap = new Hashtable<String, String>();
				CampMap.put("id", Integer.toString(idCamp));
				CampMap.put("start", String.valueOf(beggin));
				CampMap.put("end", String.valueOf(end));
				CampMap.put("educationallevel", level);
				CampMap.put("maxparticipants", String.valueOf(max));
				
				List<String> activities = getActivityNamesByCampId(idCamp);
				
				CampMap.put("activities", String.join(",", activities));

//si no fufa quita el if

 if(need_special_monitor(idCamp)){
	 CampMap.put("special_monitor", String.valueOf(true));
	}
	else{
		CampMap.put("special_monitor", String.valueOf(false));
	}
	
				result.add(CampMap);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return result;	
	}

	public Boolean hasParticipants(int idC){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("HAS_PARTICIPANTS"));
			ps.setInt(1, idC);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("participant_count")>0){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return r;
	}

	public Boolean hasSpecialMonitors(int idC){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("HAS_SPECIAL_MONITORS"));
			ps.setInt(1, idC);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("special_monitor_count")>0){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return r;
	}

	public Boolean hasActivities(int idC){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("HAS_ACTIVITIES"));
			ps.setInt(1, idC);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("activity_count")>0){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return r;
	}

    public int deleteCamp(int id){
		int status=0;
		try {
			if(!hasActivities(id) && !hasParticipants(id) && !hasSpecialMonitors(id)){				
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("DELETE_CAMP_BY_ID"));
				ps.setInt(1, id);
				status=ps.executeUpdate();
			}
		} catch (Exception e) { e.printStackTrace(); }
		return status;
	}
    	
	/**
		 * Updates Camp info
		 * @param idCamp 
		 * @param name
		 * @return 1 on success
		 */

		public Boolean updateCamp(int id,LocalDate beginningDate,LocalDate endingDate,Level level,int max) {
			try{
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_CAMP"));
				
				ps.setString(1,beginningDate.toString() );
				ps.setString(2, endingDate.toString());
				ps.setString(3, level.toString());
				ps.setInt(4, max);
				ps.setInt(5,id);

				ps.executeUpdate();

				return true;
			} catch(Exception e) { 
				e.printStackTrace();
				return false;
			}
		}	
		
		/**
		 * Updates Camp BeginningDate
		 * @param id	 
		 * @param beginningDate
		 * @return 1 on success
		 */
		public int updateCampBeginningDate(int id, String beginningDate){
			int status=0;
			try{
				
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_CAMP_BEGINNINGDATE"));
				ps.setDate(1, Date.valueOf(beginningDate));
				ps.setInt(2, id);
				status=ps.executeUpdate();
			}catch (Exception e) { e.printStackTrace(); }
			return status;
		}

		/**
		 * Updates Camp EndingDate
		 * @param id	 
		 * @param EndingDate
		 * @return 1 on success
		 */
		public int updateCampEndingDate(int id, String endingDate){
			int status=0;
			try{
				
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_CAMP_ENDINGDATE"));
				ps.setDate(1, Date.valueOf(endingDate));
				ps.setInt(2, id);
				status=ps.executeUpdate();
			}catch (Exception e) { e.printStackTrace(); }
			return status;
		}

		/**
		 * Updates Camp Level
		 * @param id	 
		 * @param Level
		 * @return 1 on success
		 */
		public int updateCampLevel(int id, String level){
			int status=0;
			try{
				
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_CAMP_LEVEL"));
				ps.setString(1,(level.toString()));
				ps.setInt(2, id);
				status=ps.executeUpdate();
			}catch (Exception e) { e.printStackTrace(); }
			return status;
		}

		/**
		 * Updates Camp MaxAssistants
		 * @param id	 
		 * @param MaxAssistants
		 * @return 1 on success
		 */
		public int updateCampMaxAssistants(int id, String updateCampMaxAssistants){
			int status=0;
			try{
				PreparedStatement ps=connection.prepareStatement(sqlQueries.getProperty("UPDATE_CAMP_MAX_ASSISTANTS"));
				ps.setInt(1,Integer.valueOf(updateCampMaxAssistants));
				ps.setInt(2, id);
				status=ps.executeUpdate();
			}catch (Exception e) { e.printStackTrace(); }
			return status;
		}

	
	public Boolean need_special_monitor(int idC){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("NEED_SPECIAL_MONITOR"));
			ps.setInt(1, idC);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("special_needs_count")>0){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return r;
	}

	public int add_special_monitor(int idM, int idC){
		PreparedStatement ps=null;
		int status=0;

		try{
			if(is_special_monitor(idM)){
				ps=connection.prepareStatement(sqlQueries.getProperty("ADD_SPECIAL_MONITOR_CAMP"));
				ps.setInt(1, idM);
				ps.setInt(2, idC);
				status=ps.executeUpdate();
			}
			else{
				return 10;
			}
		}catch (Exception e) { e.printStackTrace(); }
		return status;
	}

	public Boolean is_special_monitor(int idM){
		boolean r=false;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try{
			ps = connection.prepareStatement(sqlQueries.getProperty("IS_SPECIAL_MONITOR"));
			ps.setInt(1, idM);
			rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("is_special")!=0){
					r=true;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		return r;
	}
}
