package tested;

/*
 * Input Domain:(0,1)
 * failure rate:0.005
 *errors: 1 errors(1 xor)
 * */
public class MySimulation0 {
	public static void main(String[] args) {
		MySimulation0 sim = new MySimulation0();
		System.out.println(sim.wrong(0.346));
		;
		System.out.println(sim.correct(0.346));
		System.out.println(sim.isCorrect(0.346));
	}
	public static  double min = 0.0;
	public static  double max = 1.0;

	public static  double failurerate = 0.005;

	public String correct(double x) {
		StringBuffer buffer = new StringBuffer();
		if (x > 0 && x < 1.0) {
			buffer.append("yes1");
			if (x > 0.5 && x < 0.95) {
				buffer.append("yes11");
				buffer.append("yes22");
				if ((x > 0.65 && x < 0.655)) {
					buffer.append("yes111");
					buffer.append("yes222");
					buffer.append("yes333");
					buffer.append("yes444");
					buffer.append("yes555");
					buffer.append("yes666");
					buffer.append("yes777");
					buffer.append("yes888");
					buffer.append("yes999");
					buffer.append("yes111");
					buffer.append("yes222");
					buffer.append("yes333");
					buffer.append("yes444");
					buffer.append("yes555");
					buffer.append("yes666");
					buffer.append("yes777");
					buffer.append("yes888");
					buffer.append("yes999");
					buffer.append("yes101010");
					buffer.append("yes11111");
				}
			}
		} else {
			buffer.append("no1");
		}
		return buffer.toString();
	}

	public boolean isCorrect(double x) {
		// System.out.println("correct:"+correct(x));
		// System.out.println("wrong:"+wrong(x));
		return correct(x).equals(wrong(x));
	}

	public String wrong(double x) {
		StringBuffer buffer = new StringBuffer();
		if (x > 0 && x < 1.0) {
			buffer.append("yes1");
			if (x > 0.5 && x < 0.95) {
				buffer.append("yes11");
				buffer.append("yes22");
				if ((x > 0.65 && x < 0.655)) {
					buffer.append("yes111");
					buffer.append("yes222");
					buffer.append("yes333");
					buffer.append("yes444");
					buffer.append("yes555");
					buffer.append("yes666");
					buffer.append("yes777");
					buffer.append("yes888");
					buffer.append("yes999");
					buffer.append("yes111");
					buffer.append("yes222");
					buffer.append("yes333");
					buffer.append("yes444");
					buffer.append("yes555");
					buffer.append("yes666");
					buffer.append("yes777");
					buffer.append("yes888");
					buffer.append("yes999");
					buffer.append("yes101010");
					buffer.append("yes111111");
				}
			}
		} else {
			buffer.append("no1");
		}
		return buffer.toString();
	}
}
