package a_main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import test.simulations.art_b.ART_B_ND;
import test.simulations.art_rp.ART_RP_ND;
import test.simulations.art_tp._ND.ART_TP_ND;
import test.simulations.art_tpp.ART_TPP;
import test.simulations.fscs.FSCS_ND;
import test.simulations.rrt.RRT_ND;
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.file.FileUtils;

public class MainMethodTime {
	public static void main(String[] args) throws Exception {
		int tcCounts[] = new int[2000];
		for(int i=0;i<tcCounts.length;i++){
			tcCounts[i]=(i+1)*500;
		}
		String path = "C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\time\\0-1000000";
		int d = 1;

		File rtf = FileUtils.createNewFile(path, d + "维" + "all.txt");
		BufferedWriter writer = get(rtf);

		writeTCTime("rt", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// rt
			double time = RT_ND.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writeTCTime("rrt", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// rrt
			double time = RRT_ND.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writeTCTime("fscs", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// fscs
			//double time = FSCS_ND.testTCTime(d, tcCounts[j]);
			//writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writeTCTime("b", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// art_b
			double time=ART_B_ND.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		
		writeTCTime("rp", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// art_rp
			double time = ART_RP_ND.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line",-1, writer);
		writeTCTime("tp", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// art_tp
			double time = ART_TP_ND.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writeTCTime("tpp", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// art_tpp
			double time = ART_TPP.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writeTCTime("laz", -1, writer);
		for (int j = 0; j < tcCounts.length; j++) {
			// my method
			// 1dimension
			double time = RRTtpND_H.testTCTime(d, tcCounts[j]);
			writeTCTime("", time, writer);
		}
		writeTCTime("line", -1, writer);
		writer.close();

	}

	public static void writeTCTime(String message, double time, BufferedWriter writer) {
		try {
			if (message.equals("line")) {
				writer.newLine();
			} else if (!message.equals("")) {
				writer.write(message);
				writer.newLine();
			}

			if (time != -1.0) {
				writer.write(time + "");
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedWriter get(File f) throws Exception {
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
	}
}
