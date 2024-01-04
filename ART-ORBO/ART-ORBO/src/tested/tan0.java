package tested;

import util.TestProgram;

/*
 * Input Domain:(-500,500)
 * failure rate:0.001817 1/0.001817=550.35773252614199229499174463401
 *errors: 4 errors(1 AOR, 1 ROR, 1SVR ,1CR)
 * */
public class tan0 {
	public  static double[] min = { -500 };
	public  static double[] max = { 500 };
	public  static double failureRate = 0.001817;
	public static  int Dimension = 1;

	public boolean isCorrect(double x) {
		return TestProgram.test_tanh(x);
	}
	/*
	 * public double correct(double u){
	 * 
	 * double epu = Math.exp(u); double emu = 1.0/epu;
	 * 
	 * if (Math.abs(u) < 0.3) { double u2 = u*u; return (
	 * 2*u*(1+u2/6*(1+u2/20*(1+u2/42*(1+u2/72))))/(epu+emu) ); } else { double
	 * difference = epu - emu; double sum = epu + emu; double fraction =
	 * difference/sum; return fraction; } } public double wrong(double u){ double
	 * epu = Math.exp(u); double emu = 1.0/epu;
	 * 
	 * if (Math.abs(u) <= 0.9) { double u2 = u*u; return (
	 * 2*u*(1+u2/6*(1+u/20*(1+u2/42*(1-u2/72))))/(epu+emu) ); } else { double
	 * difference = epu - emu; double sum = epu + emu; double fraction =
	 * difference/sum; return fraction; } }
	 * 
	 * public boolean isCorrect(double x){ //
	 * System.out.println("correct:"+correct(x)); //
	 * System.out.println("wrong:"+wrong(x)); return correct(x)==(wrong(x)); }
	 */
}
