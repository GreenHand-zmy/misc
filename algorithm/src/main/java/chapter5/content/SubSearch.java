package chapter5.content;

/**
 * 子字符串查找
 * Created by zmy on 2017/8/7.
 */
public class SubSearch {
    /**
     * 暴力法
     *
     * @return
     */
    public static int search(String pat, String txt) {
        int n = txt.length();
        int m = pat.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;       // 找到并返回
            }
        }
        return -1;              // 未找到返回-1
    }

    public static void main(String[] args) {
        int search = search("123", "98712");
        System.out.println(search);
    }
}
