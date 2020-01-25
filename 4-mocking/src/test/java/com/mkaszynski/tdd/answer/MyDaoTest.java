package com.mkaszynski.tdd.answer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyDaoTest {

    public static final String USER =
            "[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"test\": 2,\n" +
                    "    \"name\": \"John\",\n" +
                    "    \"email\": \"test@test.com\"\n" +
                    "  }\n" +
                    "]";
    @Mock
    private RestClient restClient;
    @InjectMocks
    private MyDao dao;


    @Test
    void testSearchUser() {
        when(restClient.executeGet(anyString(), any(ResponseVisitor.class))).thenAnswer(new RestClientAnswer(200, USER));

        List<User> resultUsers = dao.listUsers();

        assertThat(resultUsers).containsOnly(new User(1, "John", "test@test.com") );
    }

}