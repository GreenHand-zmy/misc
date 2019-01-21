package leetcode.string;

import org.junit.Assert;
import org.junit.Test;

public class No657JudgeCircle {
    public boolean judgeCircle(String moves) {
        // 机器人初始位置
        int x = 0, y = 0;

        // 遍历运动轨迹
        for (char move : moves.toCharArray()) {
            // 根据运动方向改变坐标
            if (move == 'R') {
                x += 1;
            } else if (move == 'L') {
                x -= 1;
            } else if (move == 'U') {
                y += 1;
            } else if (move == 'D') {
                y -= 1;
            }
        }
        return x == 0 && y == 0;
    }

    @Test
    public void testCase1() {
        No657JudgeCircle run = new No657JudgeCircle();
        boolean result = run.judgeCircle("UD");
        Assert.assertTrue(result);
    }

    @Test
    public void testCase2() {
        No657JudgeCircle run = new No657JudgeCircle();
        boolean result = run.judgeCircle("LL");
        Assert.assertFalse(result);
    }
}
