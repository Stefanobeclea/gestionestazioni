package it.prova.gestionestazioni.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "stazione")
public class Stazione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "denominazione")
	private String denominazione;
	@Column(name = "indirizzo")
	private String indirizzo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citta_id", nullable = false)
	private Citta citta;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "stazione_treno", joinColumns = @JoinColumn(name = "stazione_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "treno_id", referencedColumnName = "ID"))
	private Set<Treno> treni = new HashSet<Treno>();
	
	public Stazione() {
		// TODO Auto-generated constructor stub
	}

	public Stazione(String denominazione, String indirizzo, Citta citta, Set<Treno> treni) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.treni = treni;
	}

	public Stazione(String denominazione, String indirizzo, Citta citta) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}

	public Stazione(String denominazione, String indirizzo, Set<Treno> treni) {
		super();
		this.denominazione = denominazione;
		this.indirizzo = indirizzo;
		this.treni = treni;
	}

	public Long getId() {
		return id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public Citta getCitta() {
		return citta;
	}

	public Set<Treno> getTreni() {
		return treni;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setCitta(Citta citta) {
		this.citta = citta;
	}

	public void setTreni(Set<Treno> treni) {
		this.treni = treni;
	}

	@Override
	public String toString() {
		return "Stazione [id=" + id + ", denominazione=" + denominazione + ", indirizzo=" + indirizzo + "]";
	}
	
	public void removeFromTreni(Treno trenoInstance) {
		this.treni.remove(trenoInstance);
		trenoInstance.getStazioni().remove(this);
	}
	
	public void removeFromTreni(Set<Treno> treniListInstance) {
		this.treni.removeAll(treniListInstance);
		for (Treno categoriaItem : treniListInstance) {
			categoriaItem.getStazioni().remove(this);
		}
	}
	
}
