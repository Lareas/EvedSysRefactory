package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsEquivalencia;
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


public class EsEquivalenciaJpaController implements Serializable {

    public EsEquivalenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsEquivalenciaJpaController () {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EsEquivalencia getObjetoPup(int codPup) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsEquivalencia s WHERE s.codPup = " + codPup;
        System.out.println(ssql);
        TypedQuery<EsEquivalencia> qry = em.createQuery(ssql, EsEquivalencia.class);
        return qry.getSingleResult();
    }
    public void create(EsEquivalencia esEquivalencia) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esEquivalencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsEquivalencia esEquivalencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esEquivalencia = em.merge(esEquivalencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esEquivalencia.getCodEquivalencia();
                if (findEsEquivalencia(id) == null) {
                    throw new NonexistentEntityException("The esEquivalencia with id " + id + " no longer exists.");
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
            EsEquivalencia esEquivalencia;
            try {
                esEquivalencia = em.getReference(EsEquivalencia.class, id);
                esEquivalencia.getCodEquivalencia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esEquivalencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(esEquivalencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsEquivalencia> findEsEquivalenciaEntities() {
        return findEsEquivalenciaEntities(true, -1, -1);
    }

    public List<EsEquivalencia> findEsEquivalenciaEntities(int maxResults, int firstResult) {
        return findEsEquivalenciaEntities(false, maxResults, firstResult);
    }

    private List<EsEquivalencia> findEsEquivalenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsEquivalencia.class));
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

    public EsEquivalencia findEsEquivalencia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsEquivalencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsEquivalenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsEquivalencia> rt = cq.from(EsEquivalencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
