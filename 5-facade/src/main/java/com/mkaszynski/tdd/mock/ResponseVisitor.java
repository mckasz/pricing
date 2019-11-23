package com.mkaszynski.tdd.mock;

public interface ResponseVisitor<T> {

	T processResponse(Response response);
}