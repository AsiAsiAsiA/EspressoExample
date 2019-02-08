package com.example.semen.espressoexample;

import java.util.Arrays;

public class MyObject {

    byte[] bytes;

    public MyObject() {
        bytes = new byte[1000 * 1000];
        Arrays.fill(bytes, (byte) 1);
    }
}