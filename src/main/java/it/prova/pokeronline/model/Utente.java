package it.prova.pokeronline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


//Utente(che è anche il giocatore)coi campi classici
// (nome,cognome,username,password,data registrazione,stato,esperienzaAccumulata
// (indica una specie di punteggio attribuito dal sistema per cercare di livellare le abilità dei giocatori
// e far quindi disputare partite alla pari),creditoAccumulato(in pratica il credito accumulato in termini di soldi)

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotBlank(message = "{nome.notblank}")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    @Column(name = "cognome")
    private String cognome;


    @NotBlank(message = "{username.notblank}")
    @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "{password.notblank}")
    @Size(min = 8, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
    @Column(name = "password")
    private String password;

    @NotNull(message = "{dataregistrazione.notnull}")
    @Column(name = "data_registrazione")
    private Date dataRegistrazione;

    @NotNull(message = "{sesso.notnull}")
    @Enumerated(EnumType.STRING)
    private StatoUtente stato;

    @NotNull(message = "{esperienzaaccumulata.notnull}")
    @Min(value = 0)
    @Column(name = "esperienza_accumulata")
    private Long esperienzaAccumulata;

    @NotNull(message = "{creditoaccumulato.notnull}")
    @Min(value = 0)
    @Column(name = "credito_accumulato")
    private Double creditoAccumulato;


    //tavolo dove sta giocando
    @JsonIgnoreProperties(value = {"utenti"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tavolo_id")
    private Tavolo tavolo;

    //scelgo che un utente puo avere un solo ruolo e che può essere nullo alla creazione
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruolo_id")
    private Ruolo ruolo;

    public Utente() {
    }

    public Utente(String nome, String cognome, String username, String password, Date dataRegistrazione, StatoUtente stato, Long esperienzaAccumulata, Double creditoAccumulato, Tavolo tavolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataRegistrazione = dataRegistrazione;
        this.stato = stato;
        this.esperienzaAccumulata = esperienzaAccumulata;
        this.creditoAccumulato = creditoAccumulato;
        this.tavolo = tavolo;
    }

    public Utente(Long id, String nome, String cognome, String username, String password, Date dataRegistrazione, StatoUtente stato, Long esperienzaAccumulata, Double creditoAccumulato, Tavolo tavolo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.dataRegistrazione = dataRegistrazione;
        this.stato = stato;
        this.esperienzaAccumulata = esperienzaAccumulata;
        this.creditoAccumulato = creditoAccumulato;
        this.tavolo = tavolo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public StatoUtente getStato() {
        return stato;
    }

    public void setStato(StatoUtente stato) {
        this.stato = stato;
    }

    public Long getEsperienzaAccumulata() {
        return esperienzaAccumulata;
    }

    public void setEsperienzaAccumulata(Long esperienzaAccumulata) {
        this.esperienzaAccumulata = esperienzaAccumulata;
    }

    public Double getCreditoAccumulato() {
        return creditoAccumulato;
    }

    public void setCreditoAccumulato(Double creditoAccumulato) {
        this.creditoAccumulato = creditoAccumulato;
    }

    public Tavolo getTavolo() {
        return tavolo;
    }

    public void setTavolo(Tavolo tavolo) {
        this.tavolo = tavolo;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dataRegistrazione=" + dataRegistrazione +
                ", stato=" + stato +
                ", esperienzaAccumulata=" + esperienzaAccumulata +
                ", creditoAccumulato=" + creditoAccumulato +
                '}';
    }

//    public boolean isAdmin() {
//        for (Ruolo ruoloItem : ruoli) {
//            if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
//                return true;
//        }
//        return false;
//    }
//
//    public boolean isAttivo() {
//        return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
//    }
//
}
