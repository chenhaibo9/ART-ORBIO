package util;


import util.draw.StdDraw;

public class CRandomNumber{
	static {
		System.loadLibrary("libRNG2");
	}

	public static native void initSeed(long seed);



	public static native double randome0e1();

	public static native double randomi0e1();

	public static native double randomi0i1();
	
	public static void main(String[] args) {
		CRandomNumber.initSeed(3);
		for (int i = 0; i < 10000; i++) {
			StdDraw.point(CRandomNumber.randomi0i1(), CRandomNumber.randomi0i1());
		}
	}
}
