package jpa_controler;

import static main.Login.gbDeOnde;
import entities.CepLogradouros;
import static funcoes.MyFunc.getEntityManager;
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

public class CepLogradourosJpaController implements Serializable {

    public CepLogradourosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CepLogradourosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<CepLogradouros> getCEPs(String cepAluno) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from CepLogradouros s WHERE s.cep LIKE '" + cepAluno + "%'";
        System.out.println("\n"+ssql+"\n");
        TypedQuery<CepLogradouros> qry = em.createQuery(ssql, CepLogradouros.class);
        return qry.getResultList();
    }
    
    public CepLogradouros getDadosCEP(String cepAluno) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from CepLogradouros s WHERE s.cep = '" + cepAluno + "'";
        System.out.println("\n"+ssql+"\n");
        TypedQuery<CepLogradouros> qry = em.createQuery(ssql, CepLogradouros.class);
        return qry.getSingleResult();
    }
    
    public Long qtdCEP(String cepAluno) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select COUNT(s) from CepLogradouros s WHERE s.cep = '" + cepAluno + "'";
        System.out.println("\n"+ssql+"\n");
        TypedQuery<Long> qry = em.createQuery(ssql, Long.class);
        Long achados = qry.getSingleResult();
        return achados;
    }
    
    public void create(CepLogradouros cepLogradouros) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cepLogradouros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCepLogradouros(cepLogradouros.getCodigo()) != null) {
                throw new PreexistingEntityException("CepLogradouros " + cepLogradouros + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CepLogradouros cepLogradouros) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cepLogradouros = em.merge(cepLogradouros);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cepLogradouros.getCodigo();
                if (findCepLogradouros(id) == null) {
                    throw new NonexistentEntityException("The cepLogradouros with id " + id + " no longer exists.");
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
            CepLogradouros cepLogradouros;
            try {
                cepLogradouros = em.getReference(CepLogradouros.class, id);
                cepLogradouros.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cepLogradouros with id " + id + " no longer exists.", enfe);
            }
            em.remove(cepLogradouros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CepLogradouros> findCepLogradourosEntities() {
        return findCepLogradourosEntities(true, -1, -1);
    }

    public List<CepLogradouros> findCepLogradourosEntities(int maxResults, int firstResult) {
        return findCepLogradourosEntities(false, maxResults, firstResult);
    }

    private List<CepLogradouros> findCepLogradourosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CepLogradouros.class));
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

    public CepLogradouros findCepLogradouros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CepLogradouros.class, id);
        } finally {
            em.close();
        }
    }

    public int getCepLogradourosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CepLogradouros> rt = cq.from(CepLogradouros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
