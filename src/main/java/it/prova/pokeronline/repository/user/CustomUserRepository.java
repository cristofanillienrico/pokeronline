package it.prova.pokeronline.repository.user;

import java.util.List;

import it.prova.pokeronline.model.User;

public interface CustomUserRepository {
    public List<User> findByExample(User example);
}
