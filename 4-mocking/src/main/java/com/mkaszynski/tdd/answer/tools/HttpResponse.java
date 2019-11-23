package com.mkaszynski.tdd.answer.tools;

import com.mkaszynski.tdd.answer.Response;

class HttpResponse implements Response {
    private final int status;
    private final String content;

    HttpResponse(int status, String content) {
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
