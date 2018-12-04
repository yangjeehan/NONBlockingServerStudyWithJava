package com.yjh.two.util;

public class Util {
    public static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

}
