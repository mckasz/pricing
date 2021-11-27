package com.mkaszynski.tdd.answer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyDaoTest {
    @Mock
    RestClient restClient;

    public static final String USERS =
            "[{\n" +
                    "  \"id\": 1,\n" +
                    "  \"name\": \"John\",\n" +
                    "  \"email\": \"j@j.com\"\n" +
                    "}]";

    @Test
    void testSearchUser() {
        MyDao dao = new MyDao(restClient);
        TestResponse response = new TestResponse(200, USERS);
        when(restClient.executeGet(anyString(), any(ResponseVisitor.class))).thenAnswer(new ReponseVisitorCallingAnswer(response));

        List<User> result = dao.listUsers();

        assertThat(result).containsOnly(new User(1, "John", "j@j.com"));
    }

    private static class ReponseVisitorCallingAnswer implements Answer<List<User>> {

        private final MyDaoTest.TestResponse response;

        public ReponseVisitorCallingAnswer(TestResponse response) {
            this.response = response;
        }

        @Override
        public List<User> answer(InvocationOnMock invocationOnMock) throws Throwable {
            ResponseVisitor<List<User>> argument = invocationOnMock.getArgument(1);
            return argument.processResponse(response);
        }
    }

    private static class TestResponse implements Response {
        private final int status;
        private final String users;

        public TestResponse(int status, String users) {
            this.status = status;
            this.users = users;
        }

        @Override
        public int getStatus() {
            return status;
        }

        @Override
        public String getContent() {
            return users;
        }
    }
}