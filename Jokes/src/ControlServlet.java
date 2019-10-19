import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * ControllerServlet.java
 */

public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberDB memberDB;
    private JokeDB jokeDB;
    private ReveiwDB reveiwDB;
    private QueriesDB queriesDB;
    public String currentUser;
    
    public void init() {
        memberDB = new MemberDB(); 
        jokeDB = new JokeDB();
        reveiwDB = new ReveiwDB();
        queriesDB = new QueriesDB();
        currentUser = new String();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	this.doGet(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
        	switch(action) {
        		case "/":
        		{
        			response.sendRedirect("startPage.jsp");
        			break;
        		}
        		case "/initializeDatabase":
        		{
        			initializeDatabase(request, response);
        			break;
        		}
        		case "/login":
        		{
        			loginRequest(request, response);
        			break;
        		}
        		case "/register":
        		{
        			register(request, response);
        			break;
        		}
        		case "/userHome":
        		{
        			listAllJokes(request, response);
        			break;
        		}
        		case "/makePost":
        		{
        			makePost(request, response);
        			break;
        		}
        		case "/profilePage":
        		{
        			userHomepage(request, response);
        			break;
        		}
        		case "/addPost":
        		{
        			addPost(request, response);
        			break;
        		}
        		case "/addFriend":
        		{
        			addFriend(request, response);
        			break;
        		}
        		case "/jokePage":
        		{
        			buildJokePage(request, response);
        			break;
        		}
        		case "/deleteFriend":
        		{
        			deleteFriend(request, response);
        			break;
        		}
        		case "/search":
        		{
        			searchJokes(request, response);
        			break;
        		}
        		case "/addReview":
        		{
        			addReview(request, response);
        			break;
        		}
        		case "/addJoke":
        		{
        			addJoke(request, response);
        			break;
        		}
        		case "/deleteJoke":
        		{
        			deleteJoke(request, response);
        			break;
        		}
        		case "/visitUser":
        		{
        			buildUserPage(request, response);
        			break;
        		}
        		case "/queries":
        		{
        			queries(request, response);
        			break;
        		}
        	}
        } catch (SQLException ex) {
        	throw new ServletException(ex);
        }
    }
    
    private void queries(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	//the case number represents the number from the instructions
    	Integer Qid = 0;
    	if((String) request.getParameter("Qid") != null) {
    		Qid = Integer.valueOf((String) request.getParameter("Qid"));
        	request.setAttribute("Qid", Qid);
    	}
    	
    	ArrayList<Member> allMembers = new ArrayList<Member>();
    	allMembers = memberDB.getAllMembers();
    	request.setAttribute("allMembers", allMembers);
    	
    			switch(Qid) {
    				case(2):{
    					String tagX = request.getParameter("tagX");
    					String tagY = request.getParameter("tagY");
    					System.out.println(tagX);
    					ArrayList<Member> members = queriesDB.query2(tagX, tagY);
    					request.setAttribute("members", members);
    					break;
    				}
    				case(3):{
    					String member = request.getParameter("memberSelection");
    					ArrayList<Joke> jokes = queriesDB.query3(member);
    					request.setAttribute("jokes", jokes);
    					break;
    				}
    				case(4):{
    					ArrayList<Member> members = queriesDB.query4();
    					request.setAttribute("members", members);
    					break;
    				}
    				case(5):{
    					String memberX = request.getParameter("memberX");
    					String memberY = request.getParameter("memberY");
    					ArrayList<Member> members = queriesDB.query5(memberX, memberY);
    					request.setAttribute("members", members);
    					break;
    				}
    				case(6):{
    					ArrayList<Member> members = queriesDB.query6();
    					request.setAttribute("members", members);
    					break;
    				}
    				case(7):{
    					ArrayList<Member> members = queriesDB.query7();
    					request.setAttribute("members", members);
    					break;
    				}
    				case(8):{
    					ArrayList<Member> members = queriesDB.query8();
    					request.setAttribute("members", members);
    					break;
    				}
    				case(9):{
    					ArrayList<Member> members = queriesDB.query9();
    					request.setAttribute("members", members);
    					break;
    				}
    				case(10):{
    					break;
    				}
    				default:{
    					//refresh query selection
    					break;
    				}
    			}
    	RequestDispatcher dispatcher = request.getRequestDispatcher("queries.jsp");
        dispatcher.forward(request, response);
    			
	}

	private void buildUserPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	String userId = request.getParameter("userId"); 
    	
    	Member member = memberDB.getMember(userId);
    	request.setAttribute("member", member);
    	
    	List<Joke>joke = jokeDB.getUserJokes(userId);
    	request.setAttribute("joke", joke);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("visitUserHomepage.jsp");
        dispatcher.forward(request, response);
    }
    
    private void deleteJoke(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	System.out.println(currentUser);
    	String jokeId = request.getParameter("id");
        jokeDB.deleteJokeFromList(currentUser, jokeId);
    	response.sendRedirect("profilePage");
    }
    
    private void addJoke(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	int jokeId = Integer.valueOf(request.getParameter("id"));
    	Joke joke = jokeDB.getJoke(jokeId);
    	String title = joke.title;
        jokeDB.addJokeToFavList(currentUser, jokeId, title);
    	response.sendRedirect("profilePage");
    }
    
    private void addReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		Joke joke = jokeDB.getJoke(Integer.valueOf(request.getParameter("jokeId")));
		
    	if(currentUser.contentEquals(joke.userId)) {
    		response.sendRedirect("userHome");
    		return;
    	}
    	Reveiw review = new Reveiw();
		review.setJokeId(Integer.valueOf(request.getParameter("jokeId")));
		review.setRemark(request.getParameter("remarks"));
		review.setTheDate(ReveiwDB.getCurrentDate());
		review.setUserId(currentUser);
		review.setRate(Integer.valueOf(request.getParameter("rate")));
		
		reveiwDB.insert(review);
		
		response.sendRedirect("userHome");
	}

	private void searchJokes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Joke> jokes = jokeDB.search(request.getParameter("keyword"));
        request.setAttribute("jokes", jokes);      
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHome.jsp");
        dispatcher.forward(request, response);
	}

	private void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	System.out.println(currentUser);
    	String otherUser = request.getParameter("id");
    	if(!currentUser.equals(otherUser)) {
        	memberDB.deleteFriendFromList(currentUser, otherUser);
    	}
    	response.sendRedirect("profilePage");
    }
    
    private void buildJokePage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	//get the joke from the database
    	int jokeId = Integer.valueOf(request.getParameter("jokeId")); 
    	Joke joke = jokeDB.getJoke(jokeId);
    	
    	List<String> tags = jokeDB.getTags(jokeId);
    	
    	String tagLine = new String("");
    	for(int i = 0; i<tags.size(); i++) {
    		tagLine = tagLine + " " + tags.get(i);
    	}
    	joke.tags = tagLine;
    	
    	//get the reviews for this joke from the database
    	List<Reveiw> reviews = reveiwDB.getReviews(jokeId);
    	
    	request.setAttribute("reviews", reviews);
    	request.setAttribute("joke", joke);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("jokePage.jsp");
        dispatcher.forward(request, response);
    	
    }
    private void addFriend(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
    	String otherUser = request.getParameter("id");
    	if(!currentUser.contentEquals(otherUser)) {
        	memberDB.addFriendToList(currentUser, otherUser);
        	response.sendRedirect("userHome");
    	}
    	else {
        	response.sendRedirect("userHome");
    	}

    }
		

	private void makePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("currentUser", currentUser );
    	System.out.println(currentUser);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("makePost.jsp");
        dispatcher.forward(request, response);
		
	}

	private void listAllJokes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Joke> jokes = jokeDB.getJokes();
        request.setAttribute("jokes", jokes);      
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHome.jsp");
        dispatcher.forward(request, response);
		
	}


    public void initializeDatabase(HttpServletRequest request, HttpServletResponse response) 
    	throws SQLException, IOException, ServletException{
    	
        response.sendRedirect("landing.jsp");
    	memberDB.createTables();
    	jokeDB.createTables();
    	reveiwDB.createTables();
    }
    
    public void loginRequest(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{
    	//extract the info from the form
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	Member possibleMember = new Member(username, password);
    	
    	if(memberDB.checkLogin(possibleMember) == true) {
    		//this is where the member goes after a successful login
    		//using forward instead of redirect so that it goes through the get/post 
    		System.out.println(currentUser);
            currentUser = username;
    		System.out.println(currentUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userHome");
            dispatcher.forward(request, response);
    	}
    	else {
    		//this is where you handle a failed login attempt
            response.sendRedirect("landing.jsp");
    	}
    }
    
    public void register(HttpServletRequest request, HttpServletResponse response)
    	throws SQLException, IOException, ServletException{
    	String password = request.getParameter("password");
    	String confirmPassword = request.getParameter("confirmPassword");
    	if(password.contentEquals(confirmPassword)) {
    		String firstname = request.getParameter("firstName");
        	String lastname = request.getParameter("lastName");
        	String email = request.getParameter("email");
        	int age = Integer.parseInt(request.getParameter("age"));
        	String gender= request.getParameter("gender");
        	String username = request.getParameter("username");
        	Member newMember = new Member(username, password, firstname, lastname, email, gender, age);

        	if(memberDB.checkUser(newMember) == false){
        		response.sendRedirect("landing.jsp");
        	}
        	else if(memberDB.checkEmail(newMember) == false){
        		response.sendRedirect("landing.jsp");
        	}
        	else {
        		memberDB.insert(newMember);
        		//using forward instead of redirect so that it goes through the get/post 
                RequestDispatcher dispatcher = request.getRequestDispatcher("/userHome");
                dispatcher.forward(request, response);

        	}
    	}
    	
    }
    
    public void userHomepage(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException{
    	List<Member> members = memberDB.getMembers(currentUser);
        request.setAttribute("members", members); 
        List<Joke> jokes = jokeDB.getFavoriteJokes(currentUser);
        request.setAttribute("jokes", jokes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userHomepage.jsp");
        dispatcher.forward(request, response);
    }
    
    public void addPost(HttpServletRequest request, HttpServletResponse response)
        	throws SQLException, IOException, ServletException{
    	
    	String title = request.getParameter("title");
    	String description = request.getParameter("description");
    	String tags = request.getParameter("tags");
    	
    	Joke newJoke = new Joke(JokeDB.getCurrentDate(), currentUser, title, description, tags);
    	jokeDB.insert(newJoke);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/userHome");
    	dispatcher.forward(request, response);
    	}


    	
//------------end class
}