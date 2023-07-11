package engine.services.servicesimpl;

import engine.entities.CompletedQuiz;
import engine.repositories.CompletedQuizRepository;
import engine.services.CompletedQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompletedQuizServiceImpl implements CompletedQuizService {

    private CompletedQuizRepository completedQuizRepository;

    @Autowired
    public CompletedQuizServiceImpl(CompletedQuizRepository completedQuizRepository) {
        this.completedQuizRepository = completedQuizRepository;
    }

    @Override
    public void save(CompletedQuiz completedQuiz) {
        this.completedQuizRepository.save(completedQuiz);
    }

    @Override
    public Page<CompletedQuiz> findAllByUsername(String username, Pageable pageable) {
        return this.completedQuizRepository.findAllByUsername(username, pageable);
    }

    public Page<CompletedQuiz> getCompletedQuizzes(String username, int pageNumber) {
        // Query the database for all completed quizzes by a user, sorted by completedAt in descending order
        Sort sortByCompletedAtDesc = Sort.by("completedAt").descending();
        Pageable pageable = PageRequest.of(pageNumber, 10, sortByCompletedAtDesc);

        // Return the page of completed quizze
        return this.completedQuizRepository.findAllByUsername(username, pageable);
    }


}
