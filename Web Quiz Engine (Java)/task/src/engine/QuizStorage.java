package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import engine.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class QuizStorage {

    @JsonUnwrapped
    private List<Quiz> quizList;

    public QuizStorage() {
        this.quizList = new ArrayList<>();
    }

    public void addQuiz(Quiz quiz) {
        this.quizList.add(quiz);
    }

    public Quiz returnQuizById(int id) {
        /* If id is less than the size of the list then return null, this is to avoid error 500 and let the method on
           the controller handle the response */
        if (this.quizList.size() < id) {
            return null;
        } else {
            return this.quizList.get(id - 1);
        }
    }

    public int size() {
        return this.quizList.size();
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    // Logic for checking if the user answers is/are correct
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
