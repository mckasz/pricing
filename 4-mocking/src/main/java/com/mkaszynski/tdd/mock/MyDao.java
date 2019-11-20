package com.mkaszynski.tdd.mock;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class MyDao {
    private RestClient restClient;

    MyDao(RestClient restClient) {
        this.restClient = restClient;
    }

    User searchUsers(String login)  {
        String url = "service/users/" + login;
        return restClient.executeGet(url, response -> {
            if (response.getStatus() == 200) {
                try {
                    return new ObjectMapper().readValue(response.getContent(), User.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            throw new IllegalArgumentException("Unable to find user for given login: " + login + " status code: " + response.getStatus());
        });
    }
}
