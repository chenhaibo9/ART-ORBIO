package tested;

import util.TestProgram;

public class airy  {
	public static  double[] min = { -5000 };
	public static  double[] max = { 5000 };
	public static double failureRate = 0.000716;
	public static  int Dimension = 1;

	public boolean isCorrect(double x) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return TestProgram.test_airy(x);
	}

	public boolean isCorrect(double[] x) {
		if (x.length == this.Dimension) {
			return TestProgram.test_airy(x[0]);
		} else {
			return true;
		}
	}
}
