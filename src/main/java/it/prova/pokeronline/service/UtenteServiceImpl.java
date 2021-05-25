package it.prova.pokeronline.service;

import it.prova.pokeronline.model.User;
import it.prova.pokeronline.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository repository;


    @Override
    public List<User> listAllElements() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Transactional
    public User aggiorna(User userInstance) {
        return repository.save(userInstance);
    }

    @Transactional
    public User inserisciNuovo(User userInstance) {
        return repository.save(userInstance);
    }

    @Transactional
    public void rimuovi(User userInstance) {
        repository.delete(userInstance);
    }

    @Override
    public List<User> findByExample(User example) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
