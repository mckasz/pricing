package com.mkaszynski.tdd.mock;

public interface RestClient {

    <T> T executeGet(String url, ResponseVisitor<T> visitor);
}
