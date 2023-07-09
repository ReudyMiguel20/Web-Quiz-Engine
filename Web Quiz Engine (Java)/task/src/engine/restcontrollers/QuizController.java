package engine.restcontrollers;

import engine.dto.AnswerQuiz;
import engine.dto.CreateNewQuiz;
import engine.dto.QuizResult;
import engine.entities.Quiz;
import engine.services.servicesimpl.QuizServiceImpl;
import engine.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class QuizController {
    
    private QuizServiceImpl quizService;
    private UserServiceImpl userService;

    @Autowired
    public QuizController(QuizServiceImpl quizService, UserServiceImpl userService) {
        this.quizService = quizService;
        this.userService = userService;
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

    @GetMapping("/quizzes")
    public ResponseEntity<?> getAllQuizzes() {
            return ResponseEntity.ok().body(this.quizService.getAllQuizzes());
    }


    @PostMapping("/quizzes")
    public ResponseEntity<?> createNewQuiz(Authentication auth, @Valid @RequestBody CreateNewQuiz newQuiz) {
        Quiz tempQuiz = new Quiz(newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions(), newQuiz.getAnswer(), auth.getName());

        this.quizService.save(tempQuiz);

        return ResponseEntity.ok().body(tempQuiz);
    }

    @PostMapping("/quizzes/{id}/solve")
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

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(Authentication auth, @PathVariable int id) {
        String userEmail = auth.getName();
        Quiz tempQuiz = this.quizService.findById(id);

        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        }

        boolean isUserOwnerOfQuiz = this.userService.isUserOwnerOfQuiz(userEmail, tempQuiz);

        if (isUserOwnerOfQuiz) {
            this.quizService.deleteQuiz(tempQuiz);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
