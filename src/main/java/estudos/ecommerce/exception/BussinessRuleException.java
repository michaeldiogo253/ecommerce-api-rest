package estudos.ecommerce.exception;

public class BussinessRuleException extends RuntimeException {

    private String message;

    public BussinessRuleException(String message) {

        this.message = message;
    }
}
