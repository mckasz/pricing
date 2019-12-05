package com.mkaszynski.tdd.answer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MyDaoTest {

    public static final String USER =
            "{\n" +
            "  \"name\": \"John\",\n" +
            "  \"login\": \"jj\",\n" +
            "  \"age\": 12\n" +
            "}";


    @Test
    void testSearchUser() {
        RestClient restClient = null;
        MyDao dao = new MyDao(restClient);

        List<User> users = dao.listUsers();

        assertThat(users).isNotEmpty();
    }
}