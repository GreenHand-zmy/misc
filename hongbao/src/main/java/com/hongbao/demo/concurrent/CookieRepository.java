package com.hongbao.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zmy on 2018/5/9.
 */
@Component
public class CookieRepository<T> extends LinkedBlockingQueue<T> {
    private Logger logger = LoggerFactory.getLogger(CookieRepository.class);

    public static final int MAXSIZE = 1000;

    public CookieRepository() {
        super(MAXSIZE);
    }

    /**
     * 向阻塞队列添加元素,超时取消阻塞
     *
     * @param t
     */
    public void putCookie(T t) {
        super.offer(t);
    }

    /**
     * 向阻塞队列获取元素,若无元素,则一直阻塞
     *
     * @return
     */
    public T getCookie() {
        try {
            return super.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从阻塞队列中获取可用的cookie
     *
     * @param luckyNumber
     * @return
     */
    public List<T> getAvailableCookieInfo(int luckyNumber) {
        List<T> resultList = new ArrayList<>();
        for (int i = 0; i < luckyNumber; i++) {
            resultList.add(getCookie());
        }
        logger.info(Thread.currentThread().getName()+" 获取了");
        return resultList;
    }

    /**
     * 归还数据给阻塞队列
     *
     * @param availableCookieInfo
     */
    public void returnCookieInfo(List<T> availableCookieInfo) {
        for (T t : availableCookieInfo) {
            putCookie(t);
        }

    }
}
