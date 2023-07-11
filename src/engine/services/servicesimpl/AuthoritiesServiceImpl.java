package engine.services.servicesimpl;

import engine.entities.Authorities;
import engine.entities.User;
import engine.repositories.AuthoritiesRepository;
import engine.services.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {

    private AuthoritiesRepository authoritiesRepository;

    @Autowired
    public AuthoritiesServiceImpl(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }


    // Assign the user role to a user when they register for an account.
    @Override
    public void assignUserRole(User user) {
        Authorities tempAuthority = new Authorities(user.getEmail(), "ROLE_USER");

        this.authoritiesRepository.save(tempAuthority);
    }
}
