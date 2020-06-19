package test.temp;

public class SpyEmailNotifier implements EmailNotifier{

    private boolean called;
    private String email;

    @Override
    public boolean isCalled() {
        return called;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void sendRegisterEmail(String email) {
        this.called = true;
        this.email = email;
    }
}