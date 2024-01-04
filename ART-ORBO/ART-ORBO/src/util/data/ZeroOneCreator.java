package util.data;

public class ZeroOneCreator implements DataCreator {
	@Override
	public double[] maxCreator(int n) {
		double[] max = new double[n];
		for (int i = 0; i < n; i++) {
			max[i] = 1.0;
		}
		return max;
	}

	@Override
	public double[] minCreator(int n) {
		double[] min = new double[n];
		for (int i = 0; i < n; i++) {
			min[i] = 0;
		}
		return min;
	}
}
