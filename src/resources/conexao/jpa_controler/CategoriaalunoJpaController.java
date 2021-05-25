package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Categoriaaluno;
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

public class CategoriaalunoJpaController implements Serializable {

    public CategoriaalunoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CategoriaalunoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(Categoriaaluno categoriaaluno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(categoriaaluno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoriaaluno(categoriaaluno.getCategoriaAlunoId()) != null) {
                throw new PreexistingEntityException("Categoriaaluno " + categoriaaluno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoriaaluno> getCategoriaalunoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Categoriaaluno> qry = em.createQuery("select s from Categoriaaluno s WHERE s.categoriaAluno LIKE '%" + pesq + "%'", Categoriaaluno.class);
        return qry.getResultList();
    }
    
    public List<Categoriaaluno> getCategoriaalunoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Categoriaaluno> qry = em.createQuery("select s from Categoriaaluno s WHERE s.categoriaAluno = '" + pesq + "'", Categoriaaluno.class);
        return qry.getResultList();
    }

    public void edit(Categoriaaluno categoriaaluno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            categoriaaluno = em.merge(categoriaaluno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = categoriaaluno.getCategoriaAlunoId();
                if (findCategoriaaluno(id) == null) {
                    throw new NonexistentEntityException("The categoriaaluno with id " + id + " no longer exists.");
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
            Categoriaaluno categoriaaluno;
            try {
                categoriaaluno = em.getReference(Categoriaaluno.class, id);
                categoriaaluno.getCategoriaAlunoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaaluno with id " + id + " no longer exists.", enfe);
            }
            em.remove(categoriaaluno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoriaaluno> findCategoriaalunoEntities() {
        return findCategoriaalunoEntities(true, -1, -1);
    }

    public List<Categoriaaluno> findCategoriaalunoEntities(int maxResults, int firstResult) {
        return findCategoriaalunoEntities(false, maxResults, firstResult);
    }

    private List<Categoriaaluno> findCategoriaalunoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoriaaluno.class));
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

    public Categoriaaluno findCategoriaaluno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoriaaluno.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaalunoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoriaaluno> rt = cq.from(Categoriaaluno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
