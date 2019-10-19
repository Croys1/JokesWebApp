
public class Joke {
	
	//member variables
	protected int jokeId;
	protected String date;
	protected String userId;
	protected String title;
	protected String description;
	protected String tags;
	//constructors
	Joke(){
	}
	
	public Joke(String date, String userId, String title, String description, String tags) {

		this.date = date;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.tags = tags;
	}
	

	public Joke(int jokeId, String date, String userId, String title, String description) {
		this.jokeId = jokeId;
		this.date = date;
		this.userId = userId;;
		this.title = title;
		this.description = description;
	}
	public Joke(String date, String userId, String title, String description) {
		this.date = date;
		this.userId = userId;
		this.title = title;
		this.description = description;
	}
	public int getJokeId() {
		return jokeId;
	}
	public String getDate() {
		return date;
	}
	public String getUserId() {
		return userId;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getTags() {
		return tags;
	}
}
