package com.mkaszynski.tdd.mock;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class RestClientHttpAnswer implements Answer<Object> {
    private final int status;
    private final String content;

    RestClientHttpAnswer(int status, String content) {
        this.status = status;
        this.content = content;
    }

    @Override
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        ResponseVisitor visitor = (ResponseVisitor) invocationOnMock.getArguments()[1];
        return visitor.processResponse(new TestResponse(status, content));
    }
}
