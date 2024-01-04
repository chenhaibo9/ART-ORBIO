package util;

import java.util.ArrayList;
import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.ND.NRectRegion;

public class RandomCreator {
	private Random random;
	private int dimension;
	private double[] min;
	private double[] max;

	public RandomCreator(Random random, int dimension, double[] min, double[] max) {
		this.random = random;
		this.dimension = dimension;
		this.min = min;
		this.max = max;
	}

	public NPoint randomPoint() {
		// generate from the input domain
		NPoint point = new NPoint();
		double[] xn = new double[this.dimension];
		for (int i = 0; i < xn.length; i++) {
			xn[i] = random.nextDouble() * (max[i] - min[i]) + min[i];
		}
		point.setDimension(this.dimension);
		point.setXn(xn);
		return point;
	}

	public NPoint randomPoint(NRectRegion region) {
		NPoint p = new NPoint();
		double[] start = region.getStart().getXn();
		double[] end = region.getEnd().getXn();
		double xn[] = new double[dimension];

		for (int i = 0; i < xn.length; i++) {
			xn[i] = random.nextDouble() * (end[i] - start[i]) + start[i];
		}
		p.setXn(xn);
		return p;
	}
	
	public NPoint randomPoint(NRectRegion[] region) {
		NPoint p = new NPoint();
		double Size=0.0;
		for(int i=0;i<region.length;i++){
			Size+=region[i].size();
		}
		double T=random.nextDouble()*Size;
		double SumIntegral = 0.0;// 积分值总和
		double PreIntegral = 0.0;
		int temp = 0;
		for (int i = 0; i < region.length; i++) {
			if (SumIntegral < T) {
				PreIntegral = SumIntegral;
				temp = i;
			}
			SumIntegral += region[i].size() ;
		}
		//在temp处生成下一个随机点
		NRectRegion tempRegion=region[temp];
		return randomPoint(tempRegion);
	}
	public NPoint randomPoint(ArrayList<NRectRegion> region) {
		NPoint p = new NPoint();
		double Size=0.0;
		for(int i=0;i<region.size();i++){
			Size+=region.get(i).size();
		}
		double T=random.nextDouble()*Size;
		double SumIntegral = 0.0;// 积分值总和
		double PreIntegral = 0.0;
		int temp = 0;
		for (int i = 0; i < region.size(); i++) {
			if (SumIntegral < T) {
				PreIntegral = SumIntegral;
				temp = i;
			}
			SumIntegral += region.get(i).size() ;
		}
		//在temp处生成下一个随机点
		//System.out.println("at region "+temp+" generate TC.");
		NRectRegion tempRegion=region.get(temp);
		return randomPoint(tempRegion);
	}
	public NPoint randomPoint(double min,double max){
		//一维模拟程序
		double value= random.nextDouble() * (max- min) + min;
		NPoint p=new NPoint(new double[]{value});
		return p;
	}
}
