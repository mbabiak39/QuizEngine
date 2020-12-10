package engine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class UserForm {


    @NotNull
    @NotBlank(message = "Email is required")
    @Email
    @Pattern(regexp=".+@.+\\..+")
    private String email;

    @NotNull
    @NotBlank(message = "Password is required")
    @Size(min = 5)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserForm() {
    }

    public UserForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
