package jpa_controler;

import entities.CepTipoLog;
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


public class CepTipoLogJpaController implements Serializable {

    public CepTipoLogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CepTipoLog cepTipoLog) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cepTipoLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCepTipoLog(cepTipoLog.getCodigo()) != null) {
                throw new PreexistingEntityException("CepTipoLog " + cepTipoLog + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CepTipoLog cepTipoLog) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cepTipoLog = em.merge(cepTipoLog);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cepTipoLog.getCodigo();
                if (findCepTipoLog(id) == null) {
                    throw new NonexistentEntityException("The cepTipoLog with id " + id + " no longer exists.");
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
            CepTipoLog cepTipoLog;
            try {
                cepTipoLog = em.getReference(CepTipoLog.class, id);
                cepTipoLog.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cepTipoLog with id " + id + " no longer exists.", enfe);
            }
            em.remove(cepTipoLog);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CepTipoLog> findCepTipoLogEntities() {
        return findCepTipoLogEntities(true, -1, -1);
    }

    public List<CepTipoLog> findCepTipoLogEntities(int maxResults, int firstResult) {
        return findCepTipoLogEntities(false, maxResults, firstResult);
    }

    private List<CepTipoLog> findCepTipoLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CepTipoLog.class));
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

    public CepTipoLog findCepTipoLog(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CepTipoLog.class, id);
        } finally {
            em.close();
        }
    }

    public int getCepTipoLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CepTipoLog> rt = cq.from(CepTipoLog.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
