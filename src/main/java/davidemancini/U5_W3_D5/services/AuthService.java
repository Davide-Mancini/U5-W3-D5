package davidemancini.U5_W3_D5.services;

import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.MyUnauthorizedException;
import davidemancini.U5_W3_D5.payloads.LoginDTO;
import davidemancini.U5_W3_D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bCrypt;
    @Autowired
    private JWTTools jwtTools;


    public String controlloCredenziali(LoginDTO body) {
        User trovato = userService.findByEmail(body.email());
        if (bCrypt.matches(body.password(), trovato.getPassword())) {
            return jwtTools.createToken(trovato);
        } else {
            throw new MyUnauthorizedException("credenziali sbagliate");
        }

    }
}
