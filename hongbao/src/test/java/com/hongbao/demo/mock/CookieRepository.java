package com.hongbao.demo.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CookieRepository {
    private BlockingQueue<MockCookieInfo> cookieInfoQueue = new LinkedBlockingQueue<>(MAX_QUEUE_SIZE);
    private static final int NOT_MODIFIED = 0;
    private static final int MAX_MODIFIED = 5;
    private static final int MIN_QUEUE_SIZE = 10;
    private static final int MAX_QUEUE_SIZE = 1000;

    public CookieRepository(int size) {
        List<MockCookieInfo> mockCookieInfoList = new ArrayList<>(size);
        // 模拟cookie
        for (int i = 0; i < size; i++) {
            mockCookieInfoList.add(new MockCookieInfo("luck" + i, NOT_MODIFIED));
        }

        mockCookieInfoList
                .forEach(mockCookieInfo -> {
                    try {
                        cookieInfoQueue.put(mockCookieInfo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 从队列中获取有效的cookie信息
     *
     * @param length
     * @return
     */
    public List<MockCookieInfo> getAvailableCookieInfo(int length) throws InterruptedException {
        // 保证队列最小容量
        ensureMinCapacity(MIN_QUEUE_SIZE);

        List<MockCookieInfo> mockCookieInfoList = new ArrayList<>();
        // 循环取出模拟的cookie信息并放入集合
        for (int i = 0; i < length; i++) {
            mockCookieInfoList.add(cookieInfoQueue.take());
        }
        return mockCookieInfoList;
    }

    /**
     * 保证队列最小容量
     *
     * @param minQueueSize
     */
    private void ensureMinCapacity(int minQueueSize) {
        int currentQueueSize = cookieInfoQueue.size();
        if (currentQueueSize < MIN_QUEUE_SIZE) {
            int needSize = minQueueSize - currentQueueSize;
            returnCookieInfo(askForCookie(needSize));
        }
    }

    /**
     * 向队列归还cookie信息
     *
     * @param mockCookieInfoList
     */
    public void returnCookieInfo(List<MockCookieInfo> mockCookieInfoList) {
        // 将cookie信息归还给队列
        mockCookieInfoList.forEach(mockCookieInfo -> {
            try {
                returnCookieInfo(mockCookieInfo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 归还一个
     *
     * @param mockCookieInfo
     * @throws InterruptedException
     */
    public void returnCookieInfo(MockCookieInfo mockCookieInfo) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " 归还了cookie: " + mockCookieInfo);
        cookieInfoQueue.offer(mockCookieInfo, 1, TimeUnit.SECONDS);
    }

    /**
     * 模拟向数据库索要cookie
     *
     * @param length
     */
    public List<MockCookieInfo> askForCookie(int length) {
        System.out.println(Thread.currentThread().getName() + " 索取了: " + length + " 个cookie");
        List<MockCookieInfo> cookieInfoList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            MockCookieInfo mockCookieInfo = new MockCookieInfo("new" + i, 0);
            cookieInfoList.add(mockCookieInfo);
        }
        return cookieInfoList;
    }

    public boolean isAvailable(MockCookieInfo cookieInfo) {
        return cookieInfo.getModified() < MAX_MODIFIED;
    }

    public Runnable mockGuest(CookieRepository cookieRepository, int luckyNumber, int sleepTime) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " 运行时仓库容量为 " + cookieInfoQueue.size());
            long start = System.currentTimeMillis();

            // 获取有效的cookie
            List<MockCookieInfo> mockCookieInfoList = null;
            try {
                mockCookieInfoList = cookieRepository.getAvailableCookieInfo(luckyNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final List<MockCookieInfo> availableCookieInfo = new ArrayList<>();
            // 对cookie进行操作,每次耗时sleepTime(ms)
            mockCookieInfoList
                    .forEach(mockCookieInfo -> {
                        // 消费cookie
                        mockCookieInfo.setModified(mockCookieInfo.getModified() + 1);

                        // 打印修改的信息
                        System.out.println(Thread.currentThread().getName() + "使用" + mockCookieInfo.getCookieName() +
                                " 将cookie 操作次数从" + (mockCookieInfo.getModified() - 1) + "变为了" + mockCookieInfo.getModified());

                        // 如果cookie还有效,归还cookie
                        if (isAvailable(mockCookieInfo)) {
                            availableCookieInfo.add(mockCookieInfo);
                        }
                        //耗时操作
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
            long end = System.currentTimeMillis();

            // 归还cookie
            returnCookieInfo(availableCookieInfo);
            
            System.out.println(Thread.currentThread().getName() + " 运行了" + (end - start));
            System.out.println(Thread.currentThread().getName() + " 结束时仓库容量为 " + cookieInfoQueue.size());
        };
    }

    public static void main(String[] args) {
        CookieRepository cookieRepository = new CookieRepository(MAX_QUEUE_SIZE);

        Runnable case1 = cookieRepository.mockGuest(cookieRepository, 5, 1000);
        Runnable case2 = cookieRepository.mockGuest(cookieRepository, 6, 1000);
        Runnable case3 = cookieRepository.mockGuest(cookieRepository, 7, 1000);
        Runnable case4 = cookieRepository.mockGuest(cookieRepository, 8, 1000);
        Runnable case5 = cookieRepository.mockGuest(cookieRepository, 9, 1000);
        Runnable case6 = cookieRepository.mockGuest(cookieRepository, 10, 1000);

        Thread thread1 = new Thread(case1);
        thread1.setName("游客1");

        Thread thread2 = new Thread(case2);
        thread2.setName("游客2");

        Thread thread3 = new Thread(case3);
        thread3.setName("游客3");

        Thread thread4 = new Thread(case4);
        thread4.setName("游客4");

        Thread thread5 = new Thread(case5);
        thread5.setName("游客5");

        Thread thread6 = new Thread(case6);
        thread6.setName("游客6");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
