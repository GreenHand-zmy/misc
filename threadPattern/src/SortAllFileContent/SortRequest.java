package SortAllFileContent;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortRequest extends Request {
    private static final String FOLDER_URL = "D:/workspace/";
    private final Map<File, Boolean> fileBooleanMap;

    public SortRequest() {
        super(Request.SORT_TYPE);
        this.fileBooleanMap = new HashMap<>();
        File parent = new File(FOLDER_URL);
        for (File file : parent.listFiles()) {
            fileBooleanMap.put(file, false);
        }
    }

    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " calls sort");
        for (Map.Entry<File, Boolean> entry : fileBooleanMap.entrySet()) {
            boolean isSorted = entry.getValue();
            File data = entry.getKey();
            // 遍历map要是data没有排序
            if (!isSorted) {
                // 从文件中读取数据
                List<Integer> integers = ContentReader.readInteger(data.getName());
                // 给数据排序
                Collections.sort(integers);
                // 排序完重新写入文件
                FileMaker.write(data.getName(), format(integers));
                // 最后设置已经排序
                entry.setValue(true);
            }
        }
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    // 格式化数据
    private String format(List<Integer> content) {
        StringBuffer sb = new StringBuffer();
        for (Integer value : content) {
            sb.append(value + "\r\n");
        }
        int i = sb.lastIndexOf("\r\n");
        return sb.substring(0, i);
    }
}
