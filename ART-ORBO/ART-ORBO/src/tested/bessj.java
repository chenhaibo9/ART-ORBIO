package tested;

import util.TestProgram;

public class bessj  {

	public static void main(String[] args) {
	}
	public  static double[] min = { 2, -1000 };
	public static double[] max = { 300.0, 15000 };
	public static double failureRate = 0.001298;

	public  static int Dimension = 2;

	public boolean isCorrect(double x, double y) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return TestProgram.test_bessj(x, y);
	}

	public boolean isCorrect(double[] x) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		if (x.length == this.Dimension) {
			return TestProgram.test_bessj(x[0], x[1]);
		} else {
			return true;
		}
	}
}
