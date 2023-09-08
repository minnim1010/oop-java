package com.example.lotto.util;

import java.util.Random;

public class RandomUtil {
    private static final Random rand = new Random();

    private RandomUtil() {
    }

    public static int getRandomPositiveNumber(int end) {
        return rand.nextInt(end) + 1;
    }
}
