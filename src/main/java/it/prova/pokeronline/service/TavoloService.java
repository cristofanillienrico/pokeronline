package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;

import java.util.List;
import java.util.Set;

public interface TavoloService {

    List<Tavolo> listAllElements();

    Tavolo caricaSingoloElemento(Long id);

    Tavolo caricaSingoloElementoEager(Long id);

    Tavolo aggiorna(Tavolo tavoloInstance);

    Tavolo inserisciNuovo(Tavolo tavoloInstance);

    void rimuovi(Tavolo tavoloInstance);

    List<Tavolo> findByExample(Tavolo example);

    List<Tavolo> findAllByUtenteCreazione(Utente utenteCreazione);

    Tavolo findByIdAndUtenteCreazione(Long id ,Utente utenteCreazione);

    Tavolo findTavoloByUtentiContains(Utente utente);


}
