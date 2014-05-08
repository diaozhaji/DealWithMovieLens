
public class User {
	public int user_id;
	public int [] user_attr;
	
	public User(int user_id, int[] user_attr) {
		super();
		this.user_id = user_id;
		this.user_attr = user_attr;
	}

	
	public void setId(int id){
		user_id = id;
	}
	
	public void setAttr(int [] attr){
		user_attr = attr;
	}
	
}
