package test.java.chap08.auth;

public interface CustomerRepository {
    Customer findOne(String id);
}
