package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Siglaestado;
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

public class SiglaestadoJpaController implements Serializable {

    public SiglaestadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SiglaestadoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<String> getNomeDasUF() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.estado from Siglaestado s order by s.estado", String.class);
        return qry.getResultList();
    }

    public List<Siglaestado> getSiglaestadoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Siglaestado> qry = em.createQuery("select s from Siglaestado s WHERE s.estado LIKE '%" + pesq + "%'", Siglaestado.class);
        return qry.getResultList();
    }

    public List<Siglaestado> getSiglaestadoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Siglaestado> qry = em.createQuery("select s from Siglaestado s WHERE s.estado = '" + pesq + "'", Siglaestado.class);
        return qry.getResultList();
    }

    public Siglaestado getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Siglaestado s WHERE s.estado = '" + pesq + "'";
        TypedQuery<Siglaestado> qry = em.createQuery(ssql, Siglaestado.class);
        return qry.getSingleResult();
    }
    
    public Siglaestado getUFPesqUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Siglaestado> qry = em.createQuery("select s from Siglaestado s WHERE s.estado = '" + pesq + "'", Siglaestado.class);
        return qry.getSingleResult();
    }
    
    public String getUFPesqExtensaoUF(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.estado from Siglaestado s WHERE s.siglaEstado = '" + pesq + "'";
        System.out.println(ssql);
        TypedQuery<String> qry = em.createQuery(ssql, String.class);
        return qry.getSingleResult();
    }

    public void create(Siglaestado siglaestado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(siglaestado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSiglaestado(siglaestado.getSiglaEstadoId()) != null) {
                throw new PreexistingEntityException("Siglaestado " + siglaestado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Siglaestado siglaestado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            siglaestado = em.merge(siglaestado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = siglaestado.getSiglaEstadoId();
                if (findSiglaestado(id) == null) {
                    throw new NonexistentEntityException("The siglaestado with id " + id + " no longer exists.");
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
            Siglaestado siglaestado;
            try {
                siglaestado = em.getReference(Siglaestado.class, id);
                siglaestado.getSiglaEstadoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The siglaestado with id " + id + " no longer exists.", enfe);
            }
            em.remove(siglaestado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Siglaestado> findSiglaestadoEntities() {
        return findSiglaestadoEntities(true, -1, -1);
    }

    public List<Siglaestado> findSiglaestadoEntities(int maxResults, int firstResult) {
        return findSiglaestadoEntities(false, maxResults, firstResult);
    }

    private List<Siglaestado> findSiglaestadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Siglaestado.class));
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

    public Siglaestado findSiglaestado(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Siglaestado.class, id);
        } finally {
            em.close();
        }
    }

    public int getSiglaestadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Siglaestado> rt = cq.from(Siglaestado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
