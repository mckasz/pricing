package com.mkaszynski.tdd.mock;

import org.junit.jupiter.api.Test;

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

        User user = dao.searchUsers("testLogin");

        assertThat(user.getName()).isEqualTo("John");
        assertThat(user.getLogin()).isEqualTo("jj");
        assertThat(user.getAge()).isEqualTo(12);
    }
}