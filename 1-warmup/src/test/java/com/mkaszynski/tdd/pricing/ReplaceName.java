package com.mkaszynski.tdd.pricing;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

class ReplaceName {
    private final String name;

    public ReplaceName(String name) {
        this.name = name;
    }

    String value() {
        if (name == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (i == 0) {
                result.append(ch);
            } else if (isUpperCase(ch)) {
                result.append(" ").append(ch);
            } else if (isDigit(prevChar(i)) && isDigit(ch)) {
                result.append(ch);
            } else if (isDigit(prevChar(i))) {
                result.append(" ").append(ch);
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
