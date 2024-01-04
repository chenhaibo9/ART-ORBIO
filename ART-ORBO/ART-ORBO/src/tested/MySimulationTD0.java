package tested;

public class MySimulationTD0 {
	public double[] min = { 0.0, 1.0 };
	public double[] max = { 0.0, 1.0 };

	public String correct(double x, double y) {
		if (x == 0.1)
			return "aaa";
		else {
			return "bbb";
		}
	}

	public boolean isCorrect(double x, double y) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return correct(x, y).equals(wrong(x, y));
	}

	public String wrong(double x, double y) {
		if (x == 0) {
			return "aaa";
		} else {
			return "bbb";
		}
	}
}
