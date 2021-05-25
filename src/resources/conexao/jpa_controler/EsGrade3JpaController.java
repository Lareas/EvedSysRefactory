package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsGrade3;
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

public class EsGrade3JpaController implements Serializable {

    public EsGrade3JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EsGrade3JpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<EsGrade3> getEsGradePesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade3> qry = em.createQuery("select s from EsGrade3 s WHERE s.anoLetivo LIKE '%" + pesq + "%'", EsGrade3.class);
        return qry.getResultList();
    }

    public List<EsGrade3> getGradesOrdenadas(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade3> qry = em.createQuery("select s from EsGrade3 s Order By s.anoLetivo DESC, s.semestre DESC", EsGrade3.class);
        return qry.getResultList();
    }

    public List<EsGrade3> getEsGradePesqExac(String ano, String semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade3> qry = em.createQuery("select s from EsGrade3 s WHERE s.anoLetivo = '" + ano + "' AND s.semestre = '" + semestre + "'", EsGrade3.class);
        return qry.getResultList();
    }

    public List<EsGrade3> getGradeAnoSem(int ano, int semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade3> qry = em.createQuery("select s from EsGrade3 s WHERE s.anoLetivo = " + ano + " AND s.semestre = " + semestre, EsGrade3.class);
        return qry.getResultList();
    }

    public EsGrade3 getCodGradeExac(int ano, int semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade3> qry = em.createQuery("select s from EsGrade3 s WHERE s.anoLetivo = " + ano + " AND s.semestre = " + semestre, EsGrade3.class);
        return qry.getSingleResult();
    }

    public void create(EsGrade3 esGrade3) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esGrade3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsGrade3 esGrade3) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esGrade3 = em.merge(esGrade3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esGrade3.getCodGrade();
                if (findEsGrade3(id) == null) {
                    throw new NonexistentEntityException("The esGrade3 with id " + id + " no longer exists.");
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
            EsGrade3 esGrade3;
            try {
                esGrade3 = em.getReference(EsGrade3.class, id);
                esGrade3.getCodGrade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esGrade3 with id " + id + " no longer exists.", enfe);
            }
            em.remove(esGrade3);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsGrade3> findEsGrade3Entities() {
        return findEsGrade3Entities(true, -1, -1);
    }

    public List<EsGrade3> findEsGrade3Entities(int maxResults, int firstResult) {
        return findEsGrade3Entities(false, maxResults, firstResult);
    }

    private List<EsGrade3> findEsGrade3Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsGrade3.class));
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

    public EsGrade3 findEsGrade3(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsGrade3.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsGrade3Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsGrade3> rt = cq.from(EsGrade3.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
