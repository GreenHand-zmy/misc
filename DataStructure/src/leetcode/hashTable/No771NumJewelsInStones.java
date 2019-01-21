package leetcode.hashTable;

public class No771NumJewelsInStones {
    public int numJewelsInStones(String J, String S) {
        // 我拥有的宝石总数
        int num = 0;

        // 遍历我的石头
        for (int i = 0; i < S.length(); i++) {
            char Stone = S.charAt(i);

            // 遍历宝石类型
            for (int j = 0; j < J.length(); j++) {
                char jewel = J.charAt(j);
                if (Stone == jewel) {
                    num++;
                }
            }
        }
        return num;
    }

    public static void main(String[] args) {
        No771NumJewelsInStones run = new No771NumJewelsInStones();
        System.out.println(run.numJewelsInStones("aA", "aAAbbbb"));
    }
}
