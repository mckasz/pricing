package com.mkaszynski.tdd.answer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
class User {
    private int id;
    private String name;
    private String email;

    User() {
    }

    User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}