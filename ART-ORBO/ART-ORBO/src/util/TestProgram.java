package util;

public class TestProgram {
	static {
		System.loadLibrary("programlib");
	}

	public static void main(String[] args) {
		System.out.println(test_gammq(1456.9880675291643,38.11143387109041));
		// System.out.println(new bessj0().isCorrect(-37.62759377580369));
		//System.out.println(TestProgram.test_airy(-37.62759377580369));
		//System.out.println(TestProgram.test_bessj(219.85509600636087, 135.87255603090216));
	}

	public static native boolean test_airy(double a);

	public static native boolean test_bessj(double a, double b);

	public static native boolean test_bessj0(double a);

	public static native boolean test_cel(double a, double b, double c, double d);

	public static native boolean test_el2(double a, double b, double c, double d);

	public static native boolean test_erfcc(double a);

	public static native boolean test_gammq(double a, double b);

	public static native boolean test_golden(double a, double b, double c);

	public static native boolean test_plgndr(double a, double b, double c);

	public static native boolean test_probks(double a);

	public static native boolean test_sncndn(double a, double b);

	public static native boolean test_tanh(double a);
}
