package engine.services;

import engine.entities.CompletedQuiz;
import engine.entities.Quiz;
import engine.entities.User;

public interface UserService {

    void saveNewUser(User user);
    void updateUser(User user);
    boolean userAlreadyExists(User user);
    User findUserByEmail(java.lang.String email);
    boolean isUserOwnerOfQuiz(String email, Quiz quiz);

    void userCompletedQuiz(CompletedQuiz completedQuiz, String email);

//    void getUserCompletedQuizzes(String email);
//    void userCompletedQuiz(CompletedQuiz completedQuiz);
//    void userCompletedQuiz(CompletedQuiz completedQuiz, User user);
//    void userCompletedQuiz(CompletedQuiz completedQuiz, String email);
}
