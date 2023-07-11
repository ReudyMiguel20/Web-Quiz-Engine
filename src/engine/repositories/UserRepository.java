package engine.repositories;

import engine.entities.Quiz;
import engine.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, java.lang.String>,
        PagingAndSortingRepository<User, String> {
}
