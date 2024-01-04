package tested.temptest;

public class Example2 {
	public static void main(String args[]) {
		int n = 23, start, end, middle;
		int a[] = { -2, 1, 4, 5, 8, 12, 17, 23, 45, 56, 90, 100 };
		start = 0;
		end = a.length;
		middle = (start + end) / 2;
		int count = 0;
		while (n != a[middle]) {
			if (n > a[middle]) {
				start = middle;
			} else if (n < a[middle]) {
				end = middle;
			}
			middle = (start + end) / 2;
			count++;
			if (count > a.length / 2)
				break;
		}
		if (count > a.length / 2)
			System.out.println(":" + n + "不在数组中");
		else
			System.out.println(":" + n + "是数组中的第" + middle + "个元素");
	}
}
