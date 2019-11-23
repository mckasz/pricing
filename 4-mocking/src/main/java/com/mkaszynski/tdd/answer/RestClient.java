package com.mkaszynski.tdd.answer;

public interface RestClient {

    <T> T executeGet(String url, ResponseVisitor<T> visitor);
}
