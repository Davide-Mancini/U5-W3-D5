package davidemancini.U5_W3_D5.controllers;

import davidemancini.U5_W3_D5.entities.PrenotazioneEvento;
import davidemancini.U5_W3_D5.payloads.PrenotaazioneDTO;
import davidemancini.U5_W3_D5.services.EventoService;
import davidemancini.U5_W3_D5.services.PrenotazioneService;
import davidemancini.U5_W3_D5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventoService eventoService;


    @PostMapping("/nuova-prenotazione")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneEvento newPrenotazione(@RequestBody PrenotaazioneDTO body) {
        return prenotazioneService.newPrenotazione(body);
    }
}
