package ProductConsumer;

/**
 * Created by zmy on 2018/1/8.
 */
public class Main {
    public static void main(String[] args) {
        Table<String> table = new Table<>(100);
        new MakerThread("maker", table).start();
        new EaterThread("eater", table).start();
    }
}
