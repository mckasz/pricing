package com.mkaszynski.tdd.answer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyDaoTest {
    private static final String USERS = "[\n" +
            "{\n" +
            "  \"id\": 1,\n" +
            "  \"name\": \"John\",\n" +
            "  \"email\": \"john@test.com\"\n" +
            "}" +
            "]";


    @Mock
    private RestClient restClient;

    @InjectMocks
    private MyDao dao;

    @Test
    void should_return_200_when_response_is_serializable() {
        when(restClient.executeGet(any(), any())).then(new RestClientHttpAnswer(200, USERS));

        List<User> users = dao.listUsers();

        assertThat(users).containsOnly(new User(1, "John", "john@test.com"));
    }
}