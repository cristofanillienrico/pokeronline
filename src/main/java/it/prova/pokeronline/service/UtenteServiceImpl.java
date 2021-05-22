package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.utente.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository repository;


    @Override
    public List<Utente> listAllElements() {
        return (List<Utente>) repository.findAll();
    }

    @Override
    public Utente caricaSingoloElemento(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Utente caricaSingoloElementoEager(Long id) {
        return null;
    }

    @Override
    public Utente aggiorna(Utente utenteInstance) {
        return repository.save(utenteInstance);
    }

    @Override
    public Utente inserisciNuovo(Utente utenteInstance) {
        return repository.save(utenteInstance);
    }

    @Override
    public void rimuovi(Utente utenteInstance) {
        repository.delete(utenteInstance);
    }

    @Override
    public List<Utente> findByExample(Utente example) {
        return null;
    }
}
