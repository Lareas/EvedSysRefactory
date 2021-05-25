package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Programa;
import entities.Sexo;
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


public class SexoJpaController implements Serializable {

    public SexoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public SexoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<Sexo> getSexoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Sexo> qry = em.createQuery("select s from Sexo s WHERE s.sexo LIKE '%" + pesq + "%'", Sexo.class);
        return qry.getResultList();
    }
    
     public List<Sexo> getSexoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Sexo> qry = em.createQuery("select s from Sexo s WHERE s.sexo = '" + pesq + "'", Sexo.class);
        return qry.getResultList();
    }
    
    
    public void create(Sexo sexo) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sexo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSexo(sexo.getSexoId()) != null) {
                throw new PreexistingEntityException("Sexo " + sexo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sexo sexo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sexo = em.merge(sexo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = sexo.getSexoId();
                if (findSexo(id) == null) {
                    throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.");
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
            Sexo sexo;
            try {
                sexo = em.getReference(Sexo.class, id);
                sexo.getSexoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sexo with id " + id + " no longer exists.", enfe);
            }
            em.remove(sexo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sexo> findSexoEntities() {
        return findSexoEntities(true, -1, -1);
    }

    public List<Sexo> findSexoEntities(int maxResults, int firstResult) {
        return findSexoEntities(false, maxResults, firstResult);
    }

    private List<Sexo> findSexoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sexo.class));
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

    public Sexo findSexo(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sexo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSexoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sexo> rt = cq.from(Sexo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
