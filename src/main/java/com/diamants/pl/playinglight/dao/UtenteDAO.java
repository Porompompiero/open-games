/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.playinglight.dao;

import com.diamants.pl.playinglight.model.Utente;
import java.util.List;

/**
 *
 * @author Niccolò
 */
public class UtenteDAO extends BaseDAO{
    
    public Utente findID(Long uid) {
        System.out.println("UTENTE DA TROVARE: " + uid);
        try {
            return getEntityManager().createQuery("SELECT u FROM Utente u WHERE u.id = :uid AND u.eliminato = false ", Utente.class)
                    .setParameter("uid", uid)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Utente findByEmail(String email) {
        try {
            return getEntityManager().createQuery("SELECT u FROM Utente u WHERE u.email = :email AND u.eliminato = false ", Utente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }
    
    
    @Override
    public List<Utente> findAll() {
        return getEntityManager().createQuery("SELECT u FROM Utente u WHERE u.eliminato = false", Utente.class)
                .getResultList();
    }
    
    public Utente findByPhoneNumber(String tel) {
        try {
            return getEntityManager().createQuery("SELECT u FROM Utente u WHERE u.numeroTelefono = :tel AND u.eliminato = false ", Utente.class)
                    .setParameter("tel", tel)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
