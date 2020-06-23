package main.java.tdd.chap07;

public interface EmailNotifier {
    boolean isCalled();

    String getEmail();

    void sendRegisterEmail(String email);
}
