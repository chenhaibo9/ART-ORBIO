package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.core.config.Order;

/*
 * 将之前c#中的uint（32位无符号数）转成long
 * 
 * */
public class HilbertCurve2 {

	// public final double[] oneD_2_twoD(double value_10jinzhi){
	// String bin_str=Double2Bin(value_10jinzhi);
	// RefObject<Double> x=new RefObject<Double>((double)-1);
	// RefObject<Double> y=new RefObject<>((double)-1);
	// int numOfbits=bin_str.length()-2;
	// String hiber_key_valid=null;
	// if(numOfbits>32){numOfbits=32;}
	//
	// }
	// test only
	public static void main(String[] args) throws Exception {
		
		HilbertCurve2 hilbertCurve = new HilbertCurve2();
		System.out.println(hilbertCurve.Bin2Double("011"));
		// double a=0.00001111111111;
		// String a="0.000111111111111111111111111111111111111";
		// //BigDecimal b=new
		// BigDecimal("0.11111111111111111111111111111111111");
		// //System.out.println(b.toString());
		// //double result1[]=hilbertCurve.oneD_2_nD(a, 10);
		// double result2[]=hilbertCurve.oneD_2_nD3(a, 10);
		// //System.out.println(Arrays.toString(result1));
		// System.out.println(Arrays.toString(result2));

		// double results[] = hilbertCurve.oneD_2_nD(0.8886545227094472, 4);
		// for (int i = 0; i < results.length; i++) {
		// System.out.println(results[i]);
		// }
		// long start = System.nanoTime();
		// System.out.println(hilbertCurve.Double2Bin(0.8125));
		// long end = System.nanoTime();
		// System.out.println((end - start) / Math.pow(10, 3.0));

		// start = System.nanoTime();
		// System.out.println(hilbertCurve.decimal2Bin(0.8125, 64));
		// end = System.nanoTime();
		// System.out.println((end - start) / Math.pow(10, 3.0));
		// System.out.println(hilbertCurve.decimal2Binary(0.8125));

		// 比较时间
		//int[] dimension={1,2,3,4};
		//int[] dimension = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
		//		26, 27, 28, 29, 30, 31, 32 };
		//double testValue=0.3333;
		//System.out.println(testValue);
		//for (int i = 0; i < dimension.length; i++) {
		//	long start = System.nanoTime();
			//for (int j = 0; j < 100; j++)
		//		hilbertCurve.oneD_2_nD4(testValue+"", dimension[i]);
		//	long end = System.nanoTime();
			//System.out.println(end - start);
		//}

		
			//double[] a=hilbertCurve.oneD_2_nD3("0.1111111", 10);
			double[] b=hilbertCurve.oneD_2_nD4("0.101101101101", 3);
			//System.out.println("A:"+Arrays.toString(a));
			System.out.println("B:"+Arrays.toString(b));
	}

