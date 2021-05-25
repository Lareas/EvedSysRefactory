package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsDispensadet;
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


public class EsDispensadetJpaController implements Serializable {

    public EsDispensadetJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsDispensadetJpaController () {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsDispensadet esDispensadet) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esDispensadet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsDispensadet esDispensadet) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esDispensadet = em.merge(esDispensadet);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esDispensadet.getCodDispensaDet();
                if (findEsDispensadet(id) == null) {
                    throw new NonexistentEntityException("The esDispensadet with id " + id + " no longer exists.");
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
            EsDispensadet esDispensadet;
            try {
                esDispensadet = em.getReference(EsDispensadet.class, id);
                esDispensadet.getCodDispensaDet();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esDispensadet with id " + id + " no longer exists.", enfe);
            }
            em.remove(esDispensadet);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsDispensadet> findEsDispensadetEntities() {
        return findEsDispensadetEntities(true, -1, -1);
    }

    public List<EsDispensadet> findEsDispensadetEntities(int maxResults, int firstResult) {
        return findEsDispensadetEntities(false, maxResults, firstResult);
    }

    private List<EsDispensadet> findEsDispensadetEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsDispensadet.class));
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

    public EsDispensadet findEsDispensadet(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsDispensadet.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsDispensadetCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsDispensadet> rt = cq.from(EsDispensadet.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
