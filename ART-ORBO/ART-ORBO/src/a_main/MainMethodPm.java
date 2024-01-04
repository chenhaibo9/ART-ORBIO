package a_main;

import java.util.Random;

import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.BlockPattern;
import test.simulations.art_b.ART_B_ND;
import test.simulations.art_rp.ART_RP_ND;
import test.simulations.art_tp._ND.ART_TP_ND;
import test.simulations.art_tpp.ART_TPP;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rrt.RRT_ND;
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.ZeroOneCreator;

public class MainMethodPm {
	public static void main(String[] args) {
		int d=4;
		double failrate=0.001;
		
		ZeroOneCreator dataCreator = new ZeroOneCreator();
		double[] min = dataCreator.minCreator(d);
		double[] max = dataCreator.maxCreator(d);
		FailurePattern failurePattern = new BlockPattern();
		failurePattern.fail_rate = failrate;
		
		int n = 1000;
		int pm = 0;
		
		//rt
		RT_ND rt1=new RT_ND(min,max,new Random(3),failurePattern);
		System.out.println(rt1.pm());
		
		
		//rrt
		pm=0;
		double R = 0.75;
		if (d > 1) {
			R = 1.5;
		}
		
		for (int i = 0; i < n; i++) {
			RRT_ND rt = new RRT_ND(min, max, failurePattern, new Random(i * 5), R);
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		
		//fscs
		pm=0;
		int s=10;
		for (int i = 0; i < n; i++) {
			FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern, new Random(i * 3));
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		
		//b
		pm=0;
		for (int i = 0; i < n; i++) {
			ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3), failurePattern);
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		
		//rp
		pm=0;
		for (int i = 0; i < n; i++) {
			ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3), failurePattern);
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		//tpp
		pm=0;
		for (int i = 0; i < n; i++) {
			ART_TPP rt = new ART_TPP(min, max, new Random(i * 3), failurePattern, 10);
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		//tp
		pm=0;
		for (int i = 0; i < n; i++) {
			ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern, new Random(i * 3 + 3));
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
		
		//laz
		pm=0;
		for (int i = 0; i < n; i++) {
			RRTtpND_H rt = new RRTtpND_H(min, max, 0.75, failurePattern, new Random(i * 3));
			if (rt.pm()) {
				pm++;
			}
		}
		System.out.println(pm/(double)n);
	}
}
