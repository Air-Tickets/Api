package org.example.api;

import java.util.Random;

public class BarCodeGenerator {
    static public String generate() {
        StringBuilder kod = new StringBuilder();
        for (int a=0; a<10; a++) {
            kod.append(new Random().nextDouble() >= 0.5 ? '1' : '0');
        }
        return kod.toString();
    }
}
