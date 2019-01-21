package com.hongbao.demo.concurrent;

import com.hongbao.demo.domain.CookieInfo;
import com.hongbao.demo.service.CookieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * cookie生产者
 * Created by zmy on 2018/5/9.
 */
public class CookieProducer extends Thread {
    private final Logger logger = LoggerFactory.getLogger(CookieProducer.class);

    private CookieService cookieService;
    private final CookieRepository<Cookie> cookieRepository;
    private final ExecutorService executorService;


    public CookieProducer(CookieRepository<Cookie> cookieRepository,
                          ExecutorService executorService) {
        this.cookieRepository = cookieRepository;
        this.executorService = executorService;
    }

    private Runnable keepSize() {
        return () -> {
            int lowSize = (int) (CookieRepository.MAXSIZE * 0.25);
            if (cookieRepository.size() < lowSize) {

                // todo 从数据库取cookie
//                List<CookieInfo> cookieInfoList = cookieService.getAvailableCookieInfo(lowSize);

                // 打印日志
                logger.info(Thread.currentThread().getName() + " 增加了" + lowSize + "个cookie");
            }


        };
    }

    @Override
    public void run() {
        executorService.execute(keepSize());
    }
}
