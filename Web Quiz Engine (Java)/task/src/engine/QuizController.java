package engine;

import engine.dto.QuizOutcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    private Quiz defaultQuiz;

    @Autowired
    public QuizController(Quiz defaultQuiz) {
        this.defaultQuiz = defaultQuiz;
    }

    @GetMapping("/quiz")
    public ResponseEntity<?> getCurrentQuiz() {
        return ResponseEntity.ok().body(this.defaultQuiz);
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> answerCurrentQuiz(@RequestParam(name = "answer") int answerIndex) {
        if (!(answerIndex == 2)) {
            return ResponseEntity.ok().body(new QuizOutcome(false, "Wrong answer! Please, try again."));
        } else {
            return ResponseEntity.ok().body(new QuizOutcome(true, "Congratulations, you're right!"));
        }
    }
}
