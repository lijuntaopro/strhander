package lijuntao.strhander.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	public static void deleteAllDir(File file,String name){
		if(file.exists()){
			if(file.isDirectory()){
				String name2 = file.getName();
				if(name2.equals(name)){
					System.out.println("deleting file:"+file.getAbsolutePath());
					deleteDir(file);
					System.out.print("deleted file:"+file.getAbsolutePath());
					
				}else{
					File[] files = file.listFiles();
					for(File f:files){
						deleteAllDir(f,name);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
//		deleteAllDir(new File("G:/workspace3/cargo-provider"), ".svn");
		List<String> list = findFiles("G:/workspace3/cargo-web", "sessionFactory");
		System.out.println(list);
	}
	
	public static void deleteDir(File file){
		if(!file.exists()){
			System.out.println("找不到文件："+file.getAbsolutePath());
			return;
		}
		if(file.isDirectory()){
			System.out.println("文件夹删除中。。。。："+file.getAbsolutePath());
			File[] files = file.listFiles();
			for(File f:files)
				deleteDir(f);
			file.delete();
			System.out.println("文件夹删除完。。。。。："+file.getAbsolutePath());
		}else{
			file.delete();
			System.out.println("删除文件："+file.getAbsolutePath());
		}
	}
	
	public final static List<String> findFiles(String fileName,String contents){
		File file = new File(fileName);
		if(!file.exists()){
			System.out.println("没有该文件");
			return null;
		}
		List<String> list = new ArrayList<String>();
		if(file.isDirectory()){
			copyList(findFilesByDir(fileName,contents),list);
		}else{
			if(findContent(file,contents))
				list.add(file.getAbsolutePath());
		}
		return list;
	}
	
	public final static List<String> findFilesByDir(String dirName,String contents){
		List<String> list = new ArrayList<String>();
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		for(File f:files){
			if(f.isDirectory()){
				copyList(findFilesByDir(f.getAbsolutePath(), contents),list);
			}
			else
				if(findContent(f,contents))
					list.add(f.getAbsolutePath());
		}
		return list;
	}
	
	private static void copyList(List<String> source, List<String> target) {
		if(source!=null&&source.size()!=0)
			for(String s:source)
				target.add(s);
	}
	public final static boolean findContent(String fileName,String contents){
		System.out.println("正在查找文件："+fileName);
		FileReader reader = null;
		try {
			reader = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i=0;
		char[] cs = new char[1024];
		CharsExt ext = new CharsExt();
		try {
			while((i=reader.read(cs))!=-1){
				ext.add(cs, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		char[] cs2 = ext.toArrayChar();
		boolean b = CharsUtils.contain(cs2, contents.toCharArray());
//		System.out.println("是否存在文件："+b);
		return b;
	}
	public final static boolean findContent(File file,String contents){
		return findContent(file.getAbsolutePath(),contents);
	}
}
