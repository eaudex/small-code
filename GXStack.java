
import java.util.*;

// linked list implementation
public class GXStack {
	private int count;
	private GXList.ListNode head;

	public GXStack() {
		count = 0;
		head = null;
	}

	public void push(int v) {
		if (head == null) {
			head = new GXList.ListNode(v);
			count = 1;
			return;
		}
		GXList.ListNode n = new GXList.ListNode(v);
		n.next = head;
		head = n;
		count += 1;
	}

	public GXList.ListNode pop() {
		if (head == null)
			return null;

		GXList.ListNode outNode = head;
		head = outNode.next;
		count -= 1;
		return outNode;
	}

	public int size() {
		return count;
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
		s.push(1);
		s.push(2);
		s.pop();
		s.pop();
		s.pop();
		s.print();
	}

}

