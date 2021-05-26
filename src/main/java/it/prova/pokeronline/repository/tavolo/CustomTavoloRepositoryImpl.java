package it.prova.pokeronline.repository.tavolo;

import it.prova.pokeronline.model.Tavolo;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tavolo> findByExample(Tavolo example) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        List<String> whereClauses = new ArrayList<String>();

        StringBuilder queryBuilder = new StringBuilder("select t from Tavolo t where t.id = t.id ");

        if (example.getEsperienzaMinima() != null && example.getEsperienzaMinima() >= 0) {
            whereClauses.add(" t.esperienzaMinima  >= :esperienzaMinima ");
            paramaterMap.put("esperienzaMinima", example.getEsperienzaMinima());
        }
        if (example.getCifraMinima() != null && example.getCifraMinima() >= 0) {
            whereClauses.add(" t.getCifraMinima  >= :getCifraMinima ");
            paramaterMap.put("getCifraMinima", example.getCifraMinima());
        }
        if (StringUtils.isNotEmpty(example.getDenominazione())) {
            whereClauses.add(" t.denominazione like :denominazione ");
            paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
        }
        if (example.getDataCreazione() != null && example.getDataCreazione() != null) {
            whereClauses.add("t.dataCreazione >= :dataCreazione ");
            paramaterMap.put("dataCreazione", example.getDataCreazione());
        }
        if (example.getUserCreazione() != null) {
            whereClauses.add("t.utenteCreazione >= :utenteCreazione ");
            paramaterMap.put("utenteCreazione", example.getUserCreazione());
        }


        queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
        queryBuilder.append(StringUtils.join(whereClauses, " and "));
        TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

        for (String key : paramaterMap.keySet()) {
            typedQuery.setParameter(key, paramaterMap.get(key));
        }

        return typedQuery.getResultList();
    }
}

