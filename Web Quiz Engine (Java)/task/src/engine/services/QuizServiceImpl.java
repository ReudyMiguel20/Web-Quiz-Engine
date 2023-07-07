package engine.services;

import engine.entities.Quiz;
import engine.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    @Override
    public void save(Quiz quiz) {
        this.quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return this.quizRepository.findAll();
    }

    @Override
    public Quiz findById(int id) {
        return this.quizRepository.findById(id)
                .orElse(null);
    }

    @Override
    public boolean verifyAnswerQuiz(Quiz quiz, List<Integer> answers) {
        List<Integer> correctAnswers = quiz.getAnswer();

        // Some null and empty array values to return a boolean value, according to the task rules
        if (correctAnswers == null && answers == null) {
            return true;
        } else if (correctAnswers == null && answers.size() == 0) {
            return true;
        } else if (correctAnswers == null) {
            return false;
        }

        // If the array size of correct answers is not equal to the user answer array, return false
        if (correctAnswers.size() != answers.size()) {
            return false;
        }

        // Verifying the answers, counter is for checking how many correct answers are there on the user answer
        int answerCounter = 0;
        for (int correctAnswer : correctAnswers) {
            for (int userAnswer : answers) {
                if (correctAnswer == userAnswer) {
                    answerCounter++;
                    break;
                }
            }
        }

        // Returns true if all correct answers we're found
        return answerCounter == correctAnswers.size();
    }
}
