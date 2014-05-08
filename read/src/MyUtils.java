
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
	 * 按行读入数据，保存在一维字符串数组中 第一行S[0]
	 */
	public static String[] writeToDat(String path) {
		File file = new File(path);
		List list = new ArrayList();
		String[] S = null;
		try {
			BufferedReader bw = new BufferedReader(new FileReader(file));
			String line = null;
			//因为不知道有几行数据，所以先存入list集合中
			while((line = bw.readLine()) != null){
			   list.add(line);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//确定数组长度
		S = new String[list.size()];
		for(int i=0;i<list.size();i++){
			S[i] = (String) list.get(i);
		}
		return S;
		  
	}
	
	/*
	 * 将String输出至文件，写入方式为添加
	 * */
	public static void addLineToFile(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 求一个int型数字的位数
	 * */
	public static int sizeOfInt(int a){  
		int count = (int) (Math.log10(a) + 1);  
		return count;  
	} 
	
	/*
	 * 格式化输出，讲一个int数组转成字符串
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
	 * 格式化输出，打印一个int数组
	 * 
	 * */
	public static void printArray(int []a){
		String str = "";
		for(int i=0;i<a.length;i++){
			str += a[i]+" ";
		}
		System.out.println("这个数组是 "+str);
	}
	
	/*
	 * 打印一个一维数组，并把double保留3位小数
	 * 
	 * */
	public static void printArray(double []a){
		String str = "";
		DecimalFormat df = new DecimalFormat("0.000");
		
		for(int i=0;i<a.length;i++){
			str += df.format(a[i])+" ";
		}
		System.out.println("这个数组是 "+str);
	}
	
	
	/*
	 * 打印一个二维数组的值
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
	 * 打印一个二维数组，数组中值为double类型
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
	 * 将相似度矩阵的一行，转化位保留3位小数的String
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
	 * 将相似度矩阵的一行，转化int
	 * */
	public static String matrixToLineInt(double []a){
		String str = "";
		
		for(int i=0;i<a.length;i++){
			str += (int) a[i]+"    ";
		}
		return str;
	}
	
	/*
	 * 打印原始用户评分
	 * 
	 * 留在这里备份，好像没有用到
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
		// 打印用户评分矩阵
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