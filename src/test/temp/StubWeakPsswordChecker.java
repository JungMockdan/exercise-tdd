package test.temp;

public class StubWeakPsswordChecker implements WeakPsswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
       return weak;
    }
}
