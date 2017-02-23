package lijuntao.strhander.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Map;

public class StrHander {
	private int bufferSize = 1024;
	private char[] cs;
	private InputStreamReader reader;
	
	public StrHander(InputStream is){
		reader = new InputStreamReader(is);
	}
	public StrHander(String fileName){
		try {
			reader = new InputStreamReader(new FileInputStream(fileName));
		} catch (Exception e) {
			throw new RuntimeException("找不到文件");
		}
	}
	public void setBufferSize(int size){
		this.bufferSize = size;
	}
	public int getBufferSize(){
		return this.bufferSize;
	}
	public Map<String,String[]> hander(char[] parentBegin,char[] parentEnd,char[] childBegin,char[] childEnd){
		char[] as = new char[1024];
		int i = 0;
		CharsExt charsExt = new CharsExt(1024);
		try {
			while(i!=-1){
				i = reader.read(as);
				charsExt.add(as, 0, i);
			}
		} catch (IOException e) {
			throw new RuntimeException("不可读");
		}
		cs = charsExt.toArrayChar();
		int[] indexs = CharsUtils.findAllIndexs(cs, parentBegin);
		System.out.println(Arrays.toString(indexs));
		return CharsUtils.hander(indexs,cs,parentBegin,parentEnd,childBegin,childEnd);
	}
	public Map<String,String[]> hander2(char[] parentBegin,char[] parentEnd,char[] childBegin,char[] childEnd){
		StringBuffer buffer = new StringBuffer();
		char[] as = new char[1024];
		int i = 0;
		try {
			while(i!=-1){
				i = reader.read(as);
				if(i!=1024&&i>-1){
					buffer.append(new String(as,0,i));
					break;
				}
				buffer.append(as);
			}
		} catch (IOException e) {
			throw new RuntimeException("不可读");
		}
		System.out.println(buffer.toString());
		cs = buffer.toString().toCharArray();
		System.out.println("i="+i);
		int[] indexs = CharsUtils.findAllIndexs(cs, parentBegin);
		System.out.println(Arrays.toString(indexs));
		return CharsUtils.hander(indexs,cs,parentBegin,parentEnd,childBegin,childEnd);
	}
	
	
}
