package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Notifverfsao;
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


public class NotifverfsaoJpaController implements Serializable {

    public NotifverfsaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public NotifverfsaoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(Notifverfsao notifverfsao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notifverfsao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Notifverfsao notifverfsao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notifverfsao = em.merge(notifverfsao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = notifverfsao.getCodNotifVerfsao();
                if (findNotifverfsao(id) == null) {
                    throw new NonexistentEntityException("The notifverfsao with id " + id + " no longer exists.");
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
            Notifverfsao notifverfsao;
            try {
                notifverfsao = em.getReference(Notifverfsao.class, id);
                notifverfsao.getCodNotifVerfsao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notifverfsao with id " + id + " no longer exists.", enfe);
            }
            em.remove(notifverfsao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Notifverfsao> findNotifverfsaoEntities() {
        return findNotifverfsaoEntities(true, -1, -1);
    }

    public List<Notifverfsao> findNotifverfsaoEntities(int maxResults, int firstResult) {
        return findNotifverfsaoEntities(false, maxResults, firstResult);
    }

    private List<Notifverfsao> findNotifverfsaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Notifverfsao.class));
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

    public Notifverfsao findNotifverfsao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Notifverfsao.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotifverfsaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Notifverfsao> rt = cq.from(Notifverfsao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
