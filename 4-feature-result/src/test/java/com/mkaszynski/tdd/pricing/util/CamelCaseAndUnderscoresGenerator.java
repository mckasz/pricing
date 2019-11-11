package com.mkaszynski.tdd.pricing.util;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CamelCaseAndUnderscoresGenerator extends DisplayNameGenerator.ReplaceUnderscores {

    private final ReplaceCamelCase camelCaseReplacer = new ReplaceCamelCase();

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        String name = super.generateDisplayNameForMethod(testClass, testMethod);
        return camelCaseReplacer.replace(name);
    }
}
