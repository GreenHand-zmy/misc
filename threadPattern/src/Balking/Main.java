package Balking;

public class Main {
    public static void main(String[] args) {
        Data data = new Data("D:/DestTop/test.txt", "empty");
        new SaverThread("saver", data).start();
        new ChangerThread("changer", data).start();
    }
}
