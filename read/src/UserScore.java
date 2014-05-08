
public class UserScore {
	private int user_id;
	private int film_id;
	private int score;
	public UserScore(int user_id, int film_id, int score) {
		super();
		this.user_id = user_id;
		this.film_id = film_id;
		this.score = score;
	}
	
	public void setUserId(int id){
		user_id = id;
	}
	public void setFilmId(int id){
		film_id = id;
	}
	public void setScore(int sc){
		score = sc;
	}
	
	public int getUserId(){
		return user_id;
	}
	public int getFilmId(){
		return film_id;
	}
	public int getScore(){
		return score;
	}
}
