package test.simulations.art_orbo;

import datastructure.ND.NPoint;
import datastructure.ND.NRectRegion;
import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.BlockPattern;
import datastructure.failurepattern.impl.PointPatternIn2D;
import datastructure.failurepattern.impl.StripPatternIn2D;
import org.apache.poi.ss.formula.functions.Complex;
import test.ART;
import test.simulations.art_orb.ComplexRegion;
import util.data.ZeroOneCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * Hilary's method
 * */
public class ORB_FSCS_ND_Em extends ART {
	public static void main(String[] args) {
		int dimension = 7;
		ZeroOneCreator dataCreator = new ZeroOneCreator();
		double[] min = dataCreator.minCreator(dimension);
		double[] max = dataCreator.maxCreator(dimension);
		//double[] frate = {0.01, 0.005, 0.002, 0.001, 0.0005, 0.0002, 0.0001 };
		//int [] ems = {500, 1000, 2000, 5000, 10000, 20000};
		int [] ems = {1000, 2000, 3000, 4000, 5000, 6000, 7000};
		//int [] ems = {5000, 6000, 7000};
		for (int f = 0; f < ems.length; f++) {
		int times = 200;
		int fm = 0;
		//int em = 3000;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
		
			//FailurePattern pattern = new BlockPattern();
			//FailurePattern pattern = new StripPatternIn2D();
			FailurePattern pattern = new PointPatternIn2D();
			
			pattern.fail_rate = 0.001;

			ORB_FSCS_ND_Em test = new ORB_FSCS_ND_Em(min, max,  pattern, new Random(i * 5 + 3), ems[f]);
			int temp = test.run();
			fm += temp;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Em for "+ ems[f] + " test cases: " + "Em:" + (fm / (double) times) + " time:" + ((endTime - startTime) / (double) times));
//		System.out.println(fm / (double) times);
//		System.out.println("Time: " + (endTime - startTime) / (double) times);
	}
	}
	private ArrayList<ComplexRegion> regions = new ArrayList<>();
	//offset Ratio
	private double offsetRatio=0.8;
	private int k=10;
	private int em;
	public ORB_FSCS_ND_Em(double[] min, double[] max, FailurePattern failurePattern, Random random, int em) {
		super(min, max, random, failurePattern);
		this.em=em;
	}


	public int findMaxRegion() {
		double maxSize = -1;
		int index = 0;
		for (int i = 0; i < regions.size(); i++) {
			if (regions.get(i).region.size() > maxSize) {
				maxSize = regions.get(i).region.size();
				index = i;
			}
		}
		return index;
	}

	public boolean isPointInRegion(NRectRegion region, NPoint p) {
		boolean flag = true;
		for (int i = 0; i < p.getXn().length; i++) {
			if (p.getXn()[i] < region.getStart().getXn()[i] || p.getXn()[i] > region.getEnd().getXn()[i]) {
				flag = false;
			}
		}
		return flag;
	}
	//public ArrayList<NPoint> judge
	public NPoint randomTC(ComplexRegion offsetRegion,ComplexRegion originRegion) {
		//judge how many test cases in this region and return these test cases;
		ArrayList<NPoint> alreadyPointsInOffsetRegion=new ArrayList<>();
		for(int i=0;i<regions.size();i++){
			if(isPointInRegion(offsetRegion.region,regions.get(i).pointInRegion)){
				alreadyPointsInOffsetRegion.add(regions.get(i).pointInRegion);
			}
		}
		//generate k test cases in origin region
		double maxDistance = -1.0;
		NPoint bestCandidate = null;
		for (int i = 0; i < k; i++) {
			NPoint candidate = randomCreator.randomPoint(originRegion.region);
			// ÃƒÂ¨Ã‚Â®Ã‚Â¡ÃƒÂ§Ã‚Â®Ã¢â‚¬â€�ÃƒÂ¤Ã‚Â¸Ã‚Â¤ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ§Ã¢â‚¬Å¡Ã‚Â¹ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¨Ã‚Â·Ã¯Â¿Â½ÃƒÂ§Ã‚Â¦Ã‚Â»
			double minDistance = Double.MAX_VALUE;

			for (int j = 0; j < alreadyPointsInOffsetRegion.size(); j++) {
				double tempDistance = candidate.distanceToAnotherPoint(alreadyPointsInOffsetRegion.get(j));
				if (tempDistance < minDistance) {
					minDistance = tempDistance;
				}
			}
			if (maxDistance < minDistance) {
				maxDistance = minDistance;
				bestCandidate = candidate;
			}
		}


		return bestCandidate;
	}

	public int run() {
		int count = 0;
		int emCount1=0;

		// first
		NPoint p = randomCreator.randomPoint();
//		System.out.println(p);
		ComplexRegion region = new ComplexRegion();
		region.region = new NRectRegion(new NPoint(min), new NPoint(max));
		region.pointInRegion = p;
		regions.add(region);
		if (!failPattern.isCorrect(p)) {
//			return 1;
			emCount1++;
		}
		count++;

		// second
		NPoint t2 = randomCreator.randomPoint();
		if (!failPattern.isCorrect(p)) {
//			return 1;
			emCount1++;
		}
//		System.out.println(t2);
		splitRegion(p, t2, regions.get(0));
		regions.remove(0);

//		while (failPattern.isCorrect(t2)) {
		while (count < em) {
			count++;

			// ÃƒÂ¥Ã¢â‚¬Â Ã¯Â¿Â½ÃƒÂ§Ã¢â‚¬ï¿½Ã…Â¸ÃƒÂ¦Ã‹â€ Ã¯Â¿Â½ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¤Ã‚Â¸Ã‚ÂªÃƒÂ¦Ã‚ÂµÃ¢â‚¬Â¹ÃƒÂ¨Ã‚Â¯Ã¢â‚¬Â¢ÃƒÂ§Ã¢â‚¬ï¿½Ã‚Â¨ÃƒÂ¤Ã‚Â¾Ã¢â‚¬Â¹t2
			int index = findMaxRegion();
			ComplexRegion maxRegion = regions.get(index);

			//generate offset
			ComplexRegion offsetRegion=generateOffset(maxRegion);
			t2 = randomTC(offsetRegion,maxRegion);
//			System.out.println(t2);
			if (!failPattern.isCorrect(t2)) {
//				break;
				emCount1++;
			}
			// split region
			regions.remove(index);
			splitRegion(maxRegion.pointInRegion, t2, maxRegion);
		}
		return emCount1;
	}
	
