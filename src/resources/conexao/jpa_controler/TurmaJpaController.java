package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Programa;
import entities.Turma;
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


public class TurmaJpaController implements Serializable {

    public TurmaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TurmaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public Turma getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Turma s WHERE s.turma = '" + pesq + "'";
        TypedQuery<Turma> qry = em.createQuery(ssql, Turma.class);
        return qry.getSingleResult();
    }
    
    public List<Turma> getProgramas() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turma> qry = em.createQuery("select s from Turma s", Turma.class);
        return qry.getResultList();
    }
    
    public List<String> getNomeTurmas() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.turma from Turma s order by s.turma", String.class);
        return qry.getResultList();
    }
    
    public List<Turma> getTurmaPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turma> qry = em.createQuery("select s from Turma s WHERE s.turma LIKE '%" + pesq +"%'", Turma.class);
        return qry.getResultList();
    }
    
    public List<Turma> getTurmaPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Turma> qry = em.createQuery("select s from Turma s WHERE s.turma = '" + pesq +"'", Turma.class);
        return qry.getResultList();
    }
    
    public void create(Turma Turma) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(Turma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurma(Turma.getTurmaId()) != null) {
                throw new PreexistingEntityException("Turma " + Turma + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turma Turma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turma = em.merge(Turma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = Turma.getTurmaId();
                if (findTurma(id) == null) {
                    throw new NonexistentEntityException("The Turma with id " + id + " no longer exists.");
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
            Turma Turma;
            try {
                Turma = em.getReference(Turma.class, id);
                Turma.getTurmaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Turma with id " + id + " no longer exists.", enfe);
            }
            em.remove(Turma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turma> findTurmaEntities() {
        return findTurmaEntities(true, -1, -1);
    }

    public List<Turma> findTurmaEntities(int maxResults, int firstResult) {
        return findTurmaEntities(false, maxResults, firstResult);
    }

    private List<Turma> findTurmaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turma.class));
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

    public Turma findTurma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turma.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurmaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turma> rt = cq.from(Turma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

}
