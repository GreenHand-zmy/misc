package chapter1.exam;

import chapter1.content.Stack;

import java.util.NoSuchElementException;

/**
 * Created by ZMY on 2017/7/15.
 */
public class Exam {

    /**
     * exam 1.1.9
     * 将一个正整数n用二进制表示
     *
     * @param N
     * @return
     */
    public static String toBinaryString(int N) {
        String s = "";
        for (int n = N; n > 0; n /= 2) {
            s = (n % 2) + s;
        }
        return s;
    }

    /**
     * exam 1.1.14
     * 输入整数，返回不大于log2N的最大整数
     *
     * @param n
     * @return
     */
    public static int lg(int n) {
        /*
           思路：假设log2N = x，那么可以转化成2^x = N
           那么就可以通过循环让一个数从1开始一直乘以2直到大于N为止

           log2N ≥ x
           2^(log2N) ≥ 2^x
           N ≥ 2^x；
         */
        int m = 0;
        for (int i = 2; i <= n; i *= 2) {
            m++;
        }
        return m;
    }

    /**
     * 计算ln(n!)
     *
     * @param n
     * @return
     */
    public static double cal(int n) {
        /**
         *  思路：ln(n!) = ln(n)+ln(n-1)+ln(n-2)+...+ln(1)
         */
        if (n == 1) {
            return 0;
        }
        return Math.log(n) + cal(n - 1);
    }

    public static String exR1(int n) {
        if (n <= 0) return " ";
        return exR1(n - 3) + n + exR1(n - 2) + n;

    }

    public static int mystery(int a, int b) {
        if (b == 0) return 0;
        if (b % 2 == 0) return mystery(a + a, b / 2);
        return mystery(a + a, b / 2) + a;
    }

    /**
     * exam 1.3.4
     * 输入一个表达式判断括号是否完整
     * 对于[()]{}{[()()]()} 应该返回true
     * 对于[(]) 应该返回false
     *
     * @param str
     * @return
     */
    public static boolean parentheses(String str) {
        /*
            思路：利用栈先入后出的特点，遇到闭符号，则出栈，判断是否属于一对
            如果属于一对，则继续扫描，直到最后栈中无元素，返回true。
         */
        char LEFT_PAREN = '(', RIGHT_PAREN = ')';
        char LEFT_BRACE = '{', RIGHT_BRACE = '}';
        char LEFT_BRACKET = '[', RIGHT_BRACKET = ']';
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 如果符号为开符号则入栈
            if (c == LEFT_PAREN || c == LEFT_BRACE || c == LEFT_BRACKET) {
                stack.push(c);
            }
            if (c == RIGHT_PAREN) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_PAREN) {
                    return false;
                }
            } else if (c == RIGHT_BRACE) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_BRACE) {
                    return false;
                }
            } else if (c == RIGHT_BRACKET) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() != LEFT_BRACKET) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * exam 1.3.9
     * 编写一段程序，输入一个缺少左括号的表达式，输出一个补全括号之后的表达式。
     * 例如：1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
     * 输出：( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
     *
     * @return
     */
    public static String completion(String str) {
        /*
            思路：构造两个栈，操作数栈和操作符栈，遇见操作数则加入操作数栈，遇见操作符则加入
            操作符栈。当遇见')'时，弹出操作数栈中头两个操作数并弹出一个操作符首尾加上()拼接，
            再加入操作数栈中。
         */
        Stack<String> operator = new Stack<>();
        Stack<String> value = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            // 如果是操作符，入操作符栈。
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*'
                    || str.charAt(i) == '/') {
                operator.push(str.charAt(i) + "");
            } else if (str.charAt(i) == ')') {
                // 如果是右括号，进行拼接操作
                String s1 = value.pop();
                String s2 = value.pop();
                String pop = operator.pop();
                value.push("( " + s2 + pop + s1 + " )");
            } else if (str.charAt(i) <= 57 && str.charAt(i) >= 49) {
                // 如果是数字，则插入操作数栈中
                value.push(str.charAt(i) + "");
            }
        }
        return value.pop();
    }

    /**
     * exam 1.3.10
     * 将算术表达式由中序表达式转为后序表达式
     *
     * @param str
     */
    public static String infixToPostfix(String str) {
        /*
            思路：遍历字符串，如遇操作数，直接输出，如遇'('，则直接入栈，如遇')'，则一直出栈
            直到出栈字符为'('，当遇见运算符的时候，如果栈非空，则入栈，如果栈不为空，则判断优
            先级，优先级比栈首运算高，则入栈，不高，则先出栈，再入栈。
         */

        // 存取操作符
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isOperator(c)) {    // 1.操作数情况,直接拼接
                result.append(c);
            } else {
                if (c == '(') {     // 2.左括号情况
                    operatorStack.push(c);
                } else if (c == ')') {      // 3.右括号情况
                    if (operatorStack.isEmpty()) {
                        throw new NoSuchElementException("stack underflow");
                    } else {
                        char temp = operatorStack.pop();
                        while (temp != '(') {
                            result.append(temp);
                            temp = operatorStack.pop();
                        }
                    }
                } else {      // 4. +-*/情况
                    if (operatorStack.isEmpty() || priority(operatorStack.peek()) < priority(c)) {
                        // 栈首操作符优先级小于要入栈的操作符则入栈
                        operatorStack.push(c);
                    } else {
                        //
                        while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && priority(operatorStack.peek()) > priority(c)) {
                            // 准备入栈的操作符优先级小于栈首操作符，则一直出栈，直到遇见'(',或者直到栈为空
                            result.append(operatorStack.pop());
                        }
                        // 优先级大的操作符入栈
                        operatorStack.push(c);
                    }
                }
            }
        }
        // 若栈中还有操作符
        if (!operatorStack.isEmpty()) {
            while (!operatorStack.isEmpty()) {
                if (operatorStack.peek() == '(') {
                    throw new NoSuchElementException("Illegal input");
                }
                result.append(operatorStack.pop());
            }
        }
        return result.toString();
    }

    // 判断是是否是符号
    private static boolean isOperator(char c) {

        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
            return true;
        }
        return false;
    }

    // 设定优先级
    private static int priority(char c) {
        if (c == '(') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (c == '*' || c == '/') {
            return 2;
        } else {
            return 3;
        }

    }

    /**
     * exam 1.3.11
     * 输入后序表达式求值
     *
     * @param str
     * @return
     */
    public static double evaluatePostfix(String str) {
        /*
            思路:将数字压入栈中，遇到操作符就进行计算
         */
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isOperator(c)) {
                stack.push(Character.toString(c));
            } else {
                double s1 = Double.parseDouble(stack.pop());
                double s2 = Double.parseDouble(stack.pop());
                stack.push(Double.toString(doOperator(s1, s2, c)));
            }
        }
        return Double.parseDouble(stack.pop());
    }

    private static double doOperator(double a1, double a2, char operator) {
        switch (operator) {
            case '+':
                return a2 + a1;
            case '-':
                return a2 - a1;
            case '/':
                return a2 * 1.0 / a1;
            case '*':
                return a2 * 1.0 * a1;
            default:
                throw new NoSuchElementException("operator is illegal!");
        }
    }

    public static void main(String[] args) {
        System.out.println(cal(5));
    }
}
