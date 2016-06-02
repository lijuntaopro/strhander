package lijuntao.strhander.algorithm;

import java.util.Arrays;
import java.util.Random;

public class FastSort {
	public static void fastSort(int[] is,int begin,int end){
		if(is==null||is.length<2||end-begin<1||is.length-1<end||begin<0)
			return;
		if(end-begin==1){
			if(is[begin] >is[end]){
				int temp = is[begin];
				is[begin] = is[end];
				is[end] = temp;
			}
			return;
		}
		int vIndex = (end-begin)/2;
		int k = begin;
		int j = end;
		//第一个元素互换
		int v = is[k];
		is[k] = is[vIndex];
		is[vIndex] = v;
		v = is[k];
		k++;
		
		while(k<j){
			if(is[k]>v){
				if(is[j]<=v){
					int temp = is[j];
					is[j] = is[k];
					is[k] = temp;
				}
				j--;
			}else
				k++;
		}
		int temp = is[k];
		if(temp>is[begin])
			temp = is[k];
		is[k] = v;
		is[begin] = temp;
//
		if(k==end){
			fastSort(is,begin,k-1);
		}else{
			fastSort(is,begin,k);
			fastSort(is,k+1,end);
		}
		
	}
	
	public static void main(String[] args) {
//		int[] is = new int[10000];
//		Random random = new Random();
//		for(int i=0;i<10000;i++){
//			is[i] = random.nextInt(100);
//		}
		int[] is = new int[]{1,2,3,4,5,67,8,9,0,-1,4,1,2,3,4};
		System.out.println(Arrays.toString(is));
		fastSort(is,0,is.length-1);
		System.out.println(Arrays.toString(is));
	}
}
