package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Curso;
import entities.EsFotosalunos;
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


public class EsFotosalunosJpaController implements Serializable {

    public EsFotosalunosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsFotosalunosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(EsFotosalunos esFotosalunos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esFotosalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public EsFotosalunos getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsFotosalunos s WHERE s.dadoCadastroGeralId = '" + pesq + "'";
        TypedQuery<EsFotosalunos> qry = em.createQuery(ssql, EsFotosalunos.class);
        return qry.getSingleResult();
    }
    
    public List<EsFotosalunos> getFotoAluno(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsFotosalunos s WHERE s.dadoCadastroGeralId = '" + pesq + "'";
        TypedQuery<EsFotosalunos> qry = em.createQuery(ssql, EsFotosalunos.class);
        return qry.getResultList();
    }

    public void edit(EsFotosalunos esFotosalunos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esFotosalunos = em.merge(esFotosalunos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = esFotosalunos.getCodFotoAluno();
                if (findEsFotosalunos(id) == null) {
                    throw new NonexistentEntityException("The esFotosalunos with id " + id + " no longer exists.");
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
            EsFotosalunos esFotosalunos;
            try {
                esFotosalunos = em.getReference(EsFotosalunos.class, id);
                esFotosalunos.getCodFotoAluno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esFotosalunos with id " + id + " no longer exists.", enfe);
            }
            em.remove(esFotosalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsFotosalunos> findEsFotosalunosEntities() {
        return findEsFotosalunosEntities(true, -1, -1);
    }

    public List<EsFotosalunos> findEsFotosalunosEntities(int maxResults, int firstResult) {
        return findEsFotosalunosEntities(false, maxResults, firstResult);
    }

    private List<EsFotosalunos> findEsFotosalunosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsFotosalunos.class));
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

    public EsFotosalunos findEsFotosalunos(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsFotosalunos.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsFotosalunosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsFotosalunos> rt = cq.from(EsFotosalunos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
