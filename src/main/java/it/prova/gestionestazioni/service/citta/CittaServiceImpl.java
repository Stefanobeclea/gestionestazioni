package it.prova.gestionestazioni.service.citta;

import java.util.List; 

import javax.persistence.EntityManager;

import it.prova.gestionestazioni.dao.EntityManagerUtil;
import it.prova.gestionestazioni.dao.citta.CittaDAO;
import it.prova.gestionestazioni.dao.stazione.StazioneDAO;
import it.prova.gestionestazioni.model.Citta;
import it.prova.gestionestazioni.model.Stazione;

public class CittaServiceImpl implements CittaService{
	private CittaDAO cittaDAO;
	private StazioneDAO stazioneDAO;

	@Override
	public List<Citta> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Citta caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return cittaDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Citta cittaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.update(cittaInstance);

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
	public void inserisciNuovo(Citta cittaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.insert(cittaInstance);

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
	public void rimuovi(Citta cittaInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			cittaDAO.delete(cittaInstance);

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
	public void setCittaDAO(CittaDAO cittaDAO) {
		this.cittaDAO = cittaDAO;
	}
	
	@Override
	public void setStazioneDAO(StazioneDAO stazioneDAO) {
		this.stazioneDAO = stazioneDAO;
	}

	public void aggiungiArticolo(Citta cittaInstance, Stazione stazioneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			cittaDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			stazioneInstance = entityManager.merge(stazioneInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			cittaInstance = entityManager.merge(cittaInstance);

			cittaInstance.getStazioni().add(stazioneInstance);
			stazioneInstance.setCitta(cittaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

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
	public void rimuoviTramiteEager(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			//serve per injection, sia per citta che per stazione
			cittaDAO.setEntityManager(entityManager);
			stazioneDAO.setEntityManager(entityManager);
						
			
			Citta cittaInstance = cittaDAO.findByIdEager(id);

			for (Stazione stazioneItem : cittaInstance.getStazioni()) {
				stazioneItem.removeFromTreni(stazioneItem.getTreni());
				stazioneDAO.delete(stazioneItem);
			}

			cittaDAO.delete(cittaInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
}
