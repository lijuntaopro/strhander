package lijuntao.strhander.utils;

import java.io.IOException;
import java.io.Reader;

public class CharReadWriteUtils {
	public static final char[] readToChars(Reader reader,int size){
		if(size<1024)
			size = 1024;
		char[] cs = new char[size];
		int length = cs.length;
		int i = 0;
		while(i!=-1){
			try {
				i = reader.read(cs);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return null;
	}
	public static final char[] readToChars(Reader reader){
		return readToChars(reader,1024);
	}
}
