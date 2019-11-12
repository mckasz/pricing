package com.mkaszynski.tdd.pricing;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

class SeparatedName {
    private final String name;

    SeparatedName(String name) {
        this.name = name;
    }

    String value() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (i == 0) {
                result.append(toLowerCase(ch));
            } else if (isUpperCase(ch)) {
                result.append(" ").append(toLowerCase(ch));
            } else if (isDigit(prevChar(i)) && isDigit(ch)) {
                result.append(ch);
            } else if (isDigit(prevChar(i))) {
                result.append(" ").append(toLowerCase(ch));
            } else if (isDigit(ch)) {
                result.append(" ").append(ch);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    private char prevChar(int i) {
        return name.charAt(i - 1);
    }
}