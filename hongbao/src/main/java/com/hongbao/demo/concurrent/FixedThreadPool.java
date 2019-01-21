package com.hongbao.demo.concurrent;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zmy on 2018/5/11.
 */
@Component
public class FixedThreadPool extends ThreadPoolExecutor {
    private static final int nThreads = 10;

    public FixedThreadPool() {
        super(nThreads, nThreads,
                0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }
}
