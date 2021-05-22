package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Tavolo;

import java.util.List;

public interface TavoloService {

    List<Tavolo> listAllElements();

    Tavolo caricaSingoloElemento(Long id);

    Tavolo caricaSingoloElementoEager(Long id);

    Tavolo aggiorna(Tavolo tavoloInstance);

    Tavolo inserisciNuovo(Tavolo tavoloInstance);

    void rimuovi(Tavolo tavoloInstance);

    List<Tavolo> findByExample(Tavolo example);


}
