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

    @Override
    public void userCompletedQuiz(CompletedQuiz completedQuiz, String email) {
        User tempUser = findUserByEmail(email);

        this.completedQuizRepository.save(completedQuiz);
        tempUser.addNewCompletedQuiz(completedQuiz);
        updateUser(tempUser);

//        for (CompletedQuiz x : tempUser.getCompletedQuizzes()) {
//            System.out.println(x.toString());
//        }
    }

//    @Override
//    public Page<CompletedQuiz> returnAllUserCompletedQuizzes(User user) {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("completedAt").descending());
//
//
//
//        List<CompletedQuiz> completedQuizzes = user.getCompletedQuizzes()
//                .stream().sorted((cq1, cq2) -> cq2.getCompletedAt().compareTo(cq1.getCompletedAt())).toList();
//
//
//
//    }
//
//    @Override
//    public void getUserCompletedQuizzes(String email) {
//        Pageable paging = PageRequest.of(0, 10);
//        User tempUser = findUserByEmail(email);
//        Sort sortByCompletionTime = Sort.by("completedAt");
//
//        // Q: Why does this not work?
//
//        List<CompletedQuiz> completedQuizzes= tempUser.getCompletedQuizzes();
//
//    }
}
