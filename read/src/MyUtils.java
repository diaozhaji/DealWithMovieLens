
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class MyUtils {
	/*
	 * ���ж������ݣ�������һά�ַ��������� ��һ��S[0]
	 */
	public static String[] writeToDat(String path) {
		File file = new File(path);
		List list = new ArrayList();
		String[] S = null;
		try {
			BufferedReader bw = new BufferedReader(new FileReader(file));
			String line = null;
			//��Ϊ��֪���м������ݣ������ȴ���list������
			while((line = bw.readLine()) != null){
			   list.add(line);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ȷ�����鳤��
		S = new String[list.size()];
		for(int i=0;i<list.size();i++){
			S[i] = (String) list.get(i);
		}
		return S;
		  
	}
	
	/*
	 * ��String������ļ���д�뷽ʽΪ���
	 * */
	public static void addLineToFile(String fileName, String content) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ��һ��int�����ֵ�λ��
	 * */
	public static int sizeOfInt(int a){  
		int count = (int) (Math.log10(a) + 1);  
		return count;  
	} 
	
	/*
	 * ��ʽ���������һ��int����ת���ַ���
	 * */
	public static String arrayToLine(int[] a) {
		String str = "";

		for (int i = 0; i < a.length; i++) {
			
			String block = "";
			for (int j = 0; j < 6 - sizeOfInt(a[i]); j++)
				block += " ";
			
			str += a[i] + block;
		}
		return str;
	}
	
	/*
	 * ��ʽ���������ӡһ��int����
	 * 
	 * */
	public static void printArray(int []a){
		String str = "";
		for(int i=0;i<a.length;i++){
			str += a[i]+" ";
		}
		System.out.println("��������� "+str);
	}
	
	/*
	 * ��ӡһ��һά���飬����double����3λС��
	 * 
	 * */
	public static void printArray(double []a){
		String str = "";
		DecimalFormat df = new DecimalFormat("0.000");
		
		for(int i=0;i<a.length;i++){
			str += df.format(a[i])+" ";
		}
		System.out.println("��������� "+str);
	}
	
	
	/*
	 * ��ӡһ����ά�����ֵ
	 * 
	 * */
	public static void printMatrix(int[][] a){
		for(int i=0;i<a.length;i++){
			String str = "";
			for(int j=0;j<a[0].length;j++){
				str += a[i][j]+" ";
			}
			System.out.println(str);
		}
	}
	
	/*
	 * ��ӡһ����ά���飬������ֵΪdouble����
	 * 
	 * */
	public static void printMatrix(double[][] a){
		DecimalFormat df = new DecimalFormat("0.000");
		for(int i=0;i<a.length;i++){
			String str = "";
			for(int j=0;j<a[0].length;j++){
				str += df.format(a[i][j])+" ";
			}
			System.out.println(str);
		}
	}
	
	/*
	 * �����ƶȾ����һ�У�ת��λ����3λС����String
	 * */
	public static String matrixToLineDouble(double []a){
		String str = "";
		DecimalFormat df = new DecimalFormat("0.000");
		
		for(int i=0;i<a.length;i++){
			str += df.format(a[i])+" ";
		}
		return str;
	}
	
	/*
	 * �����ƶȾ����һ�У�ת��int
	 * */
	public static String matrixToLineInt(double []a){
		String str = "";
		
		for(int i=0;i<a.length;i++){
			str += (int) a[i]+"    ";
		}
		return str;
	}
	
	/*
	 * ��ӡԭʼ�û�����
	 * 
	 * �������ﱸ�ݣ�����û���õ�
	 * */
	public static void printOriUserScore(ArrayList<Film> mfa, ArrayList<UserScore> sus) {
		// TODO Auto-generated method stub
		System.out.println("printOriUserScore:");
		int[][] score = new int[sus.size()][19];
		for (int i = 0; i < sus.size(); i++) {
			for (int j = 0; j < 19; j++) {
				score[i][j] = mfa.get(sus.get(i).getFilmId() - 1).film_attr[j];
			}
		}
		// ��ӡ�û����־���
		for (int i = 0; i < sus.size(); i++) {
			String str = "";
			for (int j = 0; j < 19; j++) {
				if (score[i][j] == 1) {
					score[i][j] = sus.get(i).getScore();
				}

				str += score[i][j] + " ";
			}
			System.out.println(sus.get(i).getFilmId() + " " + str);
		}
		// MyUtils.printMatrix(score);
	}

	

}