package tested.temptest;

class Example1 {
	public static void main(String args[]) {
		float x = 12.56f, y;
		if (x <= 0) {
			y = x + 1;
		} else if (x > 0 && x <= 16) {
			y = 2 * x + 1;
		} else {
			y = 3 * x + 3;
		}
		System.out.println(y);
	}
}
