package davidemancini.U5_W3_D5.controllers;

import davidemancini.U5_W3_D5.entities.Evento;
import davidemancini.U5_W3_D5.payloads.NewEventoDTO;
import davidemancini.U5_W3_D5.services.EventoService;
import davidemancini.U5_W3_D5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventoService eventoService;


}
