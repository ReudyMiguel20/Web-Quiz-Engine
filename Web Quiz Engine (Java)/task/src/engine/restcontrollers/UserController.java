package engine.restcontrollers;

import engine.dto.CreateNewUser;
import engine.entities.User;
import engine.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // Used to register a new user, returns a 200 OK response if the user was successfully registered
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody CreateNewUser newUser) {
        // Check if the user already exists in the database
        User tempUser = new User(newUser.getEmail(), newUser.getPassword());
        boolean userAlreadyExists = this.userService.userAlreadyExists(tempUser);

        // If the user already exists, return a bad request response
        if (userAlreadyExists) {
            return ResponseEntity.badRequest().build();
        } else {
            this.userService.saveNewUser(tempUser);
            return ResponseEntity.ok().build();
        }
    }
}
