package davidemancini.U5_W3_D5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID uuid) {
        super("id " + uuid + " non trovato");
    }

    public NotFoundException(String email) {
        super(email);
    }
}
