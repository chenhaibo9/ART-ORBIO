package datastructure.failurepattern;

import java.util.Random;

import datastructure.ND.NPoint;

public abstract class FailurePattern {
	public int dimension;
	public double[] min;
	public double[] max;
	public double fail_rate;
	public Random random;

	public abstract void genFailurePattern();

	public abstract boolean isCorrect(NPoint p);
	
	public abstract void showFailurePattern();
}
