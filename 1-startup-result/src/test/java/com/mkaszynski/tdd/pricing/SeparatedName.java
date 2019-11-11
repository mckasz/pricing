package com.mkaszynski.tdd.pricing;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

class SeparatedName {

    private final String name;

    SeparatedName(String name) {
        this.name = name;
    }

    @SuppressWarnings("StringConcatenationInLoop")
    String value() {
        String result = "";
        for (int idx = 0; idx < name.length(); idx++) {
            char ch = name.charAt(idx);
            if (isFirstChar(idx)) {
                result += toLowerCase(ch);
            } else if (isUpperCase(ch)) {
                result += " " + toLowerCase(ch);
            } else if (isDigit(ch) && prevCharNonDigit(idx) && nexCharNonDigit(idx)) {
                result += " " + ch + " ";
            } else if (isDigit(ch) && nexCharNonDigit(idx)) {
                result += ch + " ";
            } else if (isDigit(ch) && prevCharNonDigit(idx)) {
                result += " " + ch;
            } else {
                result += ch;
            }
        }
        return result;
    }

    private boolean isFirstChar(int idx) {
        return idx == 0;
    }

    private boolean prevCharNonDigit(int idx) {
        return hasPrev(idx) && !isDigit(prevChar(idx));
    }

    private boolean nexCharNonDigit(int idx) {
        return hasNext(idx) && !isDigit(nextChar(idx));
    }

    private boolean hasPrev(int idx) {
        return idx >= 0;
    }

    private boolean hasNext(int idx) {
        return idx < name.length() - 1;
    }

    private char prevChar(int idx) {
        return name.charAt(idx - 1);
    }

    private char nextChar(int idx) {
        return name.charAt(idx + 1);
    }

}