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
 */
public class ORB_FSCS_ND extends ART {
	public static void main(String[] args) {
		int dimension = 7;
		ZeroOneCreator dataCreator = new ZeroOneCreator();
		double[] min = dataCreator.minCreator(dimension);
		double[] max = dataCreator.maxCreator(dimension);
		int times = 5000;
		int fm = 0;

		// double [] frate = {0.01, 0.005, 0.002, 0.001, 0.0005};
		double[] frate = { 0.0005, 0.0002 };
		for (int f = 0; f < frate.length; f++) {

			// FailurePattern pattern = new BlockPattern();
			// FailurePattern pattern = new StripPatternIn2D();
			FailurePattern pattern = new PointPatternIn2D();

			pattern.fail_rate = frate[f];

			long startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {

				ORB_FSCS_ND test = new ORB_FSCS_ND(min, max, pattern, new Random(i * 5 + 3));
				int temp = test.run();
				fm += temp;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Fm for " + frate[f] + " failure rate: " + "Fm:" + (fm / (double) times) + " time:"
					+ ((endTime - startTime) / (double) times));
			// System.out.println(fm / (double) times);
			// System.out.println("Time: " + (endTime - startTime) / (double)
			// times);
		}
	}

	private ArrayList<ComplexRegion> regions = new ArrayList<>();
	// offset Ratio
	private double offsetRatio = 0.8;
	private int k = 10;

	public ORB_FSCS_ND(double[] min, double[] max, FailurePattern failurePattern, Random random) {
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
			}
		}
		return flag;
	}

	// public ArrayList<NPoint> judge
	public NPoint randomTC(ComplexRegion offsetRegion, ComplexRegion originRegion) {
		// judge how many test cases in this region and return these test cases;
		ArrayList<NPoint> alreadyPointsInOffsetRegion = new ArrayList<>();
		for (int i = 0; i < regions.size(); i++) {
			if (isPointInRegion(offsetRegion.region, regions.get(i).pointInRegion)) {
				alreadyPointsInOffsetRegion.add(regions.get(i).pointInRegion);
			}
		}
		// generate k test cases in origin region
		double maxDistance = -1.0;
		NPoint bestCandidate = null;
		for (int i = 0; i < k; i++) {
			NPoint candidate = randomCreator.randomPoint(originRegion.region);
			// Ã¨Â®Â¡Ã§Â®â€”Ã¤Â¸Â¤Ã¤Â¸ÂªÃ§â€šÂ¹Ã§Å¡â€žÃ¨Â·ï¿½Ã§Â¦Â»
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

		// first
		NPoint p = randomCreator.randomPoint();
		// System.out.println(p);
		ComplexRegion region = new ComplexRegion();
		region.region = new NRectRegion(new NPoint(min), new NPoint(max));
		region.pointInRegion = p;
		regions.add(region);
		if (!failPattern.isCorrect(p)) {
			return 1;
		}
		count++;

		// second
		NPoint t2 = randomCreator.randomPoint();
		// System.out.println(t2);
		splitRegion(p, t2, regions.get(0));
		regions.remove(0);

		while (failPattern.isCorrect(t2)) {
			// while (count < 999) {
			count++;

			// Ã¥â€ ï¿½Ã§â€�Å¸Ã¦Ë†ï¿½Ã¤Â¸â‚¬Ã¤Â¸ÂªÃ¦Âµâ€¹Ã¨Â¯â€¢Ã§â€�Â¨Ã¤Â¾â€¹t2
			int index = findMaxRegion();
			ComplexRegion maxRegion = regions.get(index);

			// generate offset
			ComplexRegion offsetRegion = generateOffset(maxRegion);
			t2 = randomTC(offsetRegion, maxRegion);
			// System.out.println(t2);
			if (!failPattern.isCorrect(t2)) {
				break;
			}
			// split region
			regions.remove(index);
			splitRegion(maxRegion.pointInRegion, t2, maxRegion);
		}
		return count;
	}

	public ComplexRegion generateOffset(ComplexRegion region) {
		double[] regionStart = region.region.getStart().getXn();
		double[] regionEnd = region.region.getEnd().getXn();
		double[] newRegionStart = new double[regionStart.length];
		double[] newRegionEnd = new double[regionEnd.length];
		for (int i = 0; i < regionStart.length; i++) {
			double length = offsetRatio * (regionEnd[i] - regionStart[i]);
			newRegionStart[i] = regionStart[i] - length > this.min[i] ? regionStart[i] - length : this.min[i];
			newRegionEnd[i] = regionEnd[i] + length < this.max[i] ? regionEnd[i] + length : this.max[i];
		}
		ComplexRegion offsetRegion = new ComplexRegion();
		offsetRegion.region = new NRectRegion(new NPoint(newRegionStart), new NPoint(newRegionEnd));
		offsetRegion.pointInRegion = region.pointInRegion;
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

		//// Ã¤ÂºÅ’Ã§Â»Â´
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
		// region1.setEnd(new NPoint(new double[] {
		//// region.region.getEnd().getXn()[0],
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
