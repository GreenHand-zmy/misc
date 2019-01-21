package SortAllFileContent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// 制造文件
public class FileMaker {
    private static final String FOLDER_URL = "D:/workspace/";
    private static volatile int num;

    public static void write(String filename, String content) {
        init();
        // 打印当前线程
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER_URL + filename))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String content) {
        write("data-" + getNum() + ".txt", content);
    }

    private static int getNum() {
        return num++;
    }

    private static void init() {
        File file = new File(FOLDER_URL);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
