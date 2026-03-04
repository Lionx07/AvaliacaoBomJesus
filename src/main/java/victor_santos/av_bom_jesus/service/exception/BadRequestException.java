package victor_santos.av_bom_jesus.service.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}
