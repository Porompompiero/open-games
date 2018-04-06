/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.playinglight.dcs;

import com.diamants.pl.playinglight.dao.UtenteDAO;
import com.diamants.pl.playinglight.model.Utente;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Niccolò
 */
public class UtenteDCS {

    /**
     *  Metodo che aggiunge un utente al DB
     * @param utenteDAO
     * @param nome
     * @param cognome
     * @param email
     * @param password
     * @param numeroTelefono
     * @param tipo
     * @return
     */
    @Transactional
    public static Utente addUtente(UtenteDAO utenteDAO, String nome, String cognome, String email, String password, String numeroTelefono, Short tipo){

//      VERIFICO CHE L'EMAIL NON SIA GIà PRESENTE NEL DB    
        if (utenteDAO.findByEmail(email) != null){
            return utenteDAO.findByEmail(email);
        }

//      SETTO I CAMPI
        Utente utente = new Utente();
        utente.setCognome(cognome);
        utente.setEliminato(false);
        utente.setEmail(email);
        utente.setNome(nome);
        utente.setPassword(password);
        utente.setPhoneNumber(numeroTelefono);
        utente.setStatus(Utente.STATUS_REGISTER);
        utente.setTipo(tipo);
        utente.setUserAuthorities(null);
        
        return (Utente) utenteDAO.save(utente);
    }
    
    /**
     *  Metodo che permette l'edit utente con paramentri che possono essere null
     * @param utenteDAO
     * @param id
     * @param nome
     * @param cognome
     * @param email
     * @param numeroTelefono
     * @return
     */
    public static Utente editUtente(UtenteDAO utenteDAO, Long id, String nome, String cognome, String email, String numeroTelefono) {

//      VEDO SE L'UTENTE ESISTE TRAMITE L'ID
        Utente utente = utenteDAO.findID(id);

//      SE NON ESISTE RITORNO NULL  
        if (utente == null) {
            return null;
        }

//      SETTO I CAMPI DELL'UTENTE IN BASE AI PARAMETRI
        if (nome != null) {
            utente.setNome(nome);
        }
        if (cognome != null) {
            utente.setCognome(cognome);
        }
        if (email != null) {
            utente.setEmail(email);
        }
        if (numeroTelefono != null) {
            utente.setPhoneNumber(numeroTelefono);
        }

        return (Utente) utenteDAO.save(utente);
    }

}
