package datastructure.failurepattern.impl;

import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;

public class BlockPattern extends FailurePattern {
	private double eachFailLength;
	private double fail_regionS;
	private double[] fail_start;

	@Override
	public void genFailurePattern() {
		fail_start = new double[this.dimension];
		double totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}
		this.fail_regionS = this.fail_rate * totalArea;
		this.eachFailLength = Math.pow(fail_regionS, 1 / (double) this.dimension);
		 //System.out.println("EachFailArea:"+this.eachFailLength);
		for (int i = 0; i < this.dimension; i++) {
			fail_start[i] = random.nextDouble() * (max[i] - min[i] - eachFailLength) + min[i];
//			System.out.println(fail_start[i]);
			
		}
//		System.out.println();
	}

	@Override
	public boolean isCorrect(NPoint p) {
		double[] xn = p.getXn();
		boolean lead2Fail = false;
		for (int i = 0; i < this.dimension; i++) {
			if (xn[i] < this.fail_start[i] || xn[i] > (this.fail_start[i] + eachFailLength)) {
				lead2Fail = true;
			}
		}
		// System.out.println(Arrays.toString(nDPoints));
		// System.out.println("isFail:"+lead2Fail);
		// lead2Fail=false,å¤±æ•ˆï¼Œ=trueä¸�å¤±æ•ˆ
		return lead2Fail;
	}

	@Override
	public void showFailurePattern() {
		// print block pattern
		System.out.println("Pattern type:" + getClass().getSimpleName() + " Dimension:" + this.dimension);
		System.out.print("[");
		for (int i = 0; i < this.dimension; i++) {

			System.out.print("(" + fail_start[i] + "," + (fail_start[i] + eachFailLength) + ")");

			if (i != this.dimension - 1) {
				System.out.print(",");
			}
			if ((i + 1) % 5 == 0) {
				System.out.println();
			}
		}
		System.out.print("]");
		System.out.println();
	}

	public static void main(String[] args) {
		BlockPattern pattern=new BlockPattern();
		pattern.min=new double[]{0,0,0};
		pattern.max=new double[]{1,1,1};
		pattern.dimension=3;
		
		pattern.random=new Random(3);
		
		pattern.fail_rate=0.005;
		
		pattern.genFailurePattern();
		
		pattern.showFailurePattern();	}
}
