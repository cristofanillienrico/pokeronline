package it.prova.pokeronline.web.api;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RuoloService ruoloService;



    @GetMapping
    public List<Tavolo> getAll(@RequestHeader("username") String username) {
        Ruolo ruolo = ruoloService.findByUtenteUsername(username);
        if (ruolo.getCodice()=="ROLE_PLAYER"){

        }
            return tavoloService.listAllElements();
    }

    @GetMapping("/{id}")
    public Tavolo findById(@PathVariable(value = "id", required = true) long id) {
        Tavolo tavolo = tavoloService.caricaSingoloElemento(id);

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