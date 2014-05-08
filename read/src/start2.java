import java.util.ArrayList;


public class start2 {
	public static void main(String[] args) {
		ArrayList<UserScore> mus;
		mus = loadUserScore();
		
	}
	
	
	public static ArrayList<UserScore> loadUserScore(){
		String path = "/Users/jiyuan/Documents/xiuya/allbut/u1.base";
		//String path = "E://Python/test.txt";
		MyUtils rd = new MyUtils();
		String[] s = rd.writeToDat(path);
		ArrayList<UserScore> mus = new ArrayList<UserScore>();
		
		for(int index=0;index<s.length;index++){
			int num = 0;
			UserScore us = new UserScore(0,0,0);
			String temp="";
			for(int i=0;i<s[index].length();i++){
				if(!String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")){
					temp+=s[index].charAt(i);
					//System.out.println(temp);
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==0){
					System.out.println(Integer.parseInt(temp));
					us.setUserId(Integer.parseInt(temp));
					System.out.println("第"+index+"条数据的用户id "+us.getUserId());
					temp=String.valueOf("");
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==1){
					us.setFilmId(Integer.parseInt(temp));
					temp=String.valueOf("");
					System.out.println("第"+index+"条数据的电影号  "+us.getFilmId());
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==2){
					us.setScore(Integer.parseInt(temp));
					temp="";
					System.out.println("第"+index+"条数据的电影评分  "+us.getScore());
					num++;
					continue;
				}
				
			}
			mus.add(us);
			System.out.println("第"+index+"条用户数据读取完成");	
		}
		System.out.println("list中有"+mus.size()+"条数据");
		return mus;
	}
	
}
