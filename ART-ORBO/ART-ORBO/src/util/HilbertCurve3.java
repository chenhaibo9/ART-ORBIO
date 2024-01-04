package util;

import java.util.Arrays;

public class HilbertCurve3 {
	int ORDER=32;
	int DIM=3;
	long g_mask[] = { 4, 2, 1};
	long calc_P (int i, Hcode H)
	{
	    int element;
	    long P, temp1, temp2;
	    element = i / ORDER;
	    P = H.hcode[element];
	    if (i % ORDER > ORDER - DIM)
	    {
	        temp1 = H.hcode[element + 1];
	        P >>= i % ORDER;
	        temp1 <<= ORDER - i % ORDER;
	        P |= temp1;
	    }
	    else
	    P >>= i % ORDER; /* P is a DIM bit hcode */
	    /* the & masks out spurious highbit values */
	    if( DIM < ORDER)
	    P &= (1 << DIM) -1;
	    
	    return P;
	}
	long calc_P2(long S)
	{
	    int i;
	    long P;
	    P = S & g_mask[0];
	    for (i = 1; i < DIM; i++)
	    if(( S & g_mask[i] ^ (P >> 1) & g_mask[i])!=0)
	    P |= g_mask[i];
	    return P;
	}
	long calc_J (long P)
	{
	    int i;
	    long J;
	    J = DIM;
	    for (i = 1; i < DIM; i++)
	    if ((P >> i & 1) == (P & 1))
	    continue;
	    else
	    break;
	    if (i != DIM)
	    J -= i;
	    return J;
	}
	long calc_T (long P)
	{
	    if (P < 3)
	    return 0;
	    if ((P % 2)!=0)
	    return (P - 1) ^ (P - 1) / 2;
	    return (P - 2) ^ (P - 2) / 2;
	}
	/*===========================================================*/
	/* calc_tS_tT */
	/*===========================================================*/
	long calc_tS_tT(long xJ, long val)
	{
	    long retval, temp1, temp2;
	    retval = val;
	    if (xJ % DIM != 0)
	    {
	        temp1 = val >> xJ % DIM;
	        temp2 = val << DIM - xJ % DIM;
	        retval = temp1 | temp2;
	        retval &= ((long)1 << DIM) - 1;
	    }
	    return retval;
	}
	Hcode H_decode (Hcode H)
	{
	    long mask = (long)1 << ORDER - 1,
	    A, W = 0, S, tS, T, tT, J, P = 0, xJ;
	    Hcode pt=new Hcode();
	    pt.hcode=new long[DIM];
	    //Point pt = {0};
	    int i = ORDER * DIM - DIM, j;
	    P = calc_P(i, H);
	    J = calc_J(P);
	    xJ = J - 1;
	    A = S = tS = P ^ P / 2;
	    T = calc_T(P);
	    tT = T;
	    /*--- distrib bits to coords ---*/
	    for (j = DIM - 1; P > 0; P >>=1, j--)
	    if ((P & 1)!=0)
	    pt.hcode[j] |= mask;
	    for (i -= DIM, mask >>= 1; i >=0; i -= DIM, mask >>= 1)
	    {
	        P = calc_P(i, H);
	        S = P ^ P / 2;
	        tS = calc_tS_tT(xJ, S);
	        W ^= tT;
	        A = W ^ tS;
	        /*--- distrib bits to coords ---*/
	        for (j = DIM - 1; A > 0; A >>=1, j--)
	        if ((A & 1)!=0)
	        pt.hcode[j] |= mask;
	        if (i > 0)
	        {
	            T = calc_T(P);
	            tT = calc_tS_tT(xJ, T);
	            J = calc_J(P);
	            xJ += J - 1;
	        }
	    }
	    return pt;
	}
	public static void main(String[] args) {
		HilbertCurve3 test=new HilbertCurve3();
		Hcode origin=new Hcode();
		origin.hcode=new long[]{1,1,1};
		Hcode agter=test.H_decode(origin);
		System.out.println(Arrays.toString(agter.hcode));
	}
}
class Hcode  {
    long hcode[];
};
//typedef Hcode Point;
