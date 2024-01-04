package tested;

import util.TestProgram;

/*
 * Input Domain:(-300000,300000)
 * failure rate:0.001298 avf=1.0/0.001298=770.4160246533127889060092449923
 *errors: 5 errors(2 AOR, 1 ROR, 1SVR ,1CR)
 * */
public class bessj0 {
	public static double[] min = { -300000 };
	public static  double[] max = { 300000 };
	public static  double failureRate = 0.001298;
	public  static int Dimension = 1;

	double correct(double x) {
		double ax, z;
		double xx, y, ans, ans1, ans2;

		if ((ax = Math.abs(x)) < 8.0) {
			y = x * x;
			ans1 = 57568490574.0 + y * (-13362590354.0
					+ y * (651619640.7 + y * (-11214424.18 + y * (77392.33017 + y * (-184.9052456)))));
			ans2 = 57568490411.0
					+ y * (1029532985.0 + y * (9494680.718 + y * (59272.64853 + y * (267.8532712 + y * 1.0))));
			ans = ans1 / ans2;
		} else {
			z = 8.0 / ax;
			y = z * z;
			xx = ax - 0.785398164;
			ans1 = 1.0 + y * (-0.1098628627e-2 + y * (0.2734510407e-4 + y * (-0.2073370639e-5 + y * 0.2093887211e-6)));
			ans2 = -0.1562499995e-1
					+ y * (0.1430488765e-3 + y * (-0.6911147651e-5 + y * (0.7621095161e-6 - y * 0.934935152e-7)));
			ans = Math.sqrt(0.636619772 / ax) * (Math.cos(xx) * ans1 - z * Math.sin(xx) * ans2);
		}
		return ans;
	}

	public boolean isCorrect(double x) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return TestProgram.test_bessj0(x);
		// System.out.println(correct(x));
		// System.out.println(wrong(x));
		// return correct(x)==wrong(x);
	}

	public boolean isCorrect(double[] x) {
		if (x.length == this.Dimension) {
			return TestProgram.test_bessj0(x[0]);
		} else {
			return true;
		}
	}

	public double wrong(double x) {
		double ax;
		double z;
		double xx, y, ans, ans1, ans2;

		/* ERROR */
		/* if ((ax=fabs(x)) < 8.0) { */
		if ((ax = Math.abs(x)) <= 10.0) {
			y = x * x;
			ans1 = 57568490574.0 + y * (-13362590354.0
					+ y * (651619640.7 + y * (-11214424.18 + y * (77392.33017 + y * (-184.9052456)))));
			ans2 = 57568490411.0 + y * (1029532985.0 + y * (9494680.718
					/* ERROR */
					/* +y*(59272.64853+y*(267.8532712+y*1.0)))); */
					+ y * (59272.64853 + y * (267.8532712 + y + 1.0))));
			ans = ans1 / ans2;
		} else {
			z = 8.0 / ax;
			y = z * z;
			xx = ax - 0.785398164;
			ans1 = 1.0 + y * (-0.1098628627e-2 + y * (0.2734510407e-4 + y * (-0.2073370639e-5 + y * 0.2093887211e-6)));
			ans2 = -0.1562499995e-1 + y * (0.1430488765e-3
					/* ERROR */
					/* +y*(-0.6911147651e-5+y*(0.7621095161e-6 */
					+ y * (-0.6911147651e-5 + z * (0.7621095161e-6
							/* ERROR */
							/* -y*0.934935152e-7))); */
							+ y * 0.934935152e-7)));
			ans = Math.sqrt(0.636619772 / ax) * (Math.cos(xx) * ans1 - z * Math.sin(xx) * ans2);
		}
		return ans;
	}

}
