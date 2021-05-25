package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsCalendas;
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


public class EsCalendasJpaController implements Serializable {

    public EsCalendasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsCalendasJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsCalendas esCalendas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esCalendas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsCalendas esCalendas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esCalendas = em.merge(esCalendas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esCalendas.getCodescalendas();
                if (findEsCalendas(id) == null) {
                    throw new NonexistentEntityException("The esCalendas with id " + id + " no longer exists.");
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
            EsCalendas esCalendas;
            try {
                esCalendas = em.getReference(EsCalendas.class, id);
                esCalendas.getCodescalendas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esCalendas with id " + id + " no longer exists.", enfe);
            }
            em.remove(esCalendas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsCalendas> findEsCalendasEntities() {
        return findEsCalendasEntities(true, -1, -1);
    }

    public List<EsCalendas> findEsCalendasEntities(int maxResults, int firstResult) {
        return findEsCalendasEntities(false, maxResults, firstResult);
    }

    private List<EsCalendas> findEsCalendasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsCalendas.class));
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

    public EsCalendas findEsCalendas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsCalendas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsCalendasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsCalendas> rt = cq.from(EsCalendas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
