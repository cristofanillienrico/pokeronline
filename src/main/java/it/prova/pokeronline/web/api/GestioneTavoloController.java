package it.prova.pokeronline.web.api;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.PermessiNonSufficientiException;
import it.prova.pokeronline.web.api.exception.RuoloNotFoundException;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/tavolo")
public class GestioneTavoloController {


    @Autowired
    private TavoloService tavoloService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RuoloService ruoloService;


    @GetMapping
    public List<Tavolo> getAll(@RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        Ruolo ruolo = utente.getRuolo();
        if (ruolo.getCodice().equals("ROLE_PLAYER")) {
            throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());

        } else if (ruolo.getCodice().equals("ROLE_SPECIAL_PLAYER")) {
            return tavoloService.findAllByUtenteCreazione(utente);

        } else if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            return tavoloService.listAllElements();
        }
        throw new RuoloNotFoundException("Il ruolo non è associato a quelli nel db");
    }

    @GetMapping("/{id}")
    public Tavolo findById(@PathVariable(value = "id", required = true) long id, @RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        Ruolo ruolo = utente.getRuolo();
        Tavolo tavolo = null;
        if (ruolo.getCodice().equals("ROLE_PLAYER")) {
            throw new PermessiNonSufficientiException("Stai inviando una richiesta come" + ruolo.getDescrizione());

        } else if (ruolo.getCodice().equals("ROLE_SPECIAL_PLAYER")) {
            tavolo = tavoloService.findByIdAndUtenteCreazione(id, utente);

        } else if (ruolo.getCodice().equals("ROLE_ADMIN")) {
            tavolo = tavoloService.caricaSingoloElemento(id);
        }


        if (tavolo == null)
            throw new TavoloNotFoundException("Tavolo not found con id: " + id);

        return tavolo;
    }

    // gli errori di validazione vengono mostrati con 400 Bad Request ma
    // elencandoli grazie al ControllerAdvice
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tavolo createNew(@Valid @RequestBody Tavolo tavoloInput) {
        return tavoloService.inserisciNuovo(tavoloInput);
    }

    @PutMapping("/{id}")
    public Tavolo update(@Valid @RequestBody Tavolo tavoloInput, @PathVariable(required = true) Long id) {
        Tavolo tavolo = tavoloService.caricaSingoloElemento(id);

        if (tavolo == null)
            throw new TavoloNotFoundException("Tavolo not found con id: " + id);

        tavoloInput.setId(id);
        return tavoloService.aggiorna(tavoloInput);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(required = true) Long id) {
        Tavolo tavolo = tavoloService.caricaSingoloElemento(id);

        if (tavolo == null)
            throw new TavoloNotFoundException("Tavolo not found con id: " + id);

        tavoloService.rimuovi(tavolo);
    }

    @PostMapping("/search")
    public List<Tavolo> search(@RequestBody Tavolo example) {
        return tavoloService.findByExample(example);
    }

}