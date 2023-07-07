package engine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Quiz {
    @JsonIgnore
    private static int nextInt = 1;
    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @Size(min = 2)
    @NotEmpty
    private List<String> options;

    @JsonIgnore
    private List<Integer> answer;

    public Quiz() {
    }

    public Quiz(@NotBlank String title, @NotBlank String text, List<String> options, List<Integer> answer) {
        this.id = nextInt++;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer; // Answer must be -1 for future stages I guess, because it references the index position
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    //    public int getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(int answer) {
//        this.answer = answer;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return Objects.equals(title, quiz.title) && Objects.equals(text, quiz.text) && Objects.equals(options, quiz.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, options);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                '}';
    }
}
