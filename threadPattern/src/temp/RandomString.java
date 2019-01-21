package temp;

import java.util.Random;

public class RandomString {
    public static String makeRandomString(int length) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            char ch = (char) (random.nextInt(26) + 65);
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
