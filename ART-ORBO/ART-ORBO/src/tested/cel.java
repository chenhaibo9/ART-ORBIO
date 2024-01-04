package tested;

import util.TestProgram;

public class cel {
	public  static double[] min = { 0.001, 0.001, 0.001, 0.001 };
	public static  double[] max = { 1.0, 300.0, 10000.0, 1000.0 };
	public static  double failureRate = 0.000332;
	public  static int Dimension = 4;
	// 1/0.000332=3012

	public boolean isCorrect(double x, double y, double m, double n) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return TestProgram.test_cel(x, y, m, n);
	}
}
