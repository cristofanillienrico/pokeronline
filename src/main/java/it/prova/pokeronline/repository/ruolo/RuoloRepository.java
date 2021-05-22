package it.prova.pokeronline.repository.ruolo;

import it.prova.pokeronline.model.Ruolo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RuoloRepository extends CrudRepository<Ruolo, Long> {

//    @Query("from Ruolo r join fetch Utente u where u.username=?1")
//    Ruolo findByUtenteUsername(String username);
}
