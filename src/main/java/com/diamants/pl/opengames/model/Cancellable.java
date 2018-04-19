/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.opengames.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Niccolò
 */

@MappedSuperclass
public class Cancellable {
   
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean eliminato;

    /**
     * @return the eliminato
     */
    public boolean getEliminato() {
        return eliminato;
    }

    /**
     * @param eliminato the eliminato to set
     */
    public void setEliminato(boolean eliminato) {
        this.eliminato = eliminato;
    }

}