package it.prova.gestionestazioni.dao;

import it.prova.gestionestazioni.dao.citta.CittaDAO;
import it.prova.gestionestazioni.dao.citta.CittaDAOImpl;
import it.prova.gestionestazioni.dao.stazione.StazioneDAO;
import it.prova.gestionestazioni.dao.stazione.StazioneDAOImpl;
import it.prova.gestionestazioni.dao.treno.TrenoDAO;
import it.prova.gestionestazioni.dao.treno.TrenoDAOImpl;

public class MyDaoFactory {
	private static StazioneDAO stazioneDaoInstance = null;
	private static TrenoDAO trenoDaoInstance = null;
	private static CittaDAO cittaDaoInstance = null;

	public static StazioneDAO getStazioneDAOInstance() {
		if (stazioneDaoInstance == null)
			stazioneDaoInstance = new StazioneDAOImpl();

		return stazioneDaoInstance;
	}

	public static TrenoDAO getTrenoDAOInstance() {
		if (trenoDaoInstance == null)
			trenoDaoInstance = new TrenoDAOImpl();

		return trenoDaoInstance;
	}
	
	public static CittaDAO getCittaDAOInstance() {
		if (cittaDaoInstance == null)
			cittaDaoInstance = new CittaDAOImpl();

		return cittaDaoInstance;
	}
}
