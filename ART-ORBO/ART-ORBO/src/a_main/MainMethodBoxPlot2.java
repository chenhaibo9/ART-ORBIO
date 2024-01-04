package a_main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
import util.file.FileUtils;

public class MainMethodBoxPlot2 {
	// TODO 完成参数化
	public static void main(String[] args) throws Exception {
		int times = 100;
		double failrates[] = { 0.0015 };
		int d = 4;
		String path="C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\boxplot\\"+d+"维"+failrates[0];
		//File tempFile=new File("C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\boxplot");
		for (int j = 0; j < failrates.length; j++) {
			double failrate = failrates[j];
			//int d = 2;
			
			
			ZeroOneCreator dataCreator = new ZeroOneCreator();
			double[] min = dataCreator.minCreator(d);
			double[] max = dataCreator.maxCreator(d);
			FailurePattern failurePattern = new BlockPattern();
			failurePattern.fail_rate = failrate;
			failurePattern.min = min;
			failurePattern.max = max;
			failurePattern.dimension = d;

			//rt
			File rtf=FileUtils.createNewFile(path, "all.txt");
			BufferedWriter writer=get(rtf);
			int fm = 0;
			long startTime = System.currentTimeMillis();
			writer.write("rt=[");
			for (int i = 0; i < times; i++) {
				RT_ND rt = new RT_ND(min, max, new Random(i * 3), failurePattern);
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			long endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			//writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
		
			//rrt
			//File rrtf=FileUtils.createNewFile(path, "rrt.txt");
			// writer=get(rrtf);
			double r=0.75;
			fm=0;
			startTime = System.currentTimeMillis();
			writer.write("rrt=[");
			for (int i = 0; i < times; i++) {
				RRT_ND rt = new RRT_ND(min, max,  failurePattern,new Random(i * 3),r);
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			writer.write("];");
			writer.newLine();
			//writer.close();
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//fscs
			//File fscs=FileUtils.createNewFile(path, "fscs.txt");
			 //w//riter=get(fscs);
			fm=0;
			int s=10;
			startTime = System.currentTimeMillis();
			writer.write("fscs=[");
			for (int i = 0; i < times; i++) {
				FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern,new Random(i * 3));
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			//writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
			//art_b
			//File artbf=FileUtils.createNewFile(path, "artb.txt");
			// writer=get(artbf);
			fm=0;
			startTime = System.currentTimeMillis();
			writer.write("artb=[");
			for (int i = 0; i < times; i++) {
				ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.newLine();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			//writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_rp
			//File artrpf=FileUtils.createNewFile(path, "artrp.txt");
			 //writer=get(artrpf);
			fm=0;
			writer.write("artrp=[");
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_tp
			//File arttpf=FileUtils.createNewFile(path, "arttp.txt");
			 //writer=get(arttpf);
			fm=0;
			startTime = System.currentTimeMillis();
			writer.write("arttp=[");
			for (int i = 0; i < times; i++) {
				ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern,new Random(i * 3));
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			//writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_tpp
			//File arttppf=FileUtils.createNewFile(path, "arttpp.txt");
			 //writer=get(arttppf);
			fm=0;
			int k=10;
			startTime = System.currentTimeMillis();
			writer.write("arttpp=[");
			for (int i = 0; i < times; i++) {
				ART_TPP rt = new ART_TPP(min, max, new Random(i * 3),failurePattern,k);
				int temp = rt.run();
				fm += temp;
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			//writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//my method
			//1dimension
			//File lazf=FileUtils.createNewFile(path, "laz.txt");
			// writer=get(lazf);
			r=0.75;
			fm=0;
			writer.write("laz=[");
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
//				/min, max, 0.75, pattern, new Random(i * 3+v*5)
				int temp2=0;
				int timessss=1;
				for(int l=0;l<timessss;l++){
				RRTtpND_H rt = new RRTtpND_H(min, max, r, failurePattern, new Random(i * 3));
				int temp = rt.run();
				temp2+=temp;
				}
				fm += temp2/(double)timessss;
				writer.write((temp2/(double)timessss)+"");
				writer.newLine();
				writer.flush();
			}
			endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			writer.close();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
		}
	}
	public static BufferedWriter get(File f) throws Exception{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"));
	}
}
