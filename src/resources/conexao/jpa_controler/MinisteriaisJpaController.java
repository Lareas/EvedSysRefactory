package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Ministeriais;
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


public class MinisteriaisJpaController implements Serializable {

    public MinisteriaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public MinisteriaisJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getNomeDosMinisteriosF() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.ministeriais from Ministeriais s order by s.ministeriais", String.class);
        return qry.getResultList();
    }
    
    public List<Ministeriais> getMinisteriaisPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Ministeriais> qry = em.createQuery("select s from Ministeriais s WHERE s.ministeriais LIKE '%" + pesq +"%'", Ministeriais.class);
        return qry.getResultList();
    }
    
    public List<Ministeriais> getMinisteriaisPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Ministeriais> qry = em.createQuery("select s from Ministeriais s WHERE s.ministeriais = '" + pesq +"'", Ministeriais.class);
        return qry.getResultList();
    }

    public void create(Ministeriais ministeriais) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ministeriais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMinisteriais(ministeriais.getMinisteriaisId()) != null) {
                throw new PreexistingEntityException("Ministeriais " + ministeriais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ministeriais ministeriais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ministeriais = em.merge(ministeriais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = ministeriais.getMinisteriaisId();
                if (findMinisteriais(id) == null) {
                    throw new NonexistentEntityException("The ministeriais with id " + id + " no longer exists.");
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
            Ministeriais ministeriais;
            try {
                ministeriais = em.getReference(Ministeriais.class, id);
                ministeriais.getMinisteriaisId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ministeriais with id " + id + " no longer exists.", enfe);
            }
            em.remove(ministeriais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ministeriais> findMinisteriaisEntities() {
        return findMinisteriaisEntities(true, -1, -1);
    }

    public List<Ministeriais> findMinisteriaisEntities(int maxResults, int firstResult) {
        return findMinisteriaisEntities(false, maxResults, firstResult);
    }

    private List<Ministeriais> findMinisteriaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ministeriais.class));
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

    public Ministeriais findMinisteriais(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ministeriais.class, id);
        } finally {
            em.close();
        }
    }

    public int getMinisteriaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ministeriais> rt = cq.from(Ministeriais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
