package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsDispensa;
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


public class EsDispensaJpaController implements Serializable {

    public EsDispensaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsDispensaJpaController () {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsDispensa esDispensa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esDispensa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsDispensa esDispensa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esDispensa = em.merge(esDispensa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esDispensa.getCodDispensa();
                if (findEsDispensa(id) == null) {
                    throw new NonexistentEntityException("The esDispensa with id " + id + " no longer exists.");
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
            EsDispensa esDispensa;
            try {
                esDispensa = em.getReference(EsDispensa.class, id);
                esDispensa.getCodDispensa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esDispensa with id " + id + " no longer exists.", enfe);
            }
            em.remove(esDispensa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsDispensa> findEsDispensaEntities() {
        return findEsDispensaEntities(true, -1, -1);
    }

    public List<EsDispensa> findEsDispensaEntities(int maxResults, int firstResult) {
        return findEsDispensaEntities(false, maxResults, firstResult);
    }

    private List<EsDispensa> findEsDispensaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsDispensa.class));
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

    public EsDispensa findEsDispensa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsDispensa.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsDispensaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsDispensa> rt = cq.from(EsDispensa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
