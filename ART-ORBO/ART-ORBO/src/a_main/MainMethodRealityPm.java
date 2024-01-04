package a_main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.RealityClasses;
import util.data.ZeroOneCreator;
import util.file.FileUtils;

public class MainMethodRealityPm {
	public static BufferedWriter get(File f) throws Exception {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	}

	public static void main(String[] args) throws Exception {
		Class[] classes = RealityClasses.get();
		String path = "C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\大论文\\图表\\补充数据\\Pm1";

		File rtf = FileUtils.createNewFile(path, "all2.txt");
		BufferedWriter writer = get(rtf);

		for (int j = 0; j < 5; j++) {
			Class tempClass = classes[j];

			System.out.println("now class:" + tempClass.getName());

			writer.write("now class:" + tempClass.getName());
			writer.newLine();

			double failureRate = tempClass.getDeclaredField("failureRate").getDouble(null);
			double[] min = (double[]) tempClass.getDeclaredField("min").get(null);
			double[] max = (double[]) tempClass.getDeclaredField("max").get(null);
			int Dimension = (int) tempClass.getDeclaredField("Dimension").get(null);

			FailurePattern failurePattern = new RealityFailPattern(tempClass.newInstance().getClass().getSimpleName());
			failurePattern.fail_rate = failureRate;
			failurePattern.min = min;
			failurePattern.max = max;
			failurePattern.dimension = Dimension;
			int n = 1000;
			int pm = 0;

			// rt
			RT_ND rt1 = new RT_ND(min, max, new Random(3), failurePattern);
			System.out.println(rt1.pm());
			writer.write("rt " + rt1.pm());
			// rrt
			pm = 0;
			double R = 0.75;
			if (Dimension > 1) {
				R = 1.5;
			}
			for (int i = 0; i < n; i++) {
				RRT_ND rt = new RRT_ND(min, max, failurePattern, new Random(i * 5), R);
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("rrt " + (pm / (double) n));
			// fscs
			pm = 0;
			int s = 10;
			for (int i = 0; i < n; i++) {
				FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern, new Random(i * 3));
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("fscs " + (pm / (double) n));
			// b
			pm = 0;
			for (int i = 0; i < n; i++) {
				ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3), failurePattern);
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("b " + (pm / (double) n));
			// rp
			pm = 0;
			for (int i = 0; i < n; i++) {
				ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3), failurePattern);
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("rp " + (pm / (double) n));
			// tpp
			pm = 0;
			for (int i = 0; i < n; i++) {
				ART_TPP rt = new ART_TPP(min, max, new Random(i * 3), failurePattern, 10);
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("tpp " + (pm / (double) n));
			// tp
			pm = 0;
			for (int i = 0; i < n; i++) {
				ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern, new Random(i * 3 + 3));
				if (rt.pm()) {
					pm++;
				}
			}
			System.out.println(pm / (double) n);
			writer.write("tp " + (pm / (double) n));
			// laz
			if (Dimension == 1) {
				pm = 0;
				for (int i = 0; i < n; i++) {
					RRTtpND_H rt = new RRTtpND_H(min, max, 0.75, failurePattern, new Random(i * 3));
					if (rt.pm()) {
						pm++;
					}
				}
				System.out.println(pm / (double) n);
				writer.write("laz " + (pm / (double) n));
			}

			/**/
		}
		writer.close();
	}
}
