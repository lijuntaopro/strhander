package lijuntao.strhander.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author lijuntao
 * @date 2016-6-1 下午2:03:30
 * 用于处理字符数组的方法
 */
public class CharsUtils {
	private final static char[] FINAL_CS = new char[0];
	private static final int[] pIndexs = new int[0];
	/**
	 * 在源数组的某一段数组中，截取这段数组中，由两个匹配数组中的所有的长度，返回这些字符串
	 *@Author Administrator
	 *@Date 2016-6-1 下午12:04:08
	 *@param cs 源数组
	 *@param begin 源数组开始偏移量
	 *@param end 源数组结束偏移量
	 *@param beginChars 满足开始截取的字符数组
	 *@param endChars  满足最后截取的字符数组
	 *@Retuen String[]  返回满足条件的数组
	 */
	public static final String[] getProperty(char[] cs,int begin,int end,char[] beginChars,char[] endChars){
		if(cs==null||beginChars==null||endChars==null){
			System.out.println("某个数组为空");
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
	 * 截取源数组中，在偏移后，取两个字符之间的长度,赋予目标数组
	 *@Author Administrator
	 *@Date 2016-6-1 下午1:58:13
	 *@param cs
	 *@param offset
	 *@param begin
	 *@param end
	 *@param target
	 *@return
	 */
	public static final int subCharsToChars(final char[] cs,int offset,char begin,char end,final char[] target){
		if(cs==null||cs.length==0||offset>=cs.length)
			System.out.println("源数组为空,或者数组长度大于偏移量");
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
	 * 截取源数组中，在偏移后，取两个字符之间的长度
	 *@Author Administrator
	 *@Date 2016-6-1 下午1:56:29
	 *@param cs
	 *@param offset
	 *@param begin
	 *@param end
	 *@return
	 */
	public static final char[] subCharsToChars(final char[] cs,int offset,char begin,char end){
		if(cs==null||cs.length==0||offset>=cs.length)
			System.out.println("源数组为空,或者数组长度大于偏移量");
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
	 * 截取源数组在偏移量后，等于令一字符的长度,放入目标数组中
	 *@Author Administrator
	 *@Date 2016-6-1 下午1:52:33
	 *@param cs
	 *@param begin
	 *@param end
	 *@param target
	 *@return 返回截取的长度
	 */
	public static final int subChars(final char[] cs,int begin,char end,final char[] target){
		if(cs==null||cs.length<=begin)
			return -1;
		for(int k=begin;k<cs.length;k++){
			if(cs[k]==end){
				int length = k - begin+1;
				if(target.length<length){
					System.out.println("目标数组的长度不够");
					return -1;
				}
				System.arraycopy(cs, begin, target, 0, length);
				return length;
			}
		}			
		return -1;
	}
	/**
	 * 截取源数组在偏移量后，等于令一字符的长度
	 *@Author Administrator
	 *@Date 2016-6-1 下午1:51:23
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
	 *@Date 2016-6-1 下午1:39:12
	 *@param sources 源数组
	 *@param offset  源数组的偏移量
	 *@param cs  匹配数组
	 *@Retuen int 返回匹配数组在源数组的偏移量，否则为-1
	 */
	public static final int findIndex(char[] sources,int offset,char[] cs){
		if(sources==null||sources.length==0||cs==null||cs.length==0){
			System.out.println("参数数组某个为空");
			return -1; 
		}
		if(sources.length<=offset||offset<0){
			System.out.println("偏移量超出范围,offset="+offset);
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
	 *@Date 2016-6-1 下午1:48:08
	 *@param sources
	 *@param i
	 *@param cs
	 *@param j
	 *@Retuen boolean
	 *比较两个数组，匹配数组在源数组中
	 */
	public static final boolean compare(char[] sources, int i, char[] cs, int j) {
		if(cs==null||cs.length==0||cs.length<j-1||sources.length-i<cs.length-j){
			System.out.println("compare方法参数出错");
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
		//代表前一个的偏移量
		int k1 = -1;
		//代表发现存放的数组下标
		int j = 0;
		int k = 0;
		while(k>=0){
			//查找前缀（字符数组）的下标，记录在下标数值
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
			//实体下标开始为-1时，代表往后的都是-1,正常序列：从小到大，或者再添加一个-1结尾，否则是错的
			if(pIndex==-1)
				break;
			if(pIndex_next<=pIndex)
				break;
			//且结束字符小于下一个开始字符
			if(endIndex<=pIndex_next)
				//查找后缀（字符数组）的下标，其开始查找的偏移量为：前缀下标+前缀长度
				endIndex = CharsUtils.findIndex(cs,pIndex+prefix_parent_length,parentEnd);
			else
				continue;
			System.out.println("endIndex="+endIndex);
			if(endIndex>pIndex){
				//截取字符串，开始为开始数组下标+数组长度，长度为结束数组下标-（数组下标+数组长度）
				modelName = new String(cs,pIndex+prefix_parent_length,endIndex-pIndex-prefix_parent_length);
			}
			//属性开始下标
			int propertybeginIndex = endIndex + suffix_parent_length;
			//最后一个实体，从开始下标，到总字符出数组的长度查找
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
