package tested;

public class tan1 {
	public double wrong(double u) {
		double epu = Math.exp(u);
		double emu = 1.0 / epu;

		if (Math.abs(u) <= 0.9) {
			double u2 = u * u;
			return (2 * u * (1 + u2 / 6 * (1 + u / 20 * (1 + u2 / 42 * (1 - u2 / 72)))) / (epu + emu));
		} else {
			double difference = epu - emu;
			double sum = epu + emu;
			double fraction = difference / sum;
			return fraction;
		}
	}
}
