import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class start {
	public static void main(String[] args) {
		ArrayList<Film> mfa;
		ArrayList<UserScore> mus;
		mfa = getFilmMatrix();
		mus = loadUserScore();
		//LoadUserVector(mfa, mus);
		
		for(int i=0;i<mfa.size();i++){
			Calculate(LoadUserVector(mfa, mus),mfa,i);
		}
		/*
		String s_print="";
		for(int i=0;i<mus.size();i++){
			for(int j=0;j<19;j++){
				s_print+=" "+score[i][j];
			}
			System.out.println("��"+mus.get(i).getFilmId()+"����Ӱ��������"+s_print);
			s_print="";
		}
		*/		
		
		
		//System.out.println(mfa.get(1681).film_id);
		/*
		//�õ��û�����
		double user[]={0,0.834,0.952,0,0,0.952,0.952,0,0.899,0,0,0,0.714,0.714,0.866,0.952,1,0.952,0};
		//System.out.println(user[0]);
		for(int i=0;i<mfa.size();i++){
			Calculate(user,mfa,i);
		}
		*/
	}
	
	public static double [] LoadUserVector(ArrayList<Film> mfa, ArrayList<UserScore> mus){
		//һ���û��ĵ�Ӱ��Ӱ���־���score[][]
		int [][] score=new int[mus.size()][19];
		for(int i=0;i<mus.size();i++){
			for(int j=0;j<19;j++){
				score[i][j]=mfa.get(mus.get(i).getFilmId()).film_attr[j];
			}
		}
		int tmp=0;
		int OneNum=0;
		double user[]=new double[19];
		for(int j=0;j<19;j++){	//	����
			for(int i=0;i<mus.size();i++){
				if(score[i][j]==0){}
				if(score[i][j]==1){
					tmp+=mus.get(i).getScore();
					OneNum++;
				}
			}
			if(OneNum==0)
				user[j]=0;
			if(OneNum!=0)
				user[j]=(double)tmp/((double)OneNum*5.0);
			//DecimalFormat df = new DecimalFormat("0.0000");
			//String db = df.format(user[j]);
			//System.out.println("tmp�� "+tmp);
			//System.out.println("OneNum�� "+OneNum);
			//System.out.println("user["+j+"]Ϊ�� "+user[j]);
		}
		return user;
	}
	
	public static void Calculate(double user[],ArrayList<Film> fa,int p){
		//�����û�ƫ��ֵ
		//����
		double Molecular  = 0.0;
		//��ĸ
		double Denominator= 0.0;
		double result = 0.0;
		for(int i=0;i<19;i++){
			Molecular+=fa.get(p).film_attr[i]*user[i];
			Denominator+=fa.get(p).film_attr[i]*fa.get(p).film_attr[i]+user[i]*user[i];
		}
		result = Molecular/Math.sqrt(Denominator);
		System.out.println("�û����"+p+"����Ӱ�����ƶ�Ϊ result="+result);
		
	}
	
	
	public static ArrayList<Film> getFilmMatrix(){
		String path = "E://Python/allbut/u.item";
		ReadFile rd = new ReadFile();
		String[] s = rd.writeToDat(path);
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
			//System.out.println("��"+(i+1)+"����Ӱ������Ϊ"+tmp);
			tmp="";
		}
		return mFilmArray;
	}
	
	public static ArrayList<UserScore> loadUserScore(){
		//String path = "E://Python/allbut/u1.base";
		String path = "E://Python/test.txt";
		ReadFile rd = new ReadFile();
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
					System.out.println("��"+index+"�����ݵ��û�id "+us.getUserId());
					temp=String.valueOf("");
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==1){
					us.setFilmId(Integer.parseInt(temp));
					temp=String.valueOf("");
					System.out.println("��"+index+"�����ݵĵ�Ӱ��  "+us.getFilmId());
					num++;
					continue;
				}
				if(String.valueOf(s[index].charAt(i)).equalsIgnoreCase("	")&&num==2){
					us.setScore(Integer.parseInt(temp));
					temp="";
					System.out.println("��"+index+"�����ݵĵ�Ӱ����  "+us.getScore());
					num++;
					continue;
				}
				
			}
			mus.add(us);
			System.out.println("��"+index+"���û����ݶ�ȡ���");	
		}
		System.out.println("list����"+mus.size()+"������");
		return mus;
	}
	
	
}
