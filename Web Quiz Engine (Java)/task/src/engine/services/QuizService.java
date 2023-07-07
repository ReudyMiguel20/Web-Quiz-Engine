package engine.services;

import engine.entities.Quiz;

import java.util.List;

public interface QuizService {
    void save(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Quiz findById(int id);
    boolean verifyAnswerQuiz(Quiz quiz, List<Integer> answers);
}
