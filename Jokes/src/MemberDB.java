import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/MemberDB")
public class MemberDB extends HttpServlet {
	//member variables
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public MemberDB() {
		
	}
	
	//remember to change the user name and password to match your sql server
	//
	///
	///
	////
	/////
	//////
	///////
	////////
    /**
     *@see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/sampledb?"
  			          + "user=Cory&password=mySQL");
            System.out.println(connect);
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void createTables() throws SQLException {
    	
    	String SQLStatement;
    	
    	//establish connection with the server
    	connect_func();
    	
    	//check to make sure the table does not already exist
    	//if it does then drop the table
    	statement = connect.createStatement();
    	statement.executeUpdate("DROP TABLE IF EXISTS hasFavoriteJokes");
    	statement.executeUpdate("DROP TABLE IF EXISTS hasFavoriteUsers");
    	statement.executeUpdate("DROP TABLE IF EXISTS reveiws");
    	statement.executeUpdate("DROP TABLE IF EXISTS hastags");
    	statement.executeUpdate("DROP TABLE IF EXISTS jokes");
    	statement.executeUpdate("DROP TABLE IF EXISTS hasFavoriteUsers");
    	statement.executeUpdate("DROP TABLE IF EXISTS hasFavoriteJokes");
    	statement.executeUpdate("DROP TABLE IF EXISTS members");
    	
    	//create a table of members with at least 10 tuples
    	//statement to be passed to the server
    	SQLStatement = "CREATE TABLE IF NOT EXISTS members" + (
    			  "(UserId VARCHAR(30) UNIQUE NOT NULL, "
    			+ "FirstName VARCHAR(30) NOT NULL, "
    			+ "LastName VARCHAR(30) NOT NULL, "
    			+ "Password VARCHAR(30) NOT NULL, "
    			+ "Email VARCHAR(30) NOT NULL, "
    			+ "Gender VARCHAR(1) NOT NULL, "
    			+ "Age TINYINT NOT NULL, "
    			+ "IsAdmin BOOLEAN NOT NULL DEFAULT FALSE, "
    			+ "PRIMARY KEY(UserId))"
    			);
    	statement.executeUpdate(SQLStatement);
    	

    	List<Member> someMembers = new ArrayList<Member>();
    	// use insert function to build up the tables
    	//create the one specific member from the instructions
    	//and root user
    	someMembers.add(new Member("john", "pass1234","john","jacob", "iceJJfish@yahoo.com", "M", 25) );	//This one must be int the table at all times
    	someMembers.add(new Member("otherbill", "qweRty","bill","bobbert", "therealbill@yahoo.com", "M",36) );
    	someMembers.add(new Member("sweatrhyme", "gratata","juan","dolo", "juansolodolo@gmil.com", "M",18) );
    	someMembers.add(new Member("badcheeto", "cheddar","chester","cheetah", "frito-lays@yahoo.com", "M", 89) );
    	someMembers.add(new Member("sarebear", "clipper","sara", "bear", "saraduhbear@wowway.com", "F", 20) );
    	someMembers.add(new Member("lunarlinda", "jupiter","luna", "moon", "jupitersrings@gmail.com", "F", 31) );
    	someMembers.add(new Member("root", "pass1234", "root", "groot", "groottheroot@yahoo.com", "M", 18, true) ); //this is the root user
    	someMembers.add(new Member("dadjokes", "sportssportssports","andrew", "clark", "andrewtheman@godaddy.com", "M", 45) );
    	someMembers.add(new Member("spidermanithink", "wasdqwerty", "clark", "kent", "marvel@gmail.com", "M", 20) );
    	someMembers.add(new Member("coffekat", "javasucks","katterina", "snails", "slowsnail82@yahoo.com", "F", 60) );
    	
    	for(int i = 0; i < someMembers.size(); i++) {
    		insert(someMembers.get(i));
    	}

    	

    	disconnect();
    	
    	buildFriendsList();
    	
    }
    
    public void buildFriendsList() throws SQLException {
    	
    	//establish connection with the server
    	connect_func();
    	
    	statement = connect.createStatement();
    	String SQLStatement = "CREATE TABLE IF NOT EXISTS hasFavoriteUsers " +(
    			  "(UserId VARCHAR(20), " 
    			+ "OtherUserId VARCHAR(20), " 
    			+ "FOREIGN KEY(UserId)REFERENCES members(UserId), " 
    			+ "FOREIGN KEY(OtherUserId)REFERENCES members(UserId), " 
    			+ "PRIMARY KEY(UserId, OtherUserId))"
    			);
    	statement.executeUpdate(SQLStatement);	
	}
    
    

	public boolean addFriendToList(String currentUserName, String otherUserName) throws SQLException {
		connect_func();
		//users cannot add themselves
		if(currentUserName.contentEquals(otherUserName)) {
			return false;
		}
    	String SQLStatement = "INSERT INTO hasFavoriteUsers(UserId, OtherUserId) VALUES (?,?)"; 
		
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1, currentUserName);
    	preparedStatement.setString(2, otherUserName);
    	//check if the member was inserted properly
        boolean rowInserted = preparedStatement.executeUpdate() > 0;

        
        preparedStatement.close();
        
        return rowInserted;
	}
	
	public void deleteFriendFromList(String currentUser, String otherUser)  throws SQLException {
		
		connect_func();
		String deleteFriend = "DELETE FROM hasFavoriteUsers Where userId = '"+currentUser+ "' AND OtherUserId = '" + otherUser + "'"  ;
		statement =  (Statement) connect.createStatement();
		statement.executeUpdate(deleteFriend);
	}

	public boolean insert(Member member) throws SQLException{
    	connect_func();
    	String SQLStatement = "INSERT INTO MEMBERS(UserId, Password, FirstName, LastName, Email, Gender, Age, isAdmin) VALUES (?,?,?,?,?,?,?,?)";    
    	
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1, member.userId);
    	preparedStatement.setString(2, member.password);
    	preparedStatement.setString(3, member.firstName);
    	preparedStatement.setString(4, member.lastName);
    	preparedStatement.setString(5, member.email);
    	preparedStatement.setString(6, member.gender);
    	preparedStatement.setInt(7, member.age);
    	preparedStatement.setBoolean(8, member.isAdmin);
    	
    	//check if the member was inserted properly
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        return rowInserted;
    	
    }
    
    public boolean checkLogin(Member possibleMember) throws SQLException {
    	connect_func();
    	//this statement counts the number of users in the database with this username and password combo
    	String SQLStatement = "SELECT COUNT(1) FROM members WHERE(userId= ? AND Password = ?)";
    	
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1, possibleMember.userId);
    	preparedStatement.setString(2, possibleMember.password);
    	
    	
    	resultSet = preparedStatement.executeQuery();
    	
    	resultSet.next();
    	if(resultSet.getInt(1) == 1) {
    		return true;
    	}else {
    		return false;
    	}

    }
    //returns false if username does exist
    public boolean checkUser(Member newMember) 
    throws SQLException
    {
    	connect_func();
    	String SQLStatement = "SELECT COUNT(1) FROM members WHERE(userId= ? )";
    	//String SQLStatmenet = 
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1,  newMember.userId);
    	
    	resultSet = preparedStatement.executeQuery();
    	resultSet.next();
    	if(resultSet.getInt(1)==1) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    //returns false id email already exists
    public boolean checkEmail(Member newMember) 
    throws SQLException
    {
    	connect_func();
    	String SQLStatement = "SELECT COUNT(1) FROM members WHERE(email= ? )";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1,  newMember.email);
    	
    	resultSet = preparedStatement.executeQuery();
    	resultSet.next();
    	if(resultSet.getInt(1)==1) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
	public List<Member> getMembers(String currentUser) throws SQLException {
		List<Member> members = new ArrayList<Member>();
		
		connect_func();
		//Query to return all jokes in DB
    	String SQLStatement = "SELECT * FROM hasFavoriteUsers WHERE userId= \""+ currentUser +"\"";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		
    	while(resultSet.next()) {
    		Member member = new Member();
    		member.userId = resultSet.getString("otherUserId");
    		members.add(member);
    		
    	}
        disconnect();        
		return members;
	}
	
public Member getMember(String userId) throws SQLException {
		
		connect_func();
    	String SQLStatement = "SELECT * FROM members where UserId = '" + userId  + "'";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		Member member = new Member();
    	while(resultSet.next()) {
    		member.userId = resultSet.getString("userId");
    		member.firstName = resultSet.getString("firstName");
    		member.lastName = resultSet.getString("lastName");
    		member.email = resultSet.getString("email");
    		member.gender = resultSet.getString("gender");
    		member.age = resultSet.getInt("age");
    	}
		return member;
	}

public ArrayList<Member> getAllMembers() throws SQLException {
	ArrayList<Member> members = new ArrayList<Member>();
	
	connect_func();
	
	String SQLStatement = "SELECT * FROM members";
	statement =  (Statement) connect.createStatement();
	resultSet = statement.executeQuery(SQLStatement);
	
	while(resultSet.next()) {
		Member member = new Member();
		member.userId = resultSet.getString("userId");
		members.add(member);
	}
	
    disconnect();        
	return members;
}

}
