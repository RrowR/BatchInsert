package com.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Rrow
 * @date: 2022/3/11 19:34
 */
public class TestThread {
    @Test
    void Test01() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        AtomicInteger a = new AtomicInteger(1);
        for (int i = 0; i < 4; i++) {
            while (true) {
                Runnable runnable = () -> {
                    System.out.println(a.getAndIncrement());
                };
                pool.submit(runnable);
            }
        }
    }

    @Test
    void Test02(){
        
    }
}
