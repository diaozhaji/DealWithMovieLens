import java.text.DecimalFormat;
import java.util.ArrayList;


public class start {
	
	
	
	public static void main(String[] args) {
		//电影种类矩阵
		ArrayList<Film> mfa;
		//用户数据
		ArrayList<UserScore> mus;			//	总表	
		ArrayList<UserScore> sus;	//	某一个用户
		//读入数据
		mfa = getFilmMatrix();
		mus = loadAllUserScore();
		
		//getUserInfo(108,mfa,mus);
		replaceUserScoreById(108,mfa,mus);
		
		/*
		//留作矩阵填充
		double userFilmSim[] = new double[1682];
		sus = loadUserMatrix(mus, 165);
		double[] a = LoadUserVector(mfa, sus);
		a = Normalized(a);
		for (int j = 0; j < mfa.size(); j++) {
			userFilmSim[j] = Calculate(a, mfa, j);
			// 四舍五入
			// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
		}
		MyUtils.printArray(userFilmSim);
		*/
		
		/*
		//计算某一用户 用户向量的方法
		sus = loadUserMatrix(mus, 165);	
		double []a = LoadUserVector(mfa,sus);
		a = Normalized(a);
		MyUtils.printArray(a);
		
		//writoUserVectorToFile(mfa,mus);
		*/
		
		/*
		String s_print="";
		for(int i=0;i<mus.size();i++){
			for(int j=0;j<19;j++){
				s_print+=" "+score[i][j];
			}
			System.out.println("第"+mus.get(i).getFilmId()+"个电影的向量是"+s_print);
			s_print="";
		}*/
			
		
		//得到用户向量 用户165
		/*
		double user[] = { 0.000, 0.984, 1.016, 1.270, 1.016, 1.143, 0.847,
				0.000, 1.048, 0.000, 0.000, 0.000, 1.270, 0.762, 0.847, 1.016,
				0.889, 1.186, 0.000 };*/
		
		//writeUserSimMatrixToFile(mfa,mus,sus);
		
		/*
		//打印全部用户相似度方法
		double allUserFilmSim[][] = new double [943][1682];
		for(int i=0;i<943;i++){
			for(int j=0;j<mfa.size();j++ ){
				sus = loadUserMatrix(mus, i);	
				double []a = LoadUserVector(mfa,sus);
				a = Normalized(a);
				//MyUtils.printArray(a);
				
				allUserFilmSim[i][j] = Calculate(a,mfa,j);
			}
		}
		MyUtils.printMatrix(allUserFilmSim);
		*/
		
		
	}

	private static double[] replaceUserScoreById(int id, ArrayList<Film> mfa,
			ArrayList<UserScore> mus) {
		// TODO Auto-generated method stub
		double[] score = getUserFilmSimById(id, mfa, mus);
		ArrayList<UserScore> sus = loadUserScoreById(mus, id);
		int cnt = 0;
		for (int i = 0; i < sus.size(); i++) {
			int a = sus.get(i).getFilmId();
			double b = score[a];
			score[a] = sus.get(i).getScore();
			if ((score[a] > (b - 1)) && (score[a] < (b + 1)))
				cnt++;
			System.out.println("将第" + a + "个电影的评分" + b + "换成了用户真实评价的分数"
					+ score[a]);
		}
		System.out.println("该用户共评价" + sus.size() + "个电影");
		System.out.println("误差在+－1分之间的个数为:" + cnt);

		return score;
	}

	/*
	 * 打印出某一用户的相关信息
	 * 
	 * 无返回值，只是查看用户的信息
	 * 
	 * */
	private static void getUserInfo(int id, ArrayList<Film> mfa,ArrayList<UserScore> mus) {
		// TODO Auto-generated method stub
		// int id = 241;
		System.out.println("用户" + id + "情况");
		ArrayList<UserScore> sus = loadUserScoreById(mus, id);
		System.out.println("该用户共评价电影数:" + sus.size());
		MyUtils.printOriUserScore(mfa, sus);

		double[] a = LoadUserVector(mfa, sus);

		MyUtils.printArray(a);
		a = Normalized(a);
		System.out.println("归一化之后:");
		MyUtils.printArray(a);
	}


