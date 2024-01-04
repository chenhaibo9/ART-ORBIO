package test;

import java.util.Random;

import datastructure.ND.NPoint;
import datastructure.failurepattern.FailurePattern;
import util.RandomCreator;

public abstract class ART {
	public double[] min;
	public double[] max;
	public int dimension;
	public Random random;//éš�æœºæ•°ï¼Œå�¯ä»¥é€‰ç”¨CRandomNumberå’ŒMerseneTwisterFast(è¿™ä¸ªè¾ƒå¥½)
	public int pmCount;//ç®—pmæ—¶è®¾ç½®çš„æµ‹è¯•ç”¨ä¾‹æ•°é‡�
	public int emCount=1000;//ç®—emæ—¶è®¾ç½®çš„æµ‹è¯•ç”¨ä¾‹æ•°é‡�
	public int tcCount=1000;//ç®—æ—¶é—´è®¾ç½®çš„æµ‹è¯•ç”¨ä¾‹æ•°é‡�
	public FailurePattern failPattern;//å¤±æ•ˆæ¨¡å¼�
	public double totalArea;
	public RandomCreator randomCreator;//éš�æœºç”Ÿæˆ�å™¨ï¼ˆç‰¹å®šåŒºåŸŸï¼Œç‰¹å®šåŒºåŸŸç»„ä¸­ç”Ÿæˆ�ï¼‰

	public ART(double[] min, double[] max, Random random, FailurePattern failurePattern) {
		this.min = min;
		this.max = max;

		this.dimension = min.length;
		this.random = random;

		failurePattern.min = min;
		failurePattern.max = max;
		failurePattern.dimension = dimension;
		failurePattern.random = random;
		failurePattern.genFailurePattern();
		this.failPattern = failurePattern;

		totalArea = 1.0;
		for (int i = 0; i < this.dimension; i++) {
			totalArea *= (max[i] - min[i]);
		}

		randomCreator = new RandomCreator(random, dimension, min, max);
		
		this.pmCount=(int) Math.round(Math.log(0.5)/Math.log(1-failPattern.fail_rate)); //95% CI
	}

	public abstract NPoint generateNextTC();//æ ¸å¿ƒæ–¹æ³•ï¼Œç”Ÿæˆ�æµ‹è¯•ç”¨ä¾‹çš„æ–¹æ³•
	
 
	public int run(){
		int count = 0;
		NPoint p = generateNextTC();
		while (this.failPattern.isCorrect(p)) {
//		while (count < 1000) {
			count++;
//			System.out.println(p);
			p = generateNextTC();
		}
		return count;
	}
	
	public int em() {
		int emTemp = 0;
		NPoint p = generateNextTC();
		int count = 0;
		while (count < emCount) {
			if (!this.failPattern.isCorrect(p)) {
				emTemp++;
				//break;//ç›´æŽ¥è·³å‡º
			}
			count++;
			p = generateNextTC();
		}
		return emTemp;
	}
	
	public  void time2(){
		//ç”Ÿæˆ�æµ‹è¯•ç”¨ä¾‹æ‰€éœ€æ—¶é—´
		int count = 0;
		NPoint p = generateNextTC();
		while (count < tcCount) {
			count++;
			p = generateNextTC();
		}
	}
	
	public  boolean pm(){
		boolean Detected = false;
		int count = 0;
		NPoint p = generateNextTC();
		//System.out.println(pmCount);
		while (count < pmCount) {
			if (!this.failPattern.isCorrect(p)) {
				Detected = true;
				break;
			}
			count++;
			p = generateNextTC();
		}
		return Detected;
	}
	

}
