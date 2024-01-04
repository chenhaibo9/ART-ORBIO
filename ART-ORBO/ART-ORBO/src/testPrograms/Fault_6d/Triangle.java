package testPrograms.Fault_6d;

import java.util.Arrays;

/**
 * 
 * @author zxz 
 * �ж�����������
 */
public class Triangle extends PUT_6D {
	
	public double[] min={-25, -25,-25, -25, -25, -25 };
	public double[] max={25, 25,25, 25, 25, 25 };
//	public double failureRate = 0.001623; // sample failure rate by Hilary
	public double failureRate = 0.000713;
	public int Dimension = 6;
	
	public static int triangleType(int x1, int y1, int x2, int y2, int x3, int y3) {

		double[] distance = new double[3]; // ���������߳�������
		distance[0] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		distance[1] = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
		distance[2] = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
		// �������߳���������
		Arrays.sort(distance);

		if (distance[0] + distance[1] - distance[2] <= 0) // ���̱�֮�� С�ڵ�����
			return -1; // ��������
		else {
			if (distance[0] * distance[0] + distance[1] * distance[1] > distance[2] * distance[2])
				return 1; // ���������
			else if (distance[0] * distance[0] + distance[1] * distance[1] - distance[2] * distance[2] <= 1e-20)
				return 2; // ֱ��������
			else
				return 3; // �۽�������
		}

	}

	public static int triangleTypeErr(int x1, int y1, int x2, int y2, int x3, int y3) {
		double[] distance = new double[3]; // ���������߳�������
		distance[0] = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		distance[1] = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
		distance[2] = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
		// �������߳���������
		Arrays.sort(distance);

		if (distance[0] + distance[1] - distance[2] <= 0.0001) // ERROR if (distance[0] + distance[1] - distance[2] <=0)
			return -1; // ��������
		else {
			if (distance[0] * distance[0] + distance[1] * distance[1] > distance[2] * distance[2])
				return 1; // ���������
			else if (distance[0] * distance[0] + distance[1] * distance[1] - distance[2] * distance[2] <= 1e-20)
				return 2; // ֱ��������
			else
				return 3; // �۽�������
		}
	}

	@Override
	public boolean isCorrect(int x0, int y0, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
//		if (triangleType(x0, y0, x1, y1, x2, y2) != triangleTypeErr(x0, y0, x1, y1, x2, y2)) {
//			return true;
//		}
//		return false;
		return triangleType(x0, y0, x1, y1, x2, y2) == triangleTypeErr(x0, y0, x1, y1, x2, y2);
	}

	@Override
	public double modified_fn(int x0, int y0, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		triangleTypeErr(x0, y0, x1, y1, x2, y2);
		return 0;
	}

}
