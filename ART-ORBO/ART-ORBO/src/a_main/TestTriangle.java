package a_main;

import java.util.ArrayList;
import java.util.Random;

import coverage.factory.CoverageFactoryFor3D;
import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rt.RT_ND;
import tested.Triangle;

public class TestTriangle {
	public static void main(String[] args) {
		int s = 10;
		int times = 400000;
		double[] min = { 0, 0, 0 };
		double[] max = { 10000, 10000, 10000 };
		FailurePattern f = new FailurePattern() {

			@Override
			public void showFailurePattern() {

			}

			@Override
			public boolean isCorrect(NPoint p) {
				if (p.getXn().length != 3) {
					System.out.println("error");
					return false;
				} else {
					return Triangle.isCorrect((int) p.getXn()[0], (int) p.getXn()[1], (int) p.getXn()[2]);
				}
			}

			@Override
			public void genFailurePattern() {

			}
		};

		// rt
		int fm = 0;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			RT_ND rt = new RT_ND(min, max, new Random(i * 3), f);
			int temp = rt.run();
			fm += temp;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("RT Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));

		// fscs
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			FSCS_ND rt = new FSCS_ND(min, max, s, f, new Random(i * 3));
			int temp = rt.run();
			//rt.em();
			fm += temp;
		}
		
	
		endTime = System.currentTimeMillis();
		System.out.println("FSCS Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));

		//generate TestCase
		ArrayList<NPoint> rtTests=new ArrayList<>();
		ArrayList<NPoint> fscsTests=new ArrayList<>();
		
		CoverageFactoryFor3D coverage=new CoverageFactoryFor3D("", null);
		coverage.setClassName("tested.Triangle");
		
		RT_ND rt = new RT_ND(min, max, new Random(3), f);
		FSCS_ND art = new FSCS_ND(min, max, s, f, new Random(3));
		
		int count1=0,count2=0;
		int count3=0;
		for(int i=0;i<5000;i++){
			NPoint temp=rt.generateNextTC();
			NPoint temp2=art.generateNextTC();
			double coverage1=coverage.execute((int)temp.getXn()[0], (int)temp.getXn()[1], (int)temp.getXn()[2]);
			double coverage2=coverage.execute((int)temp2.getXn()[0], (int)temp2.getXn()[1], (int)temp2.getXn()[2]);
			String show1=coverage1>=coverage2?"*":"";
			String show2=coverage1>coverage2?"":"*";
			count1=coverage1>=coverage2?count1+1:count1;
			count2=coverage1>=coverage2?count2:count2+1;
			
			//System.out.println("RT coverage:"+coverage1+show1+", FSCS Coverage:"+coverage2+show2);
			
			rtTests.add(temp);
			fscsTests.add(temp2);
		}
		System.out.println(count1+":"+count2);
		
		
		//test their coverage
		
	}
	public  static void testCoverage(){
		
	}
}
