package tested;

public class probks0 {

	public  static double min = -50000;
	public static  double max = 50000;
	public  static double failureRate = 0.000387;

	public double correct(double alam) {
		double EPS1 = 0.001, EPS2 = 1.0e-8;
		double a2, fac, sum, term, termbf;

		a2 = -2.0 * alam * alam;
		fac = 2.0;
		sum = 0.0;
		termbf = 0.0;

		for (int j = 1; j <= 100; j++) {
			// Original Pascal uses Pascal routine sqr(j) instead of j*j
			term = fac * Math.exp(a2 * (j * j));
			sum += term;

			// In the Numerical recipes reference, '<=' is used instead of '<'
			if ((Math.abs(term) < (EPS1 * termbf)) || (Math.abs(term) < (EPS2 * sum)))
				return sum;
			else {
				fac = -fac;
				termbf = Math.abs(term);
			}
		}

		return 1.0;

	}

	public boolean isCorrect(double x) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return correct(x) == wrong(x);
	}

	public double wrong(double alam) {
		double EPS1 = 0.001, EPS2 = 1.0e-8;
		double a2, fac, sum, term, termbf;

		/* ERROR */
		/* a2 = -2.0 * alam * alam; */
		a2 = -2.1 * alam * alam;
		fac = 2.0;
		sum = 0.0;
		termbf = 0.0;

		for (int j = 1; j <= 100; j++) {
			// Original Pascal uses Pascal routine sqr(j) instead of j*j
			/* ERROR */
			/* term = fac * exp(a2*(j*j)); */
			term = fac * Math.exp(a2 + (j * j));
			sum += term;

			/* ERROR */
			/*
			 * // In the Numerical recipes reference, '<=' is used instead of '<'
			 */
			/*
			 * if ( ( fabs(term) < (EPS1*termbf) ) || ( fabs(term) < (EPS2*sum) ) )
			 */
			if ((Math.abs(term) < (EPS1 * termbf)) || (Math.abs(term) > (EPS2 * sum)))
				return sum;
			else {
				/* ERROR */
				/* fac = -fac; */
				fac = -term;
				termbf = Math.abs(term);
			}
		}

		return 1.0;

	}
}
