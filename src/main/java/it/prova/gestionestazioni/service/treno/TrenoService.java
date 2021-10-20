package it.prova.gestionestazioni.service.treno;

import java.util.List;

import it.prova.gestionestazioni.dao.treno.TrenoDAO;
import it.prova.gestionestazioni.model.Treno;



public interface TrenoService {
	public List<Treno> listAll() throws Exception;

	public Treno caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Treno categoriaInstance) throws Exception;

	public void inserisciNuovo(Treno categoriaInstance) throws Exception;

	public void rimuovi(Treno categoriaInstance) throws Exception;
	
	public List<Long> caricaNumeroAbitantiDiUnaCittaTramiteUnTreno(Long id) throws Exception;
	

	// per injection
	public void setTrenoDAO(TrenoDAO trenoDAO);
}
