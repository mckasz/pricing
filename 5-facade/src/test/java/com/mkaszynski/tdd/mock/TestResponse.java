package com.mkaszynski.tdd.mock;

class TestResponse implements Response {

    private final int status;
    private final String content;

    TestResponse(int status, String content) {
        this.status = status;
        this.content = content;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getContent() {
        return content;
    }
}
