package davidemancini.U5_W3_D5.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class PrenotazioneEvento {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime oraPrenotazione;

    public PrenotazioneEvento(Evento evento, User user) {
        this.evento = evento;
        this.user = user;
        this.oraPrenotazione = LocalDateTime.now(); //QUANDO SALVO UNA PRENOTAZIONE METTO DIRETTAMENTE L'ORA DI QEUL MOMENTO NEL COSTRUTTORE.
    }
}
