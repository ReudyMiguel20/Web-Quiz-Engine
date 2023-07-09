package engine.services.servicesimpl;

import engine.entities.CompletedQuiz;
import engine.entities.Quiz;
import engine.entities.User;
import engine.repositories.CompletedQuizRepository;
import engine.repositories.UserRepository;
import engine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final QuizServiceImpl quizService;
    private final CompletedQuizRepository completedQuizRepository;
    private final AuthoritiesServiceImpl authoritiesService;
    private final PasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder,
                           AuthoritiesServiceImpl authoritiesService, QuizServiceImpl quizService,
                           CompletedQuizRepository completedQuizRepository)
    {
        this.userRepository = userRepository;
        this.quizService = quizService;
        this.authoritiesService = authoritiesService;
        this.encoder = encoder;
        this.completedQuizRepository = completedQuizRepository;
    }

    /* This method is used to save a new user to the database. It first checks if the user already exists in the database,
       if not it saves the user to the database and assigns the user the role of "ROLE_USER". */
    @Override
    public void saveNewUser(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        this.userRepository.save(user);
        this.authoritiesService.assignUserRole(user);
    }

    @Override
    public void updateUser(User user) {
        this.userRepository.save(user);
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


    /* This method is used to save a completed quiz to the database. It first saves the completed quiz to the database,
       then it adds the completed quiz to the user's list of completed quizzes, and then it updates the user in the database. */
    @Override
    public void userCompletedQuiz(CompletedQuiz completedQuiz, String email) {
        User tempUser = findUserByEmail(email);

        this.completedQuizRepository.save(completedQuiz);
        tempUser.addNewCompletedQuiz(completedQuiz);
        updateUser(tempUser);
    }
}
