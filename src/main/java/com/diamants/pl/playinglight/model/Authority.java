/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.playinglight.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Niccolò
 */
@Entity
@Table(name = "w_authority")
@ApiModel(description = "Utente del sistema, con tutti i dati necessari per loggarsi")
public class Authority implements GrantedAuthority {

    @GenericGenerator(
            name = "w_authority_SequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                @Parameter(name = "sequence_name", value = "w_authority_SequenceGenerator")
                ,
                @Parameter(name = "initial_value", value = "1")
                ,
                @Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "w_authority_SequenceGenerator")
    @Column()
    private Long id;

    @ApiModelProperty(position = 1, required = true, value = "Role name")
    @Column(name = "authority", nullable = false)
    private String authority;
    
    public static final String ADMINISTRATOR = "ROLE_ADMINISTRATOR";
    public static final String DIRECTOR = "ROLE_DIRECTOR";
    public static final String AREA_MANAGER = "ROLE_AREA_MANAGER";
    public static final String SELLER = "ROLE_SELLER";
    public static final String MANAGER = "ROLE_MANAGER";
    public static final String DEVELOPER = "ROLE_DEVELOPER";

    public Authority(){
    }
    
    public Authority(String authority){
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
