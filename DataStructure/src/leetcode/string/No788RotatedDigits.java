package leetcode.string;

import org.junit.Assert;
import org.junit.Test;

public class No788RotatedDigits {
    public int rotatedDigits(int N) {
        int sum = 0;
        for (int number = 1; number <= N; number++) {
            if (isGoodNumber(number)) {
                sum++;
                System.out.println(number);
            }
        }

        return sum;
    }

    private boolean isGoodNumber(int num) {
        char[] number = String.valueOf(num).toCharArray();

        for (int i = 0; i < number.length; i++) {
            char ch = number[i];

            if (invalidNumber(ch)) {
                return false;
            }
            number[i] = rotateDigits(number[i]);
        }

        int rotatedNumber = Integer.parseInt(new String(number));
        return rotatedNumber != num;
    }

    private char rotateDigits(char digits) {
        switch (digits) {
            case '2': {
                return '5';
            }
            case '5': {
                return '2';
            }
            case '6': {
                return '9';
            }
            case '9': {
                return '6';
            }
        }
        return digits;
    }

    private boolean invalidNumber(char number) {
        return number == '3' || number == '4' || number == '7';
    }
    /**
     * static int[] ROTATEDIGITS={0,1,5,-1,-1,2,9,-1,8,6};
     *
     *     public int rotatedDigits(int N) {
     *         int res=0;
     *         for(int i=1;i<=N;i++)
     *             if(isGoodNumber(i))
     *                 res++;
     *         return res;
     *     }
     *
     *
     *
     *     public boolean isGoodNumber(int num){
     *         int res=0;
     *         int n=num;
     *         int bit=1;
     *         while(n!=0){
     *             int t = ROTATEDIGITS[n % 10];
     * 			if (t == -1)
     * 				return false;
     *             res=res+t*bit;
     *             bit=bit*10;
     *             n=n/10;
     *         }
     *         return res!=num;
     *     }
     */

    /**
     * 示例:
     * 输入: 10
     * 输出: 4
     * 解释:
     * 在[1, 10]中有四个好数： 2, 5, 6, 9。
     * 注意 1 和 10 不是好数, 因为他们在旋转之后不变。
     */
    @Test
    public void testCase() {
        int exceptResult = 4;

        int result = rotatedDigits(10);

        Assert.assertEquals(exceptResult, result);
    }
}
