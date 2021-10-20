package it.prova.gestionestazioni.dao.stazione;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazioni.model.Stazione;

public class StazioneDAOImpl implements StazioneDAO{
	private EntityManager entityManager;
	@Override
	public List<Stazione> list() throws Exception {
		return entityManager.createQuery("from Stazione", Stazione.class).getResultList();
	}

	@Override
	public Stazione get(Long id) throws Exception {
		return entityManager.find(Stazione.class, id);
	}

	@Override
	public void update(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Stazione input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}
	
	@Override
	public Stazione findByIdFetchingTreni(Long id) throws Exception {
		TypedQuery<Stazione> query = entityManager.createQuery("select s FROM Stazione s left join fetch s.treni t where s.id = :idStazione", Stazione.class);
		query.setParameter("idStazione", id);
		return query.getResultList().stream().findFirst().orElse(null);
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
