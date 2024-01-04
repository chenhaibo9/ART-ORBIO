package datastructure.failurepattern.impl;

import java.util.ArrayList;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;

public class MultiBlockPattern extends FailurePattern {
	private double eachFailLength;
	private double fail_regionS;
	private ArrayList<double[]> fail_start;

	private int failBlockCount = 5;;

	@Override
	public void genFailurePattern() {
		fail_start = new ArrayList<>(this.failBlockCount);
		;
		double totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}
		this.fail_regionS = this.fail_rate * totalArea;
		this.eachFailLength = Math.pow(fail_regionS, 1 / (double) this.dimension);
		this.eachFailLength = this.eachFailLength / (double) failBlockCount;
		// System.out.println("EachFailArea:"+this.EachFailLength);
		for (int j = 0; j < fail_start.size(); j++) {

			double[] temp=new double[this.dimension];
			for (int i = 0; i < this.dimension; i++) {
				// double start=
				temp[i] = random.nextDouble() * (max[i] - min[i] - eachFailLength) + min[i];
			}
			fail_start.add(temp);
		}
	}

	@Override
	public boolean isCorrect(NPoint p) {
		double[] xn = p.getXn();
		boolean lead2Fail = false;
		for(int j=0;j<fail_start.size();j++){
		for (int i = 0; i < this.dimension; i++) {
			if (xn[i] < this.fail_start.get(j)[i] || xn[i] > (this.fail_start.get(j)[i] + eachFailLength)) {
				lead2Fail = true;
			}
		}
		}
		// System.out.println(Arrays.toString(nDPoints));
		// System.out.println("isFail:"+lead2Fail);
		// lead2Fail=false,失效，=true不失效
		return lead2Fail;
	}

	@Override
	public void showFailurePattern() {
		// print block pattern
//		System.out.println("Pattern type:" + getClass().getSimpleName() + " Dimension:" + this.dimension);
//		System.out.print("[");
//		for (int i = 0; i < this.dimension; i++) {
//
//			System.out.print("(" + fail_start[i] + "," + (fail_start[i] + eachFailLength) + ")");
//
//			if (i != this.dimension - 1) {
//				System.out.print(",");
//			}
//			if ((i + 1) % 5 == 0) {
//				System.out.println();
//			}
//		}
//		System.out.print("]");
//		System.out.println();
	}

}
