package com.hongbao.demo;

import com.hongbao.demo.domain.CookieInfo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;

public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        BlockingQueue<CookieInfo> queue = new LinkedTransferQueue<>();
        CookieInfo cookieInfo1 = new CookieInfo();
        cookieInfo1.setOpenId("1");
        CookieInfo cookieInfo2 = new CookieInfo();
        cookieInfo2.setOpenId("1");

        queue.offer(cookieInfo1);
        queue.offer(cookieInfo2);
        System.out.println(queue);
        /*Runnable consumer = () -> {
            while (true) {
                String data = queue.poll();

                if (data != null) {
                    System.out.println(System.currentTimeMillis() + " 取出数据" + data);
                }
            }
        };

        Runnable maker = () -> {
            Random random = new Random();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.add(random.nextInt() + "");
            }
        };

        new Thread(consumer).start();
        new Thread(maker).start();
        new Thread(maker).start();
    }*/
    }
}

