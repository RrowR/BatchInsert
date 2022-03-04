package com.test;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @Author: Rrow
 * @Date: 2022/3/4 9:31 上午
 */
public class TestRandom {
    @Test
    public void Test01(){
        int v = (int) Math.ceil(Math.random() * 5000);
        System.out.println(v);
    }
}
