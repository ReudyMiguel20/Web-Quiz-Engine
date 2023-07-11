package engine.services;

import engine.entities.Quiz;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuizService {
    void save(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Page<Quiz> getPageableQuizzes(int pageNumber);
    Quiz findById(int id);
    void deleteQuiz(Quiz quiz);
    boolean verifyAnswerQuiz(Quiz quiz, List<Integer> answers);
}
