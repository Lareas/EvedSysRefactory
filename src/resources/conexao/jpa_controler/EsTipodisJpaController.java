package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsTipodis;
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


public class EsTipodisJpaController implements Serializable {

    public EsTipodisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsTipodisJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public EsTipodis getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsTipodis s WHERE s.tipodisc = '" + pesq + "'";
        TypedQuery<EsTipodis> qry = em.createQuery(ssql, EsTipodis.class);
        return qry.getSingleResult();
    }
    
    public List<String> getNomeTipoDis() {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.tipodisc from EsTipodis s order by s.tipodisc", String.class);
        return qry.getResultList();
    }

    public void create(EsTipodis esTipodis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esTipodis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsTipodis esTipodis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esTipodis = em.merge(esTipodis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esTipodis.getCodtipodis();
                if (findEsTipodis(id) == null) {
                    throw new NonexistentEntityException("The esTipodis with id " + id + " no longer exists.");
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
            EsTipodis esTipodis;
            try {
                esTipodis = em.getReference(EsTipodis.class, id);
                esTipodis.getCodtipodis();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esTipodis with id " + id + " no longer exists.", enfe);
            }
            em.remove(esTipodis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsTipodis> findEsTipodisEntities() {
        return findEsTipodisEntities(true, -1, -1);
    }

    public List<EsTipodis> findEsTipodisEntities(int maxResults, int firstResult) {
        return findEsTipodisEntities(false, maxResults, firstResult);
    }

    private List<EsTipodis> findEsTipodisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsTipodis.class));
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

    public EsTipodis findEsTipodis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsTipodis.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsTipodisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsTipodis> rt = cq.from(EsTipodis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
