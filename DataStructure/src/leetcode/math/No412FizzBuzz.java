package leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * <p>
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * <p>
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 */
public class No412FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();

        for (int num = 1; num <= n; num++) {
            if (num % 15 == 0) {
                res.add("FizzBuzz");
            } else if (num % 3 == 0) {
                res.add("Fizz");
            } else if (num % 5 == 0) {
                res.add("Buzz");
            } else {
                res.add(num + "");
            }
        }
        return res;
    }

    @Test
    public void testCase1(){
        System.out.println(fizzBuzz(15));
    }
}
