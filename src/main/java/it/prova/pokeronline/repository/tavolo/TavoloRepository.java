package it.prova.pokeronline.repository.tavolo;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TavoloRepository extends CrudRepository<Tavolo, Long> {

    List<Tavolo> findAllByUtenteCreazione(Utente utenteCreazione);

    Tavolo findByIdAndUtenteCreazione(Long id ,Utente utenteCreazione);
}
