package it.prova.gestionestazioni.dao.treno;

import java.util.List;

import it.prova.gestionestazioni.dao.IBaseDAO;
import it.prova.gestionestazioni.model.Treno;

public interface TrenoDAO extends IBaseDAO<Treno>{
	public List<Long> findNumeroAbitantiDiUnaCittaTramiteUnTreno(Long id) throws Exception;
}
