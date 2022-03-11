package estudos.ecommerce.exception;

public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(String message) {

        this.message = message;
    }
}
