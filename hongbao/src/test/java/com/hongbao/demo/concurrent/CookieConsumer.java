package com.hongbao.demo.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * Created by zmy on 2018/5/9.
 */
public class CookieConsumer extends Thread {
    private static final int NOT_MODIFIED = 0;
    private static final int MAX_MODIFIED = 5;
    private static final int MIN_QUEUE_SIZE = 10;
    private static final int MAX_QUEUE_SIZE = 1000;

    private final int luckyNumber;
    private final int sleepTime;
    private final CookieRepository<Cookie> cookieRepository;
    private final ExecutorService executorService;


    public CookieConsumer(CookieRepository<Cookie> cookieRepository, ExecutorService executorService
            , int luckyNumber, int sleepTime) {
        this.cookieRepository = cookieRepository;
        this.executorService = executorService;
        this.luckyNumber = luckyNumber;
        this.sleepTime = sleepTime;
    }

    private boolean isAvailable(Cookie cookie) {
        return cookie.getModifiedCount() < MAX_MODIFIED;
    }

    public Runnable mockGuest(int luckyNumber, int sleepTime) {
        return () -> {

            // todo 日志
            System.out.println(Thread.currentThread().getName() + " 运行时仓库容量为 " + cookieRepository.size());
            long start = System.currentTimeMillis();

            // 获取有效的cookie
            List<Cookie> mockCookieInfoList = cookieRepository.getAvailableCookieInfo(luckyNumber);
            try {
                // 对cookie进行操作,每次耗时sleepTime(ms)
                mockCookieInfoList
                        .forEach(Cookie -> {
                            // 消费cookie
                            Cookie.setModifiedCount(Cookie.getModifiedCount() + 1);

                            // 打印修改的信息
                            System.out.println(Thread.currentThread().getName() + "使用" + Cookie.getCookieName() +
                                    " 将cookie 操作次数从" + (Cookie.getModifiedCount() - 1) + "变为了" + Cookie.getModifiedCount());

                            //耗时操作
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });

            } finally {
                List<Cookie> availableCookies = mockCookieInfoList.stream()
                        .filter(this::isAvailable)
                        .collect(Collectors.toList());
                // 归还cookie
                // todo 日志
                System.out.println("进行归还");
                cookieRepository.returnCookieInfo(availableCookies);
            }
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " 运行了" + (end - start));
            System.out.println(Thread.currentThread().getName() + " 结束时仓库容量为 " + cookieRepository.size());
        };
    }

    @Override
    public void run() {
        executorService.execute(mockGuest(luckyNumber, sleepTime));
    }
}
