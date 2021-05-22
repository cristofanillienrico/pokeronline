package it.prova.pokeronline.repository.utente;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<Utente, Long> {


}
