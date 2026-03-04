package victor_santos.av_bom_jesus.service.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {

    }

    public NotFoundException(String msg){
        super(msg);
    }
}
