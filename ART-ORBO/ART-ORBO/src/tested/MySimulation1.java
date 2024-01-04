package tested;

public class MySimulation1 {
	public String wrong(double x) {
		StringBuffer buffer = new StringBuffer();
		// (x>0&&x<0.582)||(x>0.587&&x<1.0)
		if ((x > 0 && x < 1.0)) {
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

}
