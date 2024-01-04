package a_main;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
import test.simulations.rrttp.hilbert.RRTtpND_H;
import test.simulations.rt.RT_ND;
import util.data.ZeroOneCreator;
import util.file.FileUtils;

public class MainMethod {
    // TODO 完成参数化
    public static void main(String[] args) throws Exception {
        int times = 200;
        double failureRates[] = {0.05};
        int[] dimension = {1};
        for (int l = 0; l < dimension.length; l++) {
           // String path = "C:\\Users\\xijiaxiang\\Desktop\\研究方向和内容\\小论文\\补充数据\\fm\\strip";

            for (int j = 0; j < failureRates.length; j++) {
                double failureRate = failureRates[j];
                System.out.println("failure rate:" + failureRate);

                // for 10,20,30
                int d = dimension[l];

               // File rtf = FileUtils.createNewFile(path, "0528-"+d + "维-" + failureRate + "all.txt");
             //   BufferedWriter writer = get(rtf);

                ZeroOneCreator dataCreator = new ZeroOneCreator();
                double[] min = dataCreator.minCreator(d);
                double[] max = dataCreator.maxCreator(d);
                FailurePattern failurePattern = new BlockPattern();
                //FailurePattern failurePattern=new PointPatternIn4D();
                //FailurePattern failurePattern = new PointPatternIn4D();
                failurePattern.fail_rate = failureRate;
                failurePattern.min = min;
                failurePattern.max = max;
                failurePattern.dimension = d;

                // rt
              /*  int fm = 0;
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    RT_ND rt = new RT_ND(min, max, new Random(i * 3), failurePattern);
                    int temp = rt.run();
                    fm += temp;
                }
                long endTime = System.currentTimeMillis();
              //  writer.write((fm / (double) times) + "");
              //  writer.newLine();
                System.out
                        .println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
*/
                // rrt
               /* double r = 0.75;

                if (d >= 2) {
                    r = 1.5;
                }
                fm = 0;
                startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    RRT_ND rt = new RRT_ND(min, max, failurePattern, new Random(i * 3), r);
                    int temp = rt.run();
                    fm += temp;
                }
                endTime = System.currentTimeMillis();
                writer.write((fm / (double) times) + "");
                writer.newLine();
                System.out
                        .println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));


                // fscs
                fm = 0;
                int s = 10;
                startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    FSCS_ND rt = new FSCS_ND(min, max, s, failurePattern, new Random(i * 3));
                    int temp = rt.run();
                    fm += temp;
                }
                endTime = System.currentTimeMillis();
                writer.write((fm / (double) times) + "");
                writer.newLine();
                System.out
                        .println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));

                // art_b
               long fm = 0;
                long startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    ART_B_ND rt = new ART_B_ND(min, max, new Random(i * 3), failurePattern);
                    int temp = rt.run();
                    fm += temp;
                }
                long endTime = System.currentTimeMillis();
               // writer.write((fm / (double) times) + "");
               // writer.newLine();
                System.out
                        .println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
                */
				// art_rp
				long fm = 0;
				long startTime = System.currentTimeMillis();
				for (int i = 0; i < times; i++) {
					ART_RP_ND rt = new ART_RP_ND(min, max, new Random(i * 3), failurePattern);
					int temp = rt.run();
					fm += temp;
				}
				long endTime = System.currentTimeMillis();
				//writer.write((fm / (double) times) + "");
				//writer.newLine();
				System.out
						.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
				/*
				// art_tp
				fm = 0;
				startTime = System.currentTimeMillis();
				for (int i = 0; i < times; i++) {
					ART_TP_ND rt = new ART_TP_ND(min, max, failurePattern, new Random(i * 3));
					int temp = rt.run();
					fm += temp;
				}
				endTime = System.currentTimeMillis();
				writer.write((fm / (double) times) + "");
				writer.newLine();
				System.out
						.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
				
				// art_tpp
				fm = 0;
				int k = 10;
				startTime = System.currentTimeMillis();
				for (int i = 0; i < times; i++) {
					ART_TPP rt = new ART_TPP(min, max, new Random(i * 3), failurePattern, k);
					int temp = rt.run();
					fm += temp;
				}
				endTime = System.currentTimeMillis();
				writer.write((fm / (double) times) + "");
				writer.newLine();
				System.out
						.println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
				*/
                // my method
                // 1dimension
                /*
                r = 0.75;
                fm = 0;
                startTime = System.currentTimeMillis();
                for (int i = 0; i < times; i++) {
                    // /min, max, 0.75, pattern, new Random(i * 3+v*5)
                    RRTtpND_H rt = new RRTtpND_H(min, max, r, failurePattern, new Random(i * 3));
                    int temp = rt.run();
                    fm += temp;
                }
                endTime = System.currentTimeMillis();
                writer.write((fm / (double) times) + "");
                writer.newLine();
                System.out
                        .println("Fm:" + (fm / (double) times) + " times:" + ((endTime - startTime) / (double) times));
				*/
             //   writer.close();
            }
        }
    }

    public static BufferedWriter get(File f) throws Exception {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
    }
}
