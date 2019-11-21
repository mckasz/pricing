package com.mkaszynski.tdd.pricing.tools;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CamelCaseAndUnderscoresGenerator extends DisplayNameGenerator.ReplaceUnderscores {

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        String name = super.generateDisplayNameForMethod(testClass, testMethod);
        return new SeparatedName(name).value();
    }
}
