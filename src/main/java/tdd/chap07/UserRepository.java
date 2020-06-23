package main.java.tdd.chap07;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
