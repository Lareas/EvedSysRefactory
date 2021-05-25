package jpa_controler;

import entities.CepBairros;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;
import jpa_controler.exceptions.PreexistingEntityException;


public class CepBairrosJpaController implements Serializable {

    public CepBairrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CepBairros cepBairros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cepBairros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCepBairros(cepBairros.getCodigo()) != null) {
                throw new PreexistingEntityException("CepBairros " + cepBairros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CepBairros cepBairros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cepBairros = em.merge(cepBairros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cepBairros.getCodigo();
                if (findCepBairros(id) == null) {
                    throw new NonexistentEntityException("The cepBairros with id " + id + " no longer exists.");
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
            CepBairros cepBairros;
            try {
                cepBairros = em.getReference(CepBairros.class, id);
                cepBairros.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cepBairros with id " + id + " no longer exists.", enfe);
            }
            em.remove(cepBairros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CepBairros> findCepBairrosEntities() {
        return findCepBairrosEntities(true, -1, -1);
    }

    public List<CepBairros> findCepBairrosEntities(int maxResults, int firstResult) {
        return findCepBairrosEntities(false, maxResults, firstResult);
    }

    private List<CepBairros> findCepBairrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CepBairros.class));
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

    public CepBairros findCepBairros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CepBairros.class, id);
        } finally {
            em.close();
        }
    }

    public int getCepBairrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CepBairros> rt = cq.from(CepBairros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
