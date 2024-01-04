package datastructure.ND;

public class NPoint {
	public static void main(String[] args) {
		// NPoint npoint=new NPoint(null);
		// System.out.println(npoint.toString());
		// npoint.setXn(new double[]{2.2,3,4.6,7,8});
		// System.out.println(npoint.toString());

	}
	double xn[];

	public int dimension;

	public NPoint() {
		
	}

	public NPoint(double xn[]) {
		this.xn = xn;
		if (xn != null) {
			this.dimension = xn.length;
		} else {
			this.dimension = -1;
		}
	}

	public int getDimension() {
		return dimension;
	}

	public double[] getXn() {
		return xn;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public void setXn(double[] xn) {
		this.xn = xn;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		if (xn != null) {
			for (int i = 0; i < xn.length; i++) {
				if (i == xn.length - 1)
					sb.append(xn[i] + ")");
				else
					sb.append(xn[i] + ",");
			}
		} else {
			sb.append("null)");
		}
		return sb.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Point[");
		if (xn != null) {
			for (int i = 0; i < xn.length; i++) {
				if (i == xn.length - 1)
					sb.append("x" + i + "=" + xn[i] + "]");
				else
					sb.append("x" + i + "=" + xn[i] + ",");
			}
		} else {
			sb.append("null]");
		}
		return sb.toString();
	}

	public double distanceToAnotherPoint(NPoint p2) {
		double[] p1xn = this.getXn();
		double[] p2xn = p2.getXn();
		double distance = 0.0;
		for (int i = 0; i < p1xn.length; i++) {
			distance += Math.pow((p2xn[i] - p1xn[i]), 2);
		}
		distance = Math.sqrt(distance);
		return distance;
	}


	public NPoint midPointWithAnotherPoint(NPoint p2) {
		double[] p1xn = this.getXn();
		double[] p2xn = p2.getXn();
		double[] mid = new double[this.dimension];
		for (int i = 0; i < mid.length; i++) {
			mid[i] = 0.5 * (p1xn[i] + p2xn[i]);
		}
		return new NPoint(mid);
	}
}
