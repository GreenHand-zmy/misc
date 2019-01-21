package leetcode.math;

import org.junit.Test;

/**
 * 有1000只水桶，其中有且只有一桶装的含有毒药，其余装的都是水。它们从外观看起来都一样。
 * 如果小猪喝了毒药，它会在15分钟内死去。
 * <p>
 * 问题来了，如果需要你在一小时内，弄清楚哪只水桶含有毒药，你最少需要多少只猪？
 * <p>
 * 回答这个问题，并为下列的进阶问题编写一个通用算法。
 * <p>
 * 进阶:
 * <p>
 * 假设有 n 只水桶，猪饮水中毒后会在 m 分钟内死亡，你需要多少猪（x）就能在 p 分钟内找出“有毒”水桶？
 * n只水桶里有且仅有一只有毒的桶。
 */
public class XNo458PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int times = minutesToTest % minutesToDie == 0
                ? minutesToTest / minutesToDie : (minutesToTest / minutesToDie) - 1;

        return buckets / times;
    }

    @Test
    public void testCase1() {
        System.out.println(poorPigs(1000, 15, 60));
    }
}
