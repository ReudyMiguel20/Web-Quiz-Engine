package engine.services;

import engine.entities.User;

public interface AuthoritiesService {

    void assignUserRole(User user);
}
