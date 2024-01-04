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

public class MainMethodRealityEm {
	// TODO 完成参数化
	public static void main(String[] args)
			throws Exception, IllegalAccessException, NoSuchFieldException, SecurityException, InstantiationException {
		int times = 1;
		int time2 = 1000;
		int Count = 2000;//测试用例个数
		Class[] classes = RealityClasses.get();
		String path = "C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\大论文\\图表\\补充数据\\Em2";

		for (int j = 0; j < classes.length; j++) {

			// =SUM(A2:GS2)/((ROW(GT2)-1)*500)

			Class tempClass = classes[j];

			File rtf = FileUtils.createNewFile(path,
					Count + "_" + (j + 1) + "_" + tempClass.getName().split("\\.")[1] + ".txt");
			BufferedWriter writer = get(rtf);

			System.out.println("now class:" + tempClass.getName());

			// writer.write("now class:" + tempClass.getName());
			// writer.newLine();

			double failureRate = tempClass.getDeclaredField("failureRate").getDouble(null);
			double[] min = (double[]) tempClass.getDeclaredField("min").get(null);
			double[] max = (double[]) tempClass.getDeclaredField("max").get(null);
			int Dimension = (int) tempClass.getDeclaredField("Dimension").get(null);

			FailurePattern failurePattern = new RealityFailPattern(tempClass.newInstance().getClass().getSimpleName());
			failurePattern.fail_rate = failureRate;
			failurePattern.min = min;
			failurePattern.max = max;
			failurePattern.dimension = Dimension;

			// rt
			for (int i = 0; i < times; i++) {
				int temp2 = 0;
				for (int k = 0; k < time2; k++) {
					RT_ND rt = new RT_ND(min, max, new Random(k * 3), failurePattern);
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + (temp2 / (double) time2));
				writer.newLine();
				writer.flush();
			}

			// rrt
			double r = 1.5;
			if (Dimension == 1) {
				r = 0.75;
			} else {
				r = 1.5;
			}
			for (int i = 0; i < times; i++) {
				int temp2 = 0;
				for (int k = 0; k < time2; k++) {
					RRT_ND rt = new RRT_ND(min, max, failurePattern, new Random(k * 3), r);
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + (temp2 / (double) time2));
				writer.newLine();
				writer.flush();
			}

			// fscs
			int s = 10;
			for (int i = 0; i < times; i++) {
				int temp2 = 0;
				for (int k = 0; k < time2; k++) {
					FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern, new Random(k * 3));
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" +temp2 / ((double) time2));
				writer.newLine();
				writer.flush();
			}

			// art_b

			for (int i = 0; i < times; i++) {
				int temp2 = 0;
				for (int k = 0; k < time2; k++) {
					ART_B_ND rt = new ART_B_ND(min, max, new Random(k * 3), failurePattern);
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + temp2 / ((double) time2));
				writer.newLine();
				writer.flush();
			}

			// art_rp

			for (int i = 0; i < times; i++) {
				int temp2 = 0;
				for (int k = 0; k < time2; k++) {
					ART_RP_ND rt = new ART_RP_ND(min, max, new Random(k * 3), failurePattern);
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + temp2 / ((double) time2));
				writer.newLine();
				writer.flush();
			}

			// art_tpp
			int k = 10;
			for (int i = 0; i < times; i++) {

				int temp2 = 0;
				for (int m = 0; m < time2; m++) {
					ART_TPP rt = new ART_TPP(min, max, new Random(m * 3), failurePattern, k);
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + temp2 / ((double) time2));
				writer.newLine();
				writer.flush();
			}

			// art_tp
			for (int i = 0; i < times; i++) {

				int temp2 = 0;
				for (int m = 0; m < time2; m++) {
					ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern, new Random(m * 3));
					rt.emCount = Count;
					int temp = rt.em();
					temp2 += temp;
				}
				writer.write("" + temp2 / ((double) time2));
				writer.newLine();
				writer.flush();
			}

			// my method 1dimension
			if (Dimension == 1) {
				for (int i = 0; i < times; i++) {

					int temp2 = 0;
					for (int m = 0; m< time2; m++) {
						RRTtpND_H rt = new RRTtpND_H(min, max, 0.75, failurePattern, new Random(m * 3));
						rt.emCount = Count;
						int temp = rt.em();
						temp2 += temp;
					}
					writer.write("" + temp2 / ((double) time2));
					writer.newLine();
					writer.flush();
				}
			}
			writer.close();

		}

	}

	public static BufferedWriter get(File f) throws Exception {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	}

}
