package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsEquivalenciadet;
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


public class EsEquivalenciadetJpaController implements Serializable {

    public EsEquivalenciadetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsEquivalenciadetJpaController () {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<EsEquivalenciadet> pegaFilhoeEqui(Integer codEqui) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsEquivalenciadet s WHERE s.codEquivalencia = " + codEqui;
        System.out.println(ssql);
        TypedQuery<EsEquivalenciadet> qry = em.createQuery(ssql, EsEquivalenciadet.class);
        return qry.getResultList();
    }
    
    public void create(EsEquivalenciadet esEquivalenciadet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esEquivalenciadet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsEquivalenciadet esEquivalenciadet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esEquivalenciadet = em.merge(esEquivalenciadet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esEquivalenciadet.getCodEquivalenciaDet();
                if (findEsEquivalenciadet(id) == null) {
                    throw new NonexistentEntityException("The esEquivalenciadet with id " + id + " no longer exists.");
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
            EsEquivalenciadet esEquivalenciadet;
            try {
                esEquivalenciadet = em.getReference(EsEquivalenciadet.class, id);
                esEquivalenciadet.getCodEquivalenciaDet();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esEquivalenciadet with id " + id + " no longer exists.", enfe);
            }
            em.remove(esEquivalenciadet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsEquivalenciadet> findEsEquivalenciadetEntities() {
        return findEsEquivalenciadetEntities(true, -1, -1);
    }

    public List<EsEquivalenciadet> findEsEquivalenciadetEntities(int maxResults, int firstResult) {
        return findEsEquivalenciadetEntities(false, maxResults, firstResult);
    }

    private List<EsEquivalenciadet> findEsEquivalenciadetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsEquivalenciadet.class));
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

    public EsEquivalenciadet findEsEquivalenciadet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsEquivalenciadet.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsEquivalenciadetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsEquivalenciadet> rt = cq.from(EsEquivalenciadet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
