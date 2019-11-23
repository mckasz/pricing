package com.mkaszynski.tdd.answer;

import com.mkaszynski.tdd.answer.tools.Serializer;

import java.io.IOException;
import java.util.List;

class MyDao {
    private final Serializer serializer = new Serializer();
    private RestClient restClient;

    MyDao(RestClient restClient) {
        this.restClient = restClient;
    }

    List<User> listUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        return restClient.executeGet(url, response -> {
            if (response.getStatus() == 200) {
                try {
                    return serializer.deserialize(response.getContent(), User.class);
                } catch (IOException e) {
                    throw new IllegalStateException("Unable to deserialize response content " + response.getContent(), e);
                }
            }
            throw new IllegalArgumentException("Unable to list users. Response status code: " + response.getStatus());
        });
    }

}
