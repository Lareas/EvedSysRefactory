package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Frequencia;
import entities.Programa;
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

public class FrequenciaJpaController implements Serializable {

    public FrequenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FrequenciaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<String> getNomeFrequencia() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.frequencia from Frequencia s order by s.frequencia", String.class);
        return qry.getResultList();
    }

    public List<Frequencia> getFrequenciaPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Frequencia> qry = em.createQuery("select s from Frequencia s WHERE s.frequencia LIKE '%" + pesq + "%'", Frequencia.class);
        return qry.getResultList();
    }

    public List<Frequencia> getFrequenciaPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Frequencia> qry = em.createQuery("select s from Frequencia s WHERE s.frequencia  = '" + pesq + "'", Frequencia.class);
        return qry.getResultList();
    }

    public Frequencia getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Frequencia> qry = em.createQuery("select s from Frequencia s WHERE s.frequencia = '" + pesq + "'", Frequencia.class);
        return qry.getSingleResult();
    }

    public void create(Frequencia frequencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(frequencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFrequencia(frequencia.getFrequenciaId()) != null) {
                throw new PreexistingEntityException("Frequencia " + frequencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Frequencia frequencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            frequencia = em.merge(frequencia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = frequencia.getFrequenciaId();
                if (findFrequencia(id) == null) {
                    throw new NonexistentEntityException("The frequencia with id " + id + " no longer exists.");
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
            Frequencia frequencia;
            try {
                frequencia = em.getReference(Frequencia.class, id);
                frequencia.getFrequenciaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The frequencia with id " + id + " no longer exists.", enfe);
            }
            em.remove(frequencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Frequencia> findFrequenciaEntities() {
        return findFrequenciaEntities(true, -1, -1);
    }

    public List<Frequencia> findFrequenciaEntities(int maxResults, int firstResult) {
        return findFrequenciaEntities(false, maxResults, firstResult);
    }

    private List<Frequencia> findFrequenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Frequencia.class));
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

    public Frequencia findFrequencia(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Frequencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getFrequenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Frequencia> rt = cq.from(Frequencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
