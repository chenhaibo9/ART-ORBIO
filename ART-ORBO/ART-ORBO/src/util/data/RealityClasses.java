package util.data;

import tested.airy;
import tested.bessj;
import tested.bessj0;
import tested.cel;
import tested.el2;
import tested.erfcc;
import tested.gammq;
import tested.golden;
import tested.plgndr;
import tested.probks;
import tested.sncndn;
import tested.tanh;

public class RealityClasses {
	public static Class<?>[] get(){
		 Class<?>[] classes={airy.class,bessj0.class,erfcc.class,probks.class,tanh.class,
				 	sncndn.class,golden.class,el2.class,
				 	bessj.class,gammq.class,cel.class,plgndr.class};
		//Class<?>[] classes={airy.class,bessj.class};
		return classes;
	}
//	public static double[] getDoubles(Class classes,String colum){
//		try {
//			return (double[])(classes.getDeclaredField(colum).get(null));
//		} catch (Exception e) {
//			System.out.println("error in RealityClasses");
//			e.printStackTrace();
//		}finally {
//			return null;
//		} 
//		
//	}
//	public static int getInt(Class classes,String colum){
//		try {
//			return (int)(classes.getDeclaredField(colum).get(null));
//		} catch (Exception e) {
//			System.out.println("error in RealityClasses");
//			e.printStackTrace();
//		}finally {
//			return 0;
//		} 
//	}
//	public static double getDouble(Class classes,String colum){
//		try {
//			return (double)(classes.getDeclaredField(colum).get(null));
//		} catch (Exception e) {
//			System.out.println("error in RealityClasses");
//			e.printStackTrace();
//		}finally {
//			return 0.0;
//		} 
//	}
}
