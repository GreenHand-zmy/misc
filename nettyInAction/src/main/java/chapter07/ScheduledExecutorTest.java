package chapter07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
    public static void main(String[] args) {
        // 创建一个拥有10个线程的
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        executor.schedule(
                // 创建一个Runnable已供调度稍后执行
                () -> {
                    System.out.println("5 seconds later");
                }, 5, TimeUnit.SECONDS);

        // 一旦调度任务完成,就释放资源
        executor.shutdown();
    }
}
