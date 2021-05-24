package it.prova.pokeronline.repository.tavolo;

import it.prova.pokeronline.model.Tavolo;

import java.util.List;

public interface CustomTavoloRepository {

    List<Tavolo> findByExample(Tavolo example);
}
