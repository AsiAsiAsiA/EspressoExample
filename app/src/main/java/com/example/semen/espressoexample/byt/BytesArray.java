package com.example.semen.espressoexample.byt;

import java.util.Arrays;

class BytesArray {

    private byte[] bytes;

    BytesArray(int count) {
        bytes = new byte[100*100*count];
        Arrays.fill(bytes, (byte) 1);
    }
}