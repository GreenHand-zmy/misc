package SortAllFileContent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ContentReader {
    private static final String FOLDER_URL = "D:/workspace/";

    public static synchronized String read(String filename) {
        StringBuffer content = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FOLDER_URL + filename))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (!str.isEmpty()){
                    content.append(str + "\r\n");
                }
                int i = content.lastIndexOf("\r\n");
                return content.substring(0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Integer> readInteger(String filename) {
        String read = read(filename);
        String[] split = read.split("\r\n");
        Integer[] result = new Integer[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return Arrays.asList(result);
    }

}
