package test.java.chap07;


public class UserRegister {

    private WeakPasswordChecker psswordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister() {
    }

    public UserRegister(WeakPasswordChecker psswordChecker) {
        this.psswordChecker = psswordChecker;
    }

    public UserRegister(WeakPasswordChecker psswordChecker, UserRepository userRepository) {
        this.psswordChecker = psswordChecker;
        this.userRepository = userRepository;
    }

    public UserRegister(WeakPasswordChecker psswordChecker,
                        UserRepository userRepository,
                        EmailNotifier emailNotifier) {
        this.psswordChecker = psswordChecker;
        this.userRepository = userRepository;
        this.emailNotifier = emailNotifier;
    }

    public void register(String id, String pw, String email) {
        if (psswordChecker.checkPasswordWeak(pw)) {
            throw new WeakPasswordException();
        }
        User user = userRepository.findById(id);
        if (user != null) {
            throw new DupIdException();
        }
        userRepository.save(new User(id,pw,email));
        emailNotifier.sendRegisterEmail(email);
    }
}
