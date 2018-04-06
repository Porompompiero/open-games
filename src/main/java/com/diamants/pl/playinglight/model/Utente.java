/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.playinglight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Niccolò
 */
@Entity
@Table(name = "v_utente", schema = "public")
@ApiModel(description = "Utente della web app")
public class Utente extends Cancellable implements UserDetails, Serializable {

    @GenericGenerator(
            name = "v_utente_SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "v_utente_SequenceGenerator")
                ,
                @Parameter(name = "initial_value", value = "1")
                ,
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "v_utente_SequenceGenerator")
    @Column()
    private Long id;

    @ApiModelProperty(position = 2, required = true, value = "Email is used as a system username")
    @Column(nullable = false)
    private String email;

    //@JsonIgnore
    @ApiModelProperty(position = 3, required = true, value = "Encrypted Password for System Access")
    @Column(nullable = false)
    private String password;

    @ApiModelProperty(position = 4, required = true, value = "User name")
    @Column(nullable = false)
    private String nome;

    @ApiModelProperty(position = 5, required = true, value = "User last name")
    @Column(nullable = false)
    private String cognome;

    @Column
    private Short stato;

    public static final Short STATUS_REGISTER = 0;
    public static final Short STATUS_ENABLED = 1;
    public static final Short STATUS_EXPIRED = 2;
    

    @Column
    private Short tipo;

    public static final Short TYPE_SUPERADMIN = 0;
    public static final Short TYPE_ADMINISTRATOR = 1;
    public static final Short TYPE_DIRECTOR = 2;
    public static final Short TYPE_AREA_MANAGER = 3;

    private String numeroTelefono;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "w_utente_authority_cross", joinColumns
            = @JoinColumn(name = "type"), inverseJoinColumns
            = @JoinColumn(name = "w_authority_id"))
    private List<Authority> authorities;

    //*************************************************************************/
    public Utente() {

    }

    public Utente(String email, Long id, String surname, String name, String password) {

        this.email = email;
        this.id = id;
        this.cognome = surname;
        this.nome = name;
        this.password = password;

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utente other = (Utente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return numeroTelefono;
    }

    public void setPhoneNumber(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !Objects.equals(this.stato, STATUS_EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Objects.equals(this.stato, STATUS_ENABLED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !Objects.equals(this.stato, STATUS_EXPIRED);
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.getStatus(), STATUS_ENABLED);
    }

    public Short getStatus() {
        return stato;
    }

    public void setStatus(Short status) {
        this.stato = status;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo ){
        this.tipo = tipo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setUserAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

}
