package it.prova.gestionestazioni.dao.stazione;

import it.prova.gestionestazioni.dao.IBaseDAO;
import it.prova.gestionestazioni.model.Stazione;

public interface StazioneDAO extends IBaseDAO<Stazione>{
	
	public Stazione findByIdFetchingTreni(Long id) throws Exception;

}
