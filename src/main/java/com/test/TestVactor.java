package com.test;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Rrow
 * @date: 2022/2/22 21:06
 */
public class TestVactor {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            pool.submit(() -> {
                for (int j = 0; j < 4; j++) {
                    vector.add(String.valueOf(j));
                }
            });
        }
        pool.shutdown();
        System.out.println(vector.size());
    }
}
