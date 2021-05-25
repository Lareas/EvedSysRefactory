/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsRegaltnotas;
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
public class EsRegaltnotasJpaController implements Serializable {

    public EsRegaltnotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsRegaltnotasJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsRegaltnotas esRegaltnotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esRegaltnotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsRegaltnotas esRegaltnotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esRegaltnotas = em.merge(esRegaltnotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esRegaltnotas.getCodRegAltNotas();
                if (findEsRegaltnotas(id) == null) {
                    throw new NonexistentEntityException("The esRegaltnotas with id " + id + " no longer exists.");
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
            EsRegaltnotas esRegaltnotas;
            try {
                esRegaltnotas = em.getReference(EsRegaltnotas.class, id);
                esRegaltnotas.getCodRegAltNotas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esRegaltnotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(esRegaltnotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsRegaltnotas> findEsRegaltnotasEntities() {
        return findEsRegaltnotasEntities(true, -1, -1);
    }

    public List<EsRegaltnotas> findEsRegaltnotasEntities(int maxResults, int firstResult) {
        return findEsRegaltnotasEntities(false, maxResults, firstResult);
    }

    private List<EsRegaltnotas> findEsRegaltnotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsRegaltnotas.class));
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

    public EsRegaltnotas findEsRegaltnotas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsRegaltnotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsRegaltnotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsRegaltnotas> rt = cq.from(EsRegaltnotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
