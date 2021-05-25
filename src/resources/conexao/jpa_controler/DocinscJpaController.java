package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Docinsc;
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


public class DocinscJpaController implements Serializable {

    public DocinscJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DocinscJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getTipoDoc() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nomeDocInsc from Docinsc s order by s.nomeDocInsc", String.class);
        return qry.getResultList();
    }
    
    public List<Docinsc> getDocinscPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docinsc> qry = em.createQuery("select s from Docinsc s WHERE s.nomeDocInsc = '" + pesq + "'", Docinsc.class);
        return qry.getResultList();
    }
    
    public List<Docinsc> getDocinscPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docinsc> qry = em.createQuery("select s from Docinsc s WHERE s.nomeDocInsc LIKE '%" + pesq + "%'", Docinsc.class);
        return qry.getResultList();
    }
    
    public Short getNumTipoDoc(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        Short prov = 0;
        TypedQuery<Short> qry = em.createQuery("select s.codDocInsc from Docinsc s WHERE s.nomeDocInsc = '" + pesq + "'", Short.class);
        prov = qry.getSingleResult();
        return prov;
    }
    
    public void create(Docinsc docinsc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(docinsc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docinsc docinsc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            docinsc = em.merge(docinsc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = docinsc.getCodDocInsc();
                if (findDocinsc(id) == null) {
                    throw new NonexistentEntityException("The docinsc with id " + id + " no longer exists.");
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
            Docinsc docinsc;
            try {
                docinsc = em.getReference(Docinsc.class, id);
                docinsc.getCodDocInsc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docinsc with id " + id + " no longer exists.", enfe);
            }
            em.remove(docinsc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docinsc> findDocinscEntities() {
        return findDocinscEntities(true, -1, -1);
    }

    public List<Docinsc> findDocinscEntities(int maxResults, int firstResult) {
        return findDocinscEntities(false, maxResults, firstResult);
    }

    private List<Docinsc> findDocinscEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docinsc.class));
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

    public Docinsc findDocinsc(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docinsc.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocinscCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docinsc> rt = cq.from(Docinsc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
