package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

class SeparateNameGenerator extends DisplayNameGenerator.ReplaceUnderscores {

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        String name = super.generateDisplayNameForMethod(testClass, testMethod);
        return new SeparateName(name).value();
    }
}
