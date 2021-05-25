package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Cordapele;
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


public class CordapeleJpaController implements Serializable {

    public CordapeleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CordapeleJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    public void create(Cordapele cordapele) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cordapele);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Cordapele getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cordapele> qry = em.createQuery("select s from Cordapele s WHERE s.cordapele = '" + pesq + "'", Cordapele.class);
        return qry.getSingleResult();
    }
    
    public List<String> getNomeDaCordapele() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.cordapele from Cordapele s order by s.cordapele", String.class);
        return qry.getResultList();
    }

    public void edit(Cordapele cordapele) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cordapele = em.merge(cordapele);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cordapele.getCodcordapele();
                if (findCordapele(id) == null) {
                    throw new NonexistentEntityException("The cordapele with id " + id + " no longer exists.");
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
            Cordapele cordapele;
            try {
                cordapele = em.getReference(Cordapele.class, id);
                cordapele.getCodcordapele();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cordapele with id " + id + " no longer exists.", enfe);
            }
            em.remove(cordapele);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cordapele> findCordapeleEntities() {
        return findCordapeleEntities(true, -1, -1);
    }

    public List<Cordapele> findCordapeleEntities(int maxResults, int firstResult) {
        return findCordapeleEntities(false, maxResults, firstResult);
    }

    private List<Cordapele> findCordapeleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cordapele.class));
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

    public Cordapele findCordapele(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cordapele.class, id);
        } finally {
            em.close();
        }
    }

    public int getCordapeleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cordapele> rt = cq.from(Cordapele.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
