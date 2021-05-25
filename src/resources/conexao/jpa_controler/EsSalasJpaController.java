package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsSalas;
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


public class EsSalasJpaController implements Serializable {

    public EsSalasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsSalasJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public EsSalas getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsSalas s WHERE s.nomesala = '" + pesq + "'";
        TypedQuery<EsSalas> qry = em.createQuery(ssql, EsSalas.class);
        return qry.getSingleResult();
    }
    
    public List<String> getNomeSalas() {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nomesala from EsSalas s order by s.nomesala", String.class);
        return qry.getResultList();
    }

    public void create(EsSalas esSalas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esSalas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsSalas esSalas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esSalas = em.merge(esSalas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esSalas.getCodessala();
                if (findEsSalas(id) == null) {
                    throw new NonexistentEntityException("The esSalas with id " + id + " no longer exists.");
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
            EsSalas esSalas;
            try {
                esSalas = em.getReference(EsSalas.class, id);
                esSalas.getCodessala();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esSalas with id " + id + " no longer exists.", enfe);
            }
            em.remove(esSalas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsSalas> findEsSalasEntities() {
        return findEsSalasEntities(true, -1, -1);
    }

    public List<EsSalas> findEsSalasEntities(int maxResults, int firstResult) {
        return findEsSalasEntities(false, maxResults, firstResult);
    }

    private List<EsSalas> findEsSalasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsSalas.class));
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

    public EsSalas findEsSalas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsSalas.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsSalasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsSalas> rt = cq.from(EsSalas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
