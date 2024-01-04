package test.Reality.art_orbo_reality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Fault_10d.CalGCD;
import Fault_5d.CalDay;
import Fault_6d.Complex;
import Fault_6d.Triangle;
import Fault_8d.Line;
//import Fault_12d.*;
import datastructure.ND.NPoint;
import datastructure.ND.NRectRegion;
import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.RealityFailPattern;
import test.ART;
import test.simulations.art_orb.ComplexRegion;
import testPrograms.Fault_10d.NearestDistance;
import testPrograms.Fault_8d.TwoLinesPos;
import tested.*;


public class ORB_FSCS_ND_Real extends ART {
	public static void main(String[] args) {
		//airy beTested=new airy();
	    //tanh beTested=new tanh();
	    //erfcc beTested=new erfcc();
		//probks beTested=new probks();
		//bessj0 beTested=new bessj0();
		//bessj beTested=new bessj();
		//gammq beTested=new gammq();
		//sncndn beTested=new sncndn();
		//golden beTested=new golden();
		//plgndr beTested=new plgndr();
		//cel beTested=new cel();
		//el2 beTested=new el2();
		//Tcas beTested = new Tcas();
		//Select beTested = new Select();
		//Triangle beTested = new Triangle();
		//Line beTested = new Line();
		CalDay beTested = new CalDay();
		//CalGCD beTested = new CalGCD();
		//Complex beTested = new Complex();
		//TwoLinesPos beTested = new TwoLinesPos();
		//NearestDistance beTested = new NearestDistance();
		//
		
		double[] min = beTested.min;
		double[] max = beTested.max;
		int times = 3000;
		int fm = 0;
		FailurePattern pattern = new RealityFailPattern(beTested.getClass().getSimpleName());
		pattern.min = min;
		pattern.max = max;
		pattern.fail_rate = beTested.failureRate;
		pattern.dimension = beTested.Dimension;
		
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			//long startTime = System.currentTimeMillis();

			ORB_FSCS_ND_Real test = new ORB_FSCS_ND_Real(min, max, pattern, new Random(i * 5 + 3));
			int temp = test.run();
			fm += temp;
			//long endTime = System.currentTimeMillis();
			//System.out.println(endTime - startTime); 
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Fmeasure: "+ fm / (double) times);
		System.out.println("Time: "+(endTime1 - startTime1) / (double) times);
	}
	
	ArrayList<ComplexRegion> regions = new ArrayList<>();
	private double offsetRatio=0.8;
	private int k=10;
	
	public ORB_FSCS_ND_Real(double[] min, double[] max, FailurePattern failurePattern, Random random) {
		super(min, max, random, failurePattern);
	
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
				//break;   /// Hilary added
			}
		}
		return flag;
	}

	public NPoint randomTC() {
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

//	public NPoint randomTC(ComplexRegion region) {
////		double radius = calculateRadius(region.region);
//		NPoint result = randomTC(region.region);
//		boolean flag = true;
//		while (flag) {
//			flag = false;
//			result = randomTC(region.region);
//
//			// Ã¦Å½â€™Ã©â„¢Â¤Ã¥Å’ÂºÃ¥Å¸Å¸Ã¦ËœÂ¯Ã¥Å“â€ 
//			// Ã¨Â®Â¡Ã§Â®â€”Ã¨Â·ï¿½Ã§Â¦Â»
//			double[] tested = region.pointInRegion.getXn();
//			double distance = 0;
//			double[] untested = result.getXn();
//			for (int j = 0; j < this.dimension; j++) {
//				distance += Math.pow((tested[j] - untested[j]), 2);
//			}
//			distance = Math.sqrt(distance);
//			if (distance < radius) {
//				flag = true;
//				// break;
//			}
//			/*
//			 * //Ã¦Å½â€™Ã©â„¢Â¤Ã¥Å’ÂºÃ¥Å¸Å¸Ã¦ËœÂ¯Ã¦Â­Â£Ã¦â€“Â¹Ã¥Â½Â¢ if(Math.abs(p.p-tests.get(i).p)<radius){
//			 * if(Math.abs(p.q-tests.get(i).q)<radius){ flag=true; } }
//			 */
//		}
//		return result;
//	}

//	public NPoint randomTC(NRectRegion region) {
//		double[] start = region.getStart().getXn();
//		double[] end = region.getEnd().getXn();
//		double[] xn = new double[this.dimension];
//		for (int i = 0; i < xn.length; i++) {
//			xn[i] = random.nextDouble() * (end[i] - start[i]) + start[i];
//		}
//		NPoint result = new NPoint(xn);
//		result.setDimension(this.dimension);
//		return result;
//	}

	public int run() {
		int count = 0;

		// first
		NPoint p = randomTC();
		ComplexRegion region = new ComplexRegion();
		region.region = new NRectRegion(new NPoint(min), new NPoint(max));
		region.pointInRegion = p;
		regions.add(region);
		if (!this.failPattern.isCorrect(p)) {
			return 1;
		}
		count++;

		// second
		NPoint t2 = randomTC();
		splitRegion(p, t2, regions.get(0));
		regions.remove(0);

		while (this.failPattern.isCorrect(t2)) {
			count++;

			// Ã¥â€ ï¿½Ã§â€�Å¸Ã¦Ë†ï¿½Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¦Âµâ€¹Ã¨Â¯â€¢Ã§â€�Â¨Ã¤Â¾â€¹t2
			int index = findMaxRegion();
			ComplexRegion maxRegion = regions.get(index);
			ComplexRegion offsetRegion=generateOffset(maxRegion);
			t2 = randomTC(offsetRegion,maxRegion);

			if (!this.failPattern.isCorrect(t2)) {
				break;
			}
			// split region
			regions.remove(index);
			splitRegion(maxRegion.pointInRegion, t2, maxRegion);
		}
		return count;
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
		// Ã¦â€°Â¾Ã¥Ë†Â°Ã¦Å“â‚¬Ã¥Â¤Â§Ã§Å¡â€žÃ¨Â¾Â¹
		double maxBian = 0.0;
		int maxIndex = 0;
		for (int i = 0; i < region.region.getStart().getXn().length; i++) {
			if (region.region.getEnd().getXn()[i] - region.region.getStart().getXn()[i] > maxBian) {
				maxBian = region.region.getEnd().getXn()[i] - region.region.getStart().getXn()[i];
				maxIndex = i;
			}
		}

		// Ã¤Â¸â‚¬Ã¥Ë†â€ Ã¤Â¸ÂºÃ¤ÂºÅ’
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
		// if (maxIndex == 0) {// xÃ¨Â½Â´Ã©â€¢Â¿
		// region1.setStart(region.region.getStart());
		// region1.setEnd(new NPoint(new double[] { mid[0],
		// region.region.getEnd().getXn()[1] }));
		//
		// region2.setStart(new NPoint(new double[] { mid[0],
		// region.region.getStart().getXn()[1] }));
		// region2.setEnd(region.region.getEnd());
		// } else if (maxIndex == 1) {// yÃ¨Â½Â´Ã©â€¢Â¿
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
	public NPoint generateNextTC() {
		// TODO Auto-generated method stub
		return null;
	}
}
