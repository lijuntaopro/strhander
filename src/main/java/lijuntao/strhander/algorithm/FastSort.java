package lijuntao.strhander.algorithm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FastSort {
	public static void fastSort(int a[], int start, int end) {
		if(end - start == 0)
			return;
		int i,j;
		i = start;
		j = end;
		while(i<j){
			while(i<j && a[i] <= a[j])
				j--;
			if(i<j)
				swap(a, i, j);
			while(i<j && a[i] < a[j])
				i++;
			if(i<j);
				swap(a, i, j);
		}
		if(i-start>1)
			fastSort(a, start, i - 1); 
		if(end-j>1)
			fastSort(a, i + 1,end); 
	}
	
	public static void quickSort(int a[], int start, int end) {  
        int i, j;  
        i = start;  
        j = end;  
        if ((a == null) || (a.length == 0))  
            return;  
          
        while (i < j) {//查找基准点下标  
            while (i < j && a[i] <= a[j])  
                // 以数组start下标的数据为key，右侧扫描  
                j--;  
            if (i < j) { // 右侧扫描，找出第一个比key小的，交换位置  
                int temp = a[i];  
                a[i] = a[j];  
                a[j] = temp;  
            }  
            while (i < j && a[i] < a[j])  
                // 左侧扫描（此时a[j]中存储着key值）  
                i++;  
            if (i < j) { // 找出第一个比key大的，交换位置  
                int temp = a[i];  
                a[i] = a[j];  
                a[j] = temp;  
            }  
        }  
        if (i - start > 1) { // 递归调用，把key前面的完成排序  
            quickSort(a, 0, i - 1);  
        }  
        if (end - j > 1) {  
            quickSort(a, j + 1, end); // 递归调用，把key后面的完成排序  
        }  
    }  
	public static void main(String[] args) {
		int size = 1000000;
		int[] is = new int[size];
		Random random = new Random();
		for(int i=0;i<size;i++){
			is[i] = random.nextInt(100);
		}
//		int[] is = new int[]{1,2,3,5,67,8,9,0,-1,4,4};
//		System.out.println(Arrays.toString(is));
//		Map<String, Integer> map = count(is);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(format.format(new Date()));
		fastSort(is,0,is.length-1);
		System.out.println(format.format(new Date()));
//		Map<String, Integer> map2 = count(is);
//		quickSort(is,0,is.length-1);
//		System.out.println(Arrays.toString(is));
//		System.out.println(map);
//		System.out.println(map2);
	}
	
	public static void swap(int[] a,int i,int j){
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static Map<String,Integer> count(int[] a){
		Map<String,Integer> map = new HashMap<String, Integer>();
		for(int i=0;i<a.length;i++){
			Integer in = map.get(a[i]+"");
			int temp = 1;
			if(in!=null)
				temp = in + 1;
			map.put(a[i]+"", temp);
		}
		return map;
	}
}
