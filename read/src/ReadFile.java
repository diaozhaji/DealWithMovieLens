
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
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
	




	

}