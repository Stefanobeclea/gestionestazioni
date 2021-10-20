package it.prova.gestionestazioni.dao.citta;

import it.prova.gestionestazioni.dao.IBaseDAO;
import it.prova.gestionestazioni.model.Citta;

public interface CittaDAO extends IBaseDAO<Citta>{
	public Citta findByIdEager(Long id) throws Exception;
}
