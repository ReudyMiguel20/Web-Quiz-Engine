package engine.services.servicesimpl;

import engine.entities.Quiz;
import engine.entities.User;
import engine.repositories.UserRepository;
import engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthoritiesServiceImpl authoritiesService;
    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder,
                           AuthoritiesServiceImpl authoritiesService)
    {
        this.userRepository = userRepository;
        this.authoritiesService = authoritiesService;
        this.encoder = encoder;
    }

    @Override
    public void saveNewUser(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        this.authoritiesService.assignUserRole(user);
    }

    @Override
    public boolean userAlreadyExists(User user) {
        return findUserByEmail(user.getEmail()) != null;
    }

    @Override
    public User findUserByEmail(java.lang.String email) {
        return this.userRepository.findById(email).orElse(null);
    }

    @Override
    public boolean isUserOwnerOfQuiz(String email, Quiz quiz) {
        return quiz.getAuthor().equals(email);
    }
}
