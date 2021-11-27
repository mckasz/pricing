package com.mkaszynski.tdd.pricing;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class SeparateName {

    String text;

    public static SeparateName of(String testName) {
        return new SeparateName(testName);
    }

    public String value() {
        StringBuilder sb = new StringBuilder();
        if (text == null) {
            return null;
        }

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (isFirstCharacter(i)) {
                sb.append(text.charAt(i));
            } else if (Character.isUpperCase(c)) {
                sb.append(' ').append(c);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private boolean isFirstCharacter(int i) {
        return i == 0;
    }
}