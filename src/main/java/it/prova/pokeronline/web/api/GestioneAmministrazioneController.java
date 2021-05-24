package it.prova.pokeronline.web.api;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Utente;
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
    public List<Utente> getAll(@RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Ruolo ruolo = utente.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.listAllElements();
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());

    }

    @GetMapping("/{id}")
    public Utente findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Ruolo ruolo = utente.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.caricaSingoloElemento(id);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Utente createNew(@Valid @RequestBody Utente utenteInput, @RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Ruolo ruolo = utente.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return utenteService.inserisciNuovo(utenteInput);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    @PutMapping("/{id}")
    public Utente update(@Valid @RequestBody Utente utenteInput, @PathVariable(required = true) Long id, @RequestHeader("username") String username) {
        Utente utenteHeader = utenteService.findByUsername(username);
        if (utenteHeader == null) {
            throw new UtenteNonTrovatoException("Utente header non trovato");
        }
        Ruolo ruolo = utenteHeader.getRuolo();
        if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            Utente utenteDaModificare = utenteService.caricaSingoloElemento(id);

            if (utenteDaModificare == null)
                throw new UtenteNonTrovatoException("Utente da modificare not found con id: " + id);

            utenteInput.setId(id);
            return utenteService.aggiorna(utenteInput);
        }
        throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());


    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id) {
        Utente utente = utenteService.caricaSingoloElemento(id);

        if (utente == null)
            throw new UtenteNonTrovatoException("Utente not found con id: " + id);

        utenteService.rimuovi(utente);
    }

    @PostMapping("/search")
    public List<Utente> search(@RequestBody Utente example) {
        return utenteService.findByExample(example);
    }
}
