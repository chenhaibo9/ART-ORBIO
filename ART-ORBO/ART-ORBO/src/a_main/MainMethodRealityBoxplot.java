package a_main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.RealityFailPattern;
import test.simulations.art_b.ART_B_ND;
import test.simulations.art_rp.ART_RP_ND;
import test.simulations.art_tp._ND.ART_TP_ND;
import test.simulations.art_tpp.ART_TPP;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rrt.RRT_ND;
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.RealityClasses;
import util.file.FileUtils;

public class MainMethodRealityBoxplot {
	// TODO 完成参数化
	public static void main(String[] args) throws Exception, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		int times = 100;
		Class[] classes=RealityClasses.get();
		String path="C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\boxplot\\";
		//File tempfile=new File(path+""+tempClass.getName());
		//tempfile.mkdir();
		//System.out.println("now class:"+tempClass.getName());
		File rtf=FileUtils.createNewFile(path, "LAZRealityAll.txt");
		BufferedWriter writer=get(rtf);
		for (int j =0; j < classes.length; j++) {
			
			//=SUM(A2:GS2)/((ROW(GT2)-1)*500)
			
			Class tempClass=classes[j];
			
			
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
/*			int fm = 0;
			long startTime = System.currentTimeMillis();
			writer.write("rt=[");
			for (int i = 0; i < times; i++) {
				RT_ND rt = new RT_ND(min, max, new Random(i * 3), failurePattern);
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			long endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
		
			//rrt
			double r=1.5;
			if(Dimension==1) {
				r=0.75;
			}else {
				r=1.5;
			}
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
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//fscs
			fm=0;
			int s=10;
			startTime = System.currentTimeMillis();
			writer.write("fscs=[");
			for (int i = 0; i < times; i++) {
				FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern,new Random(i * 3));
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			writer.write("];");
			writer.newLine();
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
			//art_b
			fm=0;
			startTime = System.currentTimeMillis();
			writer.write("artb=[");
			for (int i = 0; i < times; i++) {
				ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			writer.write("];");
			writer.newLine();
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_rp
			fm=0;
			startTime = System.currentTimeMillis();
			writer.write("artrp=[");
			for (int i = 0; i < times; i++) {
				ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3),failurePattern);
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			writer.write("];");
			writer.newLine();
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
			//art_tpp
			fm=0;
			int k=10;
			writer.write("arttpp=[");
			startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_TPP rt = new ART_TPP(min, max, new Random(i * 3),failurePattern,k);
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			writer.write("];");
			writer.newLine();
			endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			//art_tp
			 fm=0;
			 writer.write("arttp=[");
			 startTime = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern,new Random(i * 3));
				int temp = rt.run();
				writer.write(temp+"");
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			writer.write("];");
			writer.newLine();
			 endTime = System.currentTimeMillis();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
		*/
			//my method
			//1dimension
			int fm=0;
			long startTime = System.currentTimeMillis();
			writer.write(tempClass.getSimpleName());
			writer.newLine();
			writer.write("laz=[");
			writer.newLine();
			for (int i = 0; i < times; i++) {
				RRTtpND_H rt = new RRTtpND_H(min, max,0.75,failurePattern, new Random(i * 3));
				int temp = rt.run();
				writer.write(temp);
				writer.newLine();
				writer.flush();
				fm += temp;
			}
			long endTime = System.currentTimeMillis();
			writer.write("];");
			writer.newLine();
			System.out.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
			
			
		}
		writer.close();
	}
	public static BufferedWriter get(File f) throws Exception{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"));
	}

}
