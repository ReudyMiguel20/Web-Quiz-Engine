package engine.restcontrollers;

import engine.QuizStorage;
import engine.dto.CreateNewQuiz;
import engine.dto.QuizResult;
import engine.entities.Quiz;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class QuizController {

    private QuizStorage quizStorage;

    @Autowired
    public QuizController(QuizStorage quizStorage) {
        this.quizStorage = quizStorage;
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<?> getSpecificQuiz(@PathVariable int id) {
        Quiz tempQuiz = this.quizStorage.returnQuizById(id);

        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(tempQuiz);
        }
    }

    @GetMapping("quizzes")
    public ResponseEntity<?> getAllQuizzes() {
        //Keeping this in case it should return notFound for the list being empty
//        if (this.quizStorage.size() == 0) {
//            return ResponseEntity.notFound().build();
//        } else {
            return ResponseEntity.ok().body(this.quizStorage.getQuizList());
//        }
    }


    @PostMapping("/quizzes")
    public ResponseEntity<?> createNewQuiz(@Valid @RequestBody CreateNewQuiz newQuiz) {
        Quiz tempQuiz = new Quiz(newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions(), newQuiz.getAnswer());

        this.quizStorage.addQuiz(tempQuiz);
        return ResponseEntity.ok().body(tempQuiz);
    }

    @PostMapping("quizzes/{id}/solve")
    public ResponseEntity<?> solveSpecificQuiz(@PathVariable int id, @RequestParam(name = "answer") int index) {
        Quiz tempQuiz = this.quizStorage.returnQuizById(id);

        // If quiz is not found
        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        }

        // Response depending on the index input by user
        if (!(tempQuiz.getAnswer() == index)) {
            return ResponseEntity.ok().body(new QuizResult(false, "Wrong answer! Please, try again."));
        } else {
            return ResponseEntity.ok().body(new QuizResult(true, "Congratulations, you're right!"));
        }
    }
}
