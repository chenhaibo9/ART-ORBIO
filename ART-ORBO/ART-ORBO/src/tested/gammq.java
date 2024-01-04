package tested;

import util.TestProgram;

public class gammq {

	public static  double[] min = { 0.0, 0.0 };
	public static  double[] max = { 1700.0, 40.0 };
	public static  double failureRate = 0.000690;
	public  static int Dimension = 2;

	public boolean isCorrect(double x, double y) {
		return TestProgram.test_gammq(x, y);
	}
}
