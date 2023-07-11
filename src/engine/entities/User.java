package engine.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
            + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")
    @NotEmpty
    @NotNull
    @Id
    @Column(name = "username")
    private java.lang.String email;

    @Size(min = 5)
    @NotEmpty
    @NotNull
    private java.lang.String password;

    private int enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CompletedQuiz> completedQuizzes;

    @OneToMany
    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Quiz> quizzes;



    public User() {
    }

    public User(java.lang.String email, java.lang.String password) {
        this.email = email;
        this.password = password;
        this.enabled = 1;
        this.completedQuizzes = new ArrayList<>();
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public java.lang.String getPassword() {
        return this.password;
    }

    @Override
    public java.lang.String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<CompletedQuiz> getCompletedQuizzes() {
        return completedQuizzes;
    }

    public void setCompletedQuizzes(List<CompletedQuiz> completedQuizzes) {
        this.completedQuizzes = completedQuizzes;
    }

    public void addNewCompletedQuiz(CompletedQuiz completedQuiz) {
        this.completedQuizzes.add(completedQuiz);
    }
}
