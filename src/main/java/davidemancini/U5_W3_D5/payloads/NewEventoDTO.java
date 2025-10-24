package davidemancini.U5_W3_D5.payloads;

import davidemancini.U5_W3_D5.entities.User;

import java.time.LocalDate;
import java.util.UUID;

public record NewEventoDTO(String titolo,
                           String descrizione,
                           LocalDate date,
                           String luogo,
                           int numPostiDisponibili,
                           UUID creatoreEvento
) {
}
