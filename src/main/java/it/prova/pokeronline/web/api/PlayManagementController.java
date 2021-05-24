package it.prova.pokeronline.web.api;


import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.RuoloService;
import it.prova.pokeronline.service.TavoloService;
import it.prova.pokeronline.service.UtenteService;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import it.prova.pokeronline.web.api.exception.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/* Play management (in genere a carico del PLAYER):
COMPRA CREDITO (ti invio di quanto aumentare il mio credito…lasciamo stare le considerazioni che emergono
 sul fatto che ci vorrebbe un payment provider tipo Paypal).
 DAMMI IL LAST GAME (restituisce un valore solo se io sono ancora nel set di qualche tavolo).
 ABBANDONA PARTITA (il sistema fa il ++ di esperienza. Qui si individua immediatamente un bug cioè qualcuno
  per accumulare esperienza potrebbe entrare e uscire n volte senza giocare. Ma a noi non importa…).
  RICERCA (ricerca solo i tavoli in cui esperienza minima <= esperienza accumulata).
  GIOCA PARTITA A DETERMINATO TAVOLO inviato come input ovviamente (Gestire a piacere il caso
   credito < cifra minima)
 */


@RestController
@RequestMapping("api/play")
public class PlayManagementController {

    @Autowired
    private TavoloService tavoloService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private RuoloService ruoloService;


    @PostMapping("/{creditoComprato}")
    @ResponseStatus(HttpStatus.OK)
    public void compraCredito(@PathVariable(value = "creditoComprato", required = true) Double creditoComprato, @RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }

        utente.setCreditoAccumulato(utente.getCreditoAccumulato() + creditoComprato);
        utenteService.aggiorna(utente);

    }

    @GetMapping("/lastgame")
    public Tavolo lastGame(@RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        Tavolo tavoliDiUtente = tavoloService.findTavoloByUtentiContains(utente);

        if (tavoliDiUtente == null) {
            throw new TavoloNotFoundException("L'utente non è in nessun tavolo ");
        }

        return tavoliDiUtente;


    }

    @PutMapping("/abbandonapartita")
    @ResponseStatus(HttpStatus.OK)
    public void abbandonaPartita(@RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        if (utente.getTavolo() == null) {

            throw new TavoloNotFoundException("L'utente non sta giocando nessuna partita");

        }

        Long nuovaEsperienza = utente.getEsperienzaAccumulata();
        utente.setEsperienzaAccumulata(++nuovaEsperienza);
        utente.setTavolo(null);
        utenteService.aggiorna(utente);

    }

    @GetMapping("/tavolipapabili")
    public List<Tavolo> ricercaTavoliPapabili(@RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }
        List<Tavolo> tavoliPapabili = tavoloService.findAllByEsperienzaMinimaIsLessThanEqual(utente.getEsperienzaAccumulata());
        return tavoliPapabili;

    }

    //se tavolo non contiene gia utente
    //se giocatore ha abbastanza esperienza e abbastanza soldi
    //gioca
    //lascia tavolo
    @PostMapping("/{idTavolo}")
    @ResponseStatus(HttpStatus.OK)
    public void giocaPartita(@PathVariable(value = "idTavolo", required = true) Long idTavolo, @RequestHeader("username") String username) {
        Utente utente = utenteService.findByUsername(username);
        if (utente == null) {
            throw new UtenteNonTrovatoException("Utente non trovato");
        }

        utente.setCreditoAccumulato(utente.getCreditoAccumulato() + creditoComprato);
        utenteService.aggiorna(utente);

    }


}
