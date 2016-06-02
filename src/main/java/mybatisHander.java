

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lijuntao.strhander.utils.CharsUtils;

import org.junit.Test;

public class mybatisHander {
	@Test
	public void hander(){
		FileReader reader = null;
		try {
			reader = new FileReader(new File("C:/Users/Administrator/Desktop/Noname2.html"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char[] cs = new char[1024*1024];
		try {
			int i = reader.read(cs);
			System.out.println("i="+i);
			if(i!=-1){
				char[] cs1 = "association property=\"".toCharArray();
				char[] cs2 = "column=\"".toCharArray();
				char[] cs3 = {'"'};
				System.out.println(Arrays.toString(CharsUtils.getProperty(cs,0,cs.length,cs2,cs3)));;
				int prefix_parent = cs1.length;
				//int prefix_child = cs2.length;
				int suffix = cs3.length;
				Map<String,String[]> map = new HashMap<String,String[]>();
				int[] cs1index = new int[1000];
				int k = 0;
				int k1 = -1;
				int j = 0;
				while(k>=0){
					k1++;
					//����ǰ׺���ַ����飩���±꣬��¼���±���ֵ
					k = CharsUtils.findIndex(cs,k1,cs1);
					cs1index[j++] = k;
					k1 = k;
					System.out.print("k="+k+";");
				}
				System.out.println();
				for(int m=0;m<cs1index.length;m++){
					//ʵ���±꿪ʼΪ-1ʱ����������Ķ���-1
					if(cs1index[m]==-1)
						break;
					//���Һ�׺���ַ����飩���±꣬�俪ʼ���ҵ�ƫ����Ϊ��ǰ׺�±�+ǰ׺����
					int n = CharsUtils.findIndex(cs,cs1index[m]+prefix_parent,cs3);
					System.out.println("n="+n);
					String modelName = "";
					if(n>cs1index[m]){
						//��ȡ�ַ�������ʼΪ��ʼ�����±�+���鳤�ȣ�����Ϊ���������±�-�������±�+���鳤�ȣ�
						modelName = new String(cs,cs1index[m]+prefix_parent,n-cs1index[m]-prefix_parent);
						System.out.println("modelName:"+modelName);
					}
					//���Կ�ʼ�±�
					int propertybeginIndex = n + suffix;
					//���һ��ʵ�壬�ӿ�ʼ�±꣬�����ַ�������ĳ��Ȳ���
					String[] strings = null;
					if(cs1index[m+1]!=-1)
						strings = CharsUtils.getProperty(cs,propertybeginIndex,cs1index[m+1],cs2,cs3);
					else
						strings = CharsUtils.getProperty(cs,propertybeginIndex,cs.length,cs2,cs3);
					System.out.println("modelName="+modelName+";strings="+Arrays.toString(strings));
					map.put(modelName, strings);
				}
				System.out.println(map);
				printMap(map);
				printMapSqlSimple(map);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printMap(Map<String,String[]> map){
		StringBuffer buf = new StringBuffer();
		String prefix = "";
		for(Entry<String, String[]> entry:map.entrySet()){
			String[] strs = entry.getValue();
			if(strs!=null&&strs.length!=0){
				int i = strs[0].indexOf("_");
				if(i!=-1)
					prefix = strs[0].substring(0,i);
			}else
				continue;
//			buf.append("ʵ��:"+entry.getKey()+";���Ը���:"+strs.length+"\n");
			buf.append("<sql id=\""+prefix+"\">\n");
			for(int j=0;j<strs.length;j++){
				int k = strs[j].indexOf('_');
				String substring = "";
				String str = strs[j];
				if(str.length()>30){
					str = str.substring(0, 30);
				}
				if(k>-1&&k!=strs[j].length()-1)
					substring = strs[j].substring(k+1);
				if(j==0)
					buf.append(substring+" "+str+",\n");
				else if(j==strs.length-1)
					buf.append(substring+" "+str+"\n");
				else if(j%5==0)
					buf.append(substring+" "+str+",\n");
				else
					buf.append(substring+" "+str+",");
			}
			buf.append("</sql>\n");
		}
		System.out.println(buf.toString());
	}
	public void printMapSqlSimple(Map<String,String[]> map){
		StringBuffer buf = new StringBuffer();
		String prefix = "";
		for(Entry<String, String[]> entry:map.entrySet()){
			String[] strs = entry.getValue();
			if(strs!=null&&strs.length!=0){
				int i = strs[0].indexOf("_");
				if(i!=-1)
					prefix = strs[0].substring(0,i);
			}else
				continue;
//			buf.append("ʵ��:"+entry.getKey()+";���Ը���:"+strs.length+"\n");
			buf.append("<sql id=\""+prefix+"_simple\">\n");
			
			for(int j=0;j<strs.length;j++){
				String str = strs[j];
				if(str.length()>30)
					str = str.substring(0,30);
				if(j==0)
					buf.append(str+",");
				else if(j==strs.length-1)
					buf.append(str+"\n");
				else if(j%10==0)
					buf.append(str+",\n");
				else
					buf.append(str+",");
			}
			buf.append("</sql>\n");
		}
		System.out.println(buf.toString());
	}
	

}
