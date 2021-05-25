/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsCategdis;
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
 * @author ticoa
 */
public class EsCategdisJpaController implements Serializable {

    public EsCategdisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsCategdisJpaController () {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<String> getCategDis() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.categDis from EsCategdis s order by s.categDis", String.class);
        return qry.getResultList();
    }
    
    public void create(EsCategdis esCategdis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esCategdis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsCategdis esCategdis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esCategdis = em.merge(esCategdis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esCategdis.getCodCategDis();
                if (findEsCategdis(id) == null) {
                    throw new NonexistentEntityException("The esCategdis with id " + id + " no longer exists.");
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
            EsCategdis esCategdis;
            try {
                esCategdis = em.getReference(EsCategdis.class, id);
                esCategdis.getCodCategDis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esCategdis with id " + id + " no longer exists.", enfe);
            }
            em.remove(esCategdis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsCategdis> findEsCategdisEntities() {
        return findEsCategdisEntities(true, -1, -1);
    }

    public List<EsCategdis> findEsCategdisEntities(int maxResults, int firstResult) {
        return findEsCategdisEntities(false, maxResults, firstResult);
    }

    private List<EsCategdis> findEsCategdisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsCategdis.class));
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

    public EsCategdis findEsCategdis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsCategdis.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsCategdisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsCategdis> rt = cq.from(EsCategdis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
