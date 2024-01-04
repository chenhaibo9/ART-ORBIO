package util;

public class TestProgramEm {
	static {
		System.loadLibrary("programlibEm");
	}

	public static void main(String[] args) {
		int[] result=test_tanh(0.5);
		for(int temp:result){
			System.out.println(temp);
		}
		//System.out.println(test_gammq(1456.9880675291643,38.11143387109041));
		// System.out.println(new bessj0().isCorrect(-37.62759377580369));
		//System.out.println(TestProgram.test_airy(-37.62759377580369));
		//System.out.println(TestProgram.test_bessj(219.85509600636087, 135.87255603090216));
	}

	public static native int[] test_airy(double a);

	public static native int[] test_bessj(double a, double b);

	public static native int[] test_bessj0(double a);

	public static native int[] test_cel(double a, double b, double c, double d);

	public static native int[] test_el2(double a, double b, double c, double d);

	public static native int[] test_erfcc(double a);

	public static native int[] test_gammq(double a, double b);

	public static native int[] test_golden(double a, double b, double c);

	public static native int[] test_plgndr(double a, double b, double c);

	public static native int[] test_probks(double a);

	public static native int[] test_sncndn(double a, double b);

	public static native int[] test_tanh(double a);
}
