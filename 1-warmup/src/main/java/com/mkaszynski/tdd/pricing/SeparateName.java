package com.mkaszynski.tdd.pricing;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

class SeparateName {
    private String name;

    public SeparateName(String name) {
        this.name = name;
    }

    String value() {
        String result = "";
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (i == 0) {
                result += ch;
            } else if (isUpperCase(ch)) {
                result += " " + ch;
            } else if (isDigit(prevChar(i)) && isAlphabetic(ch)) {
                result += " " + ch;
            } else if (isDigit(ch) && isAlphabetic(prevChar(i))) {
                result += " " + ch;
            } else {
                result += ch;
            }
        }
        return result;
    }

    private char prevChar(int i) {
        return name.charAt(i - 1);
    }
}
