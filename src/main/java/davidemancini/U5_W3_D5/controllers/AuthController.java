package davidemancini.U5_W3_D5.controllers;

import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.payloads.LoginDTO;
import davidemancini.U5_W3_D5.payloads.LoginRespondeDTO;
import davidemancini.U5_W3_D5.payloads.NewUserDTO;
import davidemancini.U5_W3_D5.services.AuthService;
import davidemancini.U5_W3_D5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody NewUserDTO body) {
        return userService.register(body);
    }

    @PostMapping("/login")
    public LoginRespondeDTO login(@RequestBody LoginDTO body) {
        return new LoginRespondeDTO(authService.controlloCredenziali(body));
    }
}
