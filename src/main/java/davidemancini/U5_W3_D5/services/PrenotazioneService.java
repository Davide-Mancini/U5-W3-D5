package davidemancini.U5_W3_D5.services;

import davidemancini.U5_W3_D5.entities.Evento;
import davidemancini.U5_W3_D5.entities.PrenotazioneEvento;
import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.MyBadRequestException;
import davidemancini.U5_W3_D5.payloads.NewEventoDTO;
import davidemancini.U5_W3_D5.payloads.PrenotaazioneDTO;
import davidemancini.U5_W3_D5.repositories.EventoRepository;
import davidemancini.U5_W3_D5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UserService userService;


    public Page<PrenotazioneEvento> findAll(int pageNumber, int pageSize, String sortBY) {
        if (pageSize > 30) pageSize = 30;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBY).ascending());
        return prenotazioneRepository.findAll(pageable);
    }

    public PrenotazioneEvento newPrenotazione(PrenotaazioneDTO body) {
        User userTrovato = userService.findById(body.user());
        Evento eventoTrovato = eventoService.findById(body.evento());
        if (eventoTrovato.getNumPostiDisponibili() < 0) {
            throw new MyBadRequestException("Non ci sono posti disponibili per questo evento");

        }
        PrenotazioneEvento newPrenotazione = new PrenotazioneEvento(eventoTrovato, userTrovato);
        PrenotazioneEvento prenotazioneEventoSalvata = prenotazioneRepository.save(newPrenotazione);
        eventoService.decrementaPosti(body.evento());//USO IL METODO PER DECREMENTARE I POSTI OGNI VOLTA CHE VIENE EFFETTUATE UNA NUOVA PRENOTAZIONE.


        return prenotazioneEventoSalvata;

    }
}
