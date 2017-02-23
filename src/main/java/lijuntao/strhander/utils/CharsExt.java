package lijuntao.strhander.utils;

public class CharsExt {
	private int size = 1024;
	private char[] mycs;
	private int index = 0;
	public CharsExt(int size){
		if(size>1024)
			this.size = size;
	}
	
	public CharsExt() {
		
	}

	public void add(char[] cs,int begin,int size){
		if(mycs==null)
			mycs = new char[this.size];
		if(cs==null||cs.length==0||size<1||begin+size>cs.length){
			if(size>0)
				System.out.println("CharsExt.add参数出错");
			else
				System.out.println("CharsExt.add参数size="+size+"操作无效");
			return;
		}
		for(;size>0;size--){
			if(mycs.length-index<size){
				char[] newcs = new char[mycs.length*2];
				System.arraycopy(mycs,0,newcs,0,index);
				mycs = newcs;
			}
			mycs[index++] = cs[begin++];
		}
	}
	
	public char[] toArrayChar(){
		char[] cs = new char[index];
		if(mycs!=null)
			System.arraycopy(mycs,0,cs,0,index);
		return cs;
	}
	
}
