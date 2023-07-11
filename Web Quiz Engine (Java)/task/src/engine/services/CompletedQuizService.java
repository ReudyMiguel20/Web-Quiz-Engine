package engine.services;

import engine.entities.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompletedQuizService {
    void save(CompletedQuiz completedQuiz);
    Page<CompletedQuiz> findAllByUsername(String username, Pageable pageable);
}
