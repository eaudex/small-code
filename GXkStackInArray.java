
// K-Stack implementation using ARRAY
// bounded
public class GXkStackInArray {
	private int ken, len;
	private int[] space;
	private int[] links;
	private int[] tops;
	private int global_index;
	private final int NONE = (0x80<<24);

	public GXkStackInArray(int k, int l) {
		if(k<=0) k=1;
		if(l<=0) l=10;
		ken = k;
		len = l;
		space = new int[len];
		links = new int[len];
		for(int i=0; i<len; ++i) links[i]=i+1;
		tops = new int[ken];
		for(int i=0; i<ken; ++i) tops[i]=-1;
		global_index = 0;
	}

	public boolean push(int e, int k) {
		if (k >= ken)
			return false;
		if (global_index >= len)
			return false;
		int tdx = tops[k];
		int next = links[global_index];
		space[global_index] = e;
		links[global_index] = tdx;
		tops[k] = global_index;
		global_index = next;
		for(int kk=0; kk<ken; ++kk) print(kk);
		return true;
	}

	public int pop(int k) {
		if (k >= ken)
			return NONE;
		if (tops[k] < 0)
			return NONE;
		int tdx = tops[k];
		int out = space[tdx];
		tops[k] = links[tdx];
		links[tdx] = global_index;
		global_index = tdx;
		for(int kk=0; kk<ken; ++kk) print(kk);
		return out;
	}

	public void print(int k) {
		if (k >= ken)
			return;
		System.out.println("[Stack-" + k + "] ");
		int tdx = tops[k];
		while (tdx >= 0) {
			System.out.print(space[tdx] + " ");
			tdx = links[tdx];
		}
		System.out.println("]BOTTOM");
	}

	public static void main(String[] args) {
		GXkStackInArray sk = new GXkStackInArray(2,5);
		sk.push(1,0);
		sk.push(100,1);
		sk.push(2,0);
		sk.push(200,1);
		sk.push(3,0);
		sk.push(300,1);
		sk.pop(1);
		sk.push(300,1);
		sk.pop(0);
		sk.pop(0);
		sk.pop(0);
		sk.pop(0);
		sk.pop(1);
		sk.pop(1);
		sk.push(300,1);
	}

}
