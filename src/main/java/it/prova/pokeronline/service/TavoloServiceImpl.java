package it.prova.pokeronline.service;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.User;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TavoloServiceImpl implements TavoloService {

	@Autowired
	private TavoloRepository repository;

	@Override
	public List<Tavolo> listAllElements() {
		return (List<Tavolo>) repository.findAll();
	}

	@Override
	public Tavolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tavolo caricaSingoloElementoEager(Long id) {
		return null;
	}

	@Transactional
	public Tavolo aggiorna(Tavolo tavoloInstance) {
		if (!tavoloInstance.getUtenti().isEmpty()) {
			throw new RuntimeException("Il tavolo non è vuoto");
		}
		return repository.save(tavoloInstance);
	}

	@Transactional
	public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
		return repository.save(tavoloInstance);
	}

	@Transactional
	public void rimuovi(Tavolo tavoloInstance) {
		if (!tavoloInstance.getUtenti().isEmpty()) {
			throw new RuntimeException("Il tavolo non è vuoto");
		}
		repository.delete(tavoloInstance);
	}

	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		return repository.findByExample(example);
	}

	@Override
	public List<Tavolo> findAllByUtenteCreazione(User userCreazione) {
		return repository.findAllByUserCreazione(userCreazione);
	}

	@Override
	public Tavolo findByIdAndUtenteCreazione(Long id, User userCreazione) {
		return repository.findByIdAndUserCreazione(id, userCreazione);
	}

	@Override
	public Tavolo findTavoloByUtentiContains(User user) {
		return repository.findTavoloByUsersContains(user);
	}

	@Override
	public List<Tavolo> findAllByEsperienzaMinimaIsLessThanEqual(Long esperienzaAccumulata) {
		return repository.findAllByEsperienzaMinimaIsLessThanEqual(esperienzaAccumulata);
	}
}
