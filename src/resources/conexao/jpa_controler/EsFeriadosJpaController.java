package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsFeriados;
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


public class EsFeriadosJpaController implements Serializable {

    public EsFeriadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsFeriadosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsFeriados esFeriados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esFeriados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsFeriados esFeriados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esFeriados = em.merge(esFeriados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esFeriados.getCodesferiado();
                if (findEsFeriados(id) == null) {
                    throw new NonexistentEntityException("The esFeriados with id " + id + " no longer exists.");
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
            EsFeriados esFeriados;
            try {
                esFeriados = em.getReference(EsFeriados.class, id);
                esFeriados.getCodesferiado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esFeriados with id " + id + " no longer exists.", enfe);
            }
            em.remove(esFeriados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsFeriados> findEsFeriadosEntities() {
        return findEsFeriadosEntities(true, -1, -1);
    }

    public List<EsFeriados> findEsFeriadosEntities(int maxResults, int firstResult) {
        return findEsFeriadosEntities(false, maxResults, firstResult);
    }

    private List<EsFeriados> findEsFeriadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsFeriados.class));
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

    public EsFeriados findEsFeriados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsFeriados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsFeriadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsFeriados> rt = cq.from(EsFeriados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
