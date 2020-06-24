package main.java.tdd.chap10.user;

import main.java.tdd.chap10.user.User;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
