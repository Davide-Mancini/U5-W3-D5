package davidemancini.U5_W3_D5.controllers;

import davidemancini.U5_W3_D5.entities.Evento;
import davidemancini.U5_W3_D5.payloads.NewEventoDTO;
import davidemancini.U5_W3_D5.services.EventoService;
import davidemancini.U5_W3_D5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private UserService userService;
    @Autowired
    private EventoService eventoService;


    @PostMapping("/create-event")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')") //SOLO GLI ORGANIZZATORI POSSO CREARE NUOVI EVENTI
    public Evento newEvento(@RequestBody NewEventoDTO body) {
        return eventoService.creaNuovoEvento(body);

    }

    @DeleteMapping("/delete-event/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void deleteEvento(@PathVariable UUID eventoId) {
        eventoService.deleteEvento(eventoId);
    }

    @GetMapping("/eventi-disponibili")
    public Page<Evento> tuttiEventi() {
       
        return eventoService.findAll(0, 10, "data");
    }
}
