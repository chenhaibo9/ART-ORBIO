package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaiLie {
	public static ArrayList<double[]> GetAll(double[] xn, double[] yn) {
		double b[][] = new double[xn.length][2];
		for (int i = 0; i < xn.length; i++) {
			b[i] = new double[] { xn[i], yn[i] };
		}

		double a[][] = b;
		String s;
		// 总循环次数，控制循环量
		int it = (int) Math.pow((a[0].length), (a.length)) - 1;
		ArrayList<double[]> list = new ArrayList<>();
		while (it >= 0) {
			s = "";
			// it % a[0].length;
			// (it / a[0].length) % a[0].length;
			// (it / a[0].length) / a[0].length % a[0].length;
			// 临时变量，保存迭代器
			int temp = it;
			for (int m = 0; m < a.length; m++) {
				if (temp / a[0].length >= 0) {
					s += a[m][temp % a[0].length] + " ";
					temp /= a[0].length;
				}
			}
			String[] tempStr = s.split(" ");
			double[] tempVal = new double[tempStr.length];
			for (int i = 0; i < tempStr.length; i++) {
				tempVal[i] = Double.parseDouble(tempStr[i]);
			}
			list.add(tempVal);
			// System.out.println(s);
			it--;
		}
		return list;
	}

	public static void main(String[] args) {
		// 测试数组
		// double a[][] = { { 1.1, 2.1 }, { 1.2, 2.2 }, { 1.3, 2.3} };
		// String s;
		// // 总循环次数，控制循环量
		// int it = (int) Math.pow((a[0].length), (a.length)) - 1;
		// ArrayList<String> list=new ArrayList<>();
		// while (it >= 0) {
		// s = "";
		// // it % a[0].length;
		// // (it / a[0].length) % a[0].length;
		// // (it / a[0].length) / a[0].length % a[0].length;
		// // 临时变量，保存迭代器
		// int temp = it;
		// for (int m = 0; m < a.length; m++) {
		// if (temp / a[0].length >= 0) {
		// s += a[m][temp % a[0].length]+" ";
		// temp /= a[0].length;
		// }
		// }
		// list.add(s);
		// System.out.println(s);
		// it--;
		// }
		// double[] xn={1.1,2.1};
		// double[] yn={2.2,3.2};
		//
		//
		// ArrayList<double[]> lists=PaiLie.GetAll(xn, yn);
		// System.out.println("count:"+lists.size());
		// for (int i = 0; i < lists.size(); i++) {
		// System.out.println(Arrays.toString(lists.get(i)));
		// }
		// double p[]=new double[]{1.5,2.5};
		// ArrayList<List<double[]>> results=PaiLie.reOrder(p, lists);
		// List<double[]> results0=results.get(0);
		// List<double[]> results1=results.get(1);
		// for (int i = 0; i < results0.size(); i++) {
		// System.out.println("0:"+Arrays.toString(results0.get(i)));
		// System.out.println("1:"+Arrays.toString(results1.get(i)));
		// }
		// double[] start= {0.1,0.2,0.3};
		// double[] pxn= {0.4,0.5,0.6};
		// //change to 0.1,0.4 agroup 0.2,0.5 a group
		// double[] end= {0.5,0.6,0.7};
		//
		// List<List<Double>> result1=splitRegions(start, pxn);
		// List<List<Double>> result2=splitRegions(pxn, end);
		//
		// for(int i=0;i<result1.size();i++) {
		// List<Double> temp1=result1.get(i);
		// List<Double> temp2=result2.get(i);
		// System.out.println(temp1+","+temp2);
		// }

	}
	// public static List<List<Double>> splitRegions(double[] start,double[] end){
	// ArrayList<double[]> values=new ArrayList<>();
	// for(int i=0;i<start.length;i++) {
	// double[] temp=new double[2];
	//
	// temp[0]=start[i];
	// temp[1]=end[i];
	// values.add(temp);
	// }
	//
	//
	//
	// ArrayList<List<Double>> result=new ArrayList<>();
	// per(values, 0, new ArrayList<>(), result);
	// return result;
	// }

	public static void per(ArrayList<double[]> values, int index, ArrayList<Double> currentList,
			ArrayList<List<Double>> result) {

		if (index == values.size()) {
			// System.out.println(currentList);
			List<Double> temp = new ArrayList<>(currentList);
			result.add(temp);
			return;
		}
		double[] temp = values.get(index);

		index++;
		for (int i = 0; i < temp.length; i++) {
			// System.out.println("index:" + index + " value;" + temp[i]);
			currentList.add(temp[i]);
			per(values, index, currentList, result);
			currentList.remove(currentList.size() - 1);
		}
	}

	public static ArrayList<List<double[]>> reOrder(double p[], ArrayList<double[]> lists) {

		ArrayList<double[]> start = new ArrayList<>();
		ArrayList<double[]> end = new ArrayList<>();
		ArrayList<List<double[]>> results = new ArrayList<>();
		for (int i = 0; i < lists.size(); i++) {
			double q[] = Arrays.copyOf(lists.get(i), lists.get(i).length);
			double min[] = new double[p.length];
			double max[] = new double[p.length];
			for (int j = 0; j < p.length; j++) {

				if (p[j] < q[j]) {
					min[j] = p[j];
					max[j] = q[j];
				} else {
					max[j] = p[j];
					min[j] = q[j];
				}
			}
			start.add(min);
			end.add(max);
			results.add(start);
			results.add(end);
			// System.out.println("pailie:"+Arrays.toString(min)+","+Arrays.toString(max));
		}

		return results;
	}
}
