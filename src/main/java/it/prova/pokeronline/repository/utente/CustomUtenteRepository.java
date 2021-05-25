package it.prova.pokeronline.repository.utente;

import it.prova.pokeronline.model.User;

import java.util.List;

public interface CustomUtenteRepository {
    public List<User> findByExample(User example);
}
