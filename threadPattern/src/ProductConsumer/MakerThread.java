package ProductConsumer;

import java.util.Random;

/**
 * Created by zmy on 2018/1/8.
 */
public class MakerThread extends Thread {
    private final Table<String> table;
    // item流水号
    private static int id = 0;

    public MakerThread(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                String string = "Item." + nextId();
                table.put(string);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized int nextId() {
        return id++;
    }
}
