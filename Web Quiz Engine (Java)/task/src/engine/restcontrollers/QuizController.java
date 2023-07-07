package engine.restcontrollers;

import engine.QuizStorage;
import engine.dto.AnswerQuiz;
import engine.dto.CreateNewQuiz;
import engine.dto.QuizResult;
import engine.entities.Quiz;
import engine.services.QuizServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class QuizController {

    private QuizStorage quizStorage;
    private QuizServiceImpl quizService;

    @Autowired
    public QuizController(QuizStorage quizStorage, QuizServiceImpl quizService) {
        this.quizService = quizService;
        this.quizStorage = quizStorage;
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<?> getSpecificQuiz(@PathVariable int id) {
        Quiz tempQuiz = this.quizService.findById(id);

        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(tempQuiz);
        }
    }

    @GetMapping("quizzes")
    public ResponseEntity<?> getAllQuizzes() {
            return ResponseEntity.ok().body(this.quizService.getAllQuizzes());
    }


    @PostMapping("/quizzes")
    public ResponseEntity<?> createNewQuiz(@Valid @RequestBody CreateNewQuiz newQuiz) {
        Quiz tempQuiz = new Quiz(newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions(), newQuiz.getAnswer());

        this.quizService.save(tempQuiz);

        return ResponseEntity.ok().body(tempQuiz);
    }

    @PostMapping("quizzes/{id}/solve")
    public ResponseEntity<?> solveSpecificQuiz(@PathVariable int id, @RequestBody AnswerQuiz answerQuiz) {
        // Initializing variables and setting up values
        Quiz tempQuiz = this.quizService.findById(id);
        List<Integer> answers = answerQuiz.getAnswer();

        // If quiz is not found
        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        }

        //Boolean value to determine the outcome of the 'verifyAnswerQuiz' method from QuizServiceImpl
        boolean areAnswersCorrect = this.quizService.verifyAnswerQuiz(tempQuiz, answers);

        if (areAnswersCorrect) {
            return ResponseEntity.ok().body(new QuizResult(true, "Congratulations, you're right!"));
        } else {
            return ResponseEntity.ok().body(new QuizResult(false, "Wrong answer! Please, try again."));
        }
    }
}
