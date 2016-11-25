package _099_helper;

public class Functions {

	public static int fn1(){		
		int j=999; // this does not work since updated j is no longer available after fn1() completes
		j=j+1;
		return j;
	}

	static int i=99; 
	public static int fn2(){		
		i=i+1;
		return i;
	}

}
