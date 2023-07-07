package engine.dto;

import java.util.ArrayList;
import java.util.List;

public class AnswerQuiz {

    private List<Integer> answer;

    public AnswerQuiz() {
        this.answer = new ArrayList<>();
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
