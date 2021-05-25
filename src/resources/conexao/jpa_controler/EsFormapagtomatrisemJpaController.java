/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsFormapagtomatrisem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;

/**
 *
 * @author ticoa
 */
public class EsFormapagtomatrisemJpaController implements Serializable {

    public EsFormapagtomatrisemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EsFormapagtomatrisemJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    public void create(EsFormapagtomatrisem esFormapagtomatrisem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esFormapagtomatrisem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsFormapagtomatrisem esFormapagtomatrisem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esFormapagtomatrisem = em.merge(esFormapagtomatrisem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esFormapagtomatrisem.getCodFormaPagtoMatriSem();
                if (findEsFormapagtomatrisem(id) == null) {
                    throw new NonexistentEntityException("The esFormapagtomatrisem with id " + id + " no longer exists.");
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
            EsFormapagtomatrisem esFormapagtomatrisem;
            try {
                esFormapagtomatrisem = em.getReference(EsFormapagtomatrisem.class, id);
                esFormapagtomatrisem.getCodFormaPagtoMatriSem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esFormapagtomatrisem with id " + id + " no longer exists.", enfe);
            }
            em.remove(esFormapagtomatrisem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsFormapagtomatrisem> findEsFormapagtomatrisemEntities() {
        return findEsFormapagtomatrisemEntities(true, -1, -1);
    }

    public List<EsFormapagtomatrisem> findEsFormapagtomatrisemEntities(int maxResults, int firstResult) {
        return findEsFormapagtomatrisemEntities(false, maxResults, firstResult);
    }

    private List<EsFormapagtomatrisem> findEsFormapagtomatrisemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsFormapagtomatrisem.class));
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

    public EsFormapagtomatrisem findEsFormapagtomatrisem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsFormapagtomatrisem.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsFormapagtomatrisemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsFormapagtomatrisem> rt = cq.from(EsFormapagtomatrisem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
