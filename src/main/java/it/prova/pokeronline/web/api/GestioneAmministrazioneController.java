package it.prova.pokeronline.web.api;

import it.prova.pokeronline.model.StatoUtente;
import it.prova.pokeronline.model.User;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.PermessiNonSufficientiException;
import it.prova.pokeronline.web.api.exception.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/utente")
public class GestioneAmministrazioneController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<User> getAll(@RequestHeader("username") String username) {
        User user = utenteService.findByUsername(username);
        if (user == null) {
            throw new UtenteNonTrovatoException("User non trovato");
        }
        Ruolo ruolo = user.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.listAllElements();
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());

    }

    @GetMapping("/{id}")
    public User findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("username") String username) {
        User user = utenteService.findByUsername(username);
        if (user == null) {
            throw new UtenteNonTrovatoException("User non trovato");
        }
        Ruolo ruolo = user.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.caricaSingoloElemento(id);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createNew(@Valid @RequestBody User userInput, @RequestHeader("username") String username) {
        User user = utenteService.findByUsername(username);
        if (user == null) {
            throw new UtenteNonTrovatoException("User non trovato");
        }
        Ruolo ruolo = user.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.inserisciNuovo(userInput);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    @PutMapping("/{id}")
    public User update(@Valid @RequestBody User userInput, @PathVariable(required = true) Long id, @RequestHeader("username") String username) {
        User userHeader = utenteService.findByUsername(username);
        if (userHeader == null) {
            throw new UtenteNonTrovatoException("User header non trovato");
        }
        Ruolo ruolo = userHeader.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            User userDaModificare = utenteService.caricaSingoloElemento(id);

            if (userDaModificare == null)
                throw new UtenteNonTrovatoException("User da modificare not found con id: " + id);

            userInput.setId(id);
            return utenteService.aggiorna(userInput);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id, @RequestHeader("username") String username) {
        User userHeader = utenteService.findByUsername(username);
        if (userHeader == null) {
            throw new UtenteNonTrovatoException("User header non trovato");
        }
        Ruolo ruolo = userHeader.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            User userDaEliminare = utenteService.caricaSingoloElemento(id);

            if (userDaEliminare == null)
                throw new UtenteNonTrovatoException("User da eliminare not found con id: " + id);
            userDaEliminare.setStato(StatoUtente.DISABILITATO);
            utenteService.aggiorna(userDaEliminare);
        }


    }

    @PostMapping("/search")
    public List<User> search(@RequestBody User example, @RequestHeader("username") String username) {
        User userHeader = utenteService.findByUsername(username);
        if (userHeader == null) {
            throw new UtenteNonTrovatoException("User header non trovato");
        }
        Ruolo ruolo = userHeader.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.findByExample(example);

        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }
}
