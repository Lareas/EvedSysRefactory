/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Tabbancos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;

/**
 *
 * @author luiza
 */
public class TabbancosJpaController implements Serializable {

    public TabbancosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tabbancos tabbancos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tabbancos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public TabbancosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public Tabbancos getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Tabbancos s WHERE s.NomeBanco = '" + pesq + "'";
        TypedQuery<Tabbancos> qry = em.createQuery(ssql, Tabbancos.class);
        return qry.getSingleResult();
    }

    public void edit(Tabbancos tabbancos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tabbancos = em.merge(tabbancos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tabbancos.getChaveBanco();
                if (findTabbancos(id) == null) {
                    throw new NonexistentEntityException("The tabbancos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tabbancos tabbancos;
            try {
                tabbancos = em.getReference(Tabbancos.class, id);
                tabbancos.getChaveBanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabbancos with id " + id + " no longer exists.", enfe);
            }
            em.remove(tabbancos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabbancos> findTabbancosEntities() {
        return findTabbancosEntities(true, -1, -1);
    }

    public List<Tabbancos> findTabbancosEntities(int maxResults, int firstResult) {
        return findTabbancosEntities(false, maxResults, firstResult);
    }

    private List<Tabbancos> findTabbancosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabbancos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tabbancos findTabbancos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabbancos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTabbancosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabbancos> rt = cq.from(Tabbancos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
