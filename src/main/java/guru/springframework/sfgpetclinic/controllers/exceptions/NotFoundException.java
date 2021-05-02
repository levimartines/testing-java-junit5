package guru.springframework.sfgpetclinic.controllers.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg);
    }

}
