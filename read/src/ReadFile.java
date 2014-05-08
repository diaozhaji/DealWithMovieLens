
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadFile {
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
	




	

}