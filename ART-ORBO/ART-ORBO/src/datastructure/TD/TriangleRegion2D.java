package datastructure.TD;

import datastructure.ND.NPoint;

public class TriangleRegion2D {

	NPoint p1;
	NPoint p2;
	NPoint p3;

	public TriangleRegion2D() {
		super();
	}

	public TriangleRegion2D(NPoint p1, NPoint p2, NPoint p3) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	public NPoint getP1() {
		return p1;
	}

	public NPoint getP2() {
		return p2;
	}

	public NPoint getP3() {
		return p3;
	}

	public double getSquare() {
		// (1/2)*(p1.pp2.q+p2.pp3.q+p3.pp1.q-p1.pp3.q-p2.pp1.q-p3.pp2.q)
		/*
		 * System.out.println("p1:" + p1); System.out.println("p2:" + p2);
		 * System.out.println("p3:" + p3); System.out.println("square:" + ((0.5)
		 * Math.abs(p1.p * p2.q + p2.p * p3.q + p3.p * p1.q - p1.p * p3.q - p2.p * p1.q
		 * - p3.p * p2.q)));
		 */
		
		return (0.5) * Math.abs(p1.getXn()[0] * p2.getXn()[1] + p2.getXn()[0]* p3.getXn()[1] + p3.getXn()[0] * p1.getXn()[1] - p1.getXn()[0] * p3.getXn()[1]- p2.getXn()[0] * p1.getXn()[1] - p3.getXn()[0] * p2.getXn()[1]);
	}

	public void setP1(NPoint p1) {
		this.p1 = p1;
	}

	public void setP2(NPoint p2) {
		this.p2 = p2;
	}

	public void setP3(NPoint p3) {
		this.p3 = p3;
	}

	public void setPs(NPoint p1, NPoint p2, NPoint p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public String toString() {
		return "TriangleRegion [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
	}

}
