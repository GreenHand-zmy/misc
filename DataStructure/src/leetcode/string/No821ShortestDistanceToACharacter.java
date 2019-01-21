package leetcode.string;

public class No821ShortestDistanceToACharacter {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];

        for (int i = 0; i < S.length(); i++) {
            int distance = 0;
            if (S.charAt(i) == C){
                res[i] = distance;
            }
            distance++;
        }
        return null;
    }
}
