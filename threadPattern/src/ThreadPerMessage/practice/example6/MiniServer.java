package ThreadPerMessage.practice.example6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer {
    // 服务器端口
    private final int portNumber;

    public MiniServer(int portNumber) {
        this.portNumber = portNumber;
    }

    public void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        System.out.println("Listening on" + serverSocket);
        try {
            while (true) {
                System.out.println("Accepting...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to" + clientSocket);
                new Thread(){
                    public void run(){
                        try {
                            Service.service(clientSocket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        } finally {
            serverSocket.close();
        }
    }
}
