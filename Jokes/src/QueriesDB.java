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

@WebServlet("/QueriesDB")
public class QueriesDB extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private JokeDB  jokeDB = new JokeDB();
	
	QueriesDB(){
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
	    
	    public ArrayList<Member> query2(String tagX, String tagY) throws SQLException {
	    	ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT UserId "
					+ "FROM members "
					+ "WHERE UserId "
					+ "IN( "
						+ "SELECT J.UserId "
						+ "FROM jokes J, jokes K, hastags S, hastags T "
						+ "WHERE J.UserId=K.UserId "
						+ "AND J.JokeId<>K.JokeId "
						+ "AND J.theDate=K.theDate " 
						+ "AND J.JokeId=S.JokeId "
						+ "AND K.JokeId=T.JokeId "
						+ "AND S.TagWord = '" + tagX + "' "
						+ "AND T.TagWord = '" + tagY + "' "
					+ ")";
			System.out.println(SQLStatement);
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;
	    }
		public ArrayList<Joke> query3(String member) throws SQLException {
			ArrayList<Joke> jokes = new ArrayList<Joke>();
			connect_func();
			String SQLStatement = "SELECT DISTINCT J.JokeId " + 
					"FROM reveiws R, jokes J " + 
					"WHERE J.UserId = '" +  member + "'" +
					"AND R.JokeId = J.JokeId " + 
					"NOT IN ( " + 
					"SELECT JokeId " + 
					"FROM reveiws " + 
					"WHERE Rate < 2)";
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Joke joke = new Joke();
	    		joke.userId = member;
	    		joke.jokeId = resultSet.getInt("JokeId");
	    		joke.title = jokeDB.getJokeTitle(joke.jokeId);
	    		jokes.add(joke);
	    	}
			
			return jokes;
		}
		public ArrayList<Member> query5(String memberX, String memberY) throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT user1.otherUserId " + 
					"FROM hasfavoriteUsers user1, hasfavoriteusers user2 " + 
					"WHERE user1.UserId = '" + memberX + "' " +
					"AND user2.UserId = '" + memberY + "' " +
					"AND user1.OtherUserId = user2.OtherUserId";
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("otherUserId");
	    		members.add(member);
	    	}
			return members;
		}
		public ArrayList<Member> query6() throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT UserId "
					+ "FROM members "
					+ "WHERE UserId "
					+ "NOT IN( "
						+ "SELECT M.UserId "
						+ "FROM members M, reveiws R, jokes J "
						+ "WHERE R.JokeId = J.JokeId AND J.UserId = M.UserId AND R.Rate = 3 "
						+ "GROUP BY J.JokeId "
						+ "HAVING COUNT(*) > 2 "
					+ ")";
					
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;
		}
		public ArrayList<Member> query9() throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT DISTINCT UserId "
					+ "FROM jokes "
					+ "WHERE UserId "
					+ "NOT IN( "
						+ "SELECT M.UserId "
						+ "FROM members M, jokes J, reveiws R "
						+ "WHERE M.UserId = J.UserId AND J.JokeId = R.JokeId AND R.Rate = 0 "
					+ ")";
					
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;
		}
		public ArrayList<Member> query4() throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT J.UserId "
					+ "FROM jokes J "
					+ "WHERE J.theDate>='2018-03-01' GROUP BY UserId "
					+ "HAVING COUNT(*)=( "
						+ "SELECT MAX(Num) "
						+ "FROM (SELECT UserId, COUNT(*) as Num "
						+ "FROM jokes "
						+ "WHERE theDate>='2018-03-01' "
						+ "GROUP BY UserId)ALIAS "
					+ ")";
					
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;
		}
		public ArrayList<Member> query7() throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT UserId "
					+ "FROM members "
					+ "WHERE UserId "
					+ "NOT IN ( "
						+ "SELECT UserId "
						+ "FROM reveiws "
						+ "WHERE rate < 1 "
					+ ")";
					
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;

		}
		public ArrayList<Member> query8() throws SQLException {
			ArrayList<Member> members = new ArrayList<Member>();
			connect_func();
			String SQLStatement = "SELECT UserId "
					+ "FROM members "
					+ "WHERE UserId "
					+ "IN( "
						+ "SELECT UserId "
						+ "FROM reveiws bigR "
						+ "WHERE bigR.Rate = 0 "
						+ "GROUP BY UserId "
						+ "HAVING COUNT(*) =( "
							+ "SELECT COUNT(*) "
							+ "FROM reveiws lilR "
							+ "WHERE lilR.UserId = bigR.UserId "
						+ ")"
					+ ")";
					
	    	statement =  (Statement) connect.createStatement();
	    	resultSet = statement.executeQuery(SQLStatement);
	    	
	    	while(resultSet.next()) {
	    		Member member = new Member();
	    		member.userId = resultSet.getString("UserId");
	    		members.add(member);
	    	}
			return members;
		}
}

