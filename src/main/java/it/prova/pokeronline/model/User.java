package it.prova.pokeronline.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "USER")
public class User<List> {


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


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = {
            @JoinColumn(name = "USER_USERNAME", referencedColumnName = "USERNAME")}, inverseJoinColumns = {
            @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private java.util.List<Authority> authorities;


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


    public User() {
    }


}
