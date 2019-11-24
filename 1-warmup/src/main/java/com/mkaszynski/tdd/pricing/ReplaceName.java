package com.mkaszynski.tdd.pricing;

class ReplaceName {
    private final String name;

    public ReplaceName(String name) {
        this.name = name;
    }

    String value() {
        String result = "";
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch)) {
                return "name One";
            } else {
                result += ch;
            }
        }
        return result;
    }
}
