package it.prova.pokeronline.repository.utente;

import it.prova.pokeronline.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends CrudRepository<User, Long>, CustomUtenteRepository {

    User findByUsername(String username);


}