	/*
	 * 将全部用户相似度矩阵写入文件
	 * 考虑2种可能性，1种保留3位小数，1种将相似度四舍五入
	 * 体现在matrixToLineDouble()和matrixToLineInt()方法上
	 * 
	 * */
	private static void writeUserSimMatrixToFile(ArrayList<Film> mfa,ArrayList<UserScore> mus){
		// TODO Auto-generated method stub
		//String filePath = "/Users/jiyuan/Documents/xiuya/全部用户相似度矩阵3位小数.txt";
		//String filePath = "/Users/jiyuan/Documents/xiuya/全部用户相似度矩阵四舍五入.txt";
		String filePath = "/Users/jiyuan/Documents/xiuya/test.txt";
		for (int i = 0; i < 10; i++)
			MyUtils.appendMethodB(filePath, i + "     ");
		for (int i = 10; i < 100; i++)
			MyUtils.appendMethodB(filePath, i + "    ");
		for (int i = 100; i < 1000; i++)
			MyUtils.appendMethodB(filePath, i + "   ");
		for (int i = 1000; i < 1682; i++)
			MyUtils.appendMethodB(filePath, i + "  ");
		MyUtils.appendMethodB(filePath, "\n");
		
		for (int i = 1; i < 944; i++) {
			double userFilmSim[] = new double[1682];
			ArrayList<UserScore> sus = loadUserScoreById(mus, i);
			double[] a = LoadUserVector(mfa, sus);
			a = Normalized(a);
			for (int j = 0; j < mfa.size(); j++) {
				userFilmSim[j] = Calculate(a, mfa, j);
				// 四舍五入
				// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
			}
			MyUtils.printArray(userFilmSim);
			String content = MyUtils.matrixToLineDouble(userFilmSim);
			MyUtils.appendMethodB(filePath, content + "\n");
			// MyUtils.appendMethodB("/Users/jiyuan/Documents/xiuya/全部用户相似度矩阵四舍五入.txt",content+"\n");

			System.out.println("第" + i + "个用户相似度写入成功");
		}

	}
	
	/*
	 * 得到用户与电影的相似度
	 */
	public static double []getUserFilmSimById(int id,ArrayList<Film> mfa,ArrayList<UserScore> mus){
		
		double userFilmSim[] = new double[1682];
		ArrayList<UserScore> sus = loadUserScoreById(mus, id);
		double[] a = LoadUserVector(mfa, sus);
		a = Normalized(a);
		for (int j = 0; j < mfa.size(); j++) {
			userFilmSim[j] = Calculate(a, mfa, j);
			// 四舍五入
			// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
		}
		MyUtils.printArray(userFilmSim);
		
		return userFilmSim;
		
	}
	

	/*
	 * 计算所有用户向量 得到矩阵写入文件
	 */
	private static void writoUserVectorToFile(ArrayList<Film> mfa , ArrayList<UserScore> mus) {
		// TODO Auto-generated method stub
		double[][] userVector = new double[943][19];
		for (int id = 1; id < 944; id++) {
			ArrayList<UserScore> sus = loadUserScoreById(mus, id);
			// System.out.println("用户"+id+"的评分矩阵大小为"+sus.size());
			double[] user = new double[19];

			for (int i = 0; i < 19; i++) {

				user = LoadUserVector(mfa, sus);
				userVector[id - 1][i] = user[i];

			}
			user = Normalized(user);

			String log = id + " ";
			String f = "";
			DecimalFormat df = new DecimalFormat("0.000");

			for (int i = 0; i < 19; i++) {
				// f = df.format(userVector[id - 1][i]);
				f = df.format(user[i]);
				log += f + " ";
			}
			// System.out.println(log);
			log += "\n";
			MyUtils.appendMethodB("/Users/jiyuan/Documents/xiuya/所有用户向量.txt",log);

		}
	}
	
	/*
	 * 根据电影分类矩阵，用户评分矩阵计算用户向量
	 * 
	 * */
	public static double [] LoadUserVector(ArrayList<Film> mfa, ArrayList<UserScore> singleUser){
		//一个用户的电影电影评分矩阵score[][]
		int [][] score=new int[singleUser.size()][19];
		for(int i=0;i<singleUser.size();i++){
			for(int j=0;j<19;j++){
				score[i][j]=mfa.get(singleUser.get(i).getFilmId()-1).film_attr[j];
			}
		}/*
		//打印用户评分矩阵
		for(int i=0;i<singleUser.size();i++){
			String str = "";
			for(int j=0;j<19;j++){
				if(score[i][j]==1){
					score[i][j]=singleUser.get(i).getScore();
				}
				
				str += score[i][j]+" ";
			}
			System.out.println(singleUser.get(i).getFilmId()+" "+str);
		}*/
		//MyUtils.printMatrix(score);
		
		double sum=0;
		int sumOneNum=0;
		double user[]=new double[19];
		for(int j=0;j<19;j++){	//	按列
			int tmp=0;
			int OneNum=0;
			for(int i=0;i<singleUser.size();i++){
				if(score[i][j]==0){}
				if(score[i][j]!=0){
					tmp+=singleUser.get(i).getScore();
					OneNum++;
					
					sum+=singleUser.get(i).getScore();
					sumOneNum++;
				}
			}
			if(OneNum==0)
				user[j]=0;
			if(OneNum!=0)
				user[j]=(double)tmp/((double)OneNum);			
		}
		sum = sum/sumOneNum;
		System.out.println("该用户评价所有项目的平均分为："+sum);
		for(int i=0;i<19;i++){
			user[i]=user[i]/sum;
			//System.out.println("user["+i+"]为： "+user[i]);
		}
		return user;
	}
	
