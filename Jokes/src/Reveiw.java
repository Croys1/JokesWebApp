
public class Reveiw {
	
	//member variables
	protected int reveiwId;
	protected String userId;
	protected int jokeId;
	protected String theDate;
	protected int rate;
	protected String remark;
	
	//constructor
	Reveiw(){
		
	}
	
	Reveiw(int reveiwId, String userId, int jokeId, String theDate, int rate, String remark){
		this.reveiwId = reveiwId;
		this.userId = userId;
		this.jokeId = jokeId;
		this.theDate = theDate;
		this.rate = rate;
		this.remark = remark;
	}
	public int getReveiwId() {
		return reveiwId;
	}

	public void setReveiwId(int reveiwId) {
		this.reveiwId = reveiwId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getJokeId() {
		return jokeId;
	}

	public void setJokeId(int jokeId) {
		this.jokeId = jokeId;
	}

	public String getTheDate() {
		return theDate;
	}

	public void setTheDate(String theDate) {
		this.theDate = theDate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	Reveiw(String userId, String theDate, int jokeId, int rate, String remark){
		this.userId = userId;
		this.theDate = theDate;
		this.jokeId = jokeId;
		this.rate = rate;
		this.remark = remark;
	}

}
