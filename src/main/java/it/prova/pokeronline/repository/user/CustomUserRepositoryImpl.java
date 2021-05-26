package it.prova.pokeronline.repository.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pokeronline.model.User;

public class CustomUserRepositoryImpl implements CustomUserRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> findByExample(User example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select User u  where u.id = u.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" u.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" u.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getUsername())) {
			whereClauses.add(" u.username like :username ");
			paramaterMap.put("username", "%" + example.getUsername() + "%");
		}
		if (StringUtils.isNotEmpty(example.getPassword())) {
			whereClauses.add(" u.password like :password ");
			paramaterMap.put("password", "%" + example.getPassword() + "%");
		}
		if (example.getDataRegistrazione() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDataRegistrazione());
		}
		if (example.getCreditoAccumulato() != null) {
			whereClauses.add("u.creditoAccumulato >= :creditoAccumulato ");
			paramaterMap.put("creditoAccumulato", example.getCreditoAccumulato());
		}
		if (example.getCreditoAccumulato() != null) {
			whereClauses.add("u.creditoResiduo >= :creditoResiduo ");
			paramaterMap.put("creditoResiduo", example.getCreditoAccumulato());
		}
		if (example.getEsperienzaAccumulata() != null) {
			whereClauses.add("u.esperienzaAccumulata >= :esperienzaAccumulata ");
			paramaterMap.put("esperienzaAccumulata", example.getEsperienzaAccumulata());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<User> typedQuery = entityManager.createQuery(queryBuilder.toString(), User.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
}
