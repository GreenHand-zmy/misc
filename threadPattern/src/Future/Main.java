package Future;

public class Main {
    public static void main(String[] args) {
        Host host = new Host();
        Data data1 = host.request(10, 'A');
        Data data2 = host.request(10, 'B');
        Data data3 = host.request(10, 'C');
        System.out.println("Main otherJob Begin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main otherJob End");
        System.out.println("data1" + data1.getContent());
        System.out.println("data2" + data2.getContent());
        System.out.println("data3" + data3.getContent());
    }
}
