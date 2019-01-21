package leetcode.string;

public class No709ToLowerCase {
    public String toLowerCase(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 如果为大写
            if (c <= 90 && c >= 65) {
                c = (char) (c + 32);
            }
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // a-z[97,122] A-Z[65-90]
        No709ToLowerCase run = new No709ToLowerCase();
        System.out.println(run.toLowerCase("lOVELY"));
    }
}
