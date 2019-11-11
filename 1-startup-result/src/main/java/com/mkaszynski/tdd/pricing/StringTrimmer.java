package com.mkaszynski.tdd.pricing;

public class StringTrimmer {

    private static final String DOTS = "...";

    public static String trim(String value, int maxLength) {
        if (value == null) {
            return null;
        }
        if (maxLength < DOTS.length()) {
            return DOTS;
        }
        if (value.length() > maxLength) {
            return value.substring(0, maxLength - 3) + DOTS;
        } else {
            return value;
        }
    }
}
