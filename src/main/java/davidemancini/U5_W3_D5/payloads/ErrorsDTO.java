package davidemancini.U5_W3_D5.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorsDTO(String messaggio,
                        LocalDateTime oraErrore) {
}
