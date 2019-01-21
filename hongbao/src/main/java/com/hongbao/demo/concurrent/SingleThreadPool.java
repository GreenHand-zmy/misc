package com.hongbao.demo.concurrent;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义单线程池
 * Created by zmy on 2018/5/11.
 */
@Component
public class SingleThreadPool extends ThreadPoolExecutor {
    private static final int MAX_RUNNING = 10;

    public SingleThreadPool() {
        super(1, 1,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(MAX_RUNNING));
    }
}
