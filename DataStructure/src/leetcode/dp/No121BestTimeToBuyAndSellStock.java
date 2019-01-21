package leetcode.dp;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 */
public class No121BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        return maxProfit2(prices);
    }

    private int maxProfit1(int[] prices) {
        int profit = 0;

        for (int buyingDay = 0; buyingDay < prices.length; buyingDay++) {

            for (int sellingDay = buyingDay + 1; sellingDay < prices.length; sellingDay++) {
                int curProfit = prices[sellingDay] - prices[buyingDay];
                if (curProfit > profit) {
                    profit = curProfit;
                }
            }
        }
        return profit;
    }

    private int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    /**
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     */
    @Test
    public void testCase1() {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /**
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    @Test
    public void testCase2() {
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
    }
}
