package leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 你现在是棒球比赛记录员。
 * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 * <p>
 * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
 * 你需要返回你在所有回合中得分的总和。
 */
public class No682BaseballGame {
    public int calPoints(String[] ops) {
        Stack<Integer> opsStack = new Stack<>();
        int sum = 0;
        for (String op : ops) {
            /*if (op.matches("-?\\d+")) {
                int points = Integer.parseInt(op);

                opsStack.push(points);
                sum += points;
            } else*/
            if ("+".equals(op)) {
                Integer lastOps1 = opsStack.pop();
                Integer lastOps2 = opsStack.pop();

                Integer mods = lastOps1 + lastOps2;
                opsStack.push(lastOps2);
                opsStack.push(lastOps1);

                opsStack.push(mods);

                sum += mods;
            } else if ("D".equals(op)) {
                Integer mods = opsStack.peek() * 2;
                opsStack.push(mods);

                sum += mods;
            } else if ("C".equals(op)) {
                Integer mods = opsStack.pop();

                sum -= mods;
            } else {
                int points = Integer.parseInt(op);

                opsStack.push(points);
                sum += points;
            }
        }
        return sum;
    }

    /**
     * 输入: ["5","2","C","D","+"]
     * 输出: 30
     * 解释:
     * 第1轮：你可以得到5分。总和是：5。
     * 第2轮：你可以得到2分。总和是：7。
     * 操作1：第2轮的数据无效。总和是：5。
     * 第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
     * 第4轮：你可以得到5 + 10 = 15分。总数是：30。
     */
    @Test
    public void testCase1() {
        int exceptResult = 30;
        int result = calPoints(new String[]{"5", "2", "C", "D", "+"});

        Assert.assertEquals(exceptResult, result);
    }

    /**
     * 输入: ["5","-2","4","C","D","9","+","+"]
     * 输出: 27
     * 解释:
     * 第1轮：你可以得到5分。总和是：5。
     * 第2轮：你可以得到-2分。总数是：3。
     * 第3轮：你可以得到4分。总和是：7。
     * 操作1：第3轮的数据无效。总数是：3。
     * 第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
     * 第5轮：你可以得到9分。总数是：8。
     * 第6轮：你可以得到-4 + 9 = 5分。总数是13。
     * 第7轮：你可以得到9 + 5 = 14分。总数是27。
     */
    @Test
    public void testCase2() {
        int exceptResult = 27;
        int result = calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"});

        Assert.assertEquals(exceptResult, result);
    }
}
