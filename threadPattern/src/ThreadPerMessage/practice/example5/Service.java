package ThreadPerMessage.practice.example5;

public class Service {
    //方法1连续按下按钮时，每次按下都会执行service
/*    public static void service() {
        new Thread() {
            public void run() {
                doService();
            }
        }.start();
    }*/

    //方法2 使用Thread-Per-Message模式和SingleThreadedExecution模式
    public static void service() {
        new Thread() {
            public void run() {
                doService();
            }
        }.start();
    }

    private synchronized static void doService() {
        System.out.println(Thread.currentThread().getName() + "service run");
        for (int i = 0; i < 10; i++) {
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n" + Thread.currentThread().getName() + "done");
    }
/*    private static void doService() {
        System.out.println(Thread.currentThread().getName() + "service run");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " .");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "done");
    }*/
}
