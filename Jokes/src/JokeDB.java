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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/JokesDB")
public class JokeDB extends HttpServlet {
	//member variables
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public JokeDB() {
		
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
    	statement.executeUpdate("DROP TABLE IF EXISTS jokes");
    	//create a table of jokes with at least 10 tuples
    	//statement to be passed to the server
    	SQLStatement = "CREATE TABLE IF NOT EXISTS jokes" + (
    			 "(JokeId MEDIUMINT NOT NULL AUTO_INCREMENT,"
    			+ "TheDate DATE, "
    			+ "UserId VARCHAR(30) NOT NULL, "
    			+ "Description VARCHAR(500) NOT NULL, "
    			+ "Title VARCHAR(140), "
    			+ "PRIMARY KEY(JokeId), "
    			+ "FOREIGN KEY(UserId) REFERENCES members(UserId))"
    			);
    	statement.executeUpdate(SQLStatement);
    	
    	//create tags table for jokes
    	SQLStatement = "CREATE TABLE IF NOT EXISTS hastags" + (
    		"(JokeId MEDIUMINT NOT NULL, "
    		+ "TagWord VARCHAR(20) NOT NULL, "
    		+ "FOREIGN KEY(JokeId) REFERENCES jokes(JokeId))"
   			);
    	statement.executeUpdate(SQLStatement);

    	List<Joke> someJokes = new ArrayList<Joke>();
    	// use insert function to build up the table
    	
    	someJokes.add(new Joke(0, getCurrentDate(), "john", "A lame joke", "this is not even a joke its just a bunch of words?"));
    	someJokes.add(new Joke(1, getCurrentDate(), "john", "A lame joke the sequal", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(2, getCurrentDate(), "john", "A lame joke the prequal", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(3, getCurrentDate(), "otherBill", "A lame joke the pre sequal", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(4, getCurrentDate(), "otherBill", "A lame joke the first", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(5, getCurrentDate(), "otherBill", "A lame joke the original", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(6, getCurrentDate(), "root", "A lame joke directors cut", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(7, getCurrentDate(), "root", "A lame joke the remake", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(8, getCurrentDate(), "root", "A lame joke recolored", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	someJokes.add(new Joke(9, getCurrentDate(), "dadJokes", "A lame joke 2", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim."));
    	
    	for(int i = 0; i < someJokes.size(); i++) {
    		insert(someJokes.get(i));
    	}
    	

    	disconnect();
    	
    	buildFavJokesList();
    }
    
    public void buildFavJokesList() throws SQLException {
    	
    	//establish connection with the server
    	connect_func();
    	
    	statement = connect.createStatement();
    	String SQLStatement = "CREATE TABLE IF NOT EXISTS hasFavoriteJokes " +(
    			  "(UserId VARCHAR(20), " 
    			+ "JokeId MEDIUMINT NOT NULL, "
    			+ "Title VARCHAR(140),"
    			+ "PRIMARY KEY(UserId, JokeId))"
    			);
    	statement.executeUpdate(SQLStatement);	
    	
		SQLStatement = "ALTER TABLE hasFavoriteJokes ADD CONSTRAINT userUser FOREIGN KEY(UserId)REFERENCES members(UserId)";
    	statement.executeUpdate(SQLStatement);	
    	
    	SQLStatement = "ALTER TABLE hasFavoriteJokes ADD CONSTRAINT userJoke FOREIGN KEY(JokeId)REFERENCES jokes(JokeId)";
    	statement.executeUpdate(SQLStatement);
    	
	}
    
	public boolean addJokeToFavList(String currentUserName, int jokeId, String title) throws SQLException{
		connect_func();
    	String SQLStatement = "INSERT INTO hasFavoriteJokes(UserId, JokeId, Title) VALUES (?,?,?)"; 
		
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setString(1, currentUserName);
    	preparedStatement.setInt(2, jokeId);
    	preparedStatement.setString(3, title);

    	//check if the member was inserted properly
        boolean rowInserted = preparedStatement.executeUpdate() > 0;

        
        preparedStatement.close();
        
        return rowInserted;
	}
    
    public boolean addTag(int jokeId, String tagWord) throws SQLException {
    	connect_func();
    	String SQLStatement = "INSERT INTO hastags(JokeId, TagWord) VALUES (?,?)";
    	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
    	preparedStatement.setInt(1, jokeId);
    	preparedStatement.setString(2, tagWord);
    	
    	//check if the tag was inserted properly
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        
        return rowInserted;
    	
    }
    
	public void updateTagTable(String[] tagList, int jokeId) throws SQLException {
		for(int i =0; i < tagList.length; i++) {
			addTag(jokeId, tagList[i]);
		}
		
	}
    
    public boolean insert(Joke joke) throws SQLException{
    	if(jokeLimit(joke)){
        	connect_func();
        	String SQLStatement = "INSERT INTO JOKES(TheDate, UserId, Title, Description) VALUES (?,?,?,?)";    
        	
        	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
        	preparedStatement.setString(1, getCurrentDate());
        	preparedStatement.setString(2, joke.userId);
        	preparedStatement.setString(3, joke.title);
        	preparedStatement.setString(4, joke.description);
            boolean rowInserted = preparedStatement.executeUpdate() > 0;
            
        	if(joke.tags != null) {
        		System.out.println("in the add tags");
            	SQLStatement = "SELECT JokeId FROM sampledb.jokes WHERE(TheDate = '"+ getCurrentDate() +"' && UserId = '"+ joke.userId +"' && Title ='"+joke.title+"')";
            	statement =  (Statement) connect.createStatement();
            	resultSet = statement.executeQuery(SQLStatement);
            	while(resultSet.next()) {
            		//this one line parses the tags into a array of single word string then adds them to the tag table
            		updateTagTable(parseTags(joke.tags), resultSet.getInt("JokeId"));            	
            	}
            	
            	
        	}

            preparedStatement.close();
            
            return rowInserted;
    	}
    	else {
    		return false;
    	}
    }
    //do we use this
    public String getJokeTitle(int jokeID) throws SQLException {
    	connect_func();
    	String SQLStatement = "Select Title, JokeId FROM Jokes";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
    	while(resultSet.next()) {
    		if(jokeID == resultSet.getInt("JokeId"))
    			return resultSet.getString("Title");
    	}
    	
    	resultSet.close();
        statement.close();  
    	return null;
    }
    
    //not finished
    private boolean jokeLimit(Joke joke) throws SQLException {
		List<Joke> jokes = new ArrayList<Joke>();
		

		jokes = getJokes();
    	String theDate = getCurrentDate(); 
    	String user = joke.userId;
    	int count = 0;
    	for(int i =0; i < jokes.size(); i++) {
    		Joke tempJoke = jokes.get(i);
    		
    		if(tempJoke.userId.contentEquals(user) && tempJoke.date.contentEquals(theDate)) {
    			count++;
    			System.out.println(count);

    		}
    	}
    	if(count >=4) {
    		//user cannot post anymore jokes today
    		return false;
    	}
    	else {
    		return true;
    	}
	}

	public static String getCurrentDate(){
    	//get the current date using the java until and then convert to the SQL format
    	java.util.Date jDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(jDate.getTime());
        
        return sDate.toString();
        
    }

	public List<Joke> getJokes() throws SQLException {
		List<Joke> jokes = new ArrayList<Joke>();
		
		connect_func();
		//Query to return all jokes in DB
    	String SQLStatement = "SELECT * FROM sampledb.jokes";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		
    	while(resultSet.next()) {
    		Joke joke = new Joke();
    		joke.jokeId = resultSet.getInt("JokeId");
    		joke.date = resultSet.getString("TheDate");
    		joke.userId = resultSet.getString("userId");
    		joke.title = resultSet.getString("title");
    		joke.description = resultSet.getString("description");
    		jokes.add(joke);
    		
    	}
        resultSet.close();
        statement.close();         
        disconnect();        
		return jokes;
	}

	public List<String> getTags(int jokeId) throws SQLException {
		connect_func();
    	String SQLStatement = "SELECT * FROM hasTags where JokeID = " + jokeId;
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
    	
    	List<String> tags = new ArrayList<String>();
    	while(resultSet.next()){
    		tags.add(resultSet.getString("TagWord"));
    	}
		return tags;
	}

	public Joke getJoke(int jokeId) throws SQLException {
		
		connect_func();
    	String SQLStatement = "SELECT * FROM jokes where JokeID = " + jokeId;
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		Joke joke = new Joke();
    	while(resultSet.next()) {
    		joke.jokeId = resultSet.getInt("JokeId");
    		joke.date = resultSet.getString("TheDate");
    		joke.userId = resultSet.getString("userId");
    		joke.title = resultSet.getString("title");
    		joke.description = resultSet.getString("description");
    	}
    
		return joke;
	}

	public String[] parseTags(String tags) {
		String[]tagList = tags.split(" ");
		return tagList;
	}

	public int getId() throws SQLException {
		connect_func();
    	String SQLStatement = "SELECT * FROM jokes";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
    	int jokeId = 0;
    	while(resultSet.next()) {
    		jokeId = resultSet.getInt("JokeId");
    	}
		
		return jokeId;
	}
	
	public List<Joke> getFavoriteJokes(String currentUser) throws SQLException {
		List<Joke> favoriteJokes = new ArrayList<Joke>();
		
		connect_func();
		//Query to return all jokes in DB
    	String SQLStatement = "SELECT * FROM hasFavoriteJokes WHERE userId= \""+ currentUser +"\"";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		
    	while(resultSet.next()) {
    		Joke favoriteJoke = new Joke();
    		favoriteJoke.userId = currentUser;
    		favoriteJoke.jokeId = resultSet.getInt("jokeId");
    		favoriteJoke.title = resultSet.getString("title");
    		favoriteJokes.add(favoriteJoke);
    		//needs to return title
    		
    	}
        disconnect();        
		return favoriteJokes;
	}
	
	public void deleteJokeFromList(String currentUser, String jokeId)  throws SQLException {
		
		connect_func();
		String deleteJoke = "DELETE FROM hasFavoriteJokes Where userId = '"+currentUser+ "' AND jokeId = '" + jokeId + "'"  ;
		statement =  (Statement) connect.createStatement();
		statement.executeUpdate(deleteJoke);
	}

	public List<Joke> search(String keyword) throws SQLException {
		List<Joke> jokes = new ArrayList<Joke>();
		
		connect_func();
		//Query to return all jokes in DB that correspond to the keyword the user searched for
    	String SQLStatement = "SELECT * FROM sampledb.hastags H, sampledb.jokes J Where H.JokeId = J.JokeId AND h.TagWord = '" + keyword + "'";
    	statement =  (Statement) connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
		
    	while(resultSet.next()) {
    		Joke joke = new Joke();
    		joke.jokeId = resultSet.getInt("JokeId");
    		joke.date = resultSet.getString("TheDate");
    		joke.userId = resultSet.getString("userId");
    		joke.title = resultSet.getString("title");
    		joke.description = resultSet.getString("description");
    		jokes.add(joke);
    		
    	}
        resultSet.close();
        statement.close();         
        disconnect();        
		return jokes;
	}
	
	public List<Joke> getUserJokes(String userId) throws SQLException {
		List<Joke> favoriteJokes = new ArrayList<Joke>();
		
		connect_func();
		String SQLStatement = "SELECT * FROM jokes WHERE userId= '"+ userId + "'";
		statement =  (Statement) connect.createStatement();
		resultSet = statement.executeQuery(SQLStatement);
		
		while(resultSet.next()) {
			Joke favoriteJoke = new Joke();
			favoriteJoke.date = resultSet.getString("theDate");
			favoriteJoke.title = resultSet.getString("title");
			favoriteJoke.jokeId = resultSet.getInt("jokeId");
			favoriteJokes.add(favoriteJoke);
		}
	    disconnect();        
		return favoriteJokes;
	}




    

}
