package ThreadPerMessage.practice.example6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Service {
    private Service() {
    }

    public static void service(Socket clientSocket) throws IOException {
        System.out.println(Thread.currentThread().getName()
                + " : Service.service Begin");
        try {
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeBytes("Http/1.0 200 OK\r\n");
            out.writeBytes("Content-type:text/html\r\n");
            out.writeBytes("\r\n");
            out.writeBytes("<html><head><title>countdown</title></head><body>");
            out.writeBytes("<h1>countdown start!</h1>");
            for (int i = 10; i >= 0; i--) {
                out.writeBytes("<h1>" + i + "</h1>");
                out.flush();
                Thread.sleep(1000);
            }
            out.writeBytes("</body></html>");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }


    }
}