	public final void ADD(int dst, int u, int v, double[][] abcd, double[][] tmp) {
		abcd[(dst)][0] = (tmp[(u)][0] + tmp[v][0]) / 2.0;
		abcd[(dst)][1] = (tmp[(u)][1] + tmp[(v)][1]) / 2.0;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no directv
	// equivalent in Java:
	// ORIGINAL LINE: public void ADD(int dst,int u, int v,uint[,] abcd,uint[,]
	// tmp)
	public final void ADD(int dst, int u, int v, long[][] abcd, long[][] tmp) {
		abcd[(dst)][0] = tmp[(u)][0] + tmp[v][0];
		abcd[(dst)][1] = tmp[(u)][1] + tmp[(v)][1];
	}

	public final double Bin2Double(String bin_str) {
		double sum = 0;
		double binbase = 1;
		int len = bin_str.length();
		for (int i = 0; i < len; i++) {
			binbase = binbase * 0.5;
			if (bin_str.charAt(i) == '1') {
				sum += binbase;
			}

		}
		return sum;

	}

	/**
	 * 0.[00001000]numbits为8，key为8
	 * 
	 * @param key
	 * @param numbits
	 * @param xx
	 * @param yy
	 */
	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public void convert_hilbert_key(uint key, int numbits, ref
	// uint xx,ref uint yy)
	public final void convert_hilbert_key(long key, int numbits, RefObject<Long> xx, RefObject<Long> yy) {

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint[,] abcd = new uint[4, 2] { { 0, 0 }, { 0, 1 }, {
		// 1, 1 }, { 1, 0 } };
		long[][] abcd = new long[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		// ({0, 0}, {0,1}, {1, 1}, {1, 0});
		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint[,] tmp = new uint[4, 2];
		long[][] tmp = new long[4][2];
		while (key > 1) // should be > 0, but this is safer for (invalid) odd
						// numbers
		{
			// uint[,] tmp=new uint[4,2]; /* save abcd here */
			byte subcell; // unsigned char subcell;
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < 4; k++) {
					tmp[k][j] = abcd[k][j];
				}
			}
			// memcpy(tmp, abcd, sizeof tmp); /* save our 4 points with the new
			// ones */
			numbits -= 2; // each subdivision decision takes 2 bits
			// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no
			// direct equivalent in Java:
			// ORIGINAL LINE: uint u_subcell=(key >> numbits) & 3;
			long u_subcell = (key >> numbits) & 3;
			subcell = Byte.parseByte((new Long(u_subcell)).toString());
			// namely these two (taken from the top)
			key &= (int) ((1 << numbits) - 1);
			// update key by removing the bits we used
			// * Add the two points with indices u and v (in tmp) and store the
			// result at
			// * index dst in abcd (for both x(0) and y(1)).
			// *
			/// #define ADD(dst, u, v) (abcd[(dst)][0] = tmp[(u)][0] +
			// tmp[(v)][0],
			// abcd[(dst)][1] = tmp[(u)][1] + tmp[(v)][1])

			switch (subcell) {
			// divide into subcells
			case 0:
				// h(key, numbits, a << 1, a + d, a + c, a + b);
				ADD(0, 0, 0, abcd, tmp);
				ADD(1, 0, 3, abcd, tmp);
				ADD(2, 0, 2, abcd, tmp);
				ADD(3, 0, 1, abcd, tmp);
				break;
			case 1:
				// h(key, numbits, b + a, b << 1, b + c, b + d);
				ADD(0, 1, 0, abcd, tmp);
				ADD(1, 1, 1, abcd, tmp);
				ADD(2, 1, 2, abcd, tmp);
				ADD(3, 1, 3, abcd, tmp);
				break;
			case 2:
				// h(key, numbits, c + a, c + b, c << 1, c + d);
				ADD(0, 2, 0, abcd, tmp);
				ADD(1, 2, 1, abcd, tmp);
				ADD(2, 2, 2, abcd, tmp);
				ADD(3, 2, 3, abcd, tmp);
				break;
			case 3:
				// h(key, numbits, d + c, d + b, d + a, d << 1);
				ADD(0, 3, 2, abcd, tmp);
				ADD(1, 3, 1, abcd, tmp);
				ADD(2, 3, 0, abcd, tmp);
				ADD(3, 3, 3, abcd, tmp);
				break;
			}

			/// #undef ADD
		}
		// final result is the midpoint of the cell, i.e. (a + b + c + d) / 4
		xx.argvalue = (abcd[0][0] + abcd[1][0] + abcd[2][0] + abcd[3][0] + 1) >> 2;
		yy.argvalue = (abcd[0][1] + abcd[1][1] + abcd[2][1] + abcd[3][1] + 1) >> 2;
		System.out.println("二维点为x=" + (new Long(xx.argvalue)).toString() + ";y=" + (new Long(yy.argvalue)).toString());
		// printf("x: %u y: %un", *xx, *yy);

	}

	/**
	 * 0.[00001000]numbits为8，key为8
	 * 
	 * @param key
	 * @param numbits
	 * @param xx
	 * @param yy
	 */
	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public ArrayList convert_hilbert_key_V1(uint key, int
	// numbits, ref double xx,ref double yy)
	public final ArrayList convert_hilbert_key_V1(long key, int numbits, RefObject<Double> xx, RefObject<Double> yy) {

		double[][] abcd = new double[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		// ({0,0}, {0, 1}, {1, 1}, {1, 0});
		// unit square
		double[][] tmp = new double[4][2];
		java.util.ArrayList nds = new java.util.ArrayList();
		while (numbits > 1) // should be > 0, but this is safer for (invalid)
							// odd numbers
		{
			// uint[,] tmp=new uint[4,2]; /* save abcd here */
			byte subcell; // unsigned char subcell;
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 4; k++) {
					tmp[k][j] = abcd[k][j];
				}
			}

			// memcpy(tmp, abcd, sizeof tmp); /* save our 4 points with the new
			// ones */

			numbits -= 2; // each subdivision decision takes 2 bits
			// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no
			// direct equivalent in Java:
			// ORIGINAL LINE: uint u_subcell=(key >> numbits) & 3;
			long u_subcell = (key >> numbits) & 3;
			subcell = Byte.parseByte((new Long(u_subcell)).toString());
			// namely these two (taken from the top)
			// key &= (uint)((1<<numbits) - 1); /* update key by removing the
			// bits we used */

			// * Add the two points with indices u and v (in tmp) and store the
			// result at
			// * index dst in abcd (for both x(0) and y(1)).
			// *
			/// #define ADD(dst, u, v) (abcd[(dst)][0] = tmp[(u)][0] +
			// tmp[(v)][0],
			// abcd[(dst)][1] = tmp[(u)][1] + tmp[(v)][1])

			switch (subcell) { // divide into subcells
			case 0:
				// h(key, numbits, a << 1, a + d, a + c, a + b);
				ADD(0, 0, 0, abcd, tmp);
				ADD(1, 0, 3, abcd, tmp);
				ADD(2, 0, 2, abcd, tmp);
				ADD(3, 0, 1, abcd, tmp);
				break;
			case 1:
				// h(key, numbits, b + a, b << 1, b + c, b + d);
				ADD(0, 1, 0, abcd, tmp);
				ADD(1, 1, 1, abcd, tmp);
				ADD(2, 1, 2, abcd, tmp);
				ADD(3, 1, 3, abcd, tmp);
				break;
			case 2:
				// h(key, numbits, c + a, c + b, c << 1, c + d);
				ADD(0, 2, 0, abcd, tmp);
				ADD(1, 2, 1, abcd, tmp);
				ADD(2, 2, 2, abcd, tmp);
				ADD(3, 2, 3, abcd, tmp);
				break;
			case 3:
				// h(key, numbits, d + c, d + b, d + a, d << 1);
				ADD(0, 3, 2, abcd, tmp);
				ADD(1, 3, 1, abcd, tmp);
				ADD(2, 3, 0, abcd, tmp);
				ADD(3, 3, 3, abcd, tmp);
				break;
			}

			/// #undef ADD
		}
		// final result is the midpoint of the cell, i.e. (a + b + c + d) / 4
		xx.argvalue = (abcd[0][0] + abcd[1][0] + abcd[2][0] + abcd[3][0]) / 4.0;
		yy.argvalue = (abcd[0][1] + abcd[1][1] + abcd[2][1] + abcd[3][1]) / 4.0;
		nds.add(xx.argvalue);
		nds.add(yy.argvalue);
		return nds;
		// Console.WriteLine("二维点为x="+xx.ToString()+";y="+yy.ToString());
		// printf("x: %u y: %un", *xx, *yy);

	}

	public String decimal2Bin(double value, int k) {
		StringBuilder binary = new StringBuilder();
		// double fractional = value;
		binary.append('0');
		binary.append('.');
		while (k-- > 0) {
			value *= 2;
			int fract_bit = (int) value;
			if (fract_bit == 1) {
				value -= fract_bit;
				binary.append('1');
			} else
				binary.append('0');
		}
		return binary.toString();
	}

	public String decimal2Bin(String value, int k) {
		StringBuilder binary = new StringBuilder();
		// double fractional = value;
		binary.append('0');
		binary.append('.');
		BigDecimal temp = new BigDecimal(value);
		while (k-- > 0) {
			temp = temp.multiply(new BigDecimal(2));
			int fract_bit = temp.intValue();
			if (fract_bit == 1) {
				temp = temp.subtract(new BigDecimal(1));
				binary.append('1');
			} else
				binary.append('0');
		}
		return binary.toString();
	}

	/**
	 * 小于1的小数转换为二进制
	 * 
	 * @param val_double
	 * @return
	 */
	public final String Double2Bin(double val_double) {
		double ud = 2 * val_double;
		StringBuilder bin_sb = new StringBuilder();
		double converVal = 0;
		// equivalent in Java: ORIGINAL LINE: uint i=1;
		long i = 1;
		double eachbit = 0;
		double resolution = 1e-22;
		if ((ud - 1) == 0) {
			bin_sb.append("1");
		}
		while ((ud - 1) != 0) {
			if (ud > 1) {
				eachbit = 1;
				bin_sb.append("1");
			} else if (ud < 1) {
				eachbit = 0;
				bin_sb.append("0");
			}
			// eachbit = Convert.ToDouble(ud.ToString().Substring(0, 1));
			converVal += eachbit * (1 / Math.pow(2, i));
			i = i + 1;
			if (ud > 1) {
				ud = (ud - 1) * 2;
			} else if (ud < 1) {
				ud = ud * 2;
			}
			if ((ud - 1) == 0) {
				bin_sb.append("1");
				break;
			}
			if (Math.abs(converVal - val_double) < resolution) {
				// 达到一定的精度
				break;
			}
		}
		return "0." + bin_sb.toString();
	}

	/**
	 * 将一维转换为多维
	 * 
	 * @param hilbercode
	 * @param dim
	 * @return
	 */
	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
	// equivalent in Java:
	// ORIGINAL LINE: public string[] HilbertCode2Coordinates(string
	// hilbercode,uint dim)
	public final String[] HilbertCode2Coordinates(String hilbercode, long dim) {
		final int Wordbits = 32;
		final int OrderOfHilbert = 32;
		int ndimbits = hilbercode.length();
		// int dim_int = Integer.parseInt((new Long(dim)).toString());
		int dim_int = (int) dim;
		// 填充补0使得hilbertcode为dim的整数倍位
		int paddings = -1;
		int intNdim = ndimbits / dim_int;
		if ((ndimbits % dim_int) != 0) {
			ndimbits = intNdim * dim_int + dim_int;
			paddings = ndimbits - hilbercode.length();
			for (int kindex = 0; kindex < paddings; kindex++) {
				hilbercode += "0";
			}
		}
		String[] point = new String[(int) dim];
		// equivalent in Java: ORIGINAL LINE: uint mask = (uint)1 << Wordbits -
		// 1;
		long mask = (long) 1 << Wordbits - 1; // 31个1
		// equivalent in Java: ORIGINAL LINE: uint element, temp1, temp2, A, W =
		// 0, S, tS, T, tT, J,
		// P = 0, xJ;
		long element, temp1, temp2, A, W = 0, S, tS, T, tT, J, P = 0, xJ;

		int i = 0, j;
		for (int kindex = 0; kindex < dim; kindex++) {
			point[kindex] = "";
		}

		// --- P ---
		String p_str = hilbercode.substring(i, i + dim_int);
		P = Integer.parseInt(p_str, 2);
		// --- xJ ---
		J = dim;
		for (j = 1; j < dim_int; j++) {
			if ((P >> j & 1) == (P & 1)) {
				continue;
			} else {
				break;
			}
		}
		if (j != dim_int) {
			// J = J - Integer.parseInt((new Integer(j)).toString());
			J = J - j;
		}
		xJ = J - 1;

		// --- S, tS, A ---
		A = S = tS = P ^ (P / 2); // 异或运算

		// --- T ---
		if (P < 3) {
			T = 0;
		} else {
			if (P % 2 != 0) {
				T = (P - 1) ^ (P - 1) / 2;
			} else {
				T = (P - 2) ^ (P - 2) / 2;
			}
		}

		// --- tT ---
		tT = T;

		// --- distrib bits to coords ---
		// for (j = DIM - 1; P > 0; P >>=1, j--)
		// if (P & 1)
		// pt.hcode[j] |= mask;
		for (j = dim_int - 1; j >= 0; A >>= 1, j--) {
			if ((A & 1) != 0) {
				point[j] = point[j] + "1";
			} else {
				point[j] = point[j] + "0";
			}
		}

		int noOfshiftbits = 0;
		for (i = dim_int, mask >>= 1; i < ndimbits; i = i + dim_int, mask >>= 1) {
			// --- P ---
			p_str = hilbercode.substring(i, i + dim_int);
			P = Integer.parseInt(p_str, 2);

			// --- S ---
			S = P ^ (P / 2);

			// --- tS ---
			noOfshiftbits = (int) (xJ % dim);
			if (xJ % dim != 0) {
				temp1 = S >> (noOfshiftbits);
				temp2 = S << (dim_int - noOfshiftbits);
				tS = temp1 | temp2;
				tS &= ((int) 1 << dim_int) - 1;
			} else {
				tS = S;
			}

			// --- W ---
			W ^= tT;

			// --- A ---
			A = W ^ tS;

			// --- distrib bits to coords ---
			for (j = dim_int - 1; j >= 0; A >>= 1, j--) {
				// if ((A & 1)!=0)
				// point[j] |= mask;
				if ((A & 1) != 0) {
					point[j] = point[j] + "1";
				} else {
					point[j] = point[j] + "0";
				}
			}

			// --- T ---
			if (P < 3) {
				T = 0;
			} else {
				if (P % 2 != 0) {
					T = (P - 1) ^ (P - 1) / 2;
				} else {
					T = (P - 2) ^ (P - 2) / 2;
				}
			}

			// --- tT ---
			noOfshiftbits = (int) (xJ % dim);
			if (xJ % dim != 0) {
				temp1 = T >> noOfshiftbits;
				temp2 = T << (dim_int - noOfshiftbits);
				tT = temp1 | temp2;
				tT &= ((int) 1 << dim_int) - 1;
			} else {
				tT = T;
			}

			// --- xJ ---
			J = dim;
			for (j = 1; j < dim; j++) {
				if ((P >> j & 1) == (P & 1)) {
					continue;
				} else {
					break;
				}
			}
			if (j != dim) {
				J -= (int) j;
			}
			xJ += J - 1;
		}
		return point;
	}

	public final String[] HilbertCode2Coordinates2(String hilbercode, long dim) {
		final int Wordbits = 64;
		final int OrderOfHilbert = 32;
		int ndimbits = hilbercode.length();
		// int dim_int = Integer.parseInt((new Long(dim)).toString());
		int dim_int = (int) dim;
		// 填充补0使得hilbertcode为dim的整数倍位
		int paddings = -1;
		int intNdim = ndimbits / dim_int;
		if ((ndimbits % dim_int) != 0) {
			ndimbits = intNdim * dim_int + dim_int;
			paddings = ndimbits - hilbercode.length();
			for (int kindex = 0; kindex < paddings; kindex++) {
				hilbercode += "0";
			}
		}
		//System.out.println(hilbercode);
		String[] point = new String[(int) dim];
		// equivalent in Java: ORIGINAL LINE: uint mask = (uint)1 << Wordbits -
		// 1;
		long mask = (long) 1 << Wordbits - 1; // 31个1
		// equivalent in Java: ORIGINAL LINE: uint element, temp1, temp2, A, W =
		// 0, S, tS, T, tT, J,
		// P = 0, xJ;
		long element, temp1, temp2, A, W = 0, S, tS, T, tT, J, P = 0, xJ;

		int i = 0, j;
		for (int kindex = 0; kindex < dim; kindex++) {
			point[kindex] = "";
		}

		// --- P ---
		String p_str = hilbercode.substring(i, i + dim_int);
		P = Integer.parseInt(p_str, 2);
		// --- xJ ---
		J = dim;
		for (j = 1; j < dim_int; j++) {
			if ((P >> j & 1) == (P & 1)) {
				continue;
			} else {
				break;
			}
		}
		if (j != dim_int) {
			// J = J - Integer.parseInt((new Integer(j)).toString());
			J = J - j;
		}
		xJ = J - 1;

		// --- S, tS, A ---
		A = S = tS = P ^ (P / 2); // 异或运算

		// --- T ---
		if (P < 3) {
			T = 0;
		} else {
			if (P % 2 != 0) {
				T = (P - 1) ^ (P - 1) / 2;
			} else {
				T = (P - 2) ^ (P - 2) / 2;
			}
		}

		// --- tT ---
		tT = T;

		// --- distrib bits to coords ---
		// for (j = DIM - 1; P > 0; P >>=1, j--)
		// if (P & 1)
		// pt.hcode[j] |= mask;
		for (j = dim_int - 1; j >= 0; A >>= 1, j--) {
			if ((A & 1) != 0) {
				point[j] = point[j] + "1";
			} else {
				point[j] = point[j] + "0";
			}
		}

		int noOfshiftbits = 0;
		for (i = dim_int, mask >>= 1; i < ndimbits; i = i + dim_int, mask >>= 1) {
			// --- P ---
			p_str = hilbercode.substring(i, i + dim_int);
			P = Integer.parseInt(p_str, 2);

			// --- S ---
			S = P ^ (P / 2);

			// --- tS ---
			noOfshiftbits = (int) (xJ % dim);
			if (xJ % dim != 0) {
				temp1 = S >> (noOfshiftbits);
				temp2 = S << (dim_int - noOfshiftbits);
				tS = temp1 | temp2;
				tS &= ((int) 1 << dim_int) - 1;
			} else {
				tS = S;
			}

			// --- W ---
			W ^= tT;

			// --- A ---
			A = W ^ tS;

			// --- distrib bits to coords ---
			for (j = dim_int - 1; j >= 0; A >>= 1, j--) {
				// if ((A & 1)!=0)
				// point[j] |= mask;
				if ((A & 1) != 0) {
					point[j] = point[j] + "1";
				} else {
					point[j] = point[j] + "0";
				}
			}

			// --- T ---
			if (P < 3) {
				T = 0;
			} else {
				if (P % 2 != 0) {
					T = (P - 1) ^ (P - 1) / 2;
				} else {
					T = (P - 2) ^ (P - 2) / 2;
				}
			}

			// --- tT ---
			noOfshiftbits = (int) (xJ % dim);
			if (xJ % dim != 0) {
				temp1 = T >> noOfshiftbits;
				temp2 = T << (dim_int - noOfshiftbits);
				tT = temp1 | temp2;
				tT &= ((int) 1 << dim_int) - 1;
			} else {
				tT = T;
			}

			// --- xJ ---
			J = dim;
			for (j = 1; j < dim; j++) {
				if ((P >> j & 1) == (P & 1)) {
					continue;
				} else {
					break;
				}
			}
			if (j != dim) {
				J -= (int) j;
			}
			xJ += J - 1;
		}
		//System.out.println(Arrays.toString(point));
		return point;
	}
	
	
	void invert(char ch[],int m,int n)
	{
		int i;
		char temp;
		for(i=m;i<(m+n)/2;i++)
		{
			temp=ch[i];
			ch[i]=ch[m+n-1-i];
			ch[m+n-1-i]=temp;
		}
	}
	void loopMove(char c[],int steps)
	{
		if(steps>c.length){
			steps=steps%c.length;
		}
		int len;
		len=(c.length)-steps;
		invert(c,0,len);//对字符串中前len位进行逆置
		invert(c,len,(c.length));//对字符串后steps位进行逆置
		invert(c,0,(c.length));//再对整个字符串进行逆置
	}
	public final String[] HilbertCode2Coordinates3(String hilbercode, long dim) {
		//final int Wordbits = 64;
		final int OrderOfHilbert = 64;
		int ndimbits = hilbercode.length();
		//System.out.println("origin:"+ndimbits);
		//System.out.println("need:"+(dim*OrderOfHilbert));
		System.out.println(hilbercode);
		if(ndimbits<dim*OrderOfHilbert){
			int count=ndimbits;
			while(count<dim*OrderOfHilbert){
				hilbercode+="0";
				count++;
			}
		}
		//System.out.println("after:"+hilbercode.length());
		System.out.println(hilbercode);
		
		//group p from 1 to m ,each group has n(dimension) values
		String[] pgroups=new String[OrderOfHilbert];
		for(int i=0;i<pgroups.length;i++){
			pgroups[i]=hilbercode.substring((int)(i*dim), (int)(i*dim+dim));
		}
		System.out.println("pgroups:"+Arrays.toString(pgroups));
		
		//Ji 
		int[] J=new int[OrderOfHilbert];
		for(int i=0;i<pgroups.length;i++){
			String eachp=pgroups[i];
			if(!eachp.contains("0")||!eachp.contains("1")){
				J[i]=(int) dim;
			}else{
				char[] c=eachp.toCharArray();
				char last=c[c.length-1];
				for(int j=c.length-2;j>=0;j--){
					if(c[j]!=last){
						J[i]=j+1;
						break;
					}
				}
			}
		}
		System.out.println("J:"+Arrays.toString(J));
		//E -> copy first bit of P ,then xor
		String[] E=new String[OrderOfHilbert];
	 	for(int i=0;i<E.length;i++){
	 		E[i]=pgroups[i].charAt(0)+"";
	 		char[] c=pgroups[i].toCharArray();
	 		for(int j=1;j<c.length;j++){
	 			char pre=c[j-1];
	 			char cur=c[j];
	 			E[i]+=((pre-'0')^(cur-'0'))+"";
	 		}
	 	}
	 	System.out.println("E:"+Arrays.toString(E));
		
		//T
	 	String[] T=new String[OrderOfHilbert];
	 	for(int i=0;i<T.length;i++){
	 		String eachE=E[i];
	 		T[i]="";
	 		if(eachE.length()>=2){
	 			T[i]=eachE.substring(0,eachE.length()-1);
	 		}
	 		char lastBit=eachE.charAt((int)dim-1);
	 		if(lastBit=='1'){
	 			T[i]+="0";	
	 		}else{
	 			T[i]+="1";
	 		}
	 		//System.out.println((lastBit-'0'));
	 		//T[i]+=((~(lastBit-'0'))+"");
	 		//System.out.println("midT:"+T[i]);
	 		//if number of 1 is eve
	 		char[] c=T[i].toCharArray();
	 		int count=0;
	 		for(int j=0;j<c.length;j++){
	 			if(c[j]=='1'){
	 				count++;
	 			}
	 		}
	 		if(count%2==1){
	 			char temp=c[J[i]-1];
	 			if(temp=='1'){
	 				c[J[i]-1]='0';
	 			}else{
	 				c[J[i]-1]='1';
	 			}
	 		}
	 		T[i]=String.copyValueOf(c);
	 	}
	 	System.out.println("T:"+Arrays.toString(T));
		
	 	//eE
	 	String[] eE=new String[OrderOfHilbert];
	 	for(int i=0;i<eE.length;i++){
	 		if(i==0){
	 			eE[i]=new String(E[i]);
	 		}else{
	 			char[] c=E[i].toCharArray();
	 			int shiftCount=0;
	 			for(int j=0;j<i;j++){
	 				shiftCount+=J[j]-1;
	 			}
	 			loopMove(c, shiftCount);
	 			eE[i]=String.copyValueOf(c);
	 		}
	 	}
	 	System.out.println("eE:"+Arrays.toString(eE));
		
	 	//tT
	 	String[] tT=new String[OrderOfHilbert];
	 	for(int i=0;i<tT.length;i++){
	 		if(i==0){
	 			tT[i]=new String(T[i]);
	 		}else{
	 			char[] c=T[i].toCharArray();
	 			int shiftCount=0;
	 			for(int j=0;j<i;j++){
	 				shiftCount+=J[j]-1;
	 			}
	 			loopMove(c, shiftCount);
	 			tT[i]=String.copyValueOf(c);
	 		}
	 	}
	 	System.out.println("tT:"+Arrays.toString(tT));
		
	 	//W
	 	String[] W=new String[OrderOfHilbert];
	 	for(int i=0;i<W.length;i++){
	 		if(i==0){
	 			W[i]=new String();
	 			for(int j=0;j<dim;j++){
	 				W[i]+="0";
	 			}
	 		}else{
	 			char[] c1=W[i-1].toCharArray();
	 			char[] c2=tT[i-1].toCharArray();
	 			char[] c=new char[(int)dim];
	 			for(int j=0;j<c.length;j++){
	 				c[j]=(char) ('0'+((c1[j]-'0')^(c2[j]-'0')));
	 			}
	 			W[i]=String.copyValueOf(c);
	 		}
	 	}
	 	System.out.println("W:"+Arrays.toString(W));
		
	 	//A
	 	String[] A=new String[OrderOfHilbert];
	 	for(int i=0;i<A.length;i++){
	 		char[] c1=W[i].toCharArray();
 			char[] c2=eE[i].toCharArray();
 			char[] c=new char[(int)dim];
 			for(int j=0;j<c.length;j++){
 				c[j]=(char) ('0'+((c1[j]-'0')^(c2[j]-'0')));
 			}
 			A[i]=String.copyValueOf(c);
	 	}
	 	System.out.println("A:"+Arrays.toString(A));
		
	 	//point
		String[] point = new String[(int) dim];
		for(int i=0;i<point.length;i++){
			point[i]="";
			for(int j=0;j<A.length;j++){
				String temp=A[j];
				point[i]+=temp.charAt(i);
			}
		}
		//System.out.println("points:"+Arrays.toString(point));
		return point;
	}
	//// 下面这个方法将求解一维到多维进行封装 by XJX
	public final double[] oneD_2_nD(double value_10jinzhi, int dim) {
		double[] curvals = new double[dim];
		// System.out.println("---------------------");
		// long starttime = System.currentTimeMillis();
		String bin_str = decimal2Bin(value_10jinzhi, 32);
		// long endtime = System.currentTimeMillis();
		// System.out.println(endtime - starttime);
		int numOfbits = bin_str.length() - 2;
		if (numOfbits > 32) {
			numOfbits = 32;
		}
		// starttime = System.currentTimeMillis();
		String[] pointOfstrs = HilbertCode2Coordinates(bin_str.substring(2, 2 + numOfbits), (int) dim);
		// endtime = System.currentTimeMillis();
		// System.out.println(endtime - starttime);
		// starttime = System.currentTimeMillis();
		for (int i = 0; i < dim; i++) {
			curvals[i] = Bin2Double(pointOfstrs[i]);
		}
		// endtime = System.currentTimeMillis();
		// System.out.println(endtime - starttime);
		return curvals;
	}

	public final double[] oneD_2_nD2(double value_10jinzhi, int dim, long[] time) {
		double[] curvals = new double[dim];
		// System.out.println("---------------------");
		// long starttime = System.nanoTime();
		String bin_str = decimal2Bin(value_10jinzhi, 64);
		// String bin_str=Double2Bin(value_10jinzhi);
		// long endtime = System.nanoTime();
		// time[0] = endtime - starttime;
		// System.out.println(endtime-starttime);
		int numOfbits = bin_str.length() - 2;
		if (numOfbits > 32) {
			numOfbits = 32;
		}
		// starttime = System.currentTimeMillis();
		String[] pointOfstrs = HilbertCode2Coordinates(bin_str.substring(2, 2 + numOfbits), (int) dim);
		// endtime = System.currentTimeMillis();
		// time[1] = endtime - starttime;
		// starttime = System.currentTimeMillis();
		for (int i = 0; i < dim; i++) {
			curvals[i] = Bin2Double(pointOfstrs[i]);
		}
		// endtime = System.currentTimeMillis();
		// time[2] = endtime - starttime;
		// System.out.println(endtime-starttime);
		return curvals;
	}

	public final double[] oneD_2_nD3(String value_10jinzhi, int dim) {
		double[] curvals = new double[dim];
		String bin_str = decimal2Bin(value_10jinzhi, 64);
		int numOfbits = bin_str.length() - 2;
		//System.out.println("point:"+bin_str);
		String[] pointOfstrs = HilbertCode2Coordinates2(bin_str.substring(2, 2 + numOfbits), (int) dim);
		for (int i = 0; i < dim; i++) {
			curvals[i] = Bin2Double(pointOfstrs[i]);
		}
		return curvals;
	}
	public final double[] oneD_2_nD4(String value_10jinzhi, int dim) {
		double[] curvals = new double[dim];
		String bin_str = decimal2Bin(value_10jinzhi, 64);
		int numOfbits = bin_str.length() - 2;
		String[] pointOfstrs = HilbertCode2Coordinates3(bin_str.substring(2, 2 + numOfbits), (int) dim);
		for (int i = 0; i < dim; i++) {
			curvals[i] = Bin2Double(pointOfstrs[i]);
		}
		return curvals;
	}
}

// ----------------------------------------------------------------------------------------
// Copyright © 2006 - 2010 Tangible Software Solutions Inc.
// This class can be used by anyone provided that the copyright notice remains
// intact.
//
// This class is used to simulate the ability to pass arguments by reference in
// Java.
// ----------------------------------------------------------------------------------------
// final class RefObject<T> {
// public T argvalue;
//
// public RefObject(T refarg) {
// argvalue = refarg;
// }
// }