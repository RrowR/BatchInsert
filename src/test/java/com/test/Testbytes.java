package com.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @Author: Rrow
 * @Date: 2022/3/15 4:00 下午
 */
public class Testbytes {
    @Test
    void TestChangeToBytes(){
        String str = "hello";
        byte[] bytes = str.getBytes();
        System.out.println(Arrays.toString(bytes));
    }
}
