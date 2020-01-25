package com.mkaszynski.tdd.pricing;

class SeparateName {
    private String name;

    public SeparateName(String name) {
        this.name = name;
    }

    String value() {
        String result = "";
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch) && i > 0) {
                result += " " + ch;
            } else {
                result += ch;
            }
        }
        return result;
    }
}
