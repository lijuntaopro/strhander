package lijuntao.strhander.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	
	public static final void printSqlStrSimple(Map<String,String[]> map,String subPrefix,String subSuffix,String addPrefix,String addSuffix,int maxLenthg){
		StringBuffer buffer = new StringBuffer();
		Set<Entry<String,String[]>> set = map.entrySet();
		for(Entry<String,String[]> entry:set){
			String[] strs = entry.getValue();
			buffer.append("<sql id=\""+entry.getKey()+"\">\n");
			for(String str:strs){
					str = strPrefixAddOrRemove(strPrefixAddOrRemove(str,subPrefix,false),addPrefix,true);
					str = strSuffixAddOrRemove(strSuffixAddOrRemove(str,subSuffix,false),addSuffix,true);
					str = substringBylength(str,maxLenthg);
					buffer.append(str+",");
			}
			buffer.append("</sql>\n");
		}
		System.out.println(buffer.toString());
	}
	
	public static String strPrefixAddOrRemove(String str,String addprefix,boolean b){
		if(str==null||str.length()==0||addprefix==null||addprefix.length()==0)
			return str;
		if(b){
			return addprefix + str;
		}else{
			int i = str.indexOf(addprefix);
			if(i!=-1)
				str = str.substring(i);
			return str;
		}
	}
	
	public static String strSuffixAddOrRemove(String str,String addSuffix,boolean b){
		if(str==null||str.length()==0||addSuffix==null||addSuffix.length()==0)
			return str;
		if(b){
			return str + addSuffix;
		}else{
			int i = str.indexOf(addSuffix);
			if(i!=-1)
				str = str.substring(0,i);
			return str;
		}
	}
	
	public String getPrefixOrSuffix(String str,String prefixOrSuffix,boolean b){
		if(str==null||str.length()==0||prefixOrSuffix==null||prefixOrSuffix.length()==0)
			return str;
		int i = str.indexOf(prefixOrSuffix);
		if(i!=-1){
			if(b)
				str = str.substring(0,i);
			else
				str = str.substring(i);
		}
		return str;
	}
	
	public static String substringBylength(String str ,int length){
		if(str==null||length<1||str.length()<length)
			return str;
		return str.substring(0, length);
	}
}
