package engine.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateNewUser {

    @Email
    @NotEmpty
    @NotNull
    private String email;

    @Size(min = 5)
    @NotEmpty
    @NotNull
    private String password;

    public CreateNewUser() {
    }

    public CreateNewUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
