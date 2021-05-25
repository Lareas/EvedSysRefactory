package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Arqsys;
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


public class ArqsysJpaController implements Serializable {

    public ArqsysJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    

    public ArqsysJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public void create(Arqsys arqsys) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(arqsys);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArqsys(arqsys.getCod()) != null) {
                throw new PreexistingEntityException("Arqsys " + arqsys + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arqsys arqsys) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            arqsys = em.merge(arqsys);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = arqsys.getCod();
                if (findArqsys(id) == null) {
                    throw new NonexistentEntityException("The arqsys with id " + id + " no longer exists.");
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
            Arqsys arqsys;
            try {
                arqsys = em.getReference(Arqsys.class, id);
                arqsys.getCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arqsys with id " + id + " no longer exists.", enfe);
            }
            em.remove(arqsys);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arqsys> findArqsysEntities() {
        return findArqsysEntities(true, -1, -1);
    }

    public List<Arqsys> findArqsysEntities(int maxResults, int firstResult) {
        return findArqsysEntities(false, maxResults, firstResult);
    }

    private List<Arqsys> findArqsysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arqsys.class));
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

    public Arqsys findArqsys(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arqsys.class, id);
        } finally {
            em.close();
        }
    }

    public int getArqsysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arqsys> rt = cq.from(Arqsys.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
