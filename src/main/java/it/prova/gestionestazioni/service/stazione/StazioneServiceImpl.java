package it.prova.gestionestazioni.service.stazione;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestionestazioni.dao.EntityManagerUtil;
import it.prova.gestionestazioni.dao.stazione.StazioneDAO;
import it.prova.gestionestazioni.model.Stazione;
import it.prova.gestionestazioni.model.Treno;





public class StazioneServiceImpl implements StazioneService{
	private StazioneDAO stazioneDAO;

	@Override
	public void setStazioneDAO(StazioneDAO stazioneDAO) {
		this.stazioneDAO = stazioneDAO;
	}

	@Override
	public List<Stazione> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Stazione caricaSingoloElemento(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Stazione StazioneInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.update(StazioneInstance);

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
	public void inserisciNuovo(Stazione StazioneInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.insert(StazioneInstance);

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
	public void rimuovi(Stazione StazioneInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			stazioneDAO.delete(StazioneInstance);

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
	public void aggiungiTreno (Stazione StazioneInstance, Treno trenoInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			StazioneInstance = entityManager.merge(StazioneInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			trenoInstance = entityManager.merge(trenoInstance);

			StazioneInstance.getTreni().add(trenoInstance);
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

	
	public void creaECollegaStazioneETreno(Stazione StazioneInstance, Treno trenoInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// collego le due entità: questa cosa funziona grazie al fatto che ho
			// CascadeType.PERSIST, CascadeType.MERGE dentro l'owner della relazione (Cd in
			// questo caso)
			StazioneInstance.getTreni().add(trenoInstance);

			// ********************** IMPORTANTE ****************************
			// se io rimuovo i cascade, non funziona più e si deve prima salvare il genere
			// (tramite genereDAO iniettando anch'esso) e poi
			// sfruttare i metodi addTo o removeFrom dentro Cd:
			// GenereDAO genereDAO = MyDaoFactory.getGenereDAOInstance();
			// genereDAO.setEntityManager(entityManager);
			// genereDAO.insert(genereTransientInstance);
			// cdTransientInstance.addToGeneri(genereTransientInstance);
			// in questo caso però se il genere è già presente non ne tiene conto e
			// inserirebbe duplicati, ma è logico
			// ****************************************************************

			// inserisco il cd
			stazioneDAO.insert(StazioneInstance);

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
	public Stazione caricaSingoloElementoEagerTreni(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			stazioneDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return stazioneDAO.findByIdFetchingTreni(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
}