	public ComplexRegion generateOffset(ComplexRegion region){
		double[] regionStart=region.region.getStart().getXn();
		double[] regionEnd=region.region.getEnd().getXn();
		double[] newRegionStart=new double[regionStart.length];
		double[] newRegionEnd=new double[regionEnd.length];
		for(int i=0;i<regionStart.length;i++){
			double length=offsetRatio*(regionEnd[i]-regionStart[i]);
			newRegionStart[i]=regionStart[i]-length>this.min[i]?regionStart[i]-length:this.min[i];
			newRegionEnd[i]=regionEnd[i]+length<this.max[i]?regionEnd[i]+length:this.max[i];
		}
		ComplexRegion offsetRegion=new ComplexRegion();
		offsetRegion.region=new NRectRegion(new NPoint(newRegionStart),new NPoint(newRegionEnd));
		offsetRegion.pointInRegion=region.pointInRegion;
		return offsetRegion;
	}
	public void splitRegion(NPoint p, NPoint t2, ComplexRegion region) {
		double[] pn = p.getXn();
		double[] t2n = t2.getXn();

		double[] mid = new double[pn.length];
		for (int i = 0; i < mid.length; i++) {
			mid[i] = (pn[i] + t2n[i]) / 2.0;
		}
		// System.out.println("middle point:"+Arrays.toString(mid));
		// ÃƒÂ¦Ã¢â‚¬Â°Ã‚Â¾ÃƒÂ¥Ã‹â€ Ã‚Â°ÃƒÂ¦Ã…â€œÃ¢â€šÂ¬ÃƒÂ¥Ã‚Â¤Ã‚Â§ÃƒÂ§Ã…Â¡Ã¢â‚¬Å¾ÃƒÂ¨Ã‚Â¾Ã‚Â¹
		double maxBian = 0.0;
		int maxIndex = 0;
		for (int i = 0; i < region.region.getStart().getXn().length; i++) {
			if (region.region.getEnd().getXn()[i] - region.region.getStart().getXn()[i] > maxBian) {
				maxBian = region.region.getEnd().getXn()[i] - region.region.getStart().getXn()[i];
				maxIndex = i;
			}
		}

		// ÃƒÂ¤Ã‚Â¸Ã¢â€šÂ¬ÃƒÂ¥Ã‹â€ Ã¢â‚¬Â ÃƒÂ¤Ã‚Â¸Ã‚ÂºÃƒÂ¤Ã‚ÂºÃ…â€™
		NRectRegion region1 = new NRectRegion();
		NRectRegion region2 = new NRectRegion();

		region1.setStart(region.region.getStart());
		double[] end = Arrays.copyOf(region.region.getEnd().getXn(), region.region.getEnd().getXn().length);
		end[maxIndex] = mid[maxIndex];
		region1.setEnd(new NPoint(end));

		double[] start = Arrays.copyOf(region.region.getStart().getXn(), region.region.getStart().getXn().length);
		start[maxIndex] = mid[maxIndex];
		region2.setStart(new NPoint(start));
		region2.setEnd(region.region.getEnd());

		//// ÃƒÂ¤Ã‚ÂºÃ…â€™ÃƒÂ§Ã‚Â»Ã‚Â´
		// if (maxIndex == 0) {// xÃƒÂ¨Ã‚Â½Ã‚Â´ÃƒÂ©Ã¢â‚¬Â¢Ã‚Â¿
		// region1.setStart(region.region.getStart());
		// region1.setEnd(new NPoint(new double[] { mid[0],
		// region.region.getEnd().getXn()[1] }));
		//
		// region2.setStart(new NPoint(new double[] { mid[0],
		// region.region.getStart().getXn()[1] }));
		// region2.setEnd(region.region.getEnd());
		// } else if (maxIndex == 1) {// yÃƒÂ¨Ã‚Â½Ã‚Â´ÃƒÂ©Ã¢â‚¬Â¢Ã‚Â¿
		// region1.setStart(region.region.getStart());
		// region1.setEnd(new NPoint(new double[] { region.region.getEnd().getXn()[0],
		// mid[1] }));
		//
		// region2.setStart(new NPoint(new double[] {
		// region.region.getStart().getXn()[0], mid[1] }));
		// region2.setEnd(region.region.getEnd());
		// }
		ComplexRegion cr1 = new ComplexRegion();
		cr1.region = region1;
		if (isPointInRegion(region1, p)) {
			cr1.pointInRegion = p;
		} else {
			cr1.pointInRegion = t2;
		}
		ComplexRegion cr2 = new ComplexRegion();
		cr2.region = region2;
		if (isPointInRegion(region2, t2)) {
			cr2.pointInRegion = t2;
		} else {
			cr2.pointInRegion = p;
		}
		regions.add(cr1);
		regions.add(cr2);
	}

	@Override
	public int em() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NPoint generateNextTC() {
		// TODO Auto-generated method stub
		return null;
	}
}
