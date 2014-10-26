
import java.util.*;

public class PerformMetrics {

	public static Iterable<GX.Pair<Double,Double>> roc(int[] labels, double[] decValues) {
		if (labels.length != decValues.length)
			return null;

		Integer[] indices = new Integer[labels.length];
		for (int i=0; i<indices.length; ++i)
			indices[i] = i;
		Arrays.sort(indices, new GX.IndexCMP(decValues));
		Arrays.sort(decValues);

		int pos=0, neg=0;
		for (int i=0; i<labels.length; ++i) {
			if (labels[i] > 0)
				pos += 1;
			else
				neg += 1;
		}

		//output
		LinkedList<GX.Pair<Double,Double>> points = new LinkedList<GX.Pair<Double,Double>>();

		int currTP = 0;
		int currFP = 0;
		double currTPR = 0.0;
		double currFPR = 0.0;
		points.add(new GX.Pair<Double,Double>(0.0,0.0));
		for (int i=decValues.length-1; i>=0; --i) {
			// use decValues[i] as threshold, decValues[] (>= decValues[i]) are predicted as positive; otherwise, negative.
			int y = labels[indices[i]];
			if (y > 0)
				currTP += 1;
			else
				currFP += 1;
			currTPR = (double)currTP/pos;
			currFPR = (double)currFP/neg;
			points.add(new GX.Pair<Double,Double>(Double.valueOf(currFPR),Double.valueOf(currTPR)));
		}

		System.out.println(points);
		return points;
	}

	public static void main(String[] args) {

		int[] labels = {1, -1, 1, -1, 1};
		double[] decValues = {0.1, 0.4, 0.2, 0.9, 0.0};

		roc(labels, decValues);

	}


}
