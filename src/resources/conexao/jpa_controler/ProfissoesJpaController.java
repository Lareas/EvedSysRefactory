package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Profissoes;
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


public class ProfissoesJpaController implements Serializable {

    public ProfissoesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ProfissoesJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public Profissoes getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Profissoes> qry = em.createQuery("select s from Profissoes s WHERE s.nomeProfissao = '" + pesq + "'", Profissoes.class);
        return qry.getSingleResult();
    }

    public List<Profissoes> getProfissoesPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Profissoes> qry = em.createQuery("select s from Profissoes s WHERE s.nomeProfissao LIKE '%" + pesq + "%'", Profissoes.class);
        return qry.getResultList();
    }
    
    public List<Profissoes> getProfissoesPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Profissoes> qry = em.createQuery("select s from Profissoes s WHERE s.nomeProfissao = '" + pesq + "'", Profissoes.class);
        return qry.getResultList();
    }
    public List<String> getNomeDasProfissoes() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nomeProfissao from Profissoes s order by s.nomeProfissao", String.class);
        return qry.getResultList();
    }
    
    public Profissoes getProfUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Profissoes> qry = em.createQuery("select s from Profissoes s WHERE s.nomeProfissao = '" + pesq + "'", Profissoes.class);
        return qry.getSingleResult();
    }
    
    public void create(Profissoes profissoes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(profissoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profissoes profissoes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            profissoes = em.merge(profissoes);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = profissoes.getCodProfissao();
                if (findProfissoes(id) == null) {
                    throw new NonexistentEntityException("The profissoes with id " + id + " no longer exists.");
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
            Profissoes profissoes;
            try {
                profissoes = em.getReference(Profissoes.class, id);
                profissoes.getCodProfissao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profissoes with id " + id + " no longer exists.", enfe);
            }
            em.remove(profissoes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profissoes> findProfissoesEntities() {
        return findProfissoesEntities(true, -1, -1);
    }

    public List<Profissoes> findProfissoesEntities(int maxResults, int firstResult) {
        return findProfissoesEntities(false, maxResults, firstResult);
    }

    private List<Profissoes> findProfissoesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profissoes.class));
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

    public Profissoes findProfissoes(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profissoes.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfissoesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profissoes> rt = cq.from(Profissoes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
