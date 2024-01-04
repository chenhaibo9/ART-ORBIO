package util.random;

import java.util.Random;

import util.CRandomNumber;

public class CRandomAdapter extends Random{

	@Override
	public synchronized void setSeed(long seed) {
		CRandomNumber.initSeed(seed);
	}

	@Override
	public double nextDouble() {
		return CRandomNumber.randome0e1();
	}

}
