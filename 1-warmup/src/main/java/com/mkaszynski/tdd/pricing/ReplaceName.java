package com.mkaszynski.tdd.pricing;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

class ReplaceName {
    private final String name;

    public ReplaceName(String name) {
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
            } else if (isDigit(ch)) {
                result += " " + ch;
            } else {
                result += ch;
            }
        }
        return result;
    }
}
