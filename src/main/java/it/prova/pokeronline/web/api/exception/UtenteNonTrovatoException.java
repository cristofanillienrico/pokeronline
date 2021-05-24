package it.prova.pokeronline.web.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UtenteNonTrovatoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UtenteNonTrovatoException(String message) {
        super("Book id not found : " );
    }
}
