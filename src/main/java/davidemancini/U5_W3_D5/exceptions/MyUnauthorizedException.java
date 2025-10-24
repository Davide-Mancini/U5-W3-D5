package davidemancini.U5_W3_D5.exceptions;

public class MyUnauthorizedException extends RuntimeException {
    public MyUnauthorizedException(String message) {
        super(message);
    }
}
