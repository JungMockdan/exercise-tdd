package test.temp;


public class UserRegister {

    private WeakPsswordChecker psswordChecker;
    private UserRepository userRepository;
    private EmailNotifier emailNotifier;

    public UserRegister() {
    }

    public UserRegister(WeakPsswordChecker psswordChecker) {
        this.psswordChecker = psswordChecker;
    }

    public UserRegister(WeakPsswordChecker psswordChecker, UserRepository userRepository) {
        this.psswordChecker = psswordChecker;
        this.userRepository = userRepository;
    }

    public UserRegister(WeakPsswordChecker psswordChecker,
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
