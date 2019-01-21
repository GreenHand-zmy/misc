package ThreadPerMessage.practice.example6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new MiniServer(8888).execute();
    }
}
