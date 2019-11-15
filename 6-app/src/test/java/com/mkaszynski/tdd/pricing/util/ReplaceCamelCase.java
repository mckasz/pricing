package com.mkaszynski.tdd.pricing.util;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

class ReplaceCamelCase {

    @SuppressWarnings("StringConcatenationInLoop")
    String replace(String name) {
        String result = "";
        for (int idx = 0; idx < name.length(); idx++) {
            char ch = name.charAt(idx);
            if (isFirstChar(idx)) {
                result += toLowerCase(ch);
            } else if (isUpperCase(ch)) {
                result += " " + Character.toLowerCase(ch);
            } else if (isDigit(ch) && prevCharNonDigit(name, idx) && nexCharNonDigit(name, idx)) {
                result += " " + ch + " ";
            } else if (isDigit(ch) && nexCharNonDigit(name, idx)) {
                result += ch + " ";
            } else if (isDigit(ch) && prevCharNonDigit(name, idx)) {
                result += " " + ch;
            } else {
                result += ch;
            }
        }
        return result;
    }

    private char prevChar(String name, int idx) {
        return name.charAt(idx - 1);
    }

    private boolean isFirstChar(int idx) {
        return idx == 0;
    }

    private boolean prevCharNonDigit(String name, int idx) {
        return idx >= 0 && !isDigit(name.charAt(idx - 1));
    }

    private boolean nexCharNonDigit(String name, int idx) {
        return idx < name.length() - 1 && !isDigit(name.charAt(idx + 1));
    }
}