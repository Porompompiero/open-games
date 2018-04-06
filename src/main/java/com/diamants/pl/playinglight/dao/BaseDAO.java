/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diamants.pl.playinglight.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Niccolò
 * @param <T>
 */
@SuppressWarnings("unchecked")
@Repository
public class BaseDAO<T extends Serializable> {

    

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public T findOne(long id) {
        return getEntityManager().find(getTClass(), id);
    }

    public List<T> findAll() {
       return getEntityManager().createQuery("FROM " + getTClass().getName(), getTClass())
               .getResultList();
    }
    
    public Class<T> getTClass() {
        return  (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
    }
    
    
    @Transactional(timeout = 30)
    public Object save(Object o) {
        return getEntityManager().merge(o);
    }

    @Transactional(timeout = 30)
    public void persist(Object o) {
        getEntityManager().persist(o);
    }

    @Transactional(timeout = 30)
    public void remove(Object o) {
        getEntityManager().remove(save(o));
    }
    
    @Transactional(timeout = 30)
    public void refresh(Object o) {
        getEntityManager().refresh(o);
    }
    
     @Transactional(timeout = 30)
    public Object save(T o) {
        return getEntityManager().merge(o);
    }

    @Transactional(timeout = 30)
    public void persist(T o) {
        getEntityManager().persist(o);
    }

    @Transactional(timeout = 30)
    public void remove(T o) {
        getEntityManager().remove(save(o));
    }
    
    @Transactional(timeout = 30)
    public void refresh(T o) {
        getEntityManager().refresh(o);
    }
    
    
    public Object saveNoTrans(Object o) {
        return getEntityManager().merge(o);
    }
    
    public Object saveNoTrans(T o) {
        return getEntityManager().merge(o);
    }
    

    public void flush() {
        getEntityManager().flush();
    }

    public void clear() {
        getEntityManager().clear();
    }

}
