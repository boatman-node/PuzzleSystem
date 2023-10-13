package com.kanghaopeng.tools;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Random;


public class SmallTools {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 6;
    public static String generateCaptcha() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char character = CHARACTERS.charAt(index);
            sb.append(character);
        }
        return sb.toString();
    }

}
