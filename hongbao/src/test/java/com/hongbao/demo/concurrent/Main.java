package com.hongbao.demo.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zmy on 2018/5/9.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        CookieRepository<Cookie> cookieRepository = new CookieRepository<>();

        // todo 单线程调度cookie 后期改为自定义的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        //todo 多线程领取 后期改为自定义的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        AtomicInteger cookieId = new AtomicInteger();
        // 消费者线程
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
//            int luckyNumber = random.nextInt(10 - 5 + 1) + 5;
            int luckyNumber = 10;
            // 生产者线程
            CookieProducer cookieProducer = new CookieProducer(cookieRepository, singleThreadExecutor);
            cookieProducer.start();

            // 消费者线程
            CookieConsumer consumerCase = new CookieConsumer(cookieRepository, executorService,
                    luckyNumber, 500);
            consumerCase.start();

            // 模拟耗时
//            Thread.sleep(1000);
        }
    }
}
