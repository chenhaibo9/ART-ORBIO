package util;

import java.util.ArrayList;

public class HilbertCurve {

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
	public static void main(String[] args) {
		HilbertCurve hilbertCurve = new HilbertCurve();
		double results[] = hilbertCurve.oneD_2_nD(0.8886545227094472, 4);
		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
	}

	public final void ADD(int dst, int u, int v, double[][] abcd, double[][] tmp) {
		abcd[(dst)][0] = (tmp[(u)][0] + tmp[v][0]) / 2.0;
		abcd[(dst)][1] = (tmp[(u)][1] + tmp[(v)][1]) / 2.0;
	}

	// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no directv
	// equivalent in Java:
	// ORIGINAL LINE: public void ADD(int dst,int u, int v,uint[,] abcd,uint[,]
	// tmp)
	public final void ADD(int dst, int u, int v, int[][] abcd, int[][] tmp) {
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
	public final void convert_hilbert_key(int key, int numbits, RefObject<Integer> xx, RefObject<Integer> yy) {

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint[,] abcd = new uint[4, 2] { { 0, 0 }, { 0, 1 }, { 1, 1 },
		// { 1, 0 } };
		int[][] abcd = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		// ({0, 0}, {0,1}, {1, 1}, {1, 0});
		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint[,] tmp = new uint[4, 2];
		int[][] tmp = new int[4][2];
		while (key > 1) // should be > 0, but this is safer for (invalid) odd numbers
		{
			// uint[,] tmp=new uint[4,2]; /* save abcd here */
			byte subcell; // unsigned char subcell;
			for (int j = 0; j < 1; j++) {
				for (int k = 0; k < 4; k++) {
					tmp[k][j] = abcd[k][j];
				}
			}
			// memcpy(tmp, abcd, sizeof tmp); /* save our 4 points with the new ones */
			numbits -= 2; // each subdivision decision takes 2 bits
			// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no
			// direct equivalent in Java:
			// ORIGINAL LINE: uint u_subcell=(key >> numbits) & 3;
			int u_subcell = (key >> numbits) & 3;
			subcell = Byte.parseByte((new Integer(u_subcell)).toString());
			// namely these two (taken from the top)
			key &= (int) ((1 << numbits) - 1);
			// update key by removing the bits we used
			// * Add the two points with indices u and v (in tmp) and store the result at
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
		System.out.println(
				"二维点为x=" + (new Integer(xx.argvalue)).toString() + ";y=" + (new Integer(yy.argvalue)).toString());
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
	public final ArrayList convert_hilbert_key_V1(int key, int numbits, RefObject<Double> xx, RefObject<Double> yy) {

		double[][] abcd = new double[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
		// ({0,0}, {0, 1}, {1, 1}, {1, 0});
		// unit square
		double[][] tmp = new double[4][2];
		java.util.ArrayList nds = new java.util.ArrayList();
		while (numbits > 1) // should be > 0, but this is safer for (invalid) odd numbers
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
			int u_subcell = (key >> numbits) & 3;
			subcell = Byte.parseByte((new Integer(u_subcell)).toString());
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

	/**
	 * 小于1的小数转换为二进制
	 * 
	 * @param val_double
	 * @return
	 */
	public final String Double2Bin(double val_double) {
		double ud = 2 * val_double;
		StringBuilder bin_sb = new StringBuilder("");
		double converVal = 0;
		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint i=1;
		int i = 1;
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

			// bin_sb.Append(eachbit.ToString().Substring(0,1));
			if (ud > 1) {
				ud = (ud - 1) * 2;
			} else if (ud < 1) {
				ud = ud * 2;
			}
			if ((ud - 1) == 0) {
				bin_sb.append("1");
				break;

			}

			if (Math.abs(converVal - val_double) < resolution) // 达到一定的精度
			{

				break;
			}

		}
		return "0." + bin_sb.toString();
		// Convert.ToUInt32(bin_sb.ToString(), 2);
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
	public final String[] HilbertCode2Coordinates(String hilbercode, int dim) {
		final int Wordbits = 32;
		final int OrderOfHilbert = 32;
		int ndimbits = hilbercode.length();
		int dim_int = Integer.parseInt((new Integer(dim)).toString());

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
		String[] point = new String[dim];

		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint mask = (uint)1 << Wordbits - 1;
		int mask = (int) 1 << Wordbits - 1; // 31个1
		// C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct
		// equivalent in Java:
		// ORIGINAL LINE: uint element, temp1, temp2, A, W = 0, S, tS, T, tT, J,
		// P = 0, xJ;
		int element, temp1, temp2, A, W = 0, S, tS, T, tT, J, P = 0, xJ;

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
			J = J - Integer.parseInt((new Integer(j)).toString());
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
		// for (int kindex = 0; kindex < dim_int; kindex++)
		// {
		// // point[kindex] = point[kindex] >> 27;
		// Console.WriteLine(kindex.ToString() + ":" +
		// point[kindex].ToString());
		// }
		return point;

	}

	//// 下面这个方法将求解一维到多维进行封装 by XJX
	public final double[] oneD_2_nD(double value_10jinzhi, int dim) {
		double[] curvals = new double[dim];
		String bin_str = Double2Bin(value_10jinzhi);
		int numOfbits = bin_str.length() - 2;
		if (numOfbits > 32) {
			numOfbits = 32;
		}
		String[] pointOfstrs = HilbertCode2Coordinates(bin_str.substring(2, 2 + numOfbits), (int) dim);

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
final class RefObject<T> {
	public T argvalue;

	public RefObject(T refarg) {
		argvalue = refarg;
	}
}