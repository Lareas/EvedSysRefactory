package jpa_controler;

import static main.Login.gbDeOnde;
import entities.EsGradedisprg;
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


public class EsGradedisprgJpaController implements Serializable {

    public EsGradedisprgJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EsGradedisprgJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public Integer getCodgradedisprg(Integer cod_dis, String nomeDoPrograma) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.codgradedisprg from EsGradedisprg s WHERE s.nomeCadDisciplina.cadastroDisciplinaId  = " + cod_dis + " AND s.nomePrograma.curso = '" + nomeDoPrograma + "'";
        System.out.println(ssql);
        TypedQuery<Integer> qry = em.createQuery(ssql, Integer.class);
        return qry.getSingleResult();
    }
    
    public EsGradedisprg getCodObjeto(Integer CadastroDisciplinaId, String nomeDoPrograma) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsGradedisprg s WHERE s.nomeCadDisciplina.cadastroDisciplinaId  = " + CadastroDisciplinaId + " AND s.nomePrograma.curso = '" + nomeDoPrograma + "'";
        System.out.println(ssql);
        TypedQuery<EsGradedisprg> qry = em.createQuery(ssql, EsGradedisprg.class);
        return qry.getSingleResult();
    }
    
    public List<EsGradedisprg> getListExac(Integer cod_dis, String nomeDoPrograma) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsGradedisprg s WHERE s.nomeCadDisciplina.cadastroDisciplinaId = " + cod_dis + " AND s.nomePrograma.curso = '" + nomeDoPrograma + "'";
        System.out.println(ssql);
        TypedQuery<EsGradedisprg> qry = em.createQuery(ssql, EsGradedisprg.class);
        return qry.getResultList();
    }
    
    public List<EsGradedisprg> getListProgs(Integer cadastroDisciplinaId) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from EsGradedisprg s WHERE s.nomeCadDisciplina.cadastroDisciplinaId  = " + cadastroDisciplinaId;
        System.out.println(ssql);
        TypedQuery<EsGradedisprg> qry = em.createQuery(ssql, EsGradedisprg.class);
        return qry.getResultList();
    }
    
    public void create(EsGradedisprg esGradedisprg) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(esGradedisprg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EsGradedisprg esGradedisprg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            esGradedisprg = em.merge(esGradedisprg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = esGradedisprg.getCodgradedisprg();
                if (findEsGradedisprg(id) == null) {
                    throw new NonexistentEntityException("The esGradedisprg with id " + id + " no longer exists.");
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
            EsGradedisprg esGradedisprg;
            try {
                esGradedisprg = em.getReference(EsGradedisprg.class, id);
                esGradedisprg.getCodgradedisprg();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The esGradedisprg with id " + id + " no longer exists.", enfe);
            }
            em.remove(esGradedisprg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EsGradedisprg> findEsGradedisprgEntities() {
        return findEsGradedisprgEntities(true, -1, -1);
    }

    public List<EsGradedisprg> findEsGradedisprgEntities(int maxResults, int firstResult) {
        return findEsGradedisprgEntities(false, maxResults, firstResult);
    }

    private List<EsGradedisprg> findEsGradedisprgEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EsGradedisprg.class));
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

    public EsGradedisprg findEsGradedisprg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EsGradedisprg.class, id);
        } finally {
            em.close();
        }
    }

    public int getEsGradedisprgCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EsGradedisprg> rt = cq.from(EsGradedisprg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
