package engine.services;

import engine.entities.Quiz;
import engine.entities.User;

public interface UserService {

    void saveNewUser(User user);
    boolean userAlreadyExists(User user);
    User findUserByEmail(java.lang.String email);

    boolean isUserOwnerOfQuiz(String email, Quiz quiz);
}
