package davidemancini.U5_W3_D5.controllers;

import davidemancini.U5_W3_D5.entities.Evento;
import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.payloads.NewEventoDTO;
import davidemancini.U5_W3_D5.payloads.NewUserDTO;
import davidemancini.U5_W3_D5.services.EventoService;
import davidemancini.U5_W3_D5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventoService eventoService;


    @GetMapping("/me") //RITORNA IL PEOFILO DELLO USER "LOGGATO"
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")//PUO FARE L'UPDATE SOLO SUL PROFILO "LOGGATO"
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody NewUserDTO body) {
        return userService.findByIdAndUpdate(currentAuthenticatedUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        userService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }
}
