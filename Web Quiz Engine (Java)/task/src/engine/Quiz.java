package engine;

import java.util.List;
import java.util.Objects;

public class Quiz {

    private String title;
    private String text;
    private List<String> options;

    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
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
