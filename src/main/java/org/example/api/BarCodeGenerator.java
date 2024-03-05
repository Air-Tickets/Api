package org.example.api;

import java.util.Random;

public class BarCodeGenerator {
    static public String generate() {
        StringBuilder kod = new StringBuilder();
        for (int a=0; a<13; a++) {
            int number = (int) Math.floor(Math.random() * 10);
            kod.append(number);
        }
        return kod.toString();
    }
}
