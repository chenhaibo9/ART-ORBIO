package testPrograms.Fault_10d;

import Fault_6d.PUT_6D;
import Fault_8d.TwoLinesPos;

/**
 * 
 * @author zxz 
 * ��ʮ���������Լ��
 */
public class CalGCD extends PUT_10D {
	
	public double[] min={ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
	public double[] max={1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000};
//	public double failureRate = 0.001623; // sample failure rate by Hilary
	public double failureRate = 0.1;
	public int Dimension =10;
	
	public static int gcd(int... numbers) {
		int gcd = numbers[0];

		for (int i = 1; i < numbers.length; i++)
			gcd = gcd(gcd, numbers[i]);

		return gcd;
	}

	/** Return the gcd of two integers */
	public static int gcd(int n1, int n2) {
		int gcd = 1; // Initial gcd is 1
		int k = 1; // Possible gcd

		while (k <= n1 && k <= n2) {
			if (n1 % k == 0 && n2 % k == 0)
				gcd = k; // Update gcd
			k++;
		}

		return gcd; // Return gcd
	}

	@Override
	public double modified_fn(int x0, int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9) {
		// TODO Auto-generated method stub
		int[] numbers = { x0, x1, x2, x3, x4, x5, x6, x7, x8, x9 };
		CalGCDErr.gcd(numbers);
		return 0;
	}

	@Override
	public boolean isCorrect(int x0, int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9) {
		// TODO Auto-generated method stub
		int[] numbers = { x0, x1, x2, x3, x4, x5, x6, x7, x8, x9 };
		int orig = CalGCD.gcd(numbers);
		int err = CalGCDErr.gcd(numbers);
//		if (orig != err) {
//			return true;
//		}
//		return false;
		return orig == err;
	}

}
