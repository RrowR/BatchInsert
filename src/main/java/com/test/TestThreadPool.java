package com.test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Rrow
 * @date: 2022/2/22 12:00
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 4, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(Integer.MAX_VALUE), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        AtomicInteger i = new AtomicInteger();
        while (true){
            executor.submit(() -> {
                System.out.println(i.getAndIncrement());
            });
        }
    }
}
