package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsGrade;
import entities.GradeDispensa;
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

public class EsGradeJpaController implements Serializable {

    public EsGradeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EsGradeJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EsGrade getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsGrade s WHERE s. = '" + pesq + "'";
        TypedQuery<EsGrade> qry = em.createQuery(ssql, EsGrade.class);
        return qry.getSingleResult();
    }

    public List<EsGrade> getEsGradePesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade> qry = em.createQuery("select s from EsGrade s WHERE s.anoLetivo LIKE '%" + pesq + "%'", EsGrade.class);
        return qry.getResultList();
    }

    public List<String> getGradeMS() {
        EntityManager em = null;
        em = getEntityManager();
        String ponto = "\"" + "." + "\"";
        String sql = "select concat(s.anoLetivo, " + ponto + ", s.semestre) from EsGrade s ORDER BY s.anoLetivo DESC, s.semestre DESC";
        TypedQuery<String> qry = em.createQuery(sql, String.class);
        return qry.getResultList();
    }

    public List<GradeDispensa> getCodsGrades() {
        EntityManager em = null;
        em = getEntityManager();
        String ponto = "\"" + "." + "\"";
        String sql = "select s.codGrade, concat(s.anoLetivo, " + ponto + ", s.semestre) from EsGrade s ORDER BY s.anoLetivo DESC, s.semestre DESC";
        TypedQuery<GradeDispensa> qry = em.createQuery(sql, GradeDispensa.class);
        return qry.getResultList();
    }
    
    public Integer getCodGradePorConcat(String gradeConca) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.codGrade from EsGrade s WHERE s.anoLetivo = " + gradeConca.substring(0, 4) + " AND s.semestre = " + gradeConca.substring(5,6);
        System.out.println(ssql);
        TypedQuery<Integer> qry = em.createQuery(ssql, Integer.class);
        return qry.getSingleResult();
    }

    public List<EsGrade> getGradesOrdenadas(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade> qry = em.createQuery("select s from EsGrade s Order By s.anoLetivo DESC, s.semestre DESC", EsGrade.class);
        return qry.getResultList();
    }

    public List<EsGrade> getEsGradePesqExac(String ano, String semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade> qry = em.createQuery("select s from EsGrade s WHERE s.anoLetivo = '" + ano + "' AND s.semestre = '" + semestre + "'", EsGrade.class);
        return qry.getResultList();
    }

    public List<EsGrade> getGradeAnoSem(int ano, int semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade> qry = em.createQuery("select s from EsGrade s WHERE s.anoLetivo = " + ano + " AND s.semestre = " + semestre, EsGrade.class);
        return qry.getResultList();
    }

    public EsGrade getCodGradeExac(int ano, int semestre) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<EsGrade> qry = em.createQuery("select s from EsGrade s WHERE s.anoLetivo = " + ano + " AND s.semestre = " + semestre, EsGrade.class);
        return qry.getSingleResult();
    }

    public void create(EsGrade esGrade) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esGrade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsGrade esGrade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esGrade = em.merge(esGrade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esGrade.getCodGrade();
                if (findEsGrade(id) == null) {
                    throw new NonexistentEntityException("The esGrade with id " + id + " no longer exists.");
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
            EsGrade esGrade;
            try {
                esGrade = em.getReference(EsGrade.class, id);
                esGrade.getCodGrade();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esGrade with id " + id + " no longer exists.", enfe);
            }
            em.remove(esGrade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsGrade> findEsGradeEntities() {
        return findEsGradeEntities(true, -1, -1);
    }

    public List<EsGrade> findEsGradeEntities(int maxResults, int firstResult) {
        return findEsGradeEntities(false, maxResults, firstResult);
    }

    private List<EsGrade> findEsGradeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsGrade.class));
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

    public EsGrade findEsGrade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsGrade.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsGradeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsGrade> rt = cq.from(EsGrade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
