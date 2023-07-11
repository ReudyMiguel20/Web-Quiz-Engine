package engine.restcontrollers;

import engine.dto.AnswerQuiz;
import engine.entities.CompletedQuiz;
import engine.dto.CreateNewQuiz;
import engine.dto.QuizResult;
import engine.entities.Quiz;
import engine.services.servicesimpl.CompletedQuizServiceImpl;
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
    private CompletedQuizServiceImpl completedQuizService;

    @Autowired
    public QuizController(QuizServiceImpl quizService, UserServiceImpl userService,
                          CompletedQuizServiceImpl completedQuizService) {
        this.quizService = quizService;
        this.userService = userService;
        this.completedQuizService = completedQuizService;
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
    public ResponseEntity<?> getPageableQuizzes(@RequestParam(name = "page") int pageNumber) {
        // Get the quizzes from the database and return them in a pageable format (10 quizzes per page)
        return ResponseEntity.ok().body(this.quizService.getPageableQuizzes(pageNumber));
    }

    @GetMapping("/quizzes/completed")
    public ResponseEntity<?> getCompletedQuizzes(Authentication auth, @RequestParam(name = "page") int page) {
        String username = auth.getName();

        // Get the completed quizzes from the database and return them in a pageable format (10 quizzes per page)
        return ResponseEntity.ok().body(this.completedQuizService.getCompletedQuizzes(username, page));
    }

    @PostMapping("/quizzes")
    public ResponseEntity<?> createNewQuiz(Authentication auth, @Valid @RequestBody CreateNewQuiz newQuiz) {
        // Create a new quiz object with the CreateNewQuiz object
        Quiz tempQuiz = new Quiz(newQuiz.getTitle(), newQuiz.getText(), newQuiz.getOptions(), newQuiz.getAnswer(), auth.getName());

        // Save the quiz to the database
        this.quizService.save(tempQuiz);

        return ResponseEntity.ok().body(tempQuiz);
    }

    @PostMapping("/quizzes/{id}/solve")
    public ResponseEntity<?> solveSpecificQuiz(Authentication auth, @PathVariable int id, @RequestBody AnswerQuiz answerQuiz) {
        // Get the quiz by id
        Quiz tempQuiz = this.quizService.findById(id);
        List<Integer> answers = answerQuiz.getAnswer();

        // If the quiz doesn't exist, return 404
        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        }

        //Boolean value to determine the outcome of the 'verifyAnswerQuiz' method from QuizServiceImpl
        boolean areAnswersCorrect = this.quizService.verifyAnswerQuiz(tempQuiz, answers);

        if (areAnswersCorrect) {
            // If answers are correct, then add the quiz to the user's completed quizzes
            CompletedQuiz completedQuiz = new CompletedQuiz(tempQuiz.getId(), auth.getName());
            this.userService.userCompletedQuiz(completedQuiz, auth.getName());

            return ResponseEntity.ok().body(new QuizResult(true, "Congratulations, you're right!"));
        } else {
            return ResponseEntity.ok().body(new QuizResult(false, "Wrong answer! Please, try again."));
        }
    }

    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<?> deleteQuiz(Authentication auth, @PathVariable int id) {
        // Get the quiz by id
        String userEmail = auth.getName();
        Quiz tempQuiz = this.quizService.findById(id);

        // If the quiz doesn't exist, return 404
        if (tempQuiz == null) {
            return ResponseEntity.notFound().build();
        }

        // Check if the user is the owner of the quiz
        boolean isUserOwnerOfQuiz = this.userService.isUserOwnerOfQuiz(userEmail, tempQuiz);

        // If the user is the owner of the quiz, then delete the quiz
        if (isUserOwnerOfQuiz) {
            this.quizService.deleteQuiz(tempQuiz);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
