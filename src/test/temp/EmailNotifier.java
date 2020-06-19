package test.temp;

public interface EmailNotifier {
    boolean isCalled();

    String getEmail();

    void sendRegisterEmail(String email);
}
