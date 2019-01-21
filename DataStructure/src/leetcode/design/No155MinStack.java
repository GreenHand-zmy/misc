package leetcode.design;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 */
public class No155MinStack {
    static class MinStack {
        private Stack<Integer> stack;
        private Integer min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            cacheMin(x);
            stack.push(x);

        }

        private void cacheMin(int x) {
            if (x < min) {
                min = x;
            }
        }

        private void reCacheMin(int x) {
            if (x == min) {
                Integer min = Integer.MAX_VALUE;
                for (Integer num : stack) {
                    if (num < min) {
                        min = num;
                    }
                }

                this.min = min;
            }
        }

        public void pop() {
            reCacheMin(stack.pop());
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
