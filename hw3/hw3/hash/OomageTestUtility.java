package hw3.hash;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        List<Oomage>[] buckets = new List[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (Oomage o: oomages) {
            if (o == null) {
                throw new NullPointerException("can't add null" +
                        "to list");
            }
            int index = (o.hashCode() & 0x7fffffff) % M;
            if (!buckets[index].contains(o)) {
                buckets[index].add(o);
            }
        }
        int N = oomages.size();
        double lowBound = N / 50, highBound = N / 2.5;
        for (List each: buckets) {
            int len = each.size();
            if (len < lowBound || len > highBound) {
                return false;
            }
        }
        return true;
    }
}
