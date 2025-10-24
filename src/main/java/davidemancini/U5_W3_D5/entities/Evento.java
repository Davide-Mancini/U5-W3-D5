package davidemancini.U5_W3_D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue

    private UUID id;
    private String titoloEvento;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    private int numPostiDisponibili;
    @ManyToOne
    @JoinColumn(name = "creatore_id")
    private User creatoreEvento;

    public Evento(String titoloEvento, String descrizione, LocalDate data, String luogo, int numPostiDisponibili, User creatoreEvento) {
        this.titoloEvento = titoloEvento;
        this.descrizione = descrizione;
        this.data = data;
        this.luogo = luogo;
        this.numPostiDisponibili = numPostiDisponibili;
        this.creatoreEvento = creatoreEvento;
    }

}
