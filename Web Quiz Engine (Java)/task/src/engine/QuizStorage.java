package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import engine.entities.Quiz;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
}
