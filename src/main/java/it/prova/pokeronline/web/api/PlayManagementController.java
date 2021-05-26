package it.prova.pokeronline.web.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

//    @Autowired
//    private TavoloService tavoloService;
//
//    @Autowired
//    private UtenteService utenteService;
//
//    @Autowired
//    private RuoloService ruoloService;
//
//
//    @PostMapping("/{creditoComprato}")
//    @ResponseStatus(HttpStatus.OK)
//    public void compraCredito(@PathVariable(value = "creditoComprato", required = true) Double creditoComprato, @RequestHeader("username") String username) {
//        User user = utenteService.findByUsername(username);
//        if (user == null) {
//            throw new UtenteNonTrovatoException("User non trovato");
//        }
//
//        user.setCreditoAccumulato(user.getCreditoAccumulato() + creditoComprato);
//        utenteService.aggiorna(user);
//
//    }
//
//    @GetMapping("/lastgame")
//    public Tavolo lastGame(@RequestHeader("username") String username) {
//        User user = utenteService.findByUsername(username);
//        if (user == null) {
//            throw new UtenteNonTrovatoException("User non trovato");
//        }
//        Tavolo tavoliDiUtente = tavoloService.findTavoloByUtentiContains(user);
//
//        if (tavoliDiUtente == null) {
//            throw new TavoloNotFoundException("L'user non è in nessun tavolo ");
//        }
//
//        return tavoliDiUtente;
//
//
//    }
//
//    @PutMapping("/abbandonapartita")
//    @ResponseStatus(HttpStatus.OK)
//    public void abbandonaPartita(@RequestHeader("username") String username) {
//        User user = utenteService.findByUsername(username);
//        if (user == null) {
//            throw new UtenteNonTrovatoException("User non trovato");
//        }
//        if (user.getTavolo() == null) {
//
//            throw new TavoloNotFoundException("L'user non sta giocando nessuna partita");
//
//        }
//
//        Long nuovaEsperienza = user.getEsperienzaAccumulata();
//        user.setEsperienzaAccumulata(++nuovaEsperienza);
//        user.setTavolo(null);
//        utenteService.aggiorna(user);
//
//    }
//
//    @GetMapping("/tavolipapabili")
//    public List<Tavolo> ricercaTavoliPapabili(@RequestHeader("username") String username) {
//        User user = utenteService.findByUsername(username);
//        if (user == null) {
//            throw new UtenteNonTrovatoException("User non trovato");
//        }
//        List<Tavolo> tavoliPapabili = tavoloService.findAllByEsperienzaMinimaIsLessThanEqual(user.getEsperienzaAccumulata());
//        return tavoliPapabili;
//
//    }
//
//    //se tavolo non contiene gia utente
//    //se giocatore ha abbastanza esperienza e abbastanza soldi
//    //gioca
//    //lascia tavolo
//    @PostMapping("/gioca/{idTavolo}")
//    @ResponseStatus(HttpStatus.OK)
//    public String giocaPartita(@PathVariable(value = "idTavolo", required = true) Long idTavolo, @RequestHeader("username") String username) {
//        User user = utenteService.findByUsername(username);
//        if (user == null) {
//            throw new UtenteNonTrovatoException("User non trovato");
//        }
//        Tavolo tavolo = tavoloService.caricaSingoloElemento(idTavolo);
//
//        if (tavolo == null) {
//            throw new TavoloNotFoundException("L'user non è in nessun tavolo ");
//        }
//
//        if (tavolo.getUtenti().contains(user)) {
//            throw new PermessiNonSufficientiException("Sei già in quel tavolo");
//        }
//        if (tavolo.getEsperienzaMinima() > user.getEsperienzaAccumulata()) {
//            throw new PermessiNonSufficientiException("Non hai abbastanza esperienza");
//        }
//        if (tavolo.getCifraMinima() > user.getCreditoAccumulato()) {
//            throw new PermessiNonSufficientiException("Non hai abbastanza soldi");
//        }
//
//        //ora inizia la partita vera e propria
//        double segno = Math.random();
//
//        if (segno >= 0.5) {
//            segno = 1;
//        } else {
//            segno = -1;
//        }
//
//        Integer somma = (int) (Math.random() * 1000);
//        Double tot = segno * somma;
//
//        Double creditoResiduo = user.getCreditoAccumulato() + tot;
//        if (creditoResiduo < 0) {
//            user.setCreditoAccumulato(0d);
//            utenteService.aggiorna(user);
//            return "Hai esaurito il credito";
//
//        }
//
//        user.setCreditoAccumulato(creditoResiduo);
//        utenteService.aggiorna(user);
//        return "Partita conclusa il tuo credito è " + user.getCreditoAccumulato();
//
//
//    }


}
