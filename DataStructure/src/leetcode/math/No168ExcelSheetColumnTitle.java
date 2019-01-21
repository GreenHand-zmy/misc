package leetcode.math;

import org.junit.Assert;
import org.junit.Test;

public class No168ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            n--;
            result.insert(0, (char) ('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

    @Test
    public void testCase1() {
        String title = convertToTitle(1);
        Assert.assertEquals("A", title);
    }

    @Test
    public void testCase2() {
        String title = convertToTitle(28);
        Assert.assertEquals("AB", title);
    }

    @Test
    public void testCase3() {
        String title = convertToTitle(701);
        Assert.assertEquals("ZY", title);
    }
}
