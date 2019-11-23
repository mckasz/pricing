package com.mkaszynski.tdd.answer;

import com.mkaszynski.tdd.answer.tools.BasicRestClient;

import java.util.List;

class App {

    public static void main(String[] args) {
        MyDao dao = new MyDao(new BasicRestClient());

        List<User> users = dao.listUsers();

        printUserNames(users);
    }

    private static void printUserNames(List<User> users) {
        System.out.println("List of user names: ");
        for (User user : users) {
            System.out.println("\t" + user.getName());
        }
    }
}
