package it.prova.gestionestazioni.service.stazione;

import java.util.List;

import it.prova.gestionestazioni.dao.stazione.StazioneDAO;
import it.prova.gestionestazioni.model.Stazione;
import it.prova.gestionestazioni.model.Treno;



public interface StazioneService {
	public List<Stazione> listAll() throws Exception;

	public Stazione caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Stazione StazioneInstance) throws Exception;

	public void inserisciNuovo(Stazione StazioneInstance) throws Exception;

	public void rimuovi(Stazione StazioneInstance) throws Exception;

	public void aggiungiTreno(Stazione StazioneInstance, Treno TrenoInstance) throws Exception;
	
	public void creaECollegaStazioneETreno(Stazione StazioneInstance, Treno TrenoInstance) throws Exception;
	
	public Stazione caricaSingoloElementoEagerTreni(Long id) throws Exception;
	

	// per injection
	public void setStazioneDAO(StazioneDAO StazioneDAO);
}
