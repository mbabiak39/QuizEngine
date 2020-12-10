package engine.model;
import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    long userId;

    @Column
    private String email;

    @Column
    private String password;

    public User() {
    }

    public User(long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
