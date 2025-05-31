package exceptions;

public class InvalidKeyEntered extends RuntimeException {
    public InvalidKeyEntered(String message) {
        super(message);
    }
}
