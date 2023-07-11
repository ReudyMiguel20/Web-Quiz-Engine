package engine.repositories;

import engine.entities.CompletedQuiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompletedQuizRepository extends JpaRepository<CompletedQuiz, Integer>,
        PagingAndSortingRepository<CompletedQuiz, Integer> {

    // returns a page of completed quizzes by a user.
    Page<CompletedQuiz> findAllByUsername(String username, Pageable pageable);
}
