

// Stack implementation using ARRAY
// *bounded*
public class GXStackInArray {

	private int len;
	private int[] space;
	private int top;
	private final int NONE = (0x80<<24);

	public GXStackInArray(int l) {
		if(l<=0) l=10;
		len = l;
		space = new int[len];
		top = 0;
	}

	public boolean push(int e) {
		if (top >= len)
			return false;
		space[top] = e;
		top += 1;
		print();
		return true;
	}

	public int pop() {
		if (top <= 0)
			return NONE;
		int out = space[top-1];
		top -= 1;
		print();
		return out;
	}

	public int size() {
		return top;
	}
	public void print() {
		String header = String.format("[StackInArray] (top=%d,max_size=%d) ", top,len);
		System.out.print(header);
		for (int i=top-1; i>=0; --i)
			System.out.print(space[i] + " ");
		System.out.println("]BOTTOM");
	}

	public static void main(String[] args) {
		GXStackInArray s = new GXStackInArray(5);
		s.push(100);
		s.push(200);
		s.pop();
		s.pop();
		s.pop();
	}

}
