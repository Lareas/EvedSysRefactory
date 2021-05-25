package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Perfilusers;
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


public class PerfilusersJpaController implements Serializable {

    public PerfilusersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public PerfilusersJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
    public List<String> getNomeDosPerfis() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.perfil from Perfilusers s order by s.perfil", String.class);
        return qry.getResultList();
    }
    
    public void create(Perfilusers perfilusers) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(perfilusers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPerfilusers(perfilusers.getCodPerfil()) != null) {
                throw new PreexistingEntityException("Perfilusers " + perfilusers + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Perfilusers perfilusers) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            perfilusers = em.merge(perfilusers);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = perfilusers.getCodPerfil();
                if (findPerfilusers(id) == null) {
                    throw new NonexistentEntityException("The perfilusers with id " + id + " no longer exists.");
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
            Perfilusers perfilusers;
            try {
                perfilusers = em.getReference(Perfilusers.class, id);
                perfilusers.getCodPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The perfilusers with id " + id + " no longer exists.", enfe);
            }
            em.remove(perfilusers);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Perfilusers> findPerfilusersEntities() {
        return findPerfilusersEntities(true, -1, -1);
    }

    public List<Perfilusers> findPerfilusersEntities(int maxResults, int firstResult) {
        return findPerfilusersEntities(false, maxResults, firstResult);
    }

    private List<Perfilusers> findPerfilusersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Perfilusers.class));
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

    public Perfilusers findPerfilusers(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfilusers.class, id);
        } finally {
            em.close();
        }
    }

    public int getPerfilusersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Perfilusers> rt = cq.from(Perfilusers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
