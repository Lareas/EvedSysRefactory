/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Cadaludissit;
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
import jpa_controler.exceptions.PreexistingEntityException;

/**
 *
 * @author ticoa
 */
public class CadaludissitJpaController implements Serializable {

    public CadaludissitJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public CadaludissitJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<String> getNomeSituacao() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.descricao from Cadaludissit s order by s.descricao", String.class);
        return qry.getResultList();
    }
    
    public List<String> getEquivs() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.descricao from Cadaludissit s WHERE s.descricao LIKE '%quival%' order by s.descricao", String.class);
        return qry.getResultList();
    }

    public List<Cadaludissit> getCadaludissitPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadaludissit> qry = em.createQuery("select s from Cadaludissit s WHERE s.descricao LIKE '%" + pesq +"%'", Cadaludissit.class);
        return qry.getResultList();
    }
    
    public List<Cadaludissit> getCadaludissitPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadaludissit> qry = em.createQuery("select s from Cadaludissit s WHERE s.descricao = '" + pesq +"'", Cadaludissit.class);
        return qry.getResultList();
    }
    
    public Cadaludissit getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadaludissit> qry = em.createQuery("select s from Cadaludissit s WHERE s.descricao = '" + pesq + "'", Cadaludissit.class);
        return qry.getSingleResult();
    }
    
    
    public void create(Cadaludissit cadaludissit) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cadaludissit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCadaludissit(cadaludissit.getCadsId()) != null) {
                throw new PreexistingEntityException("Cadaludissit " + cadaludissit + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cadaludissit cadaludissit) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cadaludissit = em.merge(cadaludissit);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = cadaludissit.getCadsId();
                if (findCadaludissit(id) == null) {
                    throw new NonexistentEntityException("The cadaludissit with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cadaludissit cadaludissit;
            try {
                cadaludissit = em.getReference(Cadaludissit.class, id);
                cadaludissit.getCadsId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cadaludissit with id " + id + " no longer exists.", enfe);
            }
            em.remove(cadaludissit);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cadaludissit> findCadaludissitEntities() {
        return findCadaludissitEntities(true, -1, -1);
    }

    public List<Cadaludissit> findCadaludissitEntities(int maxResults, int firstResult) {
        return findCadaludissitEntities(false, maxResults, firstResult);
    }

    private List<Cadaludissit> findCadaludissitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cadaludissit.class));
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

    public Cadaludissit findCadaludissit(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cadaludissit.class, id);
        } finally {
            em.close();
        }
    }

    public int getCadaludissitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cadaludissit> rt = cq.from(Cadaludissit.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
