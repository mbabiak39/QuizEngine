package engine.service;

import engine.dto.UserForm;
import engine.model.User;
import engine.repositories.UserRepository;
import engine.security.PrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUser(@Valid UserForm userForm) {
        if (!userRepository.existsByEmail(userForm.getEmail())) {
            User newUser = new User();
            newUser.setEmail(userForm.getEmail());
            newUser.setPassword(encoder.encode(userForm.getPassword()));
            userRepository.save(newUser);
            return newUser;
        } else {
            throw new BadRequest();
        }
    }

    @Override
    public PrincipalDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new PrincipalDetails(user);
        } else throw new UsernameNotFoundException(email);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public class BadRequest extends RuntimeException {
    }
}
