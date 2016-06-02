package lijuntao.strhander.utils;

import java.util.Map;
import java.util.Map.Entry;

public final class PrintMap {
	public static final void printMap(Map<String,String[]> map){
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
//			buf.append("实体:"+entry.getKey()+";属性个数:"+strs.length+"\n");
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
	public static final void printMapSqlSimple(Map<String,String[]> map){
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
//			buf.append("实体:"+entry.getKey()+";属性个数:"+strs.length+"\n");
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
