package it.prova.pokeronline.repository.utente;

import it.prova.pokeronline.model.Utente;

import java.util.List;

public interface CustomUtenteRepository {
    public List<Utente> findByExample(Utente example);
}
