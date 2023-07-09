package engine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Entity
@JsonPropertyOrder({"id", "completedAt"})
public class CompletedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("id")
    private int quizId;
    private LocalDateTime completedAt;
    @JsonIgnore
    private String username;

    public CompletedQuiz() {
    }

    public CompletedQuiz(int id, String username) {
        this.username = username;
        this.quizId = id;
        this.completedAt = LocalDateTime.now();
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int id) {
        this.quizId = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CompletedQuiz{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", completedAt=" + completedAt +
                '}';
    }
}
