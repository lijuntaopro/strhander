package lijuntao.strhander.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijuntao
 * @date 2016-6-1 ����2:03:30
 * ���ڴ����ַ�����ķ���
 */
public class CharsUtils {
	private final static char[] FINAL_CS = new char[0];
	private static final int[] pIndexs = new int[0];
	/**
	 * ��Դ�����ĳһ�������У���ȡ��������У�������ƥ�������е����еĳ��ȣ�������Щ�ַ���
	 *@Author Administrator
	 *@Date 2016-6-1 ����12:04:08
	 *@param cs Դ����
	 *@param begin Դ���鿪ʼƫ����
	 *@param end Դ�������ƫ����
	 *@param beginChars ���㿪ʼ��ȡ���ַ�����
	 *@param endChars  ��������ȡ���ַ�����
	 *@Retuen String[]  ������������������
	 */
	public static final String[] getProperty(char[] cs,int begin,int end,char[] beginChars,char[] endChars){
		if(cs==null||beginChars==null||endChars==null){
			System.out.println("ĳ������Ϊ��");
			return null;
		}
		List<String> list = new ArrayList<String>();
		for(int i=begin;i<cs.length;){
			int beginIndex = findIndex(cs,i,beginChars);
			int endIndex = findIndex(cs,beginIndex+beginChars.length,endChars);
			if(beginIndex>-1 && endIndex>beginIndex && endIndex<=end+1){
				i = endIndex + endChars.length;
				String string = new String(cs,beginIndex+beginChars.length,endIndex-beginIndex-beginChars.length);
				list.add(string);
			}else{
				break;
			}
		}
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * ��ȡԴ�����У���ƫ�ƺ�ȡ�����ַ�֮��ĳ���,����Ŀ������
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:58:13
	 *@param cs
	 *@param offset
	 *@param begin
	 *@param end
	 *@param target
	 *@return
	 */
	public static final int subCharsToChars(final char[] cs,int offset,char begin,char end,final char[] target){
		if(cs==null||cs.length==0||offset>=cs.length)
			System.out.println("Դ����Ϊ��,�������鳤�ȴ���ƫ����");
		else{
			int beginindex = -1;
			boolean foundbegin = false;
			for(int j=offset;j<cs.length;j++){
				if(!foundbegin){
					if(cs[j]==begin){
						beginindex = j;
						foundbegin = true;
					}
				}else{
					if(cs[j]==end){
						int length = j - beginindex-1;
						System.arraycopy(cs, beginindex+1, target, 0, length);
						return length;
					}	
				}
			}
		}
		return -1;
	}
	/**
	 * ��ȡԴ�����У���ƫ�ƺ�ȡ�����ַ�֮��ĳ���
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:56:29
	 *@param cs
	 *@param offset
	 *@param begin
	 *@param end
	 *@return
	 */
	public static final char[] subCharsToChars(final char[] cs,int offset,char begin,char end){
		if(cs==null||cs.length==0||offset>=cs.length)
			System.out.println("Դ����Ϊ��,�������鳤�ȴ���ƫ����");
		else{
			int beginindex = -1;
			boolean foundbegin = false;
			for(int j=offset;j<cs.length;j++){
				if(!foundbegin){
					if(cs[j]==begin){
						beginindex = j;
						foundbegin = true;
					}
				}else{
					if(cs[j]==end){
						int length = j - beginindex-1;
						char[] cs2 = new char[length];
						System.arraycopy(cs, beginindex+1, cs2, 0, length);
						return cs2;
					}	
				}
			}
		}
		return FINAL_CS;
	}
	/**
	 * ��ȡԴ������ƫ�����󣬵�����һ�ַ��ĳ���,����Ŀ��������
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:52:33
	 *@param cs
	 *@param begin
	 *@param end
	 *@param target
	 *@return ���ؽ�ȡ�ĳ���
	 */
	public static final int subChars(final char[] cs,int begin,char end,final char[] target){
		if(cs==null||cs.length<=begin)
			return -1;
		for(int k=begin;k<cs.length;k++){
			if(cs[k]==end){
				int length = k - begin+1;
				if(target.length<length){
					System.out.println("Ŀ������ĳ��Ȳ���");
					return -1;
				}
				System.arraycopy(cs, begin, target, 0, length);
				return length;
			}
		}			
		return -1;
	}
	/**
	 * ��ȡԴ������ƫ�����󣬵�����һ�ַ��ĳ���
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:51:23
	 *@param cs
	 *@param begin
	 *@param end
	 *@return
	 */
	public static final char[] subChars(final char[] cs,int begin,char end){
		if(cs==null||cs.length<=begin)
			return null;
		for(int k=begin;k<cs.length;k++){
			if(cs[k]==end){
				int length = k - begin;
				char[] cs2 = new char[length];
				System.arraycopy(cs, begin, cs2, 0, length);
				return cs2;
			}
		}			
		return FINAL_CS;
	}
	/**
	 * 
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:39:12
	 *@param sources Դ����
	 *@param offset  Դ�����ƫ����
	 *@param cs  ƥ������
	 *@Retuen int ����ƥ��������Դ�����ƫ����������Ϊ-1
	 */
	public static final int findIndex(char[] sources,int offset,char[] cs){
		if(sources==null||sources.length==0||cs==null||cs.length==0){
			System.out.println("��������ĳ��Ϊ��");
			return -1; 
		}
		if(sources.length<=offset||offset<0){
			System.out.println("ƫ����������Χ,offset="+offset);
			return -1;
		}
		
		for(int i=offset;i<sources.length;i++){
			if(sources[i]==cs[0]){
				boolean b = compare(sources,i+1,cs,1);
				if(b)
					return i;
			}
		}
		return -1;
	}
	/**
	 * 
	 *@Author Administrator
	 *@Date 2016-6-1 ����1:48:08
	 *@param sources
	 *@param i
	 *@param cs
	 *@param j
	 *@Retuen boolean
	 *�Ƚ��������飬ƥ��������Դ������
	 */
	public static final boolean compare(char[] sources, int i, char[] cs, int j) {
		if(cs==null||cs.length==0||cs.length<j-1||sources.length-i<cs.length-j){
			System.out.println("compare������������");
			return false;
		}
		for(;j<cs.length;j++,i++){
			if(sources[i]!=cs[j])
				return false;
		}
		return true;
	}
	
	public final static int[] findAllIndexs(char[] source,char[] target){
		int[] pIndexs = new int[2];
		int pIndexsLength = pIndexs.length;
		//����ǰһ����ƫ����
		int k1 = -1;
		//�����ִ�ŵ������±�
		int j = 0;
		int k = 0;
		while(k>=0){
			//����ǰ׺���ַ����飩���±꣬��¼���±���ֵ
			pIndexs[j++] = k1 = k = CharsUtils.findIndex(source,++k1,target);
			if(j>=pIndexsLength){
				int[] pIndexs_2 = new int[pIndexsLength*2];
				System.arraycopy(pIndexs, 0, pIndexs_2, 0, pIndexsLength);
				pIndexsLength*=2;
				pIndexs = pIndexs_2;
			}
		}
		int[] result = new int[j];
		System.arraycopy(pIndexs,0,result,0,j);
		return result;
	}

	public final static Map<String,String[]> hander(final int[] beginIndex,final char[] cs,final char[] parentBegin,final char[] parentEnd,
			final char[] childBegin,final char[] childEnd) {
		int[] indexs = beginIndex;
		int endIndex = 0;
		int prefix_parent_length = parentBegin.length;
		int suffix_parent_length = parentEnd.length;
		Map<String,String[]> map = new HashMap<String,String[]>();
		String modelName = "";
		for(int m=0;m<indexs.length;m++){
			int pIndex = indexs[m];
			int pIndex_next = indexs[m+1];
			//ʵ���±꿪ʼΪ-1ʱ����������Ķ���-1,�������У���С���󣬻��������һ��-1��β�������Ǵ��
			if(pIndex==-1)
				break;
			if(pIndex_next<=pIndex)
				break;
			//�ҽ����ַ�С����һ����ʼ�ַ�
			if(endIndex<=pIndex_next)
				//���Һ�׺���ַ����飩���±꣬�俪ʼ���ҵ�ƫ����Ϊ��ǰ׺�±�+ǰ׺����
				endIndex = CharsUtils.findIndex(cs,pIndex+prefix_parent_length,parentEnd);
			else
				continue;
			System.out.println("endIndex="+endIndex);
			if(endIndex>pIndex){
				//��ȡ�ַ�������ʼΪ��ʼ�����±�+���鳤�ȣ�����Ϊ���������±�-�������±�+���鳤�ȣ�
				modelName = new String(cs,pIndex+prefix_parent_length,endIndex-pIndex-prefix_parent_length);
			}
			//���Կ�ʼ�±�
			int propertybeginIndex = endIndex + suffix_parent_length;
			//���һ��ʵ�壬�ӿ�ʼ�±꣬�����ַ�������ĳ��Ȳ���
			String[] strings = null;
			if(pIndex_next!=-1)
				strings = CharsUtils.getProperty(cs,propertybeginIndex,indexs[m+1],childBegin,childEnd);
			else
				strings = CharsUtils.getProperty(cs,propertybeginIndex,cs.length,childBegin,childEnd);
			System.out.println("modelName="+modelName+";strings="+Arrays.toString(strings));
			map.put(modelName, strings);
		}
		return map;
	}
}
