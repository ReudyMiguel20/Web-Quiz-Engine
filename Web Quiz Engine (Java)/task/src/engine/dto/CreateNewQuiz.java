package engine.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

public class CreateNewQuiz {

    private String title;
    private String text;
    private List<String> options;

    @Min(value = 0)
    @Max(value = 4)
    private int answer;

    public CreateNewQuiz() {
    }

    public CreateNewQuiz(String title, String text, List<String> options, int answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
