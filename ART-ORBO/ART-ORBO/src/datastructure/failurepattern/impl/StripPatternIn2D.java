package datastructure.failurepattern.impl;

import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import util.draw.StdDraw;

public class StripPatternIn2D extends FailurePattern {
	public static void main(String[] args) {
		StripPatternIn2D strip = new StripPatternIn2D();
		strip.fail_rate = 0.01;
		strip.dimension = 2;
		strip.min = new double[] { 0, 0 };
		strip.max = new double[] { 1, 1 };
		strip.random = new Random(5);

		strip.genFailurePattern();

		StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
		double x1 = strip.p1.getXn()[0];
		double y1 = strip.p1.getXn()[1];
		double x2 = strip.p3.getXn()[0];
		double y2 = strip.p3.getXn()[1];
		System.out.println("p1:(" + x1 + "," + y1 + "),p3:(" + x2 + "," + y2 + ")");
		StdDraw.line(x1, y1, x2, y2);

		double x3 = strip.p2.getXn()[0];
		double y3 = strip.p2.getXn()[1];
		double x4 = strip.p4.getXn()[0];
		double y4 = strip.p4.getXn()[1];
		System.out.println("p2:(" + x3 + "," + y3 + "),p4:(" + x4 + "," + y4 + ")");
		StdDraw.line(x3, y3, x4, y4);

		NPoint p1 = new NPoint(new double[] { 0.13, 0.13 });
		// StdDraw.point(0.1, 0.1);
		StdDraw.circle(0.13, 0.13, 0.01);
		System.out.println();
		NPoint p2 = new NPoint(new double[] { 0.78, 0.03 });

		StdDraw.circle(0.78, 0.03, 0.01);
		System.out.println(strip.isCorrect(p2));
	}
	private NPoint p1;
	private NPoint p2;
	private NPoint p3;
	private NPoint p4;
	private double x;
	private double y;
	double fail_regionS;

	private int mode;

	private boolean check0(NPoint p) {
		double[] pxn = p.getXn();
		if (pxn[0] > p1.getXn()[0] && pxn[0] < p2.getXn()[0]) {
			return false;
		} else {
			return true;
		}
	}

	private boolean check1(NPoint p) {
		double[] pxn = p.getXn();
		if (pxn[1] > p1.getXn()[1] && pxn[1] < p2.getXn()[1]) {
			return false;
		} else {
			return true;
		}
	}

	private boolean check2(NPoint p) {
		double x = p.getXn()[0];
		double y = p.getXn()[1];

		double y1 = (x - p1.getXn()[0]) * (p3.getXn()[1] - p1.getXn()[1]) / (p3.getXn()[0] - p1.getXn()[0])
				+ p1.getXn()[1];
		double y2 = (x - p2.getXn()[0]) * (p4.getXn()[1] - p2.getXn()[1]) / (p4.getXn()[0] - p2.getXn()[0])
				+ p2.getXn()[1];

		if (y > y1 && y < y2) {
			return false;
		} else {
			return true;
		}
	}

	private void gen0() {
		double[] pxn = random();
		double width = fail_regionS;
		if (pxn[0] + width < 1.0) {
			p1 = new NPoint(new double[] { pxn[0], 0 });
			p2 = new NPoint(new double[] { pxn[0] + width, 0 });
			p3 = new NPoint(new double[] { pxn[0], 1 });
			p4 = new NPoint(new double[] { pxn[0] + width, 1 });
		} else {
			gen0();
		}
	}

	private void gen1() {
		double[] pxn = random();
		double width = fail_regionS;
		if (pxn[1] + width < 1.0) {
			p1 = new NPoint(new double[] { 0, pxn[1] });
			p2 = new NPoint(new double[] { 0, pxn[1] + width });
			p3 = new NPoint(new double[] { 1, pxn[1] });
			p4 = new NPoint(new double[] { 1, pxn[1] + width });
		} else {
			gen1();
		}
	}

