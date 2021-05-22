package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.repository.ruolo.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloServiceImpl implements RuoloService {
    @Autowired
    private RuoloRepository repository;

    @Override
    public Ruolo findByUtenteUsername(String username) {
        return repository.findByUtenteUsername(username);
    }
}
