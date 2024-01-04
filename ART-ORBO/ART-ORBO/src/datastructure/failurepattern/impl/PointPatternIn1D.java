package datastructure.failurepattern.impl;

import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import util.draw.StdDraw;

public class PointPatternIn1D extends FailurePattern {

	public static void main(String[] args) {
//		PointPatternIn1D test = new PointPatternIn1D();
//		test.dimension = 2;
//		test.min = new double[] { 0, 0 };
//		test.max = new double[] { 1, 1 };
//		test.fail_rate = 0.01;
//		test.random = new Random(5);
//		test.fail_regionS = 0.01;
//		test.eachPointArea = 0.01 / 50.0;
//		test.points = new NPoint[50];
//		StdDraw.rectangle(0.5, 0.5, 0.5, 0.5);
//		for (int i = 0; i < 50; i++) {
//			NPoint temp = test.genRandomPoint();
//			System.out.println(temp);
//			StdDraw.circle(temp.getXn()[0], temp.getXn()[1], Math.sqrt(test.eachPointArea / Math.PI));
//			test.points[i] = temp;
//		}
//		// test.genFailurePattern();
//		NPoint p1 = new NPoint(new double[] { 0.33842373215897027, 0.2875843418937697 });
//		StdDraw.filledCircle(p1.getXn()[0], p1.getXn()[1], 0.001);
//
//		System.out.println("isCorrect:" + test.isCorrect(p1));
	}

	private int count = 50;

	private double fail_regionS;

	private double eachPointArea;

	private NPoint[] points;

	@Override
	public void genFailurePattern() {
		double totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}
		fail_regionS = this.fail_rate * totalArea;

		eachPointArea = fail_regionS / (double) this.count;

		points = new NPoint[this.count];

		for (int i = 0; i < this.count; i++) {
			points[i] = genRandomPoint();
		}
	}

	private NPoint genRandomPoint() {
		double x = random.nextDouble() * (this.max[0] - this.min[0]) + min[0];
		//double y = random.nextDouble() * (this.max[1] - this.min[1]) + min[1];
		return new NPoint(new double[] { x });
	}

	public int getCount() {
		return count;
	}

	@Override
	public boolean isCorrect(NPoint p) {
		boolean flag = true;
		double radius = this.eachPointArea /2.0;
		double x = p.getXn()[0];
		//double y = p.getXn()[1];
		for (int i = 0; i < this.count; i++) {
			double x1 = points[i].getXn()[0];
			//double y1 = points[i].getXn()[1];
			double distance = Math.sqrt((x1 - x) * (x1 - x) );
			if (distance < radius) {
				flag = false;
			}
		}

		return flag;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void showFailurePattern() {
		
	}
}
