package RealityFailureRate;

import java.util.Random;

import org.junit.Test;

import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.BlockPattern;
import test.simulations.rrt.RRT_ND;

public class Testbessj0 {


	/*@Test
	public void testFailureRate(){
		long times=999999999;
		//long times2=999999999999999999
		long count=0;
		int error=0;
		MersenneTwisterFast random2=new MersenneTwisterFast(3);
		Random random=new Random(3);
		while(count<times){
			double a=random.nextDouble()*(600000)-300000;
			if(!TestProgram.test_bessj0(a)){
				error++;
			}
			count++;
		}
		System.out.println(error/times);
	}
	
	@Test
	public void testFailureRate2(){
		int times=99999999;
		//long times2=999999999999999999
		int count=0;
		int error=0;
		MersenneTwisterFast random2=new MersenneTwisterFast(3);
		//Random random=new Random(3);
		while(count<times){
			double a=random2.nextDouble()*(1000)-500;
			if(!TestProgram.test_tanh(a)){
				error++;
			}
			count++;
		}
		System.out.println(error/(double)times);
	}*/
	//-ea -javaagent:${workspace_loc}/ART/Resource/jacocoagent.jar
	@Test
	public void add(){
		double [] min={0};
		double[] max={1};
		FailurePattern pattern=new BlockPattern();
		pattern.fail_rate=0.01;
		RRT_ND rt=new RRT_ND(min,max,pattern,new Random(3),0.75);
		rt.generateNextTC();
		rt.generateNextTC();
	}
	
}
