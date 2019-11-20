package com.mkaszynski.tdd.mock;

import lombok.Value;

@Value
class User {
    private final String name;
    private final String login;
    private final int age;
}
