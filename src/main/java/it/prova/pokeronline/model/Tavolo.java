package it.prova.pokeronline.model;

//Tavolo (sarebbe la partita) coi campi esperienzaMin (cioè il minimo dell’esperienzaAccumulata

// che gli utenti devono possedere per poter giocare a quel tavolo), cifraMinima (il minimo valore
// di denaro che si deve possedere per giocare a quel tavolo), denominazione …., data creazione…..
// Set di User (i giocatori) ed un User userCreazione che è colui che ha creato il tavolo.

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "tavolo")
public class Tavolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "{esperienzaminima.notnull}")
	@Min(value = 0)
	@Column(name = "esperienza_minima")
	private Long esperienzaMinima;

	@NotNull(message = "{ciframinima.notnull}")
	@Min(value = 0)
	@Column(name = "cifra_minima")
	private Double cifraMinima;

	@NotBlank(message = "{denominazione.notblank}")
	@Column(name = "denominazione")
	private String denominazione;

	@NotNull(message = "{datacreazione.notnull}")
	@Column(name = "data_creazione")
	private Date dataCreazione;

	// utenti giocatori in questo tavolo
	@JsonIgnoreProperties(value = { "tavolo" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
	private Set<User> users = new HashSet<User>(0);

	// utente creatore del tavolo
	@JsonIgnoreProperties(value = { "tavolo" })
	@NotNull(message = "{utentecreazioneid.notnull}")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_creazione_id")
	private User userCreazione;

	public Tavolo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Long esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Double getCifraMinima() {
		return cifraMinima;
	}

	public void setCifraMinima(Double cifraMinima) {
		this.cifraMinima = cifraMinima;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Set<User> getUtenti() {
		return users;
	}

	public void setUtenti(Set<User> users) {
		this.users = users;
	}

	public User getUserCreazione() {
		return userCreazione;
	}

	public void setUserCreazione(User userCreazione) {
		this.userCreazione = userCreazione;
	}

	@Override
	public String toString() {
		return "Tavolo [id=" + id + ", esperienzaMinima=" + esperienzaMinima + ", cifraMinima=" + cifraMinima
				+ ", denominazione=" + denominazione + ", dataCreazione=" + dataCreazione + "]";
	}

}
