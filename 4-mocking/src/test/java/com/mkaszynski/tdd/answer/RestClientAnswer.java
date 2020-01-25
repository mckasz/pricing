package com.mkaszynski.tdd.answer;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class RestClientAnswer implements Answer<Object> {

    private int status;
    private String content;

    RestClientAnswer(int status, String content) {
        this.status = status;
        this.content = content;
    }

    @Override
    public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        ResponseVisitor responseVisitor = (ResponseVisitor) invocationOnMock.getArguments()[1];
        return responseVisitor.processResponse(new Response() {
            @Override
            public int getStatus() {
                return status;
            }

            @Override
            public String getContent() {
                content = MyDaoTest.USER;
                return content;
            }
        });
    }
}
