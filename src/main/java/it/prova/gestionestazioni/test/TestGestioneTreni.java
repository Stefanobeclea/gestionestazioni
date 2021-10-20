package it.prova.gestionestazioni.test;

import it.prova.gestionestazioni.dao.EntityManagerUtil;
import it.prova.gestionestazioni.model.Citta;
import it.prova.gestionestazioni.model.Stazione;
import it.prova.gestionestazioni.model.Treno;
import it.prova.gestionestazioni.service.MyServiceFactory;
import it.prova.gestionestazioni.service.citta.CittaService;
import it.prova.gestionestazioni.service.stazione.StazioneService;
import it.prova.gestionestazioni.service.treno.TrenoService;

public class TestGestioneTreni {
	public static void main(String[] args) {
		StazioneService stazioneServiceInstance = MyServiceFactory.getStazioneServiceInstance();
		TrenoService trenoServiceInstance = MyServiceFactory.getTrenoServiceInstance();
		CittaService cittaServiceInstance = MyServiceFactory.getCittaServiceInstance();

		try {

			System.out.println("In tabella Treno ci sono " + trenoServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Stazione ci sono " + stazioneServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Citta ci sono " + cittaServiceInstance.listAll().size() + " elementi.");
			System.out.println(
					"**************************** inizio batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");

			testInserisciCitta(cittaServiceInstance);

			testInserimentoNuovaStazioneSenzaTreni(stazioneServiceInstance, cittaServiceInstance);

			testInserimentoNuovoTrenoSenzaStazioni(trenoServiceInstance);

			testInserisciCittaECollegamentoDueStazioni(cittaServiceInstance, stazioneServiceInstance);

			testInserisciCittaECollegamentoAStazioneECollegamentoATreno(cittaServiceInstance, stazioneServiceInstance,
					trenoServiceInstance);

			testRimozioneCittaAssociataAStazioneASuaVoltaAssociataATreni(cittaServiceInstance, stazioneServiceInstance,
					trenoServiceInstance);

			testListaNumeroAbitantiCittaDiUnTreno(cittaServiceInstance, stazioneServiceInstance, trenoServiceInstance);

			System.out.println(
					"****************************** fine batteria di test ********************************************");
			System.out.println(
					"*************************************************************************************************");
			System.out.println("In tabella Treno ci sono " + trenoServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Stazione ci sono " + stazioneServiceInstance.listAll().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserimentoNuovaStazioneSenzaTreni(StazioneService stazioneServiceInstance,
			CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovaStazioneSenzaTreni inizio.............");

		// Creo una città e la inserisco perchè la stazione non puo avere il campo
		// citta_id a null!
		Citta cittaPerIlTest = new Citta("Roma", 300L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);

		// faccio la new della stazione e la inserisco e faccio una verifica
		Stazione stazioneInstance = new Stazione("Termini", "Via Termini", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		Stazione checkInserimento = stazioneServiceInstance.caricaSingoloElemento(stazioneInstance.getId());
		if (checkInserimento.getId() != stazioneInstance.getId())
			throw new RuntimeException(
					"testInserimentoNuovaStazioneSenzaTreni fallito: la stazione non è stata inserita correttamente ");

		System.out.println(".......testInserimentoNuovaStazioneSenzaTreni fine: PASSED.............");
	}

	private static void testInserimentoNuovoTrenoSenzaStazioni(TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovoTrenoSenzaStazioni inizio.............");

		// faccio la new di treno lo inserisco
		Treno nuovoTreno = new Treno("Roma-Napoli", "RM-NP");
		trenoServiceInstance.inserisciNuovo(nuovoTreno);

		// verifico se è stato aggiunto corretamente
		Treno checkInserimento = trenoServiceInstance.caricaSingoloElemento(nuovoTreno.getId());
		if (checkInserimento.getId() != nuovoTreno.getId())
			throw new RuntimeException("testInserimentoNuovoTrenoSenzaStazioni fallito: treno non inserito ");

		System.out.println(".......testInserimentoNuovoTrenoSenzaStazioni fine: PASSED.............");
	}

	private static void testInserisciCitta(CittaService cittaServiceInstance) throws Exception {
		System.out.println(".......testInserisciCitta inizio.............");

		Citta cittaPerIlTest = new Citta("Napoli", 200L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);

		Citta checkInserimento = cittaServiceInstance.caricaSingoloElemento(cittaPerIlTest.getId());
		if (checkInserimento.getId() != cittaPerIlTest.getId())
			throw new RuntimeException("testInserisciCitta fallito: citta non inserito ");

		System.out.println(".......testInserisciCitta fine: PASSED.............");
	}

	private static void testInserisciCittaECollegamentoDueStazioni(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance) throws Exception {
		System.out.println(".......testInserisciCittaECollegamentoDueStazioni inizio.............");

		Citta cittaPerIlTest = new Citta("Firenze", 500L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);
		Stazione stazioneInstance1 = new Stazione("Termini", "Via Termini", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance1);
		Stazione stazioneInstance2 = new Stazione("Termini", "Via Termini", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance2);

		Citta checkAssociazione = cittaServiceInstance.caricaSingoloElemento(cittaPerIlTest.getId());
		Stazione checkAssociazioneStazione1 = stazioneServiceInstance.caricaSingoloElemento(stazioneInstance1.getId());
		if (checkAssociazioneStazione1.getCitta().getId() != checkAssociazione.getId())
			throw new RuntimeException(
					"testInserisciCittaECollegamentoDueStazioni fallito: citta non associata alla prima stazione");

		Stazione checkAssociazioneStazione2 = stazioneServiceInstance.caricaSingoloElemento(stazioneInstance2.getId());
		if (checkAssociazioneStazione2.getCitta().getId() != checkAssociazione.getId())
			throw new RuntimeException(
					"testInserisciCittaECollegamentoDueStazioni fallito: citta non associata alla seconda stazione");

		System.out.println(".......testInserisciCittaECollegamentoDueStazioni fine: PASSED.............");
	}

	private static void testInserisciCittaECollegamentoAStazioneECollegamentoATreno(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testInserisciCittaECollegamentoAStazioneECollegamentoATreno inizio.............");

		// creo la citta e la stazione e li associo
		Citta cittaPerIlTest = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);
		Stazione stazioneInstance = new Stazione("TreniTo", "Via To", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		Citta checkAssociazione = cittaServiceInstance.caricaSingoloElemento(cittaPerIlTest.getId());
		Stazione checkAssociazioneStazione1 = stazioneServiceInstance.caricaSingoloElemento(stazioneInstance.getId());
		if (checkAssociazioneStazione1.getCitta().getId() != checkAssociazione.getId())
			throw new RuntimeException(
					"testInserisciCittaECollegamentoAStazioneECollegamentoATreno fallito: citta non associata alla prima stazione");

		// creo i due treni, faccio la insert e li associo alla mia stazione
		Treno trenoInstance1 = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance1);
		Treno trenoInstance2 = new Treno("Torino-Francia", "TO-FR");
		trenoServiceInstance.inserisciNuovo(trenoInstance2);
		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance1);
		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance2);

		Long idStazioneAggiunta = stazioneServiceInstance.caricaSingoloElemento(stazioneInstance.getId()).getId();
		if (stazioneServiceInstance.caricaSingoloElementoEagerTreni(idStazioneAggiunta).getTreni().size() != 2)
			throw new RuntimeException(
					"testInserisciCittaECollegamentoAStazioneECollegamentoATreno fallito: Stazione non associata ai due treni");

		System.out.println(
				".......testInserisciCittaECollegamentoAStazioneECollegamentoATreno fine: PASSED.............");
	}

