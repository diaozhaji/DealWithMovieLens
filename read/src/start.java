import java.text.DecimalFormat;
import java.util.ArrayList;


public class start {
	
	
	
	public static void main(String[] args) {
		//��Ӱ�������
		ArrayList<Film> mfa;
		//�û�����
		ArrayList<UserScore> mus;			//	�ܱ�	
		ArrayList<UserScore> sus;	//	ĳһ���û�
		//��������
		mfa = getFilmMatrix();
		mus = loadAllUserScore();
		
		//getUserInfo(108,mfa,mus);
		replaceUserScoreById(108,mfa,mus);
		
		/*
		//�����������
		double userFilmSim[] = new double[1682];
		sus = loadUserMatrix(mus, 165);
		double[] a = LoadUserVector(mfa, sus);
		a = Normalized(a);
		for (int j = 0; j < mfa.size(); j++) {
			userFilmSim[j] = Calculate(a, mfa, j);
			// ��������
			// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
		}
		MyUtils.printArray(userFilmSim);
		*/
		
		/*
		//����ĳһ�û� �û������ķ���
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
			System.out.println("��"+mus.get(i).getFilmId()+"����Ӱ��������"+s_print);
			s_print="";
		}*/
			
		
		//�õ��û����� �û�165
		/*
		double user[] = { 0.000, 0.984, 1.016, 1.270, 1.016, 1.143, 0.847,
				0.000, 1.048, 0.000, 0.000, 0.000, 1.270, 0.762, 0.847, 1.016,
				0.889, 1.186, 0.000 };*/
		
		//writeUserSimMatrixToFile(mfa,mus,sus);
		
		/*
		//��ӡȫ���û����ƶȷ���
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
			System.out.println("����" + a + "����Ӱ������" + b + "�������û���ʵ���۵ķ���"
					+ score[a]);
		}
		System.out.println("���û�������" + sus.size() + "����Ӱ");
		System.out.println("�����+��1��֮��ĸ���Ϊ:" + cnt);

		return score;
	}

	/*
	 * ��ӡ��ĳһ�û��������Ϣ
	 * 
	 * �޷���ֵ��ֻ�ǲ鿴�û�����Ϣ
	 * 
	 * */
	private static void getUserInfo(int id, ArrayList<Film> mfa,ArrayList<UserScore> mus) {
		// TODO Auto-generated method stub
		// int id = 241;
		System.out.println("�û�" + id + "���");
		ArrayList<UserScore> sus = loadUserScoreById(mus, id);
		System.out.println("���û������۵�Ӱ��:" + sus.size());
		MyUtils.printOriUserScore(mfa, sus);

		double[] a = LoadUserVector(mfa, sus);

		MyUtils.printArray(a);
		a = Normalized(a);
		System.out.println("��һ��֮��:");
		MyUtils.printArray(a);
	}


	/*
	 * ��ȫ���û����ƶȾ���д���ļ�
	 * ����2�ֿ����ԣ�1�ֱ���3λС����1�ֽ����ƶ���������
	 * ������matrixToLineDouble()��matrixToLineInt()������
	 * 
	 * */
	private static void writeUserSimMatrixToFile(ArrayList<Film> mfa,ArrayList<UserScore> mus){
		// TODO Auto-generated method stub
		//String filePath = "/Users/jiyuan/Documents/xiuya/ȫ���û����ƶȾ���3λС��.txt";
		//String filePath = "/Users/jiyuan/Documents/xiuya/ȫ���û����ƶȾ�����������.txt";
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
				// ��������
				// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
			}
			MyUtils.printArray(userFilmSim);
			String content = MyUtils.matrixToLineDouble(userFilmSim);
			MyUtils.appendMethodB(filePath, content + "\n");
			// MyUtils.appendMethodB("/Users/jiyuan/Documents/xiuya/ȫ���û����ƶȾ�����������.txt",content+"\n");

			System.out.println("��" + i + "���û����ƶ�д��ɹ�");
		}

	}
	
	/*
	 * �õ��û����Ӱ�����ƶ�
	 */
	public static double []getUserFilmSimById(int id,ArrayList<Film> mfa,ArrayList<UserScore> mus){
		
		double userFilmSim[] = new double[1682];
		ArrayList<UserScore> sus = loadUserScoreById(mus, id);
		double[] a = LoadUserVector(mfa, sus);
		a = Normalized(a);
		for (int j = 0; j < mfa.size(); j++) {
			userFilmSim[j] = Calculate(a, mfa, j);
			// ��������
			// userFilmSim[j] = (int)Math.floor(userFilmSim[j]);
		}
		MyUtils.printArray(userFilmSim);
		
		return userFilmSim;
		
	}
	

	/*
	 * ���������û����� �õ�����д���ļ�
	 */
	private static void writoUserVectorToFile(ArrayList<Film> mfa , ArrayList<UserScore> mus) {
		// TODO Auto-generated method stub
		double[][] userVector = new double[943][19];
		for (int id = 1; id < 944; id++) {
			ArrayList<UserScore> sus = loadUserScoreById(mus, id);
			// System.out.println("�û�"+id+"�����־����СΪ"+sus.size());
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
			MyUtils.appendMethodB("/Users/jiyuan/Documents/xiuya/�����û�����.txt",log);

		}
	}
	
	/*
	 * ���ݵ�Ӱ��������û����־�������û�����
	 * 
	 * */
	public static double [] LoadUserVector(ArrayList<Film> mfa, ArrayList<UserScore> singleUser){
		//һ���û��ĵ�Ӱ��Ӱ���־���score[][]
		int [][] score=new int[singleUser.size()][19];
		for(int i=0;i<singleUser.size();i++){
			for(int j=0;j<19;j++){
				score[i][j]=mfa.get(singleUser.get(i).getFilmId()-1).film_attr[j];
			}
		}/*
		//��ӡ�û����־���
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
		for(int j=0;j<19;j++){	//	����
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
		System.out.println("���û�����������Ŀ��ƽ����Ϊ��"+sum);
		for(int i=0;i<19;i++){
			user[i]=user[i]/sum;
			//System.out.println("user["+i+"]Ϊ�� "+user[i]);
		}
		return user;
	}
	
	/*
	 * ����һ���û���������������ĳһ����Ӱ�����ƶ�
	 * 
	 * */
	public static double Calculate(double user[], ArrayList<Film> fa, int p) {
		// �����û�ƫ��ֵ
		// ����
		double Molecular = 0.0;
		// ��ĸ
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
		//System.out.println("�û����" + p + "����Ӱ�����ƶ�Ϊ r=" + r);
		return result;
		

	}
	
	/*
	 * ���ݾ���·�������ļ����õ���Ӱ����Ŀ����
	 * 
	 * */
	
	public static ArrayList<Film> getFilmMatrix(){
		String path = "/Users/jiyuan/Documents/xiuya/allbut/u.item";
		String[] s = MyUtils.writeToDat(path);
		ArrayList<Film> mFilmArray = new ArrayList<Film>();
		for(int index=0;index<s.length;index++){
			//System.out.println("��Ӱ��"+index);
			int [] attr=new int[19];
			Film film = new Film(index,attr);
			film.setId(index+1);//list�ĵ�һ���Ǵ�0��ʼ������Ҫ+1
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
			//System.out.println("��"+(i+1)+"����Ӱ������Ϊ "+tmp);
			tmp="";
		}
		System.out.println("����"+mFilmArray.size()+"����Ӱ����");
		return mFilmArray;
	}
	
	/*
	 * ���ݾ���·�������ļ����õ��û������־���
	 * ����ֵΪ�û����ֵ�list
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
					//System.out.println("��"+index+"�����ݵ��û�id "+us.getUserId());
					temp=String.valueOf("");
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==1){
					us.setFilmId(Integer.parseInt(temp));
					temp=String.valueOf("");
					//System.out.println("��"+index+"�����ݵĵ�Ӱ��  "+us.getFilmId());
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==2){
					us.setScore(Integer.parseInt(temp));
					temp="";
					//System.out.println("��"+index+"�����ݵĵ�Ӱ����  "+us.getScore());
					num++;
					continue;
				}
				
			}
			mus.add(us);
			//System.out.println("��"+index+"���û����ݶ�ȡ���");	
		}
		System.out.println("list����"+mus.size()+"������");
		return mus;
	}
	
	/*
	 * ��ȫ���û����־����У�����ĳһ�û������־���
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
	 * ��һ��user[19]��һ��
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
