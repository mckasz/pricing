package com.mkaszynski.tdd.mock;

interface RestClient {

    <T> T executeGet(String url, ResponseVisitor<T> visitor);
}
