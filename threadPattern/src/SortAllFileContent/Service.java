package SortAllFileContent;

import java.util.Random;

public class Service {
    private static Random random;
    static {
        random = new Random();
    }

    public static Integer getInteger() {
        return random.nextInt(1000);
    }
}
