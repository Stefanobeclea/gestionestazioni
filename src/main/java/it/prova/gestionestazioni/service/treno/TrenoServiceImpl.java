package it.prova.gestionestazioni.service.treno;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazioni.dao.EntityManagerUtil;
import it.prova.gestionestazioni.dao.treno.TrenoDAO;
import it.prova.gestionestazioni.model.Treno;



public class TrenoServiceImpl implements TrenoService{
	private TrenoDAO trenoDAO;

	@Override
	public List<Treno> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return trenoDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Treno caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return trenoDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Treno trenoInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.update(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Treno trenoInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.insert(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Treno trenoInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			trenoDAO.delete(trenoInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void setTrenoDAO(TrenoDAO trenoDAO) {
		this.trenoDAO = trenoDAO;
	}

	@Override
	public List<Long> caricaNumeroAbitantiDiUnaCittaTramiteUnTreno(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			trenoDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return trenoDAO.findNumeroAbitantiDiUnaCittaTramiteUnTreno(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	
}
