
import java.util.*;

// *be aware of nullity of a node before referencing its next/previous/data members*
// treat head node as special when applying operatios on a node of given list

public class GXList {
	private static final Random rnd = new Random();

	public static class ListNode {
		public int data;
		public ListNode next;
		public ListNode prev;
		public ListNode(int d) {
			data = d;
			next = null;
			prev = null;
		}
		@Override
		public String toString() {
			return "("+data+")";
		}

		public ListNode genNext() {
			int d = rnd.nextInt(100);
			ListNode nextNode = new ListNode(d);
			this.next = nextNode;
			nextNode.prev = this.next;
			return nextNode;
		}
	}


	// gen ranodm linked list of given size
	public static ListNode genList(int length) {
		if (length <= 0)
			return null;
		int d = rnd.nextInt(100);
		ListNode root = new ListNode(d);
		ListNode curr = root;
		for (int i=1; i<length; ++i) {
			curr = curr.genNext();
		}
		return root;
	}

	public static void print(ListNode head) {
		printUtil(head, null);
	}
	public static void printUtil(ListNode head, ListNode tail) {
		System.out.print("[LinkedList] |");
		ListNode curr = head;
		while (curr!=null && curr!=tail) {
			System.out.print(" -> " + curr);
			curr = curr.next;
		}
		if (curr == null)
			System.out.print(" -> (null)");
		else
			System.out.print(" -> " + tail);
		System.out.println();
	}

	//public static int size(ListNode root) {}
	//public static ListNode search(int d) {}

	// swap two nodes by changing values
	public static boolean swap(ListNode n1, ListNode n2) {
		if (n1==null || n2==null)
			return false;
		if (n1 == n2)
			return true;
		int tmp = n1.data;
		n1.data = n2.data;
		n2.data = tmp;
		return true;
	}
	// iterative swap pairwise by values
	public static void swapPairwiseIter(ListNode list) {
		if (list == null)
			return;
		ListNode curr = list;
		while (curr!=null && curr.next!=null) {
			swap(curr, curr.next);
			curr = curr.next.next;
		}
	}
	// recursive swap pairwise by values
	public static void swapPairwiseRecur(ListNode list) {
		if (list==null || list.next==null)
			return;
		swap(list, list.next);
		swapPairwiseRecur(list.next.next);
	}

	// swap pairwse by links
	public static ListNode swapPairwiseLinks(ListNode list) {
		if (list == null)
			return null;

		ListNode prev=null, curr=list, newHead=list;
		while (curr!=null && curr.next!=null) {
			ListNode next = curr.next;
			if (prev != null)
				prev.next = next;
			else
				newHead = next;
			curr.next = next.next;
			next.next = curr;

			prev = curr;
			curr = curr.next;
		}
		return newHead;
	}

	/*
	public static void deleteNodes(ListNode startNode, ListNode endNode) {
	}
	public static ListNode _deleteNode(ListNode startNode, ListNode endNodte, ListNode fromNode) {
		if (list == null)
			return;
		_deleteNode(list.next, list);
		list = null;
		return fromNode;
	}
	*/

	// delete sublist from startIdx to endIdx (inclusive) from given list
	public static ListNode deleteList(ListNode list, int startIdx, int endIdx) {
		if (list == null)
			return null;
		if (startIdx > endIdx)
			return null;

		int i;
		ListNode prev=null, curr=list;
		for (i=0; i<startIdx && curr!=null; ++i) {
			prev = curr;
			curr = curr.next;
		}
		if(curr==null) return list;
		ListNode prevStartNode = prev;
		ListNode startNode = curr;

		for (i=0; i<endIdx-startIdx && curr!=null; ++i) {
			curr = curr.next;
		}
		ListNode endNode = curr;

		if (prevStartNode != null) {
			if (endNode == null)
				prevStartNode.next = null;
			else
				prevStartNode.next = endNode.next;
		}
		else {
			if (endNode == null)
				return null;
			else
				list = endNode.next;
		}
		return list;
	}

	// buggy....
	public static void quickSort(ListNode list) {
		if (list==null || list.next==null)
			return;
		ListNode prev=null, curr=list;
		while (curr != null) {
			prev = curr;
			curr = curr.next;
		}
		_quickSort(list, prev);
	}
	private static void _quickSort(ListNode startNode, ListNode endNode) {
		if (startNode==null || endNode==null || startNode==endNode)
			return;
		ListNode pivotNode = _partition(startNode, endNode);
		_quickSort(startNode, pivotNode);
		_quickSort(pivotNode.next, endNode);
	}
	private static ListNode _partition(ListNode startNode, ListNode endNode) {
		ListNode pivotNode = startNode;
		int pivotValue = pivotNode.data;
		swap(pivotNode, endNode);
		ListNode finalNode = startNode;
		ListNode curr = startNode;
		while (curr != endNode) {
			if (curr.data < pivotValue) {
				swap(curr, finalNode);
				finalNode = finalNode.next;
			}
			curr = curr.next;
		}
		swap(finalNode, endNode);
		return finalNode;
	}


	public static void main(String[] args) {
		ListNode n = new ListNode(5);
		GXList.print(n);
		n.genNext();
		GXList.print(n);

		// gen random list
		ListNode r = genList(10);
		GXList.print(r);
		//swap two nodes
		swap(r, r.next);
		GXList.print(r);

		//swap nodes pairwise
		swapPairwiseIter(r);
		GXList.print(r);
		swapPairwiseRecur(r);
		GXList.print(r);
		ListNode rr = swapPairwiseLinks(r);
		GXList.print(rr);

		ListNode rrr = deleteList(rr, 0, 8);
		GXList.print(rrr);

		// buggy
		//quickSort(r);
		//r.print();
	}

}

