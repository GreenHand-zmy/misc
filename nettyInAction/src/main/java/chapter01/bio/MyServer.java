package chapter01.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static class HandleMsg implements Runnable {
        private Socket client;

        public HandleMsg(Socket socket) {
            this.client = socket;
        }


        public void run() {
            // 字符输入缓冲流
            BufferedReader reader = null;
            // 字符输出换从流
            PrintWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                writer = new PrintWriter(client.getOutputStream(), true);

                // 获取客户端输入
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null) {
                    writer.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8686);
        Socket client = null;
        while (true) {
            client = server.accept();
            System.out.println(client.getRemoteSocketAddress() + "连接成功");
            executorService.submit(new HandleMsg(client));
        }

    }
}
