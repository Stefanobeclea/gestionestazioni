package it.prova.gestionestazioni.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "treno")
public class Treno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "denominazione")
	private String denominazione;
	@Column(name = "codice")
	private String codice;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "treni")
	private Set<Stazione> stazioni = new HashSet<Stazione>();
	
	public Treno() {
		// TODO Auto-generated constructor stub
	}

	public Treno(String denominazione, String codice, Set<Stazione> stazioni) {
		super();
		this.denominazione = denominazione;
		this.codice = codice;
		this.stazioni = stazioni;
	}

	public Treno(String denominazione, String codice) {
		super();
		this.denominazione = denominazione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public String getCodice() {
		return codice;
	}

	public Set<Stazione> getStazioni() {
		return stazioni;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public void setStazioni(Set<Stazione> stazioni) {
		this.stazioni = stazioni;
	}

	@Override
	public String toString() {
		return "Treno [id=" + id + ", denominazione=" + denominazione + ", codice=" + codice + "]";
	}
	
}
