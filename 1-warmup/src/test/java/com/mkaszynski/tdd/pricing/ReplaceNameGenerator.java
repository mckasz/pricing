package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

class ReplaceNameGenerator extends DisplayNameGenerator.ReplaceUnderscores {

    @Override
    public String generateDisplayNameForMethod(Class<?> testClass, Method testMethod) {
        return super.generateDisplayNameForMethod(testClass, testMethod);
    }
}
