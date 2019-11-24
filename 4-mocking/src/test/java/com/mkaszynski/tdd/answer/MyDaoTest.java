package com.mkaszynski.tdd.answer;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MyDaoTest {

    public static final String USER =
            "[{\n" +
            "  \"name\": \"John\",\n" +
            "  \"login\": \"jj\",\n" +
            "  \"age\": 12\n" +
            "}]";


    @Test
    void testSearchUser() {
        RestClient restClient = mock(RestClient.class);
        when(restClient.executeGet(anyString(), any(ResponseVisitor.class)))
                .thenAnswer(new TestRestClientAnswer(200, USER));
        MyDao dao = new MyDao(restClient);

        List<User> users = dao.listUsers();

        assertThat(users).isNotEmpty();
    }

    private static class TestResponse implements Response {

        private final int status;
        private final String user;

        public TestResponse(int status, String user) {
            this.status = status;
            this.user = user;
        }

        @Override
        public int getStatus() {
            return status;
        }

        @Override
        public String getContent() {
            return user;
        }
    }
    private static class TestRestClientAnswer implements Answer<Object> {

        private int status;
        private String user;

        TestRestClientAnswer(int status, String user) {
            this.status = status;
            this.user = user;
        }

        @Override
        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            ResponseVisitor responseVisitor = (ResponseVisitor) invocationOnMock.getArguments()[1];
            return responseVisitor.processResponse(new TestResponse(status, user));
        }
    }
}