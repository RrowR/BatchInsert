package com.test;

import org.junit.jupiter.api.Test;

/**
 * @Author: Rrow
 * @Date: 2022/3/9 10:05 上午
 */
public class TestSplit {
    @Test
    void Test01(){
        String allTrigger = "1234";
        char c1 = allTrigger.charAt(0);
        char c2 = allTrigger.charAt(1);
        char c3 = allTrigger.charAt(2);
        char c4 = allTrigger.charAt(3);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
    }
}
