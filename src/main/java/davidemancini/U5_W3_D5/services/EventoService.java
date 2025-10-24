package davidemancini.U5_W3_D5.services;

import davidemancini.U5_W3_D5.entities.Evento;
import davidemancini.U5_W3_D5.entities.User;
import davidemancini.U5_W3_D5.exceptions.NotFoundException;
import davidemancini.U5_W3_D5.payloads.NewEventoDTO;
import davidemancini.U5_W3_D5.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UserService userService;

    public Page<Evento> findAll(int pageNumber, int pageSize, String sortBY) {
        if (pageSize > 30) pageSize = 30;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBY).ascending());
        return eventoRepository.findAll(pageable);
    }

    public Evento findById(UUID id) {
        return eventoRepository.findById(id).orElseThrow(() -> new NotFoundException("evento con id " + id + " non trovato"));
    }

    public Evento creaNuovoEvento(NewEventoDTO body) {
        User trovato = userService.findById(body.creatoreEvento());
        Evento newEvento = new Evento(body.titolo(), body.descrizione(), body.date(), body.luogo(), body.numPostiDisponibili(), trovato);

        return eventoRepository.save(newEvento);
    }

    public void deleteEvento(UUID id) {
        Evento trovato = eventoRepository.findById(id).orElseThrow(() -> new NotFoundException("evento da eliminare " + id + " non trovato"));
        eventoRepository.delete(trovato);
    }
}
