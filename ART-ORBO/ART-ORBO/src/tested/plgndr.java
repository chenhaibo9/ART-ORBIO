package tested;

import util.TestProgram;

public class plgndr {
	public static double[] min = { 10.0, 0.0, 0.0 };
	public static  double[] max = { 500.0, 11.0, 1.0 };
	public  static double failureRate = 0.000368;
	public  static int Dimension = 3;

	public boolean isCorrect(double x, double y, double m) {
		return TestProgram.test_plgndr(x, y, m);
	}
}
