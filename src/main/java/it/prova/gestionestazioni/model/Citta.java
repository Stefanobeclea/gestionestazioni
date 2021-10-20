package it.prova.gestionestazioni.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "citta")
public class Citta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "denominazione")
	private String denominazione;
	@Column(name = "numeroAbitanti")
	private Long numeroAbitanti;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "citta")
	private Set<Stazione> stazioni = new HashSet<>();
	
	public Citta() {
		// TODO Auto-generated constructor stub
	}

	public Citta(String denominazione, Long numeroAbitanti, Set<Stazione> stazioni) {
		super();
		this.denominazione = denominazione;
		this.numeroAbitanti = numeroAbitanti;
		this.stazioni = stazioni;
	}

	public Citta(String denominazione, Long numeroAbitanti) {
		super();
		this.denominazione = denominazione;
		this.numeroAbitanti = numeroAbitanti;
	}

	public Long getId() {
		return id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public Long getNumeroAbitanti() {
		return numeroAbitanti;
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

	public void setNumeroAbitanti(Long numeroAbitanti) {
		this.numeroAbitanti = numeroAbitanti;
	}

	public void setStazioni(Set<Stazione> stazioni) {
		this.stazioni = stazioni;
	}

	@Override
	public String toString() {
		return "Citta [id=" + id + ", denominazione=" + denominazione + ", numeroAbitanti=" + numeroAbitanti + "]";
	}

}
