
import java.util.*;

// stack implementation by LINKED LIST
// support push(), pop(), size()
// support the operations of getting & deleting the middle element
public class GXStack {
	private int count;
	private GXList.ListNode head, middle;

	public GXStack() {
		count = 0;
		head = null;
		middle = null;
	}

	public void push(int v) {
		if (head == null) {
			head = new GXList.ListNode(v);
			middle = head;
			count = 1;
			return;
		}

		GXList.ListNode n = new GXList.ListNode(v);
		n.next = head;
		head.prev = n;
		head = n;
		if (count%2 == 1)
			middle = middle.prev;
		count += 1;
	}

	public int pop() {
		if (head == null)
			return (0x80<<24);
		if (count == 1) {
			int outValue = head.data;
			head = null;
			middle = null;
			count = 0;
			return outValue;
		}

		GXList.ListNode outNode = head;
		head = outNode.next;
		if (count%2 == 0)
			middle = middle.next;
		outNode.next = null;
		head.prev = null;
		count -= 1;
		return outNode.data;
	}

	public int size() {
		return count;
	}

	public int getMiddle() {
		if (middle == null)
			return (0x80<<24);
		return middle.data;
	}

	public int deleteMiddle() {
		if (middle == null)
			return (0x80<<24);
		if (count == 1)
			return pop();
		if (count == 2)
			return pop();

		GXList.ListNode outNode = middle;
		GXList.ListNode next = outNode.next;
		GXList.ListNode prev = outNode.prev;
		outNode.next = null;
		outNode.prev = null;
		prev.next = next;
		next.prev = prev;
		if (count%2 == 0) {
			middle = next;
			count -= 1;
		}
		else {
			middle = prev;
			count -= 1;
		}
		return outNode.data;
	}


	public void print() {
		System.out.println("[Stack] size=" + count);
		GXList.ListNode curr = head;
		while (curr != null) {
			System.out.print(curr + " <- ");
			curr = curr.next;
		}
		System.out.println("|");
	}


	public static void main(String[] args) {
		GXStack s = new GXStack();
		s.push(2);
		s.print();
		System.out.println(s.getMiddle());

		s.push(4);
		s.print();
		System.out.println(s.getMiddle());

		s.push(6);
		s.print();
		System.out.println(s.getMiddle());

		s.push(8);
		s.print();
		System.out.println(s.getMiddle());

		s.push(10);
		s.print();
		System.out.println(s.getMiddle());

		s.push(20);
		s.print();
		System.out.println(s.getMiddle());

		s.push(40);
		s.print();
		System.out.println(s.getMiddle());

		s.pop();
		s.print();
		System.out.println(s.getMiddle());

		s.pop();
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());

		System.out.println(s.deleteMiddle());
		s.print();
		System.out.println(s.getMiddle());
	}

}

