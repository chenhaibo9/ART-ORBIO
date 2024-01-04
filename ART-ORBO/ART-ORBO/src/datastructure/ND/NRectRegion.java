package datastructure.ND;

public class NRectRegion {
	public static void main(String[] args) {
		NRectRegion region = new NRectRegion();
		region.start = new NPoint(new double[] { 0.1, 0.1, 0.1 });
		region.end = new NPoint(new double[] { 0.4, 0.2, 0.2 });
		System.out.println(region.size());
	}

	NPoint start;

	NPoint end;

	public NRectRegion() {
		super();
	}

	public NRectRegion(NPoint start, NPoint end) {
		super();
		this.start = start;
		this.end = end;
	}

	public NPoint getEnd() {
		return end;
	}

	public NPoint getStart() {
		return start;
	}

	public void setEnd(NPoint end) {
		this.end = end;
	}

	public void setStart(NPoint start) {
		this.start = start;
	}

	public double size() {
		double size = 1.0;
		if (start.xn == null || end.xn == null) {
			return -1;
		}
		for (int i = 0; i < start.xn.length; i++) {
			// if(i==0){size=Math.abs(end.xn[i]-start.xn[i]);}
			size *= Math.abs(end.xn[i] - start.xn[i]);
		}
		return size;
	}

	public boolean isPointInRegion(NPoint p) {
		boolean result = false;
		double[] start = getStart().getXn();
		double[] end = getEnd().getXn();

		double[] pxn = p.getXn();
		boolean isPIn = true;
		for (int j = 0; j < pxn.length; j++) {
			if (pxn[j] < start[j] || pxn[j] > end[j]) {
				isPIn = false;
			}
		}

		return isPIn;
	}

	@Override
	public String toString() {
		return "NRectRegion [start=" + start + ", end=" + end + "]";
	}
}
