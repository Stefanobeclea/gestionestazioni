package it.prova.gestionestazioni.service;

import it.prova.gestionestazioni.dao.MyDaoFactory;
import it.prova.gestionestazioni.service.citta.CittaService;
import it.prova.gestionestazioni.service.citta.CittaServiceImpl;
import it.prova.gestionestazioni.service.stazione.StazioneService;
import it.prova.gestionestazioni.service.stazione.StazioneServiceImpl;
import it.prova.gestionestazioni.service.treno.TrenoService;
import it.prova.gestionestazioni.service.treno.TrenoServiceImpl;

public class MyServiceFactory {
	private static StazioneService stazioneServiceInstance = null;
	private static TrenoService trenoServiceInstance = null;
	private static CittaService cittaServiceInstance = null;

	public static StazioneService getStazioneServiceInstance() {
		if (stazioneServiceInstance == null)
			stazioneServiceInstance = new StazioneServiceImpl();

		stazioneServiceInstance.setStazioneDAO(MyDaoFactory.getStazioneDAOInstance());

		return stazioneServiceInstance;
	}

	public static TrenoService getTrenoServiceInstance() {
		if (trenoServiceInstance == null)
			trenoServiceInstance = new TrenoServiceImpl();

		trenoServiceInstance.setTrenoDAO(MyDaoFactory.getTrenoDAOInstance());

		return trenoServiceInstance;
	}
	
	public static CittaService getCittaServiceInstance() {
		if (cittaServiceInstance == null)
			cittaServiceInstance = new CittaServiceImpl();

		cittaServiceInstance.setCittaDAO(MyDaoFactory.getCittaDAOInstance());

		if (stazioneServiceInstance == null)
			stazioneServiceInstance = new StazioneServiceImpl();
		cittaServiceInstance.setStazioneDAO(MyDaoFactory.getStazioneDAOInstance());

		return cittaServiceInstance;
	}
}
