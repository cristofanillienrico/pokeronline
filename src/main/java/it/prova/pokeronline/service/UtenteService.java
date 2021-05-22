package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Utente;

import java.util.List;

public interface UtenteService {

    List<Utente> listAllElements();

    Utente caricaSingoloElemento(Long id);

    Utente caricaSingoloElementoEager(Long id);

    Utente aggiorna(Utente utenteInstance);

    Utente inserisciNuovo(Utente utenteInstance);

    void rimuovi(Utente utenteInstance);

    List<Utente> findByExample(Utente example);

    Utente findByUsername(String username);

}
