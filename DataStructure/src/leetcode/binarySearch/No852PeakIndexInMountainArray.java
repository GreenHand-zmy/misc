package leetcode.binarySearch;

import org.junit.Assert;
import org.junit.Test;

public class No852PeakIndexInMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        return peakIndexInMountainArray2(A);
    }

    public int peakIndexInMountainArray1(int[] A) {
        int peakIndex = -1;

        if (A.length < 3) {
            return peakIndex;
        }

        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                peakIndex = i;
                return peakIndex;
            }
        }
        return peakIndex;
    }

    public int peakIndexInMountainArray2(int[] A) {
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    @Test
    public void testCase1() {
        int[] data = new int[]{0, 1, 0};
        int index = peakIndexInMountainArray(data);
        Assert.assertEquals(1, index);
    }

    @Test
    public void testCase2() {
        int[] data = new int[]{0, 2, 1, 0};
        int index = peakIndexInMountainArray(data);
        Assert.assertEquals(1, index);
    }
}
