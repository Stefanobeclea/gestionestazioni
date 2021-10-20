package it.prova.gestionestazioni.dao.treno;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.gestionestazioni.model.Treno;

public class TrenoDAOImpl implements TrenoDAO{
	private EntityManager entityManager;

	@Override
	public List<Treno> list() throws Exception {
		return entityManager.createQuery("from Treno", Treno.class).getResultList();
	}

	@Override
	public Treno get(Long id) throws Exception {
		return entityManager.find(Treno.class, id);
	}

	@Override
	public void update(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Treno input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}
	
	@Override
	public List<Long> findNumeroAbitantiDiUnaCittaTramiteUnTreno(Long id) throws Exception {
		TypedQuery<Long> query = entityManager.createQuery("select c.numeroAbitanti from Citta c left join c.stazioni s left join s.treni t where t.id = :idTreno", Long.class);
		query.setParameter("idTreno", id);
		return query.getResultList();
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
