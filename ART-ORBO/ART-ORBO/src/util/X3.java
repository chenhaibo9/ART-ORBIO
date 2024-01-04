package util;

public class X3 {
	public static double[] shengjinFormula(double acof, double bcof, double cof, double dof) {
		double A = bcof * bcof - 3.0 * acof * cof;// A=b^2-3ac
		double B = bcof * cof - 9.0 * acof * dof;// B=bc-9ad
		double C = cof * cof - 3.0 * bcof * dof;// C=c^2-3bd
		double delta = B * B - 4.0 * A * C;
		double root = 0.0;
		double r1 = 0.0;
		double r2 = 0.0;
		double[] roots = new double[3];
		if (delta > 0) {
			double Y1 = A * bcof + 3.0 * acof * (-B + Math.sqrt(B * B - 4.0 * A * C)) / 2.0;
			double Y2 = A * bcof + 3.0 * acof * (-B - Math.sqrt(B * B - 4.0 * A * C)) / 2.0;
			double powY1;
			double powY2;
			if (Y1 < 0) {
				powY1 = -Math.pow(-Y1, 1.0 / 3.0);
			} else {
				powY1 = Math.pow(Y1, 1.0 / 3.0);
			}
			if (Y2 < 0) {
				powY2 = -Math.pow(-Y2, 1.0 / 3.0);
			} else {
				powY2 = Math.pow(Y2, 1.0 / 3.0);
			}
			root = (-bcof - powY1 - powY2) / (3.0 * acof);
			r1 = root;
			r2 = root;
		} else if (delta == 0) {
			root = -bcof / acof + B / A;
			r1 = -B / (2.0 * A);
			r2 = r1;

		} else if (delta < 0) {
			double T = (2.0 * A * bcof - 3.0 * acof * B) / (2.0 * Math.pow(A, 3.0 / 2.0));
			double theta = Math.acos(T);
			root = (-bcof - 2.0 * Math.sqrt(A) * Math.cos(theta / 3.0)) / (3.0 * acof);
			r1 = (-bcof + Math.sqrt(A) * (Math.cos(theta / 3.0) + Math.sqrt(3.0) * Math.sin(theta / 3.0)))
					/ (3.0 * acof);
			r2 = (-bcof + Math.sqrt(A) * (Math.cos(theta / 3.0) - Math.sqrt(3.0) * Math.sin(theta / 3.0)))
					/ (3.0 * acof);
		}
		roots[0] = root;
		roots[1] = r1;
		roots[2] = r2;
		return roots;
	}
}
