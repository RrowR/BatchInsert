package com.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Rrow
 * @date: 2022/3/8 3:34
 */
public class TestThreadPoolExcutors {
    @Test
    public void Test01(){
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 3000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(20));

    }
}