	private static void testRimozioneCittaAssociataAStazioneASuaVoltaAssociataATreni(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testRimozioneCittaAssociataAStazioneASuaVoltaAssociataATreni inizio.............");

		// Creo e aggiungo i miei dati nel DB
		Citta cittaPerIlTest = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);

		Stazione stazioneInstance = new Stazione("TreniTo", "Via To", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		Treno trenoInstance1 = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance1);
		Treno trenoInstance2 = new Treno("Torino-Roma", "TO-RO");
		trenoServiceInstance.inserisciNuovo(trenoInstance2);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance1);
		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance2);

		// Elimito la citta e faccio la prova
		Long idCittaDaEliminare = cittaPerIlTest.getId();
		cittaServiceInstance.rimuoviTramiteEager(idCittaDaEliminare);

		if (cittaServiceInstance.caricaSingoloElemento(cittaPerIlTest.getId()) != null)
			throw new RuntimeException(
					"testRimozioneCittaAssociataAStazioneASuaVoltaAssociataATreni fallito: Citta non elimintata correttamente");

		System.out.println(
				".......testRimozioneCittaAssociataAStazioneASuaVoltaAssociataATreni fine: PASSED.............");
	}

	private static void testListaNumeroAbitantiCittaDiUnTreno(CittaService cittaServiceInstance,
			StazioneService stazioneServiceInstance, TrenoService trenoServiceInstance) throws Exception {
		System.out.println(".......testListaNumeroAbitantiCittaDiUnTreno inizio.............");

		// Creo la prima citta con la sua stazione e il suo treno
		Citta cittaPerIlTest = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest);

		Stazione stazioneInstance = new Stazione("TreniTo", "Via To", cittaPerIlTest);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance);

		Treno trenoInstance = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance, trenoInstance);

		// Creo la seconda citta con la sua stazione e il suo treno
		Citta cittaPerIlTest2 = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest2);

		Stazione stazioneInstance2 = new Stazione("TreniTo", "Via To", cittaPerIlTest2);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance2);

		Treno trenoInstance2 = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance2);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance2, trenoInstance2);

		// Creo la terza citta con la sua stazione e il suo treno
		Citta cittaPerIlTest3 = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest3);

		Stazione stazioneInstance3 = new Stazione("TreniTo", "Via To", cittaPerIlTest3);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance3);

		Treno trenoInstance3 = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance3);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance3, trenoInstance3);

		// Creo la quarta citta con la sua stazione e il suo treno
		Citta cittaPerIlTest4 = new Citta("Torino", 600L);
		cittaServiceInstance.inserisciNuovo(cittaPerIlTest4);

		Stazione stazioneInstance4 = new Stazione("TreniTo", "Via To", cittaPerIlTest4);
		stazioneServiceInstance.inserisciNuovo(stazioneInstance4);

		Treno trenoInstance4 = new Treno("Torino-Milano", "TO-MI");
		trenoServiceInstance.inserisciNuovo(trenoInstance4);

		stazioneServiceInstance.aggiungiTreno(stazioneInstance4, trenoInstance4);

		// Faccio il test della queri per ottenere i numeri di abitanti
		Long numeroAbitantiDaCheckare = cittaServiceInstance.caricaSingoloElemento(cittaPerIlTest.getId())
				.getNumeroAbitanti();
		Long risultatoQuery = trenoServiceInstance.caricaNumeroAbitantiDiUnaCittaTramiteUnTreno(trenoInstance.getId())
				.get(0);
		if (risultatoQuery == numeroAbitantiDaCheckare)
			throw new RuntimeException("testListaNumeroAbitantiCittaDiUnTreno fallito: la query non ha funzionato");

		System.out.println(".......testListaNumeroAbitantiCittaDiUnTreno fine: PASSED.............");
	}
}
