package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Docsoficiais;
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


public class DocsoficiaisJpaController implements Serializable {

    public DocsoficiaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public DocsoficiaisJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<String> getNomeDosDocsOficiais() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nomeDocOficial from Docsoficiais s order by s.nomeDocOficial", String.class);
        return qry.getResultList();
    }
    
    public List<Docsoficiais> getOutDocPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docsoficiais> qry = em.createQuery("select s from Docsoficiais s WHERE s.nomeDocOficial = '" + pesq + "'", Docsoficiais.class);
        return qry.getResultList();
    }
    
    public List<Docsoficiais> getOutDocPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docsoficiais> qry = em.createQuery("select s from Docsoficiais s WHERE s.nomeDocOficial LIKE '%" + pesq + "%'", Docsoficiais.class);
        return qry.getResultList();
    }
    
    public Docsoficiais getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docsoficiais> qry = em.createQuery("select s from Docsoficiais s WHERE s.nomeDocOficial = '" + pesq + "'", Docsoficiais.class);
        return qry.getSingleResult();
    }
    
    public Docsoficiais getDocOfUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docsoficiais> qry = em.createQuery("select s from Docsoficiais s WHERE s.nomeDocOficial = '" + pesq + "'", Docsoficiais.class);
        return qry.getSingleResult();
    }
    
    public void create(Docsoficiais docsoficiais) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(docsoficiais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docsoficiais docsoficiais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            docsoficiais = em.merge(docsoficiais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = docsoficiais.getDocOficialId();
                if (findDocsoficiais(id) == null) {
                    throw new NonexistentEntityException("The docsoficiais with id " + id + " no longer exists.");
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
            Docsoficiais docsoficiais;
            try {
                docsoficiais = em.getReference(Docsoficiais.class, id);
                docsoficiais.getDocOficialId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docsoficiais with id " + id + " no longer exists.", enfe);
            }
            em.remove(docsoficiais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docsoficiais> findDocsoficiaisEntities() {
        return findDocsoficiaisEntities(true, -1, -1);
    }

    public List<Docsoficiais> findDocsoficiaisEntities(int maxResults, int firstResult) {
        return findDocsoficiaisEntities(false, maxResults, firstResult);
    }

    private List<Docsoficiais> findDocsoficiaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docsoficiais.class));
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

    public Docsoficiais findDocsoficiais(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docsoficiais.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocsoficiaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docsoficiais> rt = cq.from(Docsoficiais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
