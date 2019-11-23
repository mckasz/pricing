package com.mkaszynski.tdd.answer;

public interface ResponseVisitor<T> {

	T processResponse(Response response);
}