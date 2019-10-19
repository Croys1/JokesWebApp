
public class Member {
	
	//member variables
	protected int id;
	protected String userId; //this is their userName
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String gender;
	protected int age;
	protected boolean isAdmin = false;
	
	//constructors
	Member(){
	}
	
	//do we need other overloaded constructors for this class
	
	//this constructor is used by the create tables function
	//it could also be used during registration
	public Member(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}
	public Member(String userId, String password, String firstName, String lastName, String email, String gender, int age) {
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
	}
	public Member(String userId, String password, String firstName, String lastName, String email, String gender, int age, boolean isAdmin) {
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.isAdmin = isAdmin;
	}

	//setter functions
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//getter functions
	public String getUserId() {
		return userId;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public int getAge() {
		return age;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
}
