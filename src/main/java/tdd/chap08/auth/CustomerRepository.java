package main.java.tdd.chap08.auth;

public interface CustomerRepository {
    Customer findOne(String id);
}
