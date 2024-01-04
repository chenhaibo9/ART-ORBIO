package tested;

import util.TestProgram;

/*
 * Input Domain:(-30000,30000)
 * failure rate:0.000574 avf=1/0.000574=1742.1602787456445993031358885017
 *errors: 5 errors(2 AOR, 1 ROR, 1SVR ,1CR)
 * */
public class erfcc {
	public  static double[] min = { -30000 };
	public  static double[] max = { 30000 };
	public  static double failureRate = 0.000574;
	public  static int Dimension = 1;

	public boolean isCorrect(double x) {
		return TestProgram.test_erfcc(x);
	}
	/*
	 * public double correct(double x) { double t,z,ans; z=Math.abs(x);
	 * t=1.0/(1.0+0.5*z);
	 * ans=t*Math.exp(-z*z-1.26551223+t*(1.00002368+t*(0.37409196+t*(0.09678418+
	 * t*(-0.18628806+t*(0.27886807+t*(-1.13520398+t*(1.48851587+
	 * t*(-0.82215223+t*0.17087277))))))))); return x >= 0.0 ? ans : 2.0-ans; }
	 * public double wrong(double x) { double t,z,ans;
	 * 
	 * z=Math.abs(x); t=1.0/(1.0+0.5*z);
	 * ans=t*Math.exp(-z*z-1.26551223+t*(1.00002368+t*(0.37409196+t*(0.09678418+
	 * ERROR t*(-0.18628806+t*(0.27886807+t*(-1.13520398+t*(1.48851587+
	 * t*(-0.18628806+t*(0.27886807+z*(-1.13520398+t*(1.48851587+ ERROR
	 * t*(-0.82215223+t*0.17087277))))))))); t*(-0.82215223-t*0.17087277)))))))));
	 * ERROR return x >= 0.0 ? ans : 2.0-ans; return x > 0.1 ? ans : 2.0-ans; }
	 */

}
