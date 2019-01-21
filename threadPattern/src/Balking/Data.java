package Balking;

import java.io.FileWriter;
import java.io.IOException;

// 表示当前的数据
public class Data {
    private final String filename;
    private String content;
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    // 修改数据内容
    public synchronized void change(String newContent) {
        content = newContent;
        changed = true;
    }

    // 保存数据内容,如果数据没有变化,不执行保存
    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        changed = false;
    }

    private void doSave() throws IOException {
        FileWriter writer = new FileWriter(filename);
        System.out.println(Thread.currentThread().getName() + " calls doSave " + " content " + content);
        writer.write(content);
        writer.close();
    }
}
