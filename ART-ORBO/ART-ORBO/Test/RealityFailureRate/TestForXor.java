package RealityFailureRate;

import java.util.Arrays;

import org.junit.Test;

public class TestForXor {
	@Test
	public void test(){
		char[] test={'1','2','3','4','5'};
		loopMove(test, 8);
		System.out.println(Arrays.toString(test));
	}
	void invert(char ch[],int m,int n)
	{
		int i;
		char temp;
		for(i=m;i<(m+n)/2;i++)
		{
			temp=ch[i];
			ch[i]=ch[m+n-1-i];
			ch[m+n-1-i]=temp;
		}
	}
	void loopMove(char c[],int steps)
	{
		if(steps>c.length){
			steps=steps%c.length;
		}
		int len;
		len=(c.length)-steps;
		invert(c,0,len);//对字符串中前len位进行逆置
		invert(c,len,(c.length));//对字符串后steps位进行逆置
		invert(c,0,(c.length));//再对整个字符串进行逆置
	}
}
