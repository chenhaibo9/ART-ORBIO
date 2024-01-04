package datastructure.TD;

/*
 * 一/二维的测试用例类，包含测试的值
 * */
@Deprecated
public class TestCase {
	public double p;// x
	public double q;// y
	public double m;// z
	public double n;//
	public double z;//
	public double coverage;

	public TestCase() {
		super();
	}

	public TestCase(double p) {
		super();
		this.p = p;
	}

	public TestCase(double p, double q) {
		super();
		this.p = p;
		this.q = q;
	}

	@Override
	public String toString() {
		return "TestCase [p=" + p + ", q=" + q + ", m=" + m + ", n=" + n + ", z=" + z + ", coverage=" + coverage + "]";
	}

}
