package jpa_controler;

import entities.CepCidades;
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


public class CepCidadesJpaController implements Serializable {

    public CepCidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CepCidades cepCidades) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cepCidades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCepCidades(cepCidades.getCodigo()) != null) {
                throw new PreexistingEntityException("CepCidades " + cepCidades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CepCidades cepCidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cepCidades = em.merge(cepCidades);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cepCidades.getCodigo();
                if (findCepCidades(id) == null) {
                    throw new NonexistentEntityException("The cepCidades with id " + id + " no longer exists.");
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
            CepCidades cepCidades;
            try {
                cepCidades = em.getReference(CepCidades.class, id);
                cepCidades.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cepCidades with id " + id + " no longer exists.", enfe);
            }
            em.remove(cepCidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CepCidades> findCepCidadesEntities() {
        return findCepCidadesEntities(true, -1, -1);
    }

    public List<CepCidades> findCepCidadesEntities(int maxResults, int firstResult) {
        return findCepCidadesEntities(false, maxResults, firstResult);
    }

    private List<CepCidades> findCepCidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CepCidades.class));
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

    public CepCidades findCepCidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CepCidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCepCidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CepCidades> rt = cq.from(CepCidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
