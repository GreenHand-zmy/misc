package leetcode.string;

import org.junit.Test;

public class No38CountAndSay {
    public String countAndSay(int n) {
        StringBuilder init = new StringBuilder("1");

        for (int i = 1; i < n; i++) {
            StringBuilder temp = new StringBuilder();

            for (int j = 0; j < init.length(); j++) {
                StringBuilder sb = new StringBuilder();

                int k = j;
                while (k < init.length() - 1 && init.charAt(j) == init.charAt(k + 1)) {
                    k++;
                }

                if (k - j > 0) {
                    sb.append(k - j + 1).append(init.charAt(j));
                } else {
                    sb.append(1).append(init.charAt(j));
                }

                temp.append(sb);
                j = k;
            }

            init = new StringBuilder(temp);
        }

        return init.toString();
    }

    /**
     * 输入: 1
     * 输出: "1"
     */
    @Test
    public void testCase1() {
        System.out.println(countAndSay(1));
    }

    /**
     * 输入: 4
     * 输出: "1211"
     */
    @Test
    public void testCase2() {
        System.out.println(countAndSay(6));
    }
}
