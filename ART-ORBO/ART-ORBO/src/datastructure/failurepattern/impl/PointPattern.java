package datastructure.failurepattern.impl;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import util.RandomCreator;

/*
 * 
 * */
public class PointPattern extends FailurePattern {

	private int count;// 1维50，二维7*7=49 三维4*4*4=64 四维3*3*3*3=81
	private int eachCount;
	private double fail_regionS;

	private double eachPointArea;

	private NPoint[] points;
	RandomCreator randomCreator;

	@Override
	public void genFailurePattern() {
		switch (this.dimension) {
		case 1:
			count = 50;
			eachCount = 50;
			break;
		case 2:
			count = 49;
			eachCount = 7;
			break;
		case 3:
			count = 64;
			eachCount = 4;
			break;
		case 4:
			count = 81;
			eachCount = 3;
			break;
		default:
			System.out.println("dimension is too high! error in PointPattern genFailurePattern()");
		}
		double totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}
		fail_regionS = this.fail_rate * totalArea;

		eachPointArea = fail_regionS / (double) this.count;

		points = new NPoint[this.count];
		// 求出每个点的位置即可
		double x = 1 / (double) eachCount;
		x = x * 0.5;
		switch (dimension) {
		case 1:
			for (int i = 0; i < this.count; i++) {
				points[i] = new NPoint(new double[] { (i + 1) * x });
			}
			break;
		case 2:
			for (int i = 0; i < eachCount; i++) {
				for (int j = 0; j < eachCount; j++) {
					points[i] = new NPoint(new double[] { (i + 1) * x, (j + 1) * x });
				}
			}
			break;
		case 3:
			for (int i = 0; i < eachCount; i++) {
				for (int j = 0; j < eachCount; j++) {
					for (int k = 0; k < eachCount; k++) {
						points[i] = new NPoint(new double[] { (i + 1) * x, (j + 1) * x, (k + 1) * x });
					}
				}
			}
			break;
		case 4:
			for (int i = 0; i < eachCount; i++) {
				for (int j = 0; j < eachCount; j++) {
					for (int k = 0; k < eachCount; k++) {
						for (int l = 0; l < eachCount; l++) {
							points[i] = new NPoint(new double[] { (i + 1) * x, (j + 1) * x, (k + 1) * x, (l + 1) * x });
						}
					}
				}
			}
		}

	}
	public double calculateRadius(int count) {
		if (this.dimension % 2 == 0) {
			int k = this.dimension / 2;
			double kjie = 1;
			for (int i = k; i > 0; i--) {
				kjie *= i;
			}
			double temp = (this.fail_regionS * kjie) / (count * Math.pow(Math.PI, k));

			return Math.pow(temp, 1 / (double) this.dimension);
		} else {
			int k = this.dimension / 2;
			double kjie = 1;
			double k2jie = 1;
			for (int i = k; i > 0; i--) {
				kjie *= i;
			}
			for (int i = (2 * k + 1); i > 0; i--) {
				k2jie *= i;
			}
			double temp = (this.fail_regionS * k2jie) / (kjie * Math.pow(2, 2 * k + 1) * Math.pow(Math.PI, k) * count);
			// System.out.println("return R");
			return Math.pow(temp, 1 / (double) this.dimension);
		}
	}
	@Override
	public boolean isCorrect(NPoint p) {
		boolean flag = true;
		//double radius = Math.sqrt(this.eachPointArea / Math.PI);
		double radius=calculateRadius(1);
		double pxn[] = p.getXn();
		for (int i = 0; i < this.count; i++) {
			double[] tempxn = points[i].getXn();
			double distance = 0.0;
			for (int j = 0; j < tempxn.length; j++) {
				distance += (tempxn[j] - pxn[j]) * (tempxn[j] - pxn[j]);
			}
			distance = Math.sqrt(distance);
			if (distance < radius) {
				flag = false;
				break;
			}
		}

		return flag;
	}

	@Override
	public void showFailurePattern() {

	}

	public static void main(String[] args) {
		double dimension=2;
		double number=10;

		System.out.println("recommend failure rate:"+number*Math.pow(1.0/number,dimension));
		for(int i=1;i<5;i++){
			int count=(int)Math.pow(i,dimension);
			double thisR=Math.pow(0.005/count,1.0/dimension);

			System.out.println("i="+i+" "+thisR+","+1.0/count);
		}

		System.out.println(Math.pow(0.005/number,1.0/dimension));

		System.out.println(1.0/number);


	}
}
