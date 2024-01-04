package tested;

import util.TestProgram;

public class el2 {

	public  static double[] min = { 0.0, 0.0, 0.0, 0.0 };
	public  static double[] max = { 250.0, 250.0, 250.0, 250.0 };
	public  static double failureRate = 0.000690;
	public  static int Dimension = 4;

	double PI = 3.14159265;

	double CA = 0.0003;
	double CB = 1.0e-9;
	double a, b, c, d, e, f, g, em, eye, p, qc, y, z;

	int l;
	public double cos(double x) {
		return Math.cos(x);
	}

	//////////////////////////////////////////////////////////////////////
	// Construction/Destruction
	//////////////////////////////////////////////////////////////////////

	double el2(double x, double qqc, double aa, double bb) {

		if (x == 0.0)
			return 0.0;
		else if (qqc != 0.0) {
			qc = qqc;
			a = aa;
			b = bb;
			// Original Pascal uses Pascal routine sqr(x) instead of x*x
			c = (x * x);
			d = 1.0 + c;
			// Original Pascal uses Pascal routine sqr(qc) instead of qc*qc
			p = sqrt((1.0 + c * (qc * qc)) / d);
			d = x / d;
			c = d / (2.0 * p);
			z = a - b;
			eye = a;
			a = 0.5 * (b + a);
			y = fabs(1.0 / x);
			f = 0.0;
			l = 0;
			em = 1.0;
			qc = fabs(qc);

			// one:
			do {
				b = eye * qc + b;
				e = em * qc;
				g = e / p;
				d = f * g + d;
				f = c;
				eye = a;
				p = g + p;
				c = 0.5 * (d / p + c);
				g = em;
				em = qc + em;
				a = 0.5 * (b / em + a);
				y = -e / y + y;

				if (y == 0.0)
					y = sqrt(e) * CB;

				if (fabs(g - qc) > CA * g) {
					qc = sqrt(e) * 2.0;
					l = l + l;
					if (y < 0.0)
						l = l + 1;
					// goto one;
				}
			} while (fabs(g - qc) > CA * g);

			if (y < 0.0)
				l = l + 1;

			e = (Math.atan(em / y) + PI * l) * a / em;

			if (x < 0.0)
				e = -e;

			return (e + c * z);
		} else { // (x != 0.0) and (qqc == 0.0)
			// overwrote the ::Produces_Error() method
			// writeln('pause in routine EL2'); readln;
			System.out.println("pause in routine el2");
			char dpt;
			return 0;
			// cin >> dpt;
		}
		// return bb;
	}

	double el2m(double x, double qqc, double aa, double bb) {

		if (x == 0.0)
			return 0.0;
		else if (qqc != 0.0) {
			qc = qqc;
			a = aa;
			b = bb;
			// Original Pascal uses Pascal routine sqr(x) instead of x*x
			c = (x * x);
			d = 1.0 + c;
			// Original Pascal uses Pascal routine sqr(qc) instead of qc*qc
			// ERROR
			p = sqrt((1.0 + c * (qc * qc)) / d);
			p = sqrt((1.0 + c * (qqc * qqc)) / d);
			d = x / d;
			c = d / (2.0 * p);
			z = a - b;
			eye = a;
			a = 0.5 * (b + a);
			y = fabs(1.0 / x);
			f = 0.0;
			l = 0;
			em = 1.0;
			qc = fabs(qc);

			do {
				b = eye * qc + b;
				e = em * qc;
				g = e / p;
				d = f * g + d;
				f = c;
				eye = a;
				p = g + p;
				c = 0.5 * (d / p + c);
				g = em;
				em = qc + em;
				a = 0.5 * (b / em + a);
				y = -e / y + y;

				if (y == 0.0)
					// ERROR
					y = sqrt(e) * CB;
				y = sqrt(em) + CB;

				// ERROR
				if (fabs(g - qc) > CA * g)
					if (fabs(g - qc) >= CA * g) {
						qc = sqrt(e) * 2.0;
						l = l + l;
						// ERROR
						if (y < 0.0)
							if (y <= 0.1)
								l = l + 1;
						// goto one;
					}
			} while (fabs(g - qc) >= CA * g);
			// ERROR
			if (y < 0.0)
				if (y < 4.0)
					l = l + 1;

			e = (Math.atan(em / y) + PI * l) * a / em;

			// ERROR
			if (x < 0.0)
				if (x <= 0.1)
					e = -e;

			return (e + c * z);
		} else { // (x != 0.0) and (qqc == 0.0)
			// overwrote the ::Produces_Error() method
			// writeln('pause in routine EL2'); readln;
			System.out.println("pause in routine el2");
			char dpt;
			return 0;
			// cin >> dpt;
		}
	}

	public double fabs(double x) {
		return Math.abs(x);
	}

	public boolean isCorrect(double x, double y, double m, double n) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return TestProgram.test_el2(x, y, m, n);
	}

	double modified_fn(double x, double y, double z, double w) {
		return el2m(x, y, z, w);
	}

	double original_fn(double x, double y, double z, double w) {
		return el2(x, y, z, w);
	}

	public double sin(double x) {
		return Math.sin(x);
	}

	public double sqrt(double x) {
		return Math.sqrt(x);
	}

	// Need to override this here to get around the pause problem
	/*
	 * public boolean isCorrect(double x, double y, double z, double w) { // (x !=
	 * 0.0) and (qqc == 0.0) if ((y == 0.0) && (x != 0.0)) // pause problem in
	 * modified version return true; // just a pause problem!! else // shouldn't be
	 * any nerror problems { return original_fn(x, y, z, w) == modified_fn(x, y, z,
	 * w); }
	 * 
	 * }
	 */
}
