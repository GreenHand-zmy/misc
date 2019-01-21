package leetcode.math;

import org.junit.Test;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 */
public class XNo204CountPrimes {
    public int countPrimes(int n) {
        return countPrimes1(n);
    }

    public int countPrimes2(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }

        return count;
    }

    public int countPrimes1(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int num = 2; num < n; num++) {
            if (isPrime[num]) {
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入: 10
     * 输出: 4
     * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     */
    @Test
    public void testCase1() {
        System.out.println(countPrimes(Integer.MAX_VALUE / 2));
    }

    @Test
    public void testCase2() {
        for (int num = 1; num < Integer.MAX_VALUE; num++) {
            if (isPrime(num)) {
                System.out.println(num);
            }
        }
    }
}