	private void gen2() {
		double x = random.nextDouble();
		double y = 1 - x - Math.sqrt(2 * (0.5 * (1 - x) * (1 - x) - fail_regionS));
		if (y < 1 - x) {
			p1 = new NPoint(new double[] { 0, x });
			p2 = new NPoint(new double[] { 0, x + y });
			p3 = new NPoint(new double[] { 1 - x, 1 });
			p4 = new NPoint(new double[] { 1 - x - y, 1 });
		} else {
			gen2();
		}
	}

	private void gen3() {
		double x = random.nextDouble();
		double y = 1 - x - Math.sqrt(2 * (0.5 * (1 - x) * (1 - x) - fail_regionS));
		if (y < 1 - x) {
			p1 = new NPoint(new double[] { 0, 1 - x - y });
			p2 = new NPoint(new double[] { 0, 1 - x });
			p3 = new NPoint(new double[] { 1 - x - y, 0 });
			p4 = new NPoint(new double[] { 1 - x, 0 });
		} else {
			gen3();
		}
	}

	private void gen4() {
		double x = random.nextDouble();
		double y = 1 - x - Math.sqrt(2 * (0.5 * (1 - x) * (1 - x) - fail_regionS));
		if (y < 1 - x) {
			p1 = new NPoint(new double[] { x, 1 });
			p2 = new NPoint(new double[] { x + y, 1 });
			p3 = new NPoint(new double[] { 1, x });
			p4 = new NPoint(new double[] { 1, x + y });
		} else {
			gen4();
		}
	}

	private void gen5() {
		double x = random.nextDouble();
		double y = 1 - x - Math.sqrt(2 * (0.5 * (1 - x) * (1 - x) - fail_regionS));
		if (y < 1 - x) {
			p1 = new NPoint(new double[] { x + y, 0 });
			p2 = new NPoint(new double[] { x, 0 });
			p3 = new NPoint(new double[] { 1, 1 - x - y });
			p4 = new NPoint(new double[] { 1, 1 - x });
		} else {
			gen5();
		}
	}

	@Override
	public void genFailurePattern() {
		// TODO Auto-generated method stub
		double totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}
		fail_regionS = this.fail_rate * totalArea;
		/*
		 * p1 = new NPoint(new double[] { min[0], max[1] }); p2 = new NPoint(new
		 * double[] { min[1], max[0] }); double temp1 = max[1] - min[1]; double temp2 =
		 * max[0] - min[0]; this.y = Math.sqrt((temp1 * temp2 - 2 * fail_regionS) *
		 * (temp2 / temp1)); this.x = y * ((temp1) / temp2); p3 = new NPoint(new
		 * double[] { min[0], x }); p4 = new NPoint(new double[] { min[1], y });
		 */

		// method 2
		mode = random.nextInt(6);
		// int mode=3;
		// System.out.println("mode:"+mode);
		switch (mode) {
		case 0:
			// =
			gen0();
			break;
		case 1:
			// ||
			gen1();
			break;
		case 2:
			// |-
			gen2();
			break;
		case 3:
			// |_
			gen3();
			break;
		case 4:
			// -|
			gen4();
			break;
		case 5:
			// _|
			gen5();
			break;
		default:
			break;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean isCorrect(NPoint p) {
		boolean flag = true;
		/*
		 * double[] pxn = p.getXn(); double yy = pxn[1]; double xx = pxn[0]; if (min[0]
		 * < xx && xx < min[0] + y) { double yy1 = -x / y * xx + x; double yy2 = -xx +
		 * 1; if (yy > yy1 && yy < yy2) { flag = false; } } else { double yy2 = -xx + 1;
		 * if (yy > 0 && yy < yy2) { flag = false; } } return flag;
		 */

		// method2
		switch (mode) {
		case 0:
			flag = check0(p);
			break;
		case 1:
			flag = check1(p);
			break;
		case 2:
			flag = check2(p);
			break;
		case 3:
			flag = check2(p);
			break;
		case 4:
			flag = check2(p);
			break;
		case 5:
			flag = check2(p);
			break;
		default:
			flag = true;
			break;
		}
		return flag;
	}

	public double[] random() {
		double x = this.random.nextDouble();
		double y = this.random.nextDouble();
		return new double[] { x, y };
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public void showFailurePattern() {
		// TODO Auto-generated method stub
		
	}
}
