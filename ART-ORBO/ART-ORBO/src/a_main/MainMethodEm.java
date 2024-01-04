package a_main;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.Random;

import datastructure.failurepattern.FailurePattern;
import datastructure.failurepattern.impl.BlockPattern;
import datastructure.failurepattern.impl.PointPattern;
import datastructure.failurepattern.impl.PointPatternIn1D;
import datastructure.failurepattern.impl.PointPatternIn2D;
import datastructure.failurepattern.impl.PointPatternIn3D;
import datastructure.failurepattern.impl.PointPatternIn4D;
import datastructure.failurepattern.impl.StripPattern;
import datastructure.failurepattern.impl.StripPatternIn2D;
import test.simulations.art_b.ART_B_ND;
import test.simulations.art_rp.ART_RP_ND;
import test.simulations.art_tp._ND.ART_TP_ND;
import test.simulations.art_tpp.ART_TPP;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rrt.RRT_ND;
import test.simulations.rrttp.hilbert.RRTtp1D;
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.ZeroOneCreator;
import util.file.FileUtils;

public class MainMethodEm {
	// TODO 完成参数化
	public static void main(String[] args) throws Exception {
		double failrates[] = { 0.000005, 0.000001 };
		String path = "C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\em\\simulation";

		for (int j = 0; j < failrates.length; j++) {
			double failrate = failrates[j];
			System.out.println("failure rate:" + failrate);

			int d = 1;

			File rtf = FileUtils.createNewFile(path, d + "维-" + failrate + "all.txt");
			BufferedWriter writer = get(rtf);

			ZeroOneCreator dataCreator = new ZeroOneCreator();
			double[] min = dataCreator.minCreator(d);
			double[] max = dataCreator.maxCreator(d);

			// FailurePattern failurePattern = new BlockPattern();
			// FailurePattern failurePattern=new StripPattern();
			FailurePattern failurePattern = new PointPatternIn4D();
			failurePattern.fail_rate = failrate;
			failurePattern.min = min;
			failurePattern.max = max;
			failurePattern.dimension = d;

			// rt
			double[] em = RT_ND.testEm(d, failrate);
			writerEm("rt",em, writer);
			// rrt
			em = RRT_ND.testEm(d, failrate);
			writerEm("rrt",em, writer);
			// fscs
			//em = FSCS_ND.testEm(d, failrate);
			writerEm("fscs",em, writer);
			// art_b
			// em=ART_B_ND.testEm(d, failrate);
			// art_rp
			em = ART_RP_ND.testEm(d, failrate);
			writerEm("rp",em, writer);
			// art_tp
			em = ART_TP_ND.testEm(d, failrate);
			writerEm("tp",em, writer);
			// art_tpp
			em = ART_TPP.testEm(d, failrate);
			writerEm("tpp",em, writer);
			// my method
			// 1dimension
			em = RRTtpND_H.testEm(d, failrate);
			writerEm("laz",em, writer);
			writer.close();
		}
	}

	public static void writerEm(String message, double[] em, BufferedWriter writer) {
		try {
			writer.write(message);

			writer.newLine();

			for (int i = 0; i < em.length; i++) {
				writer.write(em[i] + "");
				writer.newLine();
			}
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedWriter get(File f) throws Exception {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	}
}
