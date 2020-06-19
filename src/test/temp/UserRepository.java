package test.temp;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
