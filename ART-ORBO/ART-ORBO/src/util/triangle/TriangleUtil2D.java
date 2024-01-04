package util.triangle;


import datastructure.ND.NPoint;
import datastructure.TD.TriangleRegion2D;

public class TriangleUtil2D {
	public static  boolean IsPointInTriangle(NPoint pointA,NPoint pointB,NPoint pointC, NPoint  pointP)
    {
		TriangleRegion2D region=new TriangleRegion2D(pointA, pointB, pointC);
		double S=region.getSquare();
		
		TriangleRegion2D region1 = new TriangleRegion2D();
		region1.setPs(pointA, pointB, pointP);
		double s1 = region1.getSquare();
		TriangleRegion2D region2 = new TriangleRegion2D();
		region2.setPs(pointB, pointC, pointP);
		double s2 = region2.getSquare();
		TriangleRegion2D region3 = new TriangleRegion2D();
		region3.setPs(pointA, pointC, pointP);
		double s3 = region3.getSquare();
		if(Math.abs(s1+s2+s3-S)<0.0000000001){
			return true;
		}else{
			return false;
		}
    }
}
