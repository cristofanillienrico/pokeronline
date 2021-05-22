package it.prova.pokeronline.web.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RuoloNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RuoloNotFoundException(String message) {
        super(message);
    }
}
