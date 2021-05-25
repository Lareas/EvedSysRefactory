package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Tabuser;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;

public class TabuserJpaController implements Serializable {

    public TabuserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public TabuserJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Tabuser> getTabuserPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Tabuser> qry = em.createQuery("select s from Tabuser s WHERE s.login LIKE '%" + pesq + "%'", Tabuser.class);
        return qry.getResultList();
    }
    
    public List<Tabuser> getTabuserPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Tabuser> qry = em.createQuery("select s from Tabuser s WHERE s.login = '" + pesq + "'", Tabuser.class);
        return qry.getResultList();
    }
    
    public void create(Tabuser tabuser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tabuser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tabuser tabuser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tabuser = em.merge(tabuser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tabuser.getCoduser();
                if (findTabuser(id) == null) {
                    throw new NonexistentEntityException("The tabuser with id " + id + " no longer exists.");
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
            Tabuser tabuser;
            try {
                tabuser = em.getReference(Tabuser.class, id);
                tabuser.getCoduser();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tabuser with id " + id + " no longer exists.", enfe);
            }
            em.remove(tabuser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tabuser> findTabuserEntities() {
        return findTabuserEntities(true, -1, -1);
    }

    public List<Tabuser> findTabuserEntities(int maxResults, int firstResult) {
        return findTabuserEntities(false, maxResults, firstResult);
    }

    private List<Tabuser> findTabuserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tabuser.class));
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

    public Tabuser findTabuser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tabuser.class, id);
        } finally {
            em.close();
        }
    }

    public Tabuser findUserPorLogin(String login) {

        try {
            Query q = getEntityManager().createNamedQuery("Tabuser.findByLogin");
            q.setParameter("login", login);
            return (Tabuser) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public int getTabuserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tabuser> rt = cq.from(Tabuser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
