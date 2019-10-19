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

@WebServlet("/ReveiwDB")
public class ReveiwDB extends HttpServlet {
	//member variables
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public ReveiwDB() {
		
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
    	statement.executeUpdate("DROP TABLE IF EXISTS reveiws");
    	//create a table of reviews with at least 10 tuples
    	//statement to be passed to the server
    	SQLStatement = "CREATE TABLE IF NOT EXISTS reveiws" + (
    			 "(ReveiwId MEDIUMINT NOT NULL AUTO_INCREMENT,"
    			+ "UserId VARCHAR(30), "
    			+ "JokeId MEDIUMINT NOT NULL, "
    			+ "TheDate DATE NOT NULL, "
    			+ "Rate INTEGER NOT NULL, "
    			+ "Remark VARCHAR(140), "
    			+ "PRIMARY KEY(ReveiwId))"
    			);
    	statement.executeUpdate(SQLStatement);
    	
    	SQLStatement = "ALTER TABLE reveiws ADD CONSTRAINT user FOREIGN KEY(UserId) REFERENCES members(UserId)";
    	statement.executeUpdate(SQLStatement);
    	
    	SQLStatement = "ALTER TABLE reveiws ADD CONSTRAINT joke FOREIGN KEY(UserId) REFERENCES members(UserId)";
    	statement.executeUpdate(SQLStatement);
    	List<Reveiw> someReveiws = new ArrayList<Reveiw>();
    	// use insert function to build up the table
    	
    	someReveiws.add(new Reveiw("john", getCurrentDate(), 1, 1,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("otherBill", getCurrentDate(), 2, 2,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("otherBill", getCurrentDate(), 3, 3,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("john", getCurrentDate(), 4, 2,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("john", getCurrentDate(), 5, 3,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("otherBill", getCurrentDate(), 6, 1,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("root", getCurrentDate(), 7, 1,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("root", getCurrentDate(), 8, 2,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("root", getCurrentDate(), 9, 3,"This joke was not that funny." ));
    	someReveiws.add(new Reveiw("otherBill", getCurrentDate(), 10, 3,"This joke was totes hillar'." ));
    	
    	for(int i = 0; i < someReveiws.size(); i++) {
    		insert(someReveiws.get(i));
    	}
    	
    	statement.close();
    	disconnect();
    }
    
    public boolean insert(Reveiw reveiw) throws SQLException{
    	if(reviewLimit(reveiw)) {
        	connect_func();
        	String SQLStatement = "INSERT INTO reveiws( UserId, TheDate, jokeId, rate, remark) VALUES (?,?,?,?,?)";    
        	
        	preparedStatement = (PreparedStatement) connect.prepareStatement(SQLStatement);
        	preparedStatement.setString(1, reveiw.userId);
        	preparedStatement.setString(2, reveiw.theDate);
        	preparedStatement.setInt(3, reveiw.jokeId);
        	preparedStatement.setInt(4, reveiw.rate);
        	preparedStatement.setString(5, reveiw.remark);
        	
        	//check if the joke was inserted properly
            boolean rowInserted = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            
            return rowInserted;
    	}
    	else {
    		return false;
    	}
    	
    }
    
    private boolean reviewLimit(Reveiw reveiw) throws SQLException {
    	List<Reveiw>reviews = new ArrayList<Reveiw>();
		

		reviews = getReveiws();
    	String theDate = getCurrentDate(); 
    	String user = reveiw.userId;
    	int count = 0;
    	for(int i =0; i < reviews.size(); i++) {
    		Reveiw tempReview = reviews.get(i);
    		
    		if(tempReview.userId.contentEquals(user) && tempReview.theDate.contentEquals(theDate)) {
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

	private List<Reveiw> getReveiws() throws SQLException {
		connect_func();
		List<Reveiw> reviews = new ArrayList<Reveiw>();
		String SQLStatement = "SELECT * FROM sampledb.reveiws";
    	statement = connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
    	while(resultSet.next()) {
    		Reveiw review = new Reveiw();
    		review.setUserId(resultSet.getString("UserId"));
    		review.setTheDate(resultSet.getString("TheDate"));
    		review.setRemark(resultSet.getString("Remark"));
    		review.setRate(resultSet.getInt("Rate"));
    		reviews.add(review);
    	}
    	
		return reviews;
	}

	public static String getCurrentDate(){
    	//get the current date using the java until and then convert to the SQL format
    	java.util.Date jDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(jDate.getTime());
        
        return sDate.toString();
        
    }

	public List<Reveiw> getReviews(int jokeId) throws SQLException {
		connect_func();
		List<Reveiw> reviews = new ArrayList<Reveiw>();
		String SQLStatement = "SELECT * FROM sampledb.reveiws WHERE JokeId = '"+ jokeId+ "'";
    	statement = connect.createStatement();
    	resultSet = statement.executeQuery(SQLStatement);
    	while(resultSet.next()) {
    		Reveiw review = new Reveiw();
    		review.setUserId(resultSet.getString("UserId"));
    		review.setTheDate(resultSet.getString("TheDate"));
    		review.setRemark(resultSet.getString("Remark"));
    		review.setRate(resultSet.getInt("Rate"));
    		reviews.add(review);
    	}
    	
		return reviews;
	}
    

}
