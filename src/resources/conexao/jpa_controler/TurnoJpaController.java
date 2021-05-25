package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Turno;
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


public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TurnoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getNomeTurno() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.turno from Turno s order by s.turno", String.class);
        return qry.getResultList();
    }
    
    public Turno getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turno> qry = em.createQuery("select s from Turno s WHERE s.turno = '" + pesq + "'", Turno.class);
        return qry.getSingleResult();
    }
    
    public List<Turno> getProgramas() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turno> qry = em.createQuery("select s from Turno s", Turno.class);
        return qry.getResultList();
    }
    
    public List<Turno> getTurnoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turno> qry = em.createQuery("select s from Turno s WHERE s.turno LIKE '%" + pesq +"%'", Turno.class);
        return qry.getResultList();
    }
    
    public List<Turno> getTurnoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turno> qry = em.createQuery("select s from Turno s WHERE s.turno = '" + pesq + "'", Turno.class);
        return qry.getResultList();
    }
    
    public void create(Turno turno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(turno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurno(turno.getTurnoId()) != null) {
                throw new PreexistingEntityException("Turno " + turno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            turno = em.merge(turno);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = turno.getTurnoId();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
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
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getTurnoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

}
