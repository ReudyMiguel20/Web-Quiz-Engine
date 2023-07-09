package engine.restcontrollers;

import engine.dto.CreateNewUser;
import engine.entities.User;
import engine.services.servicesimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody CreateNewUser newUser) {
        User tempUser = new User(newUser.getEmail(), newUser.getPassword());
        boolean userAlreadyExists = this.userService.userAlreadyExists(tempUser);

        if (userAlreadyExists) {
            return ResponseEntity.badRequest().build();
        } else {
            this.userService.saveNewUser(tempUser);
            return ResponseEntity.ok().build();
        }
    }
}
