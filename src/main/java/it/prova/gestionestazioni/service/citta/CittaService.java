package it.prova.gestionestazioni.service.citta;

import java.util.List;

import it.prova.gestionestazioni.dao.citta.CittaDAO;
import it.prova.gestionestazioni.dao.stazione.StazioneDAO;
import it.prova.gestionestazioni.model.Citta;



public interface CittaService {
	public List<Citta> listAll() throws Exception;

	public Citta caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Citta cittaInstance) throws Exception;

	public void inserisciNuovo(Citta cittaInstance) throws Exception;

	public void rimuovi(Citta cittaInstance) throws Exception;
	
	public void rimuoviTramiteEager (Long id) throws Exception;
	
	// per injection
	public void setCittaDAO(CittaDAO cittaDAO);
	
	public void setStazioneDAO(StazioneDAO stazioneDAO);
}
