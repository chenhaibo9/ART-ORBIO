package datastructure.failurepattern.impl;

import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.ND.NRectRegion;
import datastructure.failurepattern.FailurePattern;


public class StripPattern extends FailurePattern {

    public double longerDimensionLengthRatio = 5;//1:5

    private NPoint start = new NPoint();
    private NPoint end = new NPoint();
    private double failureRegionSize;

    private int whichDimensionLonger;
    private double x;
    @Override
    public void genFailurePattern() {
        double[] startXn = new double[this.dimension];
        double[] endXn = new double[this.dimension];
        double totalArea = 1.0;
        for (int i = 0; i < this.dimension; i++) {
            totalArea *= (max[i] - min[i]);
        }
        failureRegionSize = totalArea * fail_rate;

        double k = Math.pow(1 / failureRegionSize, 1.0 / (this.dimension - 1.0));
        if (k < longerDimensionLengthRatio) {
            System.out.println("error! this pattern may be not a pure strip pattern");
            System.out.println("max k is " + k);
            longerDimensionLengthRatio = k;
        }

        x = Math.pow(failureRegionSize / longerDimensionLengthRatio, 1.0 / (this.dimension));
        whichDimensionLonger = random.nextInt(this.dimension);
        //System.out.println(whichDimensionLonger);
        double longerDimensionStart = random.nextDouble() * (this.max[whichDimensionLonger]-longerDimensionLengthRatio * x) + this.min[whichDimensionLonger];

        startXn[whichDimensionLonger] = longerDimensionStart;
        endXn[whichDimensionLonger] = longerDimensionStart + longerDimensionLengthRatio * x;

        for (int i = 0; i < this.dimension; i++) {
            if (i != whichDimensionLonger) {
                double tempStart = random.nextDouble() * x + this.min[i];
                startXn[i] = tempStart;
                endXn[i] = tempStart + x;
            }
        }

        start.setXn(startXn);
        end.setXn(endXn);

    }

    @Override
    public boolean isCorrect(NPoint p) {
        NRectRegion region = new NRectRegion(start, end);
        return !region.isPointInRegion(p);
        //return false;
    }

    @Override
    public void showFailurePattern() {
        System.out.println(start + "|" + end);
    }

    public void validateFailurePattern() {
        double[] min = start.getXn();
        double[] max = end.getXn();
        double totalArea = 1.0;
        for (int i = 0; i < this.dimension; i++) {
            totalArea *= (max[i] - min[i]);
        }
        System.out.println(totalArea+"  ,  "+failureRegionSize);

        System.out.println("each x="+x+" k*x="+(longerDimensionLengthRatio*x));
    }

    public static void main(String[] args) {
        StripPattern pattern = new StripPattern();
        pattern.min = new double[]{0, 0, 0};
        pattern.max = new double[]{1, 1, 1};
        pattern.dimension = 3;

        pattern.random = new Random(3);

        pattern.fail_rate = 0.1;

        pattern.longerDimensionLengthRatio = 5;

        pattern.genFailurePattern();

        //System.out.println(new NRectRegion(pattern.start, pattern.end).size());
        pattern.showFailurePattern();
        pattern.validateFailurePattern();

		/*double r=0.00005;double n=10;
        double k=Math.pow(1/r,1.0/(n-1.0));
		System.out.println("max k:"+k);
		System.out.println("x:"+Math.pow(r/k,1.0/n));*/
    }
}
