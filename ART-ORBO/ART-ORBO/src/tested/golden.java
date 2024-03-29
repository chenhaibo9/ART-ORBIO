package tested;

import util.TestProgram;

public class golden {

	public  static double[] min = { -100, -100, -100 };
	public  static double[] max = { 60, 60, 60 };
	public  static double failureRate = 0.00055;
	public  static int Dimension = 3;

	public boolean isCorrect(double x, double y, double m) {
		return TestProgram.test_golden(x, y, m);
	}

	/*
	 * public double fabs(double x){ return Math.abs(x); } public double sqrt(double
	 * x){ return Math.sqrt(x); } public double cos(double x){ return Math.cos(x); }
	 * public double sin(double x){ return Math.sin(x); }
	 * 
	 * 
	 * double golden(double ax, double bx, double cx, double tol, Double xmin)
	 * Programs using routine GOLDEN must supply an external function
	 * func(x:double):double whose minimum is to be found.
	 * 
	 * { final double R = 0.61803399; double f0, f1, f2, f3, c, x0, x1, x2, x3;
	 * 
	 * c = 1.0-R; x0 = ax; x3 = cx;
	 * 
	 * if ( fabs(cx-bx) > fabs(bx-ax) ) { x1 = bx; x2 = bx+c*(cx-bx); } else { x2 =
	 * bx; x1 = bx-c*(bx-ax); }
	 * 
	 * f1 = func(x1); f2 = func(x2); while (fabs(x3-x0) > tol*(fabs(x1)+fabs(x2))) {
	 * if (f2 < f1) { x0 = x1; x1 = x2; x2 = R*x1+c*x3; f0 = f1; f1 = f2; f2 =
	 * func(x2); } else { x3 = x2; x2 = x1; x1 = R*x2+c*x0; f3 = f2; f2 = f1; f1 =
	 * func(x1); } }
	 * 
	 * if (f1 < f2) { xmin = x1; return f1; } else { xmin = x2; return f2; } }
	 * 
	 * double goldenm(double ax, double bx, double cx, double tol, Double xmin) {
	 * final double R = 0.61803399; double f0, f1, f2, f3, c, x0, x1, x2, x3;
	 * 
	 * c = 1.0-R; x0 = ax; x3 = cx;
	 * 
	 * ERROR if ( fabs(cx-bx) > fabs(bx-ax) ) if ( fabs(cx-bx) >= (fabs(bx-ax)+ 0.1)
	 * ) { x1 = bx; x2 = bx+c*(cx-bx); } else { x2 = bx; x1 = bx-c*(bx-ax); }
	 * 
	 * f1 = func(x1); f2 = func(x2); ERROR while (fabs(x3-x0) >
	 * tol*(fabs(x1)+fabs(x2))) while (fabs(x3-x0) >= tol*(fabs(x1)+fabs(x2))) { if
	 * (f2 < f1) { x0 = x1; x1 = x2; x2 = R*x1+c*x3; f0 = f1; f1 = f2; f2 =
	 * func(x2); } else { x3 = x2; x2 = x1; ERROR x1 = R*x2+c*x0; x1 = R*x1+c*x0; f3
	 * = f2; f2 = f1; f1 = func(x1); } }
	 * 
	 * ERROR if (f1 < f2) if (f1 <= f2) { xmin = x1; return f1; } else { xmin = x2;
	 * return f2; } }
	 * 
	 * double func(double x) { return bessj0(x); }
	 * 
	 * double bessj0(double x) { double ax,z; double xx,y,ans,ans1,ans2;
	 * 
	 * if ((ax=fabs(x)) < 8.0) { y=x*x;
	 * ans1=57568490574.0+y*(-13362590354.0+y*(651619640.7
	 * +y*(-11214424.18+y*(77392.33017+y*(-184.9052456)))));
	 * ans2=57568490411.0+y*(1029532985.0+y*(9494680.718
	 * +y*(59272.64853+y*(267.8532712+y*1.0)))); ans=ans1/ans2; } else { z=8.0/ax;
	 * y=z*z; xx=ax-0.785398164; ans1=1.0+y*(-0.1098628627e-2+y*(0.2734510407e-4
	 * +y*(-0.2073370639e-5+y*0.2093887211e-6))); ans2 =
	 * -0.1562499995e-1+y*(0.1430488765e-3 +y*(-0.6911147651e-5+y*(0.7621095161e-6
	 * -y*0.934935152e-7))); ans=sqrt(0.636619772/ax)*(cos(xx)*ans1-z*sin(xx)*ans2);
	 * } return ans; }
	 * 
	 * boolean Produces_Error(double x, double y, double z) { boolean error_exists =
	 * false;
	 * 
	 * Double xmin= 1.0e-6; double tol = 1.0e-6;
	 * 
	 * error_exists = ( golden(x, y, z, tol, xmin) != goldenm(x, y, z,tol, xmin) );
	 * 
	 * //System.out.println("xmin"+xmin); return error_exists; }
	 * 
	 * 
	 * public boolean isCorrect(double x, double y, double z){ return
	 * !Produces_Error(x, y, z); }
	 * 
	 * 
	 * public static void main(String[] args) {
	 * 
	 * System.out.println(new golden().isCorrect(-99, -99, -99));; }
	 */
}
