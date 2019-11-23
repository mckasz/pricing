package com.mkaszynski.tdd.mock;

import lombok.Data;

@Data
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