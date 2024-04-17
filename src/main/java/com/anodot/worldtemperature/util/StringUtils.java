package com.anodot.worldtemperature.util;

public class StringUtils {

    private StringUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * generate random alphanumeric string
     * @param length of string to be generated
     * @return
     */
    public static String generateRandomString(int length) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (alphabet.length() * Math.random());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}
