package it.prova.pokeronline.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;


//Ruolo =➔ ADMIN, PLAYER, SPECIAL_PLAYER (lo special player può creare e gestire tavoli,
// il player solo giocare e per semplicità l’ADMIN può fare tutto).

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "ruolo")
public class Ruolo {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_PLAYER = "ROLE_PLAYER";
    public static final String ROLE_SPECIAL_PLAYER = "ROLE_SPECIAL_PLAYER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "codice")
    private String codice;

    public Ruolo() {
    }

    public Ruolo(String descrizione, String codice) {
        this.descrizione = descrizione;
        this.codice = codice;
    }

    public Ruolo(Long id, String descrizione, String codice) {
        this.id = id;
        this.descrizione = descrizione;
        this.codice = codice;
    }

    public static String getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public static String getRolePlayer() {
        return ROLE_PLAYER;
    }

    public static String getRoleSpecialPlayer() {
        return ROLE_SPECIAL_PLAYER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Ruolo{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
