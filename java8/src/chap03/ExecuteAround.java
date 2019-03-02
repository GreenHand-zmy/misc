package chap03;

import java.io.*;

public class ExecuteAround {

    // 读取一行数据
    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ExecuteAround.class.getResourceAsStream("/data.txt")))) {
            return br.readLine();
        }
    }

    // 根据策略处理数据
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ExecuteAround.class.getResourceAsStream("/data.txt")))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    public static void main(String[] args) throws IOException {
        // 读取一行数据
        String result = processFileLimited();
        System.out.println(result);
        System.out.println("---");
        // 读取一行数据
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);
        // 读取两行数据
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLines);
    }
}
