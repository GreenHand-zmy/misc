package chapter01;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class BufferTestInFileChannel {

    public static void main(String[] args) throws IOException {
        File file = new File(BufferTestInFileChannel.class.getResource("/test.txt").getPath());

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        // 获取文件管道
        FileChannel fileChannel = raf.getChannel();
        // 获取缓存,设置缓存大小为64
        ByteBuffer buffer = ByteBuffer.allocate(8);

        // 从管道里读取数据,写入缓存区
        int bytesRead = fileChannel.read(buffer);

        while (bytesRead != -1) {
            //切换缓存区为读状态
            buffer.flip();

            for (int i = buffer.position(); i < buffer.limit(); i++) {
                System.out.print((char)buffer.get(i));
            }

            // 切换缓存区为写状态,查看管道中是否还有数据
            buffer.clear();

            bytesRead = fileChannel.read(buffer);
        }

        // 关闭管道
        fileChannel.close();
    }
}
