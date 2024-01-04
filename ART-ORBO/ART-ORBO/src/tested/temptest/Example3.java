
package tested.temptest;

import java.util.LinkedList;

public class Example3 {

	public static void main(String args[]) {
		LinkedList<String> mylist = new LinkedList<>();
		mylist.add("is");
		mylist.add("a");
		int number = mylist.size();
		System.out.println("现在链表中有" + number + "个节点:");
		for (int i = 0; i < number; i++) {
			String temp = (String) mylist.get(i);
			System.out.println("第" + i + "节点中的数据:" + temp);
		}
		mylist.addFirst("It");
		mylist.addLast("door");
	}
}
