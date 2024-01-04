package tested;

import util.TestProgram;

public class probks {
	public  static double[] min = { -50000.0 };
	public static  double[] max = { 50000.0 };
	public static  double failureRate = 0.000387;
	public  static int Dimension = 1;

	public boolean isCorrect(double x) {
		return TestProgram.test_probks(x);
	}
}
