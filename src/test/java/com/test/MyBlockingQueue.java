package com.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author: Rrow
 * @Date: 2022/2/24 8:51 下午
 */
public class MyBlockingQueue {
    public static void main(String[] args) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(20000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        new Thread(() -> {
            for (int i = 0; ; i++) {
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (int i = 0; i < 3; i++) {
            Runnable runnable = () -> {
                while (true){
                    try {
                        Thread.sleep(1000);
                        System.out.println("queue.take() = " + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
    }
}
