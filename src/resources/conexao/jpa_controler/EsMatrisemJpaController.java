/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsMatrisem;
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
public class EsMatrisemJpaController implements Serializable {

    public EsMatrisemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsMatrisemJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List <EsMatrisem> getListaAnoSem(Short ano, Short sem) {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<EsMatrisem> qry = em.createQuery("select s from EsMatrisem s WHERE s.anoLetivo = " + ano + " AND s.semestreId = " + sem, EsMatrisem.class);
        return qry.getResultList();
    }

    public List <EsMatrisem> getListaMS(Short ano, Short sem, int codPrograma) {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<EsMatrisem> qry = em.createQuery("select s from EsMatrisem s WHERE s.anoLetivo = " + ano + " AND s.semestreId = " + sem + " AND s.dadoCadastroProgramaId = " + codPrograma, EsMatrisem.class);
        return qry.getResultList();
    }
    
    
    public void create(EsMatrisem esMatrisem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esMatrisem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsMatrisem esMatrisem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esMatrisem = em.merge(esMatrisem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esMatrisem.getCodMatriSem();
                if (findEsMatrisem(id) == null) {
                    throw new NonexistentEntityException("The esMatrisem with id " + id + " no longer exists.");
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
            EsMatrisem esMatrisem;
            try {
                esMatrisem = em.getReference(EsMatrisem.class, id);
                esMatrisem.getCodMatriSem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esMatrisem with id " + id + " no longer exists.", enfe);
            }
            em.remove(esMatrisem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsMatrisem> findEsMatrisemEntities() {
        return findEsMatrisemEntities(true, -1, -1);
    }

    public List<EsMatrisem> findEsMatrisemEntities(int maxResults, int firstResult) {
        return findEsMatrisemEntities(false, maxResults, firstResult);
    }

    private List<EsMatrisem> findEsMatrisemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsMatrisem.class));
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

    public EsMatrisem findEsMatrisem(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsMatrisem.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsMatrisemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsMatrisem> rt = cq.from(EsMatrisem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
