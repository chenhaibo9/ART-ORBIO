package a_main;

import java.util.Random;

import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.BlockPattern;
import datastructure.failurepattern.impl.RealityFailPattern;
import test.simulations.art_b.ART_B_ND;
import test.simulations.art_rp.ART_RP_ND;
import test.simulations.art_tp._ND.ART_TP_ND;
import test.simulations.art_tpp.ART_TPP;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rrt.RRT_ND;
import test.simulations.rrttp.hilbert.RRTtp1D;
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.RealityClasses;
import util.data.ZeroOneCreator;

public class MainMethodReality {
	// TODO 完成参数化
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		int times = 2000;
		Class[] classes=RealityClasses.get();
		for (int j = 0; j < classes.length; j++) {
			
			Class tempClass=classes[j];
			System.out.println("now class:"+tempClass.getName());
			
			double failureRate=tempClass.getDeclaredField("failureRate").getDouble(null);
			double[] min=(double[] )tempClass.getDeclaredField("min").get(null);
			double[] max=(double[]) tempClass.getDeclaredField("max").get(null);
			int Dimension=(int)tempClass.getDeclaredField("Dimension").get(null);
			
			FailurePattern failurePattern = new RealityFailPattern(tempClass.newInstance().getClass().getSimpleName());
			failurePattern.fail_rate = failureRate;
			failurePattern.min = min;
			failurePattern.max = max;
			failurePattern.dimension = Dimension;

			//rt
			int fm = 0;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				RT_ND rt = new RT_ND(min, max, new Random(i * 3), failurePattern);
				int temp = rt.run();
				fm += temp;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("ratio:"+((fm / (double) times)/(1.0/failureRate)) );
			//System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			/*
			//rrt
			double r=1.5;
			if(Dimension==1) {
				r=0.75;
			}else {
				r=1.5;
			}
			fm=0;
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				RRT_ND rt = new RRT_ND(min, max,  failurePattern,new Random(i * 3),r);
				int temp = rt.run();
				fm += temp;
			}
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//fscs
			fm=0;
			int s=10;
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern,new Random(i * 3));
				int temp = rt.run();
				fm += temp;
			}
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
			//art_b
			fm=0;
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				fm += temp;
			}
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_rp
			fm=0;
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				fm += temp;
			}
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
			//art_tpp
			fm=0;
			int k=10;
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_TPP rt = new ART_TPP(min, max, new Random(i * 3),failurePattern,k);
				int temp = rt.run();
				fm += temp;
			}
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
*/			
			//art_tp
/*			int fm=0;
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern,new Random(i * 3));
				int temp = rt.run();
				fm += temp;
			}
			long endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//my method
			//1dimension
		 int fm=0;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			RRTtpND_H rt = new RRTtpND_H(min, max, 0.75,failurePattern,new Random(i * 3));
			int temp = rt.run();
			fm += temp;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
		*/
		}
	}

}
