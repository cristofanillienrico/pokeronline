package it.prova.pokeronline.service;

import it.prova.pokeronline.model.User;

import java.util.List;

public interface UtenteService {

    List<User> listAllElements();

    User caricaSingoloElemento(Long id);

    User caricaSingoloElementoEager(Long id);

    User aggiorna(User userInstance);

    User inserisciNuovo(User userInstance);

    void rimuovi(User userInstance);

    List<User> findByExample(User example);

    User findByUsername(String username);

}
