package com.learning.linnyk.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class UserService {

    private final static List<User> users = new CopyOnWriteArrayList<>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Jack", new Date()));
    }

    private static int userCounter = users.size();

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == 0) {
            user = new User(++userCounter, user.getName(), user.getBirthDay());
        }
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return users.remove(user);
            }
        }
        return false;
    }
}
