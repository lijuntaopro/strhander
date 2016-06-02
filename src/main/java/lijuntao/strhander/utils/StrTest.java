package lijuntao.strhander.utils;

import java.util.Map;

import org.junit.Test;

public class StrTest {
	@Test
	public void test(){
		char[] cs1 = "association property=\"".toCharArray();
		char[] cs2 = "column=\"".toCharArray();
		char[] cs3 = {'"'};
		StrHander hander = new StrHander("C:/Users/Administrator/Desktop/Noname2.html");
		Map<String, String[]> map = hander.hander(cs1,cs3,cs2,cs3);
//		PrintMap.printMap(map);
		PrintMap.printSqlStrSimple(map, "_", "" , "hello", "_end", 50);
	}
	
	@Test
	public void test2(){
		
	}
}
