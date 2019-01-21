package leetcode.math;

public class No171TitleToNumber {
    public int titleToNumber(String s) {
        int result = 0;
        int pow = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            int number = s.charAt(i) - 64;
            result = (int) (result + number * Math.pow(26, pow--));
        }
        return result;
    }

    public static void main(String[] args) {
        No171TitleToNumber run = new No171TitleToNumber();
        System.out.println(run.titleToNumber("A"));
    }
}
