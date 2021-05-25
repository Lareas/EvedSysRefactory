package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Denominacao;
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

public class DenominacaoJpaController implements Serializable {

    public DenominacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DenominacaoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public Denominacao getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Denominacao> qry = em.createQuery("select s from Denominacao s WHERE s.denominacao = '" + pesq + "'", Denominacao.class);
        return qry.getSingleResult();
    }
    
    public void create(Denominacao denominacao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(denominacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDenominacao(denominacao.getDenominacaoId()) != null) {
                throw new PreexistingEntityException("Denominacao " + denominacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<String> getNomeDasDeniminacoes() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.denominacao from Denominacao s order by s.denominacao", String.class);
        return qry.getResultList();
    }

    public List<Denominacao> getDenominacaoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Denominacao> qry = em.createQuery("select s from Denominacao s WHERE s.denominacao LIKE '%" + pesq + "%'", Denominacao.class);
        return qry.getResultList();
    }
    
    public List<Denominacao> getDenominacaoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Denominacao> qry = em.createQuery("select s from Denominacao s WHERE s.denominacao = '" + pesq + "'", Denominacao.class);
        return qry.getResultList();
    }

     public Denominacao getDenoUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Denominacao> qry = em.createQuery("select s from Denominacao s WHERE s.denominacao = '" + pesq + "'", Denominacao.class);
        return qry.getSingleResult();
    }
     
    public void edit(Denominacao denominacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            denominacao = em.merge(denominacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = denominacao.getDenominacaoId();
                if (findDenominacao(id) == null) {
                    throw new NonexistentEntityException("The denominacao with id " + id + " no longer exists.");
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
            Denominacao denominacao;
            try {
                denominacao = em.getReference(Denominacao.class, id);
                denominacao.getDenominacaoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The denominacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(denominacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Denominacao> findDenominacaoEntities() {
        return findDenominacaoEntities(true, -1, -1);
    }

    public List<Denominacao> findDenominacaoEntities(int maxResults, int firstResult) {
        return findDenominacaoEntities(false, maxResults, firstResult);
    }

    private List<Denominacao> findDenominacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Denominacao.class));
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

    public Denominacao findDenominacao(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Denominacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDenominacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Denominacao> rt = cq.from(Denominacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
