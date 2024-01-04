package tested;

import util.TestProgram;

public class sncndn {
	public static  double[] min = { -5000.0, -5000.0 };
	public  static double[] max = { 5000.0, 5000.0 };
	public static  double failureRate = 0.001623;
	public  static int Dimension = 2;

	public boolean isCorrect(double x, double y) {
		return TestProgram.test_sncndn(x, y);
	}
}
