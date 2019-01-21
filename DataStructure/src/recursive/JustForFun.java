package recursive;

import org.junit.Test;

public class JustForFun {
    private String upCaseLetter(String str) {
        char[] chars = str.toCharArray();
        upCaseLetter(chars, 0);
        return new String(chars);
    }

    private void upCaseLetter(char[] chars, int index) {
        if (index > chars.length - 1) {
            return;
        }


        upCaseLetter(chars, index + 1);
        System.out.println(chars[index]);
        chars[index] = Character.toUpperCase(chars[index]);

    }

    @Test
    public void testCase1() {
        String s = upCaseLetter("abc");
        System.out.println(s);
    }
}