	/*
	 * 根据一个用户向量，计算他对某一个电影的相似度
	 * 
	 * */
	public static double Calculate(double user[], ArrayList<Film> fa, int p) {
		// 计算用户偏好值
		// 分子
		double Molecular = 0.0;
		// 分母
		double Denominator1 = 0.0;
		double Denominator2 = 0.0;
		double result = 0.0;
		for (int i = 0; i < 19; i++) {
			Molecular += fa.get(p).film_attr[i] * user[i];
			Denominator1 += fa.get(p).film_attr[i] * fa.get(p).film_attr[i];
			Denominator2 += user[i] * user[i];
		}
		result = 5 * 1.6* Molecular
				/ (Math.sqrt(Denominator1) * Math.sqrt(Denominator2));
		
		//DecimalFormat df = new DecimalFormat("0.000");
		//String r = "";
		//r = df.format(result);
		//System.out.println("用户与第" + p + "个电影的相似度为 r=" + r);
		return result;
		

	}
	
	/*
	 * 根据绝对路径读入文件，得到电影的项目矩阵
	 * 
	 * */
	
	public static ArrayList<Film> getFilmMatrix(){
		String path = "/Users/jiyuan/Documents/xiuya/allbut/u.item";
		String[] s = MyUtils.writeToDat(path);
		ArrayList<Film> mFilmArray = new ArrayList<Film>();
		for(int index=0;index<s.length;index++){
			//System.out.println("电影："+index);
			int [] attr=new int[19];
			Film film = new Film(index,attr);
			film.setId(index+1);//list的第一个是从0开始，所以要+1
			int num=0;
			int j=0;
			for(int i=0;i<s[index].length();i++){
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("|"))
					num++;
				if(num>=5)
				{
					if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("|")){}
					else{
						attr[j]=Integer.parseInt(String.valueOf(s[index].charAt(i)));
						j++;
					}
				}
			}
			for(int i=0;i<attr.length;i++){
				film.film_attr[i] = attr[i];
				//System.out.println(attr[i]);
			}
			mFilmArray.add(film);
			
		}		

		String tmp="";
		for(int i=0;i<mFilmArray.size();i++){
			for(int j=0;j<19;j++){
				tmp+=mFilmArray.get(i).film_attr[j]+" ";
			}
			//System.out.println("第"+(i+1)+"个电影的向量为 "+tmp);
			tmp="";
		}
		System.out.println("读入"+mFilmArray.size()+"条电影数据");
		return mFilmArray;
	}
	
	/*
	 * 根据绝对路径读入文件，得到用户得评分矩阵
	 * 返回值为用户评分的list
	 * 
	 * */
	
	public static ArrayList<UserScore> loadAllUserScore(){
		String path = "/Users/jiyuan/Documents/xiuya/allbut/u1.base";
		//String path = "/Users/jiyuan/Documents/xiuya/test.txt";
		String[] s = MyUtils.writeToDat(path);
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
					//System.out.println(Integer.parseInt(temp));
					us.setUserId(Integer.parseInt(temp));
					//System.out.println("第"+index+"条数据的用户id "+us.getUserId());
					temp=String.valueOf("");
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==1){
					us.setFilmId(Integer.parseInt(temp));
					temp=String.valueOf("");
					//System.out.println("第"+index+"条数据的电影号  "+us.getFilmId());
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==2){
					us.setScore(Integer.parseInt(temp));
					temp="";
					//System.out.println("第"+index+"条数据的电影评分  "+us.getScore());
					num++;
					continue;
				}
				
			}
			mus.add(us);
			//System.out.println("第"+index+"条用户数据读取完成");	
		}
		System.out.println("list中有"+mus.size()+"条数据");
		return mus;
	}
	
	/*
	 * 在全部用户评分矩阵中，查找某一用户的评分矩阵
	 * 
	 * */
	public static ArrayList<UserScore> loadUserScoreById(ArrayList<UserScore> all ,int id){
		
		ArrayList<UserScore> result = new ArrayList<UserScore>();
		
		for(int i=0;i<all.size();i++){
			if(all.get(i).getUserId()==id){
				result.add(all.get(i));
			}
		}
		
		return result;
	}
	
	
	/*
	 * 将一个user[19]归一化
	 * 
	 * */
	public static double[] Normalized(double a[]){
		double max = 0;
		for(int i=0;i<a.length;i++){
			if(a[i] > max){
				max = a[i];
			}
		}
		for(int i=0;i<a.length;i++){
			if(max!=0){
				a[i] /= max;
			}
		}
		return a;
	}

}
