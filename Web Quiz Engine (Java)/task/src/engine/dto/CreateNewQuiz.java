package engine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CreateNewQuiz {

    @NotEmpty
    private String title;
    @NotEmpty
    private String text;

    @Size(min = 2)
    @NotEmpty
    private List<String> options;

    private List<Integer> answer;

    public CreateNewQuiz() {
    }

    public CreateNewQuiz(@NotBlank String title, @NotBlank String text, List<String> options, @NotNull List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new ArrayList<>();
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

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
