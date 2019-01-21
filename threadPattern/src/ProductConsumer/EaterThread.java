package ProductConsumer;

import java.util.Random;

/**
 * Created by zmy on 2018/1/8.
 */
public class EaterThread extends Thread {
    private final Table<String> table;

    public EaterThread(String name, Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.get();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
