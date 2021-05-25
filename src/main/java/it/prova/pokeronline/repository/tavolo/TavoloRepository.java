package it.prova.pokeronline.repository.tavolo;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, CustomTavoloRepository {

    List<Tavolo> findAllByUtenteCreazione(User userCreazione);

    Tavolo findByIdAndUtenteCreazione(Long id, User userCreazione);

    Tavolo findTavoloByUtentiContains(User user);

    List<Tavolo> findAllByEsperienzaMinimaIsLessThanEqual(Long esperienzaAccumulata);
}
